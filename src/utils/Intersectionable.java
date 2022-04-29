package utils;

/**
 *
 * @author Hylia
 */
public abstract class Intersectionable {
    
    private final Color color;
    private final Color specularColor;
    private final double shininess;
    private final double reflection;
    private final double transmission;
    private final double refraction;
    
    public Intersectionable(Color color, Color specularColor, double shininess, double reflection, double transmission, double refraction) {
        this.color = color;
        this.specularColor = specularColor;
        this.shininess = shininess;
        this.reflection = reflection;
        this.transmission = transmission;
        this.refraction = refraction;
    }
    
    public abstract double getIntersection(Vec3 p, Vec3 v);    

    public abstract Vec3 getNormal(Vec3 v);

    public Color getColor() {
        return color;
    }

    public Color getSpecularColor() {
        return specularColor;
    }

    public double getShininess() {
        return shininess;
    }

    public double getReflection() {
        return reflection;
    }

    public double getTransmission() {
        return transmission;
    }

    public double getRefraction() {
        return refraction;
    }
    
    

}
