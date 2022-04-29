/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Hylia
 */
public class Color {
    
    static public final Color BLACK = new Color(0F, 0F, 0F);
    static public final Color GREY = new Color(50F, 50F, 50F);
    public static final Color DARK_GRAY = new Color(30F, 30F, 30F);
    static public final Color LIGHTGREY = new Color(80F, 80F, 80F);
    static public final Color WHITE = new Color(255F, 255F, 255F);
    static public final Color BLUE = new Color(255F, 0F, 0F);
    static public final Color GREEN = new Color(0F, 255F, 0F);
    static public final Color RED = new Color(0F, 0F, 255F);
    public static final Color YELLOW = new Color(255F, 255F, 0F);
    public static final Color CYAN = new Color(0F, 255F, 255F);
    
    
    public final float blue;
    public final float green;
    public final float red;

    public Color(float b, float g, float r) {
        this.blue = Math.min(b, 255F);
        this.green = Math.min(g, 255F);
        this.red = Math.min(r, 255F);
    }

    public Color add(Color c) {
        return new Color(
            Math.min(this.blue + c.blue, 255F),
            Math.min(this.green + c.green, 255F),
            Math.min(this.red + c.red, 255F)
        );
    }

    public Color multiply(Color c) {
        return new Color(
            (this.blue * c.blue) / 255F,
            (this.green * c.green) / 255F,
            (this.red * c.red) / 255F
        );
    }

    public Color scale(double s) {
        return new Color(
            Math.min((float)Math.floor(this.blue *  s), 255F),
            Math.min((float)Math.floor(this.green * s), 255F),
            Math.min((float)Math.floor(this.red *   s), 255F)
        );
    }
}
