A3D project (Ray Tracing) by Jugurtha ASMA, University of Poitiers 2021/2022

* Run the program
    Open a terminal in this folder and execute the command :
        - 'make' to compile the project.
        - 'make run' to run the program with default arguments (resolution: 1080x720, distance: 1.0D, depth: 3 recursion) (I let an example for this deposit in 'image' folder).
        - 'java -cp build/ raytracing.Raytracing <WIDTH> <HEIGHT> <DISTANCE> <DEPTH>' to run with your own configuration (or just edit the args in the makefile).
        - 'make clean' to clean the the project (removing the build folder and the generated TGA images).

    Each execution will generate three images (three different scenes) in the 'images' folder.

* Project structure :

    jugurtha@JUGURTHA:/mnt/e/M1/S2/A3D/projet$ tree
    .
    └── RayTracing
        ├── Makefile                    // Make file to build and run the project
        ├── README.txt                  // This README
        ├── images                      // Folder that will contain the generated images
        │   ├── Scene_1_1920x1080.jpg
        │   ├── Scene_2_1920x1080.jpg
        │   └── Scene_3_1920x1080.jpg
        └── src                         
            ├── objects                 // Package of all objects of a scene
            │   ├── Light.java       
            │   ├── Plane.java       
            │   └── Sphere.java      
            ├── raytracing              // Main package
            │   ├── Intersection.java
            │   ├── Ray.java
            │   ├── Raytracing.java  
            │   └── Scene.java       
            └── tools                   // Package of usefull classes for the project
                ├── Color.java       
                ├── Config.java      
                ├── SceneBuilder.java
                └── Vec3d.java 



