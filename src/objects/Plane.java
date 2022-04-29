package objects;

import utils.Intersectionable;
import utils.Color;
import utils.Vec3;



/**
 *
 * @author Hylia
 */
public class Plane extends Intersectionable {
    
    Vec3 normal;
    double dist;
    
    public Plane(
            Vec3 normal,           // plane's normal
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
    public double getIntersection(Vec3 p, Vec3 v) {
        double n_v = normal.dotProduct(v);

        if (n_v != 0D) {
            double lambdaI = (-(normal.dotProduct(p)) - dist) / n_v;

            if (lambdaI > 0.0001D)
                return lambdaI;
        }

        return -1D;
    }

    @Override
    public Vec3 getNormal(Vec3 v) {
        return normal;
    }
    
}
