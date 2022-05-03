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
public class Plane extends Intersection {
    
    Vec3d normal;
    double dist;
    
    public Plane(
            Vec3d normal,           // plane's normal
            double distance,        // distance of the plane from origin
            Color color,            // plane's color
            Color specularColor,    // plane's specular color
            double shininess,       // plane's shininess
            double reflection,      // plane's reflection coeffition
            double transmission,    // plane's transmission coeffition
            double refraction       // plane's refraction index
    ) {
        super(
                color,
                specularColor,
                shininess,
                reflection,
                transmission,
                refraction
        );
        this.normal = normal;
        this.dist = distance;
    }

    @Override
    public double getIntersection(Vec3d p, Vec3d v) {
        
        /**
         * Algo final :
         * 
         *  On vérifie que n.u != 0
         *  On calcule
         *  λI = (−n.P−d) / (n.u)
         *  On vérifie que λI>0
         *  Puis on en déduit l’intersection I=M(λI) à partir de λI et de l’équation du rayon.
         *  La normale de l’intersection est n (car tout point du plan a cette normale)
         */
        
        double n_v = normal.dotProduct(v);

        if (n_v != 0.0D) {
            double lambdaI = (-(normal.dotProduct(p)) - dist) / n_v;

            if (lambdaI > 0.0001D)
                return lambdaI;
        }

        return -1.0D;
    }

    @Override
    public Vec3d getNormal(Vec3d v) {
        return normal;
    }
    
}
