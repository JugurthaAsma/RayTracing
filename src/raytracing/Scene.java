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
        if (depth == 0)
            return Light.AMBIENT_LIGHT;

        Color color = Light.AMBIENT_LIGHT;
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
            return Light.AMBIENT_LIGHT;

        Vec3d I = P.add(v.mul(lambdaI)); // I = P + lambda * v
        Vec3d nI = objectI.getNormal(I);

        boolean inside = nI.dotProduct(v) > 0.0D;
        if (inside)
            nI = nI.mul(-1.0D);

        color = objectI.color.multiply(Light.AMBIENT_LIGHT);

        for (Light light : this.lights) {
            Vec3d IS = light.position.sub(I); // IS = S - I

            boolean visible = true;

            for (Intersection object : this.objects) {
                double lambdaObj = object.getIntersection(I, IS);

                if (0.0D < lambdaObj && lambdaObj < 1.0D)
                    visible = false;
            }

            if (visible) {
                nI.normalize(); // nI / ||nI||
                IS.normalize(); // IS / ||IS||
                v.normalize(); // v / ||v||

                double nIDotIS = Math.max(nI.dotProduct(IS), 0.0D); // niDotIS = max(nI . IS, 0)

                Vec3d r = IS.sub(nI.mul(2.0D * nIDotIS)); // r = IS - 2 * nIDotIS * nI
                double rDotV = Math.max(r.dotProduct(v), 0.0D); // rDotV = max(r . v, 0)

                Color diffuse = light.diffuse.multiply(objectI.color).multiply(nIDotIS); // light.diffuse
                                                                                                    // * object.color
                                                                                                    // * niDotIS
                Color specular = light.specular.multiply(objectI.specularColor).multiply(Math.pow(rDotV, objectI.shininess)); // light.specular *
                                                                            // object.specularColor * pow(rDotV,
                                                                            // object.shininess)

                color = color.add(diffuse).add(specular);
            }
        }

        if (objectI.reflection > 0.0D) {
            Vec3d r = v.sub(nI.mul(2.0D * nI.dotProduct(v))); // r = v - 2 * nIDotV * nI
            r.normalize(); // r / ||r||
            color = color.add(findColor(I, r, depth - 1).multiply(objectI.reflection));
        }

        if (objectI.transmission > 0.0D) {
            double eta = inside ? objectI.refraction : 1.0D / objectI.refraction;
            double c1 = -nI.dotProduct(v); // c1 = nI . v
            double c2 = Math.sqrt(1.0D - eta * eta * (1.0D - c1 * c1)); // c2 = sqrt(1 - eta^2 * (1 - c1^2))
            Vec3d t = v.mul(eta).add(nI.mul(eta * c1 - c2)); // t = eta * v + (eta * c1 - c2) * nI
            t.normalize(); // t / ||t||
            color = color.add(findColor(I, t, depth - 1).multiply(objectI.transmission));
        }

        //System.out.println(color);
        return color;
    }

    
}
