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

    public Scene scene;

    public SceneBuilder(int sceneNumber) {

        this.scene = new Scene();

        switch (sceneNumber) {
            case 1 : buildScene1(); break;
            case 2 : buildScene2(); break;
            case 3 : buildScene3(); break;
            default: buildScene1(); break;
        }
    }

    private void buildScene1() {

        scene.objects.add(new Plane(new Vec3d(-1D, 0D, 0D), 400D, Color.RED, Color.LIGHTGREY, 100.0D, 0.0D, 0.0D, 0.0D)); // droite
        this.scene.objects.add(new Plane(new Vec3d(0D, 0D, 1D), 250D, Color.LIGHTGREY, Color.LIGHTGREY, 100.0D, 0.0D, 0.0D, 0.0D)); // front
        scene.objects.add(new Plane(new Vec3d(1.0D, 0.0D, 0.0D), 400D, Color.BLUE, Color.LIGHTGREY, 100.0D, 0.0D, 0.0D, 0.0D)); // gauche
        scene.objects.add(new Plane(new Vec3d(0.0D, 1.0D, 0.0D), 400D, Color.WHITE, Color.LIGHTGREY, 100.0D, 0.0D, 0.0D, 0.0D)); // bottom
        scene.objects.add(new Plane(new Vec3d(0.0D, -1.0D, 0.0D), 400D, Color.WHITE, Color.LIGHTGREY, 100.0D, 0.0D, 0.0D, 0.0D)); // top

        scene.objects.add(
                new Sphere(new Vec3d(0.0D, 0.0D, -125.0D), 75.0D, Color.RED, Color.LIGHTGREY, 1000.0D, 0.0D, 0.0D, 1.0D));
/*        scene.objects.add(
            new Sphere(new Vec3d(2.0D, 1.0D, -4.0D), 0.5D, Color.RED, Color.WHITE, 10.0D, 0.1D, 0.75D, 1.1D));
    scene.objects.add(
            new Sphere(new Vec3d(-2.0D, -1.0D, -4.0D), 0.5D, Color.BLUE, Color.WHITE, 10.0D, 0.35D, 0.15D, 1.0D));
*/
        scene.lights.add(new Light(new Vec3d(100.0D, 100.0D, 0D), Color.WHITE, Color.LIGHTGREY));
    }
    
    public void buildScene2() {

        scene.lights.add(new Light(new Vec3d(0.0D, 800.0D, 0D), Color.WHITE, Color.LIGHTGREY));

        scene.objects.add(new Plane(new Vec3d(0.0D, 1.0D, 0.0D), 100.0D, Color.GREY, Color.LIGHTGREY, 100.0D, 0.0D, 0.0D, 0.0D));

        scene.objects.add(
                new Sphere(
                        new Vec3d(-220.0D, 0.0D, -800.0D), 100.0D,
                        Color.BLUE, Color.WHITE,
                        1000.0D, 0.6D,
                        0.0D, 1.0D
                )
        );

        scene.objects.add(
            new Sphere(
                    new Vec3d(0.0D, 0.0D, -800.0D), 100.0D,
                    Color.GREEN, Color.WHITE,
                    1000.0D, 0.6D,
                    0.0D, 1.0D
            )
        );

        scene.objects.add(
                new Sphere(
                        new Vec3d(220.0D, 0.0D, -800.0D), 100.0D,
                        Color.RED, Color.WHITE,
                        1000.0D, 0.6D,
                        0.0D, 1.0D
                )
        );

    }
    
    public void buildScene3() {
    }
    
    
}
