/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import tools.Color;
import tools.Vec3d;

/**
 *
 * @author JUGURTHA
 */
public class Light {
    
    public Vec3d position;
    public static Color AMBIENT_LIGHT = Color.lightgray;
    public Color diffuse, specular;
    
    public Light(Vec3d position, Color diffuse, Color specular) {
        this.position = position;
        this.diffuse = diffuse;
        this.specular = specular;
    }
    
}