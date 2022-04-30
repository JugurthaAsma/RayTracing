package utils;

import java.util.ArrayList;

import objects.Plane;
import objects.Sphere;

/**
 *
 * @author Hylia
 */
public class Scene {
    
    private final int width;
    private final int height;
    private final int maxDepth;
    private final byte[] buffer;
    private final Vec3 p;
    private final ArrayList<Intersectionable> objects;
    private final ArrayList<Light> lights;
    
    public Scene(int width, int height, int maxDepth) {
        
        this.width = width;
        this.height = height;
        this.maxDepth = maxDepth;
        this.buffer = new byte[3 * width * height];
        this.p = new Vec3(0, 0, 0);
        this.objects = new ArrayList<>();
        this.lights = new ArrayList<>();
        this.fillScene();
        this.mainLoop();

    }

    public Color findColor(Vec3 p, Vec3 v, int maxDepth) {
        Color color = Light.AMBIENT_LIGHT;
        
        if (maxDepth == 0)
            return color;

        double lambdaI = Double.MAX_VALUE;
        Intersectionable nearest = null;

        for (Intersectionable obj : this.objects) {
            double lambda = obj.getIntersection(p, v);

            if (lambda > 0D && lambda < lambdaI) {
                lambdaI = lambda;
                nearest = obj;
            }
        }

        if (nearest == null)
            return color;

        // I = P + lambdaI . v
        Vec3 I = p.add(v.scale(lambdaI)); 
        Vec3 nI = nearest.getNormal(I);

        boolean inside = nI.dotProduct(v) > 0D;
        if (inside) nI.setScale(-1D);
        
        color = nearest.getColor().multiply(color);

        for (Light light : this.lights) {

            Vec3 IS = light.getPosition().sub(I);
            boolean visible = true;

            for (Intersectionable obj : this.objects) {
                double lambda = obj.getIntersection(I, IS);
                if (lambda > 0D && lambda < 1D) visible = false;
            }

            if (visible) {

                IS.setNormalize();
                Vec3 normalizedV = v.normalize();
                double weight = Math.max(nI.dotProduct(IS), 0D);
                Vec3 r = IS.sub(nI.scale(weight * 2D));
                double rDotV = Math.max(r.dotProduct(normalizedV), 0D);
                Color diffuse = light.getDiffuse().multiply(nearest.getColor()).scale(weight);
                Color specular = light.getSpecular().multiply(nearest.getSpecularColor()).scale(Math.pow(rDotV, nearest.getShininess()));

                color = color.add(diffuse).add(specular);
            }
        }

        if (nearest.getReflection() > 0D) {
            Vec3 reflectionRay = v.sub(nI.scale(2D * nI.dotProduct(v))).normalize();
            color = color.add(findColor(I, reflectionRay, maxDepth - 1).scale(nearest.getReflection()));
        }

        if (nearest.getTransmission() > 0D) {
            double t = inside ? nearest.getRefraction() : 1D / nearest.getRefraction();
            double c1 = -nI.dotProduct(v);
            double c2 = Math.sqrt(1D - t * t * (1D - c1 * c1));
            Vec3 transmissionRay = v.scale(t).add(nI.scale(t * c1 - c2)).normalize();
            color = color.add(findColor(I, transmissionRay, maxDepth - 1).scale(nearest.getTransmission()));
        }

        return color;
    }
    
    private void fillScene() {
        
        // planes
        //this.objects.add(new Plane(new Vec3(0D, 1D, 0D),200D,Color.GREY,Color.LIGHTGREY,100D,0D,0D,0D));
        this.objects.add(new Plane(new Vec3(1D, 0D, 0D),200D,Color.BLUE,Color.LIGHTGREY,100D,0D,0D,0D));
        this.objects.add(new Plane(new Vec3(-1D, 0D, 0D),200D,Color.GREEN,Color.LIGHTGREY,100D,0D,0D,1000D));
        this.objects.add(new Plane(new Vec3(0D, 1D, 0D),50D,Color.LIGHTGREY,Color.LIGHTGREY,100D,0D,0D,0D));

        // spheres
        this.objects.add(new Sphere(new Vec3(-100D, 0D, -200D),30D,Color.LIGHTGREY,Color.WHITE,1000D,0.6D,0D,1D));
        this.objects.add(new Sphere(new Vec3(0D, 0D, -400D),30D,Color.RED,Color.WHITE,1000D,0.6D,0D,1D));
        this.objects.add(new Sphere(new Vec3(100D, 0D, -200D),30D,Color.BLUE,Color.WHITE,1000D,0.6D,0D,1D));
        this.objects.add(new Sphere(new Vec3(0D, -30D, -200D),10D,Color.GREEN,Color.WHITE,10000D,0D,0D,0D));
        
        // lights
        this.lights.add(new Light(new Vec3(200D, 500D, -200D),Color.WHITE,Color.LIGHTGREY));
        this.lights.add(new Light(new Vec3(-200D, 500D, 200D),Color.WHITE,Color.LIGHTGREY));
        
    }
    
    private void mainLoop() {
        for (int row = 0; row < this.height; row++) { // for each row of the image
            for (int col = 0; col < this.width; col++) { // for each column of the image

                int index = 3 * ((row * this.width) + col); // compute index of color for pixel (x,y) in the buffer

                double x = (col - this.width / 2D) / this.height;
                double y = (row - this.height / 2D) / this.height;
                Vec3 v = new Vec3(x, y, -1D);

                Color color = this.findColor(this.p, v, this.maxDepth);

                // Depending on the x position, select a color... 
                buffer[index] = (byte) color.getBlue();
                buffer[index + 1] = (byte) color.getGreen();
                buffer[index + 2] = (byte) color.getRed();
            }
        }
    }
    
    public void saveScene() {
        try {
            JavaTga.saveTGA("imagetest_" + this.width + "x" + this.height + "_" + this.maxDepth + ".tga", this.buffer, this.width, this.height);
        }
        catch(Exception e) {
            System.err.println("TGA file not created :" + e);
        }
    }
}
