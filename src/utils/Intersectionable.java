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
public abstract class Intersectionable {
    
    public final Color color;
    public final Color specularColor;
    public final double shininess;
    public final double reflection;
    public final double transmission;
    public final double refraction;
    
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

}
