/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import utils.Color;
import utils.Vec3;

/**
 *
 * @author Hylia
 */
public class Light {
    
    public final Vec3 position;
    public final Color diffuse, specular;
    
    public Light(
            Vec3 position,
            Color diffuse,
            Color specular
    ) {
        this.position = position;
        this.diffuse = diffuse;
        this.specular = specular;
    }
    
}
