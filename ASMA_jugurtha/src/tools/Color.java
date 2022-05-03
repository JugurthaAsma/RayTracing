/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author JUGURTHA
 */
public class Color {
    
    // BGR
    static public final Color BLACK = new Color(0.0F, 0.0F, 0.0F);
    static public final Color GREY = new Color(50.0F, 50.0F, 50.0F);
    public static final Color DARKGREY = new Color(30F, 30F, 30F);
    static public final Color LIGHTGREY = new Color(80.0F, 80.0F, 80.0F);
    static public final Color WHITE = new Color(255.0F, 255.0F, 255.0F);
    static public final Color BLUE = new Color(255.0F, 0.0F, 0.0F);
    static public final Color GREEN = new Color(0.0F, 255.0F, 0.0F);
    static public final Color RED = new Color(0.0F, 0.0F, 255.0F);
    public static final Color CYAN = new Color(255.0F, 255.0F, 0.0F);
    public static final Color YELLOW = new Color(0.0F, 255.0F, 255.0F);
    
    
    public final float b;
    public final float g;
    public final float r;

    public Color(float b, float g, float r) {
        this.b = Math.max(Math.min(b, 255.0F), 0.0F);
        this.g = Math.max(Math.min(g, 255.0F), 0.0F);
        this.r = Math.max(Math.min(r, 255.0F), 0.0F);
    }

    public Color add(Color color) {
        return new Color(
            Math.min(this.b + color.b, 255.0F),
            Math.min(this.g + color.g, 255.0F),
            Math.min(this.r + color.r, 255.0F)
        );
    }

    public Color multiply(Color color) {
        return new Color(
            (this.b * color.b) / 255.0F,
            (this.g * color.g) / 255.0F,
            (this.r * color.r) / 255.0F
        );
    }

    public Color scale(double scalar) {
        return new Color(
            Math.min((float)Math.floor(this.b *  scalar), 255F),
            Math.min((float)Math.floor(this.g * scalar), 255F),
            Math.min((float)Math.floor(this.r *   scalar), 255F)
        );
    }

    @Override
    public String toString() {
        return "Color{" + "b=" + b + ", g=" + g + ", r=" + r + '}';
    }
    
    
    
}
