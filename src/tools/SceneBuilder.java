/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import objects.Light;
import objects.Plane;
import objects.Sphere;
import raytracing.Scene;

/**
 *
 * @author JUGURTHA
 */
public class SceneBuilder {
    
    public static Scene buildScene1() {
        Scene scene = new Scene();
        /*
        scene.objects.add(new Plane(new Vec3d(0.0D, 0.0D, -1.0D), 6.0D, Color.RED, Color.LIGHTGREY, 20.0D, 0.1D));
        scene.objects.add(new Plane(new Vec3d(0.0D, 0.0D, 1.0D), 6.0D, Color.GREEN, Color.LIGHTGREY, 20.0D, 0.1D));
        scene.objects.add(new Plane(new Vec3d(1.0D, 0.0D, 0.0D), 3.0D, Color.BLUE, Color.LIGHTGREY, 20.0D, 0.1D));
        //scene.objects.add(new Plane(new Vec3d(-1.0D, 0.0D, 0.0D), 3.0D, Color.YELLOW, Color.LIGHTGREY, 20.0D, 0.1D));
        //scene.objects.add(new Plane(new Vec3d(0.0D, 1.0D, 0.0D), 1.5D, Color.CYAN, Color.LIGHTGREY, 20.0D, 0.1D));
        //scene.objects.add(new Plane(new Vec3d(0.0D, -1.0D, 0.0D), 1.5D, Color.MAGENTA, Color.LIGHTGREY, 20.0D, 0.1D));
*/
        scene.objects.add(
                new Sphere(new Vec3d(0.0D, 0.0D, -4.0D), 1D, Color.BLUE, Color.WHITE, 10.0D, 0.0D, 0.75D, 1.0D));
        scene.objects.add(
                new Sphere(new Vec3d(2.0D, 1.0D, -4.0D), 0.5D, Color.RED, Color.WHITE, 10.0D, 0.1D, 0.75D, 1.1D));
        scene.objects.add(
                new Sphere(new Vec3d(-2.0D, -1.0D, -4.0D), 0.5D, Color.BLUE, Color.WHITE, 10.0D, 0.35D, 0.15D, 1.0D));

        scene.lights.add(new Light(new Vec3d(1D, 1D, 0D), Color.WHITE, Color.LIGHTGREY));
        
        return scene;
    }
    
    public static Scene buildScene2() {
        Scene scene = new Scene();
        
        
        return scene;
        
    }
    
    public static Scene buildScene3() {
        Scene scene = new Scene();
        
        
        return scene;
        
    }
    
    
}
