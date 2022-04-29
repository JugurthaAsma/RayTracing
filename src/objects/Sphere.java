package objects;

import utils.Intersectionable;
import utils.Color;
import utils.Vec3;

/**
 *
 * @author Hylia
 */
public class Sphere extends Intersectionable {
    
    public final Vec3 center;
    public final double radius;

    public Sphere(
            Vec3 center,           // Sphere's center
            double radius,          // Sphere's radius
            Color color,            // Sphere's color
            Color specularColor,    // Sphere's specular color
            double shininess,       // Sphere's shininess
            double reflection,      // Sphere's reflection
            double transmission,    // Sphere's transmission
            double refraction       // Sphere's refraction
    ) {
        super(color,specularColor,shininess,reflection,transmission,refraction);
        this.center = center;
        this.radius = radius;
    }
    
    @Override
    public double getIntersection(Vec3 p, Vec3 v) {
        Vec3 CP = p.sub(center);
        double a = v.dotProduct(v);
        double b = v.scale(2D).dotProduct(CP);
        double c = CP.dotProduct(CP) - radius * radius;
        double delta = b * b - 4D * a * c;

        if (delta == 0D) {
            double lambda = -b / (2D * a);

            if (lambda > 0.0001D)
                return lambda;
        }
        
        if (delta > 0D) {
            double lambda1 = (-b - Math.sqrt(delta)) / (2D * a);
            double lambda2 = (-b + Math.sqrt(delta)) / (2D * a);

            if (lambda1 < 0.0001D && lambda2 > 0.0001D) {
                return lambda2;
            } else if (lambda1 > 0.0001D && lambda1 < lambda2) {
                return lambda1;
            }
        }
        
        return -1D;
    }

    @Override
    public Vec3 getNormal(Vec3 v) {
        return v.sub(center).normalize();
    }
    
}
