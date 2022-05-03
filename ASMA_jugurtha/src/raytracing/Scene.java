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
    
    public static Color AMBIENT_LIGHT = Color.DARKGREY;
    public int sceneNumber = 0;
    public ArrayList<Intersection> objects = new ArrayList<>();
    public ArrayList<Light> lights = new ArrayList<>();
    
    public Color findColor(Ray ray, int depth) {

        /**
         *  pour tous les objets obj de la scène faire
         *      Calculer λobj l’intersection (si elle existe) du rayon (start,u) avec l’objet obj
         *  fpour
         * 
         *  λI=min{λobj}, correspondant à l’objet objI
         *  Caculer I correspondant à λI, et nI la normale en I pour objI
         *  // Calcul de l’éclairement
         *  Color col=objI.color*Lambient
         *  Pour chaque source S faire
         *      viss=true
         *      Pour chaque objet obj de la scene faire // Mieux : Boucle while...
         *          Calculer λobj l’intersection (si elle existe) du rayon (I, IS) avec l’objet obj
         *          Si 1>λobj>0, alors viss=false fsi // source pas visible
         *      fpour
         * 
         *      si viss alors
         *          col + = contributions diffuse et spéculaire de la source S
         *      fsi
         *  fpour
         *  // Calculer la direction réfléchie r avec u et nI
         *  Col+=objI.kr * getRayColor(I,r)
         *  // Calculer la direction transmise t avec u, nI et les indices de réfraction
         *  col+=objI.kt * getRayColor(I,t)
         *  return col;
         */


        Color color = AMBIENT_LIGHT;
        
        if (depth == 0)
            return color;

        double lambdaI = Double.MAX_VALUE;
        Intersection objectI = null;

        for (Intersection object : this.objects) {
            double lambdaObj = object.getIntersection(ray.p, ray.v);

            if (lambdaObj > 0.0D && lambdaObj < lambdaI) {
                lambdaI = lambdaObj;
                objectI = object;
            }
        }

        if (objectI == null) return AMBIENT_LIGHT;

        Vec3d I = ray.p.add(ray.v.scale(lambdaI)); 
        Vec3d nI = objectI.getNormal(I);

        boolean inside = nI.dotProduct(ray.v) > 0.0D;
        if (inside) nI.setScale(-1.0D);
        
        color = objectI.color.multiply(AMBIENT_LIGHT);

        for (Light light : this.lights) {

            Vec3d IS = light.position.sub(I);
            boolean viss = true;

            for (Intersection object : this.objects) {
                double lambdaObj = object.getIntersection(I, IS);
                if (lambdaObj > 0.0D && lambdaObj < 1.0D) {
                    viss = false;
                    break;
                }
            }

            if (viss) {

                IS.setNormalize();
                double weight = Math.max(0.0D, nI.dotProduct(IS));

                double r_v = Math.max(0.0D,
                    IS
                    .sub(nI.scale(weight * 2.0D))
                    .dotProduct(ray.v.normalize())
                );

                Color diffuse = light.diffuse
                    .multiply(objectI.color)
                    .scale(weight)
                ;

                Color specular = light.specular
                    .multiply(objectI.specularColor)
                    .scale(Math.pow(r_v, objectI.shininess))
                ;

                color = color
                    .add(diffuse)
                    .add(specular)
                ;
            }
        }

        if (objectI.reflection > 0.0D) {
            Vec3d reflectDir = ray.v.sub(nI.scale(2.0D * nI.dotProduct(ray.v))).normalize();
            color = color.add(findColor(new Ray(I, reflectDir), depth - 1).scale(objectI.reflection));
        }

        if (objectI.transmission > 0.0D) {
            double eta = inside ? objectI.refraction : 1.0D / objectI.refraction;
            double nI_v = -nI.dotProduct(ray.v);
            double k = 1.0D - eta * eta * (1.0D - nI_v * nI_v);

            Vec3d transmiseDir = ray.v
                .scale(eta)
                .add(nI.scale(eta * nI_v - Math.sqrt(k)))
                .normalize()
            ;
            
            color = color
                .add(findColor(new Ray(I, transmiseDir), depth - 1)
                .scale(objectI.transmission))
            ;
        }

        return color;
    }
    
}
