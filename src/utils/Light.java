package utils;

/**
 *
 * @author Hylia
 */
public class Light {
    
    public static final Color AMBIENT_LIGHT = Color.BLACK;
    private final Vec3 position;
    private final Color diffuse, specular;
    
    public Light(Vec3 position,Color diffuse,Color specular) {
        this.position = position;
        this.diffuse = diffuse;
        this.specular = specular;
    }

    public Vec3 getPosition() {
        return position;
    }

    public Color getDiffuse() {
        return diffuse;
    }

    public Color getSpecular() {
        return specular;
    }
    
    
    
}
