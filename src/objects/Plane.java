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
    
    /**
     * Constructor.
     * 
     * @param normal            the normal of the plane
     * @param distance          the distance from the origin of the plane to the
     *                          plane
     * @param color             the color of the plane
     * @param specularColor     the specular color of the plane
     * @param shininess         the shininess of the plane
     * @param reflectionCoeff   the reflection coefficient of the plane
     * @param transmissionCoeff the transmission coefficient of the plane
     * @param refractionIndex   the refraction index of the plane
     */
    public Plane(Vec3d normal, double distance, Color color, Color specularColor, double shininess, double reflectionCoeff, double transmissionCoeff, double refractionIndex) {
        super(color, specularColor, shininess, reflectionCoeff, transmissionCoeff, refractionIndex);
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
        /*
        double n_v = normal.dotProduct(v);

        if (n_v != 0.0D) {
            double lambdaI = (-(normal.dotProduct(p)) - dist) / n_v;

            if (lambdaI > 0.0001D)
                return lambdaI;
        }

        return -1.0D;
*/
        
        final double t = (normal.dotProduct(v) != 0.0D) ? (-(normal.dotProduct(p)) - dist) / (normal.dotProduct(v)) : -1.0D;
        return (t > 0.0001D) ? t : -1.0D;
    
    }

    @Override
    public Vec3d getNormal(Vec3d I) {
        return normal;
    }
    
}
