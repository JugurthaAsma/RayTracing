/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raytracing;

import tools.Vec3d;

/**
 *
 * @author JUGURTHA
 */
public class Ray {
    
    Vec3d p;
    Vec3d v;

    public Ray(Vec3d p, Vec3d v) {
        this.p = p;
        this.v = v;
    }
    
}
