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

        //PLANES

        // right
        scene.objects.add(
                new Plane(
                        new Vec3d(-1.0D, 0.0D, 0.0D),
                        800.0D,
                        Color.RED,
                        Color.LIGHTGREY,
                        100.0D,
                        0.0D,
                        0.0D,
                        0.0D
                )
        );
        
        // front
        scene.objects.add(
                new Plane(
                        new Vec3d(0D, 0D, 1D),
                        2000D,
                        Color.LIGHTGREY,
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
                        800D, 
                        Color.BLUE, 
                        Color.LIGHTGREY, 
                        100.0D, 
                        0.0D, 
                        0.0D, 
                        0.0D
                )
        ); 
        
        // bottom
        scene.objects.add(
                new Plane(
                        new Vec3d(0.0D, 1.0D, 0.0D),
                        800D,
                        Color.WHITE,
                        Color.LIGHTGREY,
                        100.0D,
                        0.0D,
                        0.0D,
                        0.0D
                )
        ); 
        
        // top
        scene.objects.add(
                new Plane(
                        new Vec3d(0.0D, -1.0D, 0.0D),
                        800D,
                        Color.WHITE,
                        Color.LIGHTGREY,
                        100.0D,
                        0.0D,
                        0.0D,
                        0.0D
                )
        ); 

        scene.objects.add(
                new Sphere(
                        new Vec3d(25.0D, 25.0D, -250.0D),
                        25.0D,
                        Color.CYAN,
                        Color.LIGHTGREY,
                        1000.0D,
                        0.0D,
                        0.0D,
                        1.0D
                )
        );
        
        scene.objects.add(
            new Sphere(
                    new Vec3d(0.0D, 1.0D, -4.0D),
                    0.5D,
                    Color.DARKGREY,
                    Color.WHITE,
                    10.0D,
                    0.1D,
                    0.75D,
                    1.1D
            )
        );
        
        scene.objects.add(
            new Sphere(
                    new Vec3d(-80.0D, 0.0D, -200.0D), 
                    20.0D, 
                    Color.YELLOW, 
                    Color.WHITE, 
                    10.0D, 
                    0.35D, 
                    0.15D, 
                    1.0D
            )
        );

        // LIGHTS

        scene.lights.add(
                new Light(
                        new Vec3d(-50.0D, -50.0D,-80.0D)
                )
        );
    }
    
    public void buildScene2() {
        
        scene.sceneNumber = 2;

        //PLANES
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

        //SPHERES
        scene.objects.add(
                new Sphere(
                        new Vec3d(-220.0D, 0.0D, -500.0D),
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
                    new Vec3d(0.0D, 0.0D, -500.0D),
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
                        new Vec3d(220.0D, 0.0D, -500.0D),
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
                        new Vec3d(100.0D, -70.0D, -250.0D),
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
                        new Vec3d(-100.0D, -70.0D, -250.0D),
                        20.0D,
                        Color.YELLOW,
                        Color.WHITE,
                        1000.0D,
                        0.6D,
                        0.0D,
                        1.0D
                )
        );

        //LIGHTS
        scene.lights.add(
                new Light(
                        new Vec3d(0.0D, 800.0D, 0.0D)
                )
        );

    }
    
    public void buildScene3() {
        scene.sceneNumber = 3;

        // PLANES
        scene.objects.add(
                new Plane(
                        new Vec3d(1.0D, 0.0D, 0.0D),
                        600.0D,
                        Color.BLUE,
                        Color.LIGHTGREY,
                        10.0D,
                        0.0D,
                        0.0D,
                        0.0D
                )
        );

        scene.objects.add(
                new Plane(
                        new Vec3d(-1.0D, 0.0D, 0.0D),
                        600.0D, 
                        Color.RED,
                        Color.LIGHTGREY,
                        10.0D,
                        0.0D,
                        0.0D,
                        0.0D
                )
        );

        scene.objects.add(
                new Plane(
                        new Vec3d(0.0D, 0.0D, 1.0D),
                        1200.0D, 
                        Color.BLACK,
                        Color.GREY,
                        1.0D,
                        0.0D,
                        1.0,
                        1.3D
                )
        );

        scene.objects.add(
                new Plane(
                        new Vec3d(0.0D, 0.0D, 1.0D),
                        2000.0D, 
                        Color.YELLOW,
                        Color.LIGHTGREY,
                        1.0D,
                        0.0D,
                        0.0D,
                        0.0D
                )
        );

        scene.objects.add(
                new Plane(
                        new Vec3d(0.0D, -1.0D, 0.0D),
                        500.0D,
                        Color.WHITE,
                        Color.LIGHTGREY,
                        10.0D,
                        0.0D,
                        0.0D,
                        0.0D
                )
        );

        scene.objects.add(
                new Plane(
                        new Vec3d(0.0D, 1.0D, 0.0D),
                        500.0D, 
                        Color.WHITE,
                        Color.LIGHTGREY,
                        1.0D,
                        0.0D,
                        0.0D,
                        0.0D
                )
        );


        // SPHERES
        scene.objects.add(
                new Sphere(

                        new Vec3d(-250.0D, 0.0D, -1500.0D),
                        150.0D,
                        Color.RED,
                        Color.WHITE,
                        10.0D,
                        0.0D,
                        0.0D,
                        0.0D
                )
        );

        scene.objects.add(
                new Sphere(

                        new Vec3d(250.0D, 0.0D, -1500.0D),
                        150.0D,
                        Color.RED,
                        Color.WHITE,
                        10.0D,
                        0.0D,
                        0.0D,
                        0.0D
                )
        );
        // LIGHTS
        scene.lights.add(
                new Light(
                        new Vec3d(0.0D, -400.0D, 500.0D)
                )
        );

        scene.lights.add(
                new Light(
                        new Vec3d(0.0D, 450.0D, -1300.0D)
                )
        );
    }
    
}
