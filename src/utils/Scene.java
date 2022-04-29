/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;

import objects.Plane;
import objects.Sphere;

/**
 *
 * @author Hylia
 */
public class Scene {
    
    public static final Color AMBIENT_LIGHT = Color.BLACK;
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

    public Color findColor(Vec3 p, Vec3 v, int depth) {
        Color color = AMBIENT_LIGHT;
        
        if (depth == 0)
            return color;

        double lambdaI = Double.MAX_VALUE;
        Intersectionable objectI = null;

        for (Intersectionable object : this.objects) {
            double lambdaObj = object.getIntersection(p, v);

            if (lambdaObj > 0D && lambdaObj < lambdaI) {
                lambdaI = lambdaObj;
                objectI = object;
            }
        }

        if (objectI == null) return AMBIENT_LIGHT;

        // I = P + lambda . v
        Vec3 I = p.add(v.scale(lambdaI)); 
        Vec3 nI = objectI.getNormal(I);

        boolean inside = nI.dotProduct(v) > 0D;
        if (inside) nI.setScale(-1D);
        
        color = objectI.color.multiply(AMBIENT_LIGHT);

        for (Light light : this.lights) {

            Vec3 IS = light.position.sub(I); // IS = S - I
            boolean visible = true;

            for (Intersectionable object : this.objects) {
                double lambdaObj = object.getIntersection(I, IS);
                if (lambdaObj > 0D && lambdaObj < 1D) visible = false;
            }

            if (visible) {

                IS.setNormalize(); // IS / ||IS||
                Vec3 normalizedV = v.normalize(); // v / ||v||

                double weight = Math.max(nI.dotProduct(IS), 0D); // weight = max(nI . IS, 0)

                Vec3 r = IS.sub(nI.scale(weight * 2D)); // r = IS - 2 * weight * nI
                double rDotV = Math.max(r.dotProduct(normalizedV), 0D); // rDotV = max(r . v, 0)

                // light.diffuse * object.color * niDotIS
                Color diffuse = light.diffuse
                        .multiply(objectI.color)
                        .scale(weight)
                ;

                // light.specular * object.specularColor * pow(rDotV, object.shininess)
                Color specular = light.specular
                        .multiply(objectI.specularColor)
                        .scale(Math.pow(rDotV, objectI.shininess))
                ;

                color = color.add(diffuse).add(specular);
            }
        }

        if (objectI.reflection > 0D) {
            Vec3 r = v.sub(nI.scale(2D * nI.dotProduct(v))); // r = v - 2 * nIDotV * nI
            r.normalize(); // r / ||r||
            color = color.add(findColor(I, r, depth - 1).scale(objectI.reflection));
        }

        if (objectI.transmission > 0D) {
            double eta = inside ? objectI.refraction : 1D / objectI.refraction;
            double c1 = -nI.dotProduct(v); // c1 = nI . v
            double c2 = Math.sqrt(1D - eta * eta * (1D - c1 * c1)); // c2 = sqrt(1 - eta^2 * (1 - c1^2))
            Vec3 t = v.scale(eta).add(nI.scale(eta * c1 - c2)); // t = eta * v + (eta * c1 - c2) * nI
            t.normalize(); // t / ||t||
            color = color.add(findColor(I, t, depth - 1).scale(objectI.transmission));
        }

        return color;
    }
    
    private void fillScene() {
        // floor
        this.objects.add(new Plane(new Vec3(0D, 1D, 0D),100D,
                        Color.GREY,
                        Color.LIGHTGREY,
                        100D,
                        0D,
                        0D,
                        0D
                )
        );
        
        // left
        this.objects.add(new Plane(
                        new Vec3(1D, 0D, 0D),
                        1000D,
                        Color.BLUE,
                        Color.LIGHTGREY,
                        100D,
                        0D,
                        0D,
                        0D
                )
        );
        
        // right
        this.objects.add(new Plane(
                        new Vec3(-1D, 0D, 0D),
                        1000D,
                        Color.RED,
                        Color.LIGHTGREY,
                        100D,
                        0D,
                        0D,
                        0D
                )
        );
/*        
        // ceilling
        this.objects.add(
                new Plane(
                        new Vec3d(0D, 1D, 0D),
                        100D,
                        Color.WHITE,
                        Color.LIGHTGREY,
                        100D,
                        0D,
                        0D,
                        0D
                )
        );
/*        
        // front
        this.objects.add(
                new Plane(
                        new Vec3d(0D, 0D, 1D),
                        5000D,
                        Color.WHITE,
                        Color.LIGHTGREY,
                        -10000D,
                        0D,
                        0D,
                        0D
                )
        );
*/
        this.objects.add(new Sphere(
                        new Vec3(-220D, 0D, -800D),
                        100D,
                        Color.BLUE,
                        Color.WHITE,
                        1000D,
                        0.6D,
                        0D,
                        1D
                )
        );

        this.objects.add(new Sphere(
                    new Vec3(0D, 0D, -800D),
                    100D,
                    Color.WHITE,
                    Color.WHITE,
                    1000D,
                    0.6D,
                    0D,
                    1D
            )
        );

        this.objects.add(new Sphere(
                        new Vec3(220D, 0D, -800D),
                        100D,
                        Color.RED,
                        Color.WHITE,
                        1000D,
                        0.6D,
                        0D,
                        1D
                )
        );
        
        this.objects.add(new Sphere(
                        new Vec3(100D, -70D, -600D),
                        20D,
                        Color.CYAN,
                        Color.WHITE,
                        1000D,
                        0.6D,
                        0D,
                        1D
                )
        );
        
        this.objects.add(new Sphere(
                        new Vec3(-100D, -70D, -600D),
                        20D,
                        Color.YELLOW,
                        Color.WHITE,
                        1000D,
                        0.6D,
                        0D,
                        1D
                )
        );
        
        this.lights.add(new Light(
                        new Vec3(0D, 800D, 0D),
                        Color.WHITE,
                        Color.LIGHTGREY
                )
        );
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
                buffer[index] = (byte) color.blue;
                buffer[index + 1] = (byte) color.green;
                buffer[index + 2] = (byte) color.red;
            }
        }
    }
    
    public void saveScene() {
        try {
            JavaTga.saveTGA("imagetest.tga", this.buffer, this.width, this.height);
        }
        catch(Exception e) {
            System.err.println("TGA file not created :" + e);
        }
    }
}
