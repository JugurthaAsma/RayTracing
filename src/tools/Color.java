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
    
    static public final Color black = new Color(0.F, 0.F, 0.F);
    static public final Color gray = new Color(50.F, 50.F, 50.F);
    static public final Color lightgray = new Color(80.F, 80.F, 80.F);
    static public final Color white = new Color(255.F, 255.F, 255.F);
    static public final Color blue = new Color(255.F, 0.F, 0.F);
    static public final Color green = new Color(0.F, 255.F, 0.F);
    static public final Color red = new Color(0.F, 0.F, 255.F);
    
    public final float r;
    public final float g;
    public final float b;

    public Color(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color add(Color color) {
        return new Color(Math.min(this.r + color.r, 255.F), Math.min(this.g + color.g, 255.F), Math.min(this.b + color.b, 255.F));
    }

    public Color multiply(Color color) {
        return new Color(this.r * color.r, this.g * color.g, this.b * color.b);
    }

    public Color multiply(double scalar) {
        return new Color(this.r * (float) scalar, this.g * (float) scalar, this.b * (float) scalar);
    }

    @Override
    public String toString() {
        return "Color{" + "r=" + r + ", g=" + g + ", b=" + b + '}';
    }
    
    
    
}
