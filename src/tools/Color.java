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
    
    static public final Color BLACK = new Color(0.F, 0.F, 0.F);
    static public final Color GREY = new Color(50.F, 50.F, 50.F);
    static public final Color LIGHTGREY = new Color(80.F, 80.F, 80.F);
    static public final Color WHITE = new Color(255.F, 255.F, 255.F);
    static public final Color BLUE = new Color(255.F, 0.F, 0.F);
    static public final Color GREEN = new Color(0.F, 255.F, 0.F);
    static public final Color RED = new Color(0.F, 0.F, 255.F);
    
    public final double b;
    public final double g;
    public final double r;

    public Color(double b, double g, double r) {
        this.b = b;
        this.g = g;
        this.r = r;
    }

    public Color add(Color color) {
        return new Color(
            Math.min(this.r + color.r, 255D),
            Math.min(this.g + color.g, 255D),
            Math.min(this.b + color.b, 255D)
        );
    }

    public Color multiply(Color color) {
        return new Color(
            (this.r * color.r) / 255D,
            (this.g * color.g) / 255D,
            (this.b * color.b) / 255D
        );
    }

    public Color scale(double scalar) {
        return new Color(
            Math.min(this.r * scalar, 255D),
            Math.min(this.g * scalar, 255D),
            Math.min(this.b * scalar, 255D)
        );
    }

    @Override
    public String toString() {
        return "Color{" + "b=" + b + ", g=" + g + ", r=" + r + '}';
    }
    
    
    
}
