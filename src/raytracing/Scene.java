/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raytracing;

import java.util.ArrayList;
import objects.Light;
import tools.Color;
import tools.Vec3d;

/**
 *
 * @author JUGURTHA
 */
public class Scene {
    
    
    public static final Color AMBIENT_LIGHT = Color.BLACK;
    public int sceneNumber = 0;
    public ArrayList<Intersection> objects = new ArrayList<>();
    public ArrayList<Light> lights = new ArrayList<>();
    
    /**
    * Main method of the raytracer to find the color of a pixel.
    * 
    * @param P     the position of the ray
    * @param v     the direction of the ray
    * @param depth the depth of the recursion
    * @return the color of the pixel
    */
    public Color findColor(Vec3d P, Vec3d v, int depth) {
        Color color = AMBIENT_LIGHT;
        
        if (depth == 0)
            return color;

        double lambdaI = Double.MAX_VALUE;
        Intersection objectI = null;

        for (Intersection object : this.objects) {
            double lambdaObj = object.getIntersection(P, v);

            if (lambdaObj > 0.0D && lambdaObj < lambdaI) {
                lambdaI = lambdaObj;
                objectI = object;
            }
        }

        if (objectI == null)
            return AMBIENT_LIGHT;
        
        

        // I = P + lambda . v
        Vec3d I = P.add(v.scale(lambdaI)); 
        Vec3d nI = objectI.getNormal(I);

        
        boolean inside = nI.dotProduct(v) > 0.0D;
        if (inside)
            nI = nI.scale(-1.0D);
        
        
        color = objectI.color; //.multiply(AMBIENT_LIGHT);

        for (Light light : this.lights) {
            Vec3d IS = light.position.sub(I); // IS = S - I

            boolean visible = true;

            for (Intersection object : this.objects) {
                double lambdaObj = object.getIntersection(I, IS);

                if (lambdaObj > 0D && lambdaObj < 1D)
                    visible = false;
            }

            if (visible) {
                //nI.setNormalize(); // nI / ||nI||
                IS.setNormalize(); // IS / ||IS||
                Vec3d normalizedV = v.normalize(); // v / ||v||

                double weight = Math.max(nI.dotProduct(IS), 0D); // weight = max(nI . IS, 0)

                Vec3d r = IS.sub(nI.scale(weight * 2D)); // r = IS - 2 * weight * nI
                double rDotV = Math.max(r.dotProduct(normalizedV), 0.0D); // rDotV = max(r . v, 0)

                Color diffuse = light.diffuse.multiply(objectI.color).scale(weight);// light.diffuse * object.color * niDotIS
                Color specular = light.specular.multiply(objectI.specularColor).scale(Math.pow(rDotV, objectI.shininess)); // light.specular * object.specularColor * pow(rDotV, object.shininess)
                color = color.add(diffuse).add(specular);
            }
        }

        if (objectI.reflection > 0.0D) {
            Vec3d r = v.sub(nI.scale(2.0D * nI.dotProduct(v))); // r = v - 2 * nIDotV * nI
            r.normalize(); // r / ||r||
            color = color.add(findColor(I, r, depth - 1).scale(objectI.reflection));
        }

        if (objectI.transmission > 0.0D) {
            double eta = inside ? objectI.refraction : 1.0D / objectI.refraction;
            double c1 = -nI.dotProduct(v); // c1 = nI . v
            double c2 = Math.sqrt(1.0D - eta * eta * (1.0D - c1 * c1)); // c2 = sqrt(1 - eta^2 * (1 - c1^2))
            Vec3d t = v.scale(eta).add(nI.scale(eta * c1 - c2)); // t = eta * v + (eta * c1 - c2) * nI
            t.normalize(); // t / ||t||
            color = color.add(findColor(I, t, depth - 1).scale(objectI.transmission));
        }

        //System.out.println(color);
        return color;
    }

    
}
