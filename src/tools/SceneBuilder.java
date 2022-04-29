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

    public Scene scene = new Scene();
    
    public void buildScene(int number) {
        switch (number) {
            case 1 : buildScene1(); break;
            case 2 : buildScene2(); break;
            default: buildScene3(); break;
        }
    }

    public void buildScene1() {
        scene.sceneNumber = 1;

        scene.objects.add(
                new Plane(
                        new Vec3d(-1.0D, 0.0D, 0.0D),
                        400.0D,
                        Color.RED,
                        Color.LIGHTGREY,
                        100.0D,
                        0.0D,
                        0.0D,
                        0.0D
                )
        ); // right
        
        scene.objects.add(
                new Plane(
                        new Vec3d(0D, 0D, 1D),
                        250D,
                        Color.LIGHTGREY,
                        Color.LIGHTGREY,
                        100.0D,
                        0.0D,
                        0.0D,
                        0.0D
                )
        ); // front
        
        scene.objects.add(
                new Plane(
                        new Vec3d(1.0D, 0.0D, 0.0D), 
                        400D, 
                        Color.BLUE, 
                        Color.LIGHTGREY, 
                        100.0D, 
                        0.0D, 
                        0.0D, 
                        0.0D
                )
        ); // left
        
        scene.objects.add(
                new Plane(
                        new Vec3d(0.0D, 1.0D, 0.0D),
                        400D,
                        Color.WHITE,
                        Color.LIGHTGREY,
                        100.0D,
                        0.0D,
                        0.0D,
                        0.0D
                )
        ); // bottom
        
        scene.objects.add(
                new Plane(
                        new Vec3d(0.0D, -1.0D, 0.0D),
                        400D,
                        Color.WHITE,
                        Color.LIGHTGREY,
                        100.0D,
                        0.0D,
                        0.0D,
                        0.0D
                )
        ); // top

        scene.objects.add(
                new Sphere(
                        new Vec3d(0.0D, 0.0D, -125.0D),
                        75.0D,
                        Color.RED,
                        Color.LIGHTGREY,
                        1000.0D,
                        0.0D,
                        0.0D,
                        1.0D
                )
        );
        
        scene.objects.add(
            new Sphere(
                    new Vec3d(2.0D, 1.0D, -4.0D),
                    0.5D,
                    Color.RED,
                    Color.WHITE,
                    10.0D,
                    0.1D,
                    0.75D,
                    1.1D
            )
        );
        
        scene.objects.add(
            new Sphere(
                    new Vec3d(-2.0D, -1.0D, -4.0D), 
                    0.5D, 
                    Color.BLUE, 
                    Color.WHITE, 
                    10.0D, 
                    0.35D, 
                    0.15D, 
                    1.0D
            )
        );

        scene.lights.add(
                new Light(
                        new Vec3d(0.0D, 0.0D, 0.0D),
                        Color.WHITE,
                        Color.LIGHTGREY
                )
        );
        
        scene.lights.add(
                new Light(
                        new Vec3d(-100.0D, -100.0D, 0.0D),
                        Color.WHITE,
                        Color.LIGHTGREY
                )
        );
    }
    
    public void buildScene2() {
        
        scene.sceneNumber = 2;

        // floor
        scene.objects.add(
                new Plane(
                        new Vec3d(0.0D, 1.0D, 0.0D),
                        100.0D,
                        Color.GREY,
                        Color.LIGHTGREY,
                        100.0D,
                        0.0D,
                        0.0D,
                        0.0D
                )
        );
        
        // left
        scene.objects.add(
                new Plane(
                        new Vec3d(1.0D, 0.0D, 0.0D),
                        1000.0D,
                        Color.BLUE,
                        Color.LIGHTGREY,
                        100.0D,
                        0.0D,
                        0.0D,
                        0.0D
                )
        );
        
        // right
        scene.objects.add(
                new Plane(
                        new Vec3d(-1.0D, 0.0D, 0.0D),
                        1000.0D,
                        Color.RED,
                        Color.LIGHTGREY,
                        100.0D,
                        0.0D,
                        0.0D,
                        0.0D
                )
        );
/*        
        // ceilling
        scene.objects.add(
                new Plane(
                        new Vec3d(0.0D, 1.0D, 0.0D),
                        100.0D,
                        Color.WHITE,
                        Color.LIGHTGREY,
                        100.0D,
                        0.0D,
                        0.0D,
                        0.0D
                )
        );
/*        
        // front
        scene.objects.add(
                new Plane(
                        new Vec3d(0.0D, 0.0D, 1.0D),
                        5000.0D,
                        Color.WHITE,
                        Color.LIGHTGREY,
                        -10000.0D,
                        0.0D,
                        0.0D,
                        0.0D
                )
        );
*/
        scene.objects.add(
                new Sphere(
                        new Vec3d(-220.0D, 0.0D, -800.0D),
                        100.0D,
                        Color.BLUE,
                        Color.WHITE,
                        1000.0D,
                        0.6D,
                        0.0D,
                        1.0D
                )
        );

        scene.objects.add(
            new Sphere(
                    new Vec3d(0.0D, 0.0D, -800.0D),
                    100.0D,
                    Color.WHITE,
                    Color.WHITE,
                    1000.0D,
                    0.6D,
                    0.0D,
                    1.0D
            )
        );

        scene.objects.add(
                new Sphere(
                        new Vec3d(220.0D, 0.0D, -800.0D),
                        100.0D,
                        Color.RED,
                        Color.WHITE,
                        1000.0D,
                        0.6D,
                        0.0D,
                        1.0D
                )
        );
        
        scene.objects.add(
                new Sphere(
                        new Vec3d(100.0D, -70.0D, -600.0D),
                        20.0D,
                        Color.CYAN,
                        Color.WHITE,
                        1000.0D,
                        0.6D,
                        0.0D,
                        1.0D
                )
        );
        
        scene.objects.add(
                new Sphere(
                        new Vec3d(-100.0D, -70.0D, -600.0D),
                        20.0D,
                        Color.YELLOW,
                        Color.WHITE,
                        1000.0D,
                        0.6D,
                        0.0D,
                        1.0D
                )
        );
        
        scene.lights.add(
                new Light(
                        new Vec3d(0.0D, 800.0D, 0.0D),
                        Color.WHITE,
                        Color.LIGHTGREY
                )
        );

    }
    
    public void buildScene3() {
        scene.sceneNumber = 3;
    }
    
    
}
