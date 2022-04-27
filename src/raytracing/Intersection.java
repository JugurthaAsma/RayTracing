/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raytracing;

import tools.Color;
import tools.Vec3d;

/**
 *
 * @author JUGURTHA
 */
public abstract class Intersection {
    
    public Color color;
    public Color specularColor;
    public double shininess;
    public double reflection;
    public double transmission;
    public double refraction;
    
    public Intersection(Color color, Color specularColor, double shininess, double reflection, double transmission, double refraction) {
        this.color = color;
        this.specularColor = specularColor;
        this.shininess = shininess;
        this.reflection = reflection;
        this.transmission = transmission;
        this.refraction = refraction;
    }
    
    public abstract double getIntersection(Vec3d P, Vec3d v);    

    public abstract Vec3d getNormal(Vec3d I);

}
