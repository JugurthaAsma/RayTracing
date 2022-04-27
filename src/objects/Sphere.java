/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import raytracing.Intersection;
import tools.Color;
import tools.Vec3d;

/**
 *
 * @author JUGURTHA
 */
public class Sphere extends Intersection {
    
    public final Vec3d center;
    public final double radius;
    
    public Sphere(Vec3d center, double radius, Color color) {
        this(center, radius, color, Color.white, 10.0D, 0.02D, 0.5D, 1D);
        
    }

    
    public Sphere(Vec3d center, double radius, Color color, Color specularColor, double shininess, double reflection, double transmission, double refraction) {
        super(color, specularColor, shininess, reflection, transmission, refraction);
        this.center = center;
        this.radius = radius;
    }
    
    @Override
    public double getIntersection(Vec3d P, Vec3d v) {
        
        /**
         * L’équation peut être simplifiée en reconnaissant les produits scalaires, ce qui donne :
         * ||u||².λI+2u.CP λI+||CP||²−r²=0
         * équation du 2e degré de type :
         * aλI² + bλI + c = 0, avec a=||u||² , b=2⃗u .⃗CP et c=||CP||²−r²=0
         * 
         * Delta= b²-4ac, puis test du signe de delta
         * Si 0 > Delta , pas de solution !
         * Si Delta = 0 , 1 seule solution, rayon tangent à la sphère, vérifier que λI>0.
         * Si Delta>0, 2 solutions : λ1=(-b-sqrt(delta))/2ab-b-sqrt(delta))/2asqrt(delta))/2aa et λ2a=(-b-sqrt(delta))/2ab+sqrt(delta))/2aa
         *      Comme a>0 (c’est une norme au carré), alors on peut déduire que λ2>λ1 (pour le premier
         * on retire une quantité positive -b-sqrt(), pour la seconde on l’ajoute -b+sqrt()).
         *      Tout dépend alors de la position relative des solutions par rapport à 0
         *      Si 0>=λ2>λ1, L’intersection avec la sphère est sur la droite support, mais « derrière » le rayon, donc pas d’intersection avec le rayon
         *      Si λ2>0>λ1, P est dans la sphère et donc, le rayon ressort de la sphère à l’intersection λI=λ
         *      Si λ2>λ1>0, alors l’intersection à prendre c’est λI=λ1 car c’est la première intersection.
         * 
         * On calcule le point I correspondant à λI.
         * Il faut calculer la normale en I, Cette normale est CI / ||CI|| (rayon correspondant à I, qu’on normalise pour trouver un vecteur normal).
         */
        
        Vec3d CP = P.sub(center);
        double a = v.dotProduct(v);
        double b = v.mul(2.0D).dotProduct(CP);
        double c = CP.dotProduct(CP) - radius * radius;
        double delta = b * b - 4.0D * a * c;

        if (delta == 0.0D) {
            double lambda = -b / (2.0D * a);

            if (lambda > 0.0001D)
                return lambda;
        }
        
        if (delta > 0.0D) {
            double lambda1 = (-b - Math.sqrt(delta)) / (2.0D * a); // (-b - sqrt(delta)) / (2a)
            double lambda2 = (-b + Math.sqrt(delta)) / (2.0D * a); // (-b + sqrt(delta)) / (2a)

            if (lambda1 < 0.0001D && 0.001D < lambda2) {
                return lambda2;
            } else if (0.0001D < lambda1 && lambda1 < lambda2) {
                return lambda1;
            }
        }
        
        return -1D;
        
    }

    @Override
    public Vec3d getNormal(Vec3d I) {
        Vec3d CI = I.sub(center); // CI
        CI.normalize();

        return CI;}
    
}
