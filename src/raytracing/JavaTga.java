package raytracing;

import objects.Plane;
import tools.Vec3d;
import tools.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import objects.Light;
import objects.Sphere;

/**
 *
 * @author P. Meseure based on a Java Adaptation of a C code by B. Debouchages (M1, 2018-2019)
 */
public class JavaTga
{
    
    Scene scene;
    
    
    
    /**
     * 
     * @param fout : output file stream
     * @param s : short to write to disc in little endian
     */
    private static void writeShort(FileOutputStream fout,int n) throws IOException
    {
        fout.write(n&255);
        fout.write((n>>8)&255);
    }

    /**
     * 
     * @param filename name of final TGA file
     * @param buffer buffer that contains the image. 3 bytes per pixel ordered this way : Blue, Green, Red
     * @param width Width of the image
     * @param height Height of the image
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     * @throws IOException 
     */
    private static void saveTGA(String filename, byte buffer[], int width, int height) throws IOException, UnsupportedEncodingException {

        FileOutputStream fout = new FileOutputStream(new File(filename));

        fout.write(0); // Comment size, no comment
        fout.write(0); // Colormap type: No colormap
        fout.write(2); // Image type
        writeShort(fout,0); // Origin
        writeShort(fout,0); // Length
        fout.write(0); // Depth
        writeShort(fout,0); // X origin
        writeShort(fout,0); // Y origin
        writeShort(fout,width); // Width of the image
        writeShort(fout,height); // Height of the image
        fout.write(24); // Pixel size in bits (24bpp)
        fout.write(0); // Descriptor

        /* Write the buffer */
        fout.write(buffer);

        fout.close();
    }

    
    private static void initScene(Scene scene) {
        
        scene.objects.add(new Plane(new Vec3d(0.0D, 0.0D, -1.0D), 6.0D, Color.red, Color.lightgray, 20.0D, 0.1D));
        scene.objects.add(new Plane(new Vec3d(0.0D, 0.0D, 1.0D), 6.0D, Color.green, Color.lightgray, 20.0D, 0.1D));
        scene.objects.add(new Plane(new Vec3d(1.0D, 0.0D, 0.0D), 3.0D, Color.blue, Color.lightgray, 20.0D, 0.1D));
        //scene.objects.add(new Plane(new Vec3d(-1.0D, 0.0D, 0.0D), 3.0D, Color.YELLOW, Color.lightgray, 20.0D, 0.1D));
        //scene.objects.add(new Plane(new Vec3d(0.0D, 1.0D, 0.0D), 1.5D, Color.CYAN, Color.lightgray, 20.0D, 0.1D));
        //scene.objects.add(new Plane(new Vec3d(0.0D, -1.0D, 0.0D), 1.5D, Color.MAGENTA, Color.lightgray, 20.0D, 0.1D));

        scene.objects.add(
                new Sphere(new Vec3d(0.0D, 0.0D, -4.0D), 1D, Color.blue, Color.white, 10.0D, 0.0D, 0.75D, 1.0D));
        scene.objects.add(
                new Sphere(new Vec3d(2.0D, 1.0D, -4.0D), 0.5D, Color.red, Color.white, 10.0D, 0.1D, 0.75D, 1.1D));
        scene.objects.add(
                new Sphere(new Vec3d(-2.0D, -1.0D, -4.0D), 0.5D, Color.blue, Color.white, 10.0D, 0.35D, 0.15D, 1.0D));

        scene.lights.add(new Light(new Vec3d(1D, 1D, 0D), Color.white, Color.lightgray));

        
        
        
        
        
        
        
        
        
        
        
        /*
        // origin of ray
        Vec3d p = new Vec3d(0, 0, 0);
        
        float dist = 20;
        Vec3d leftPlaneNormal = new Vec3d(-1, 0, 0);
        Vec3d rightPlaneNormal = new Vec3d(1, 0, 0);
        Vec3d upperPlaneNormal = new Vec3d(0, -1, 0);
        Vec3d lowerPlaneNormal = new Vec3d(0, 1, 0);
        Vec3d frontPlaneNormal = new Vec3d(0, 0, 1);
        
        Plane leftPlane = new Plane(leftPlaneNormal, dist, Color.blue);
        Plane rightPlane = new Plane(rightPlaneNormal, dist, Color.red);
        Plane upperPlane = new Plane(upperPlaneNormal, dist, Color.green);
        Plane lowerPlane = new Plane(lowerPlaneNormal, dist, Color.lightgray);
        Plane frontPlane = new Plane(frontPlaneNormal, 0.04F, Color.white);
        
        Sphere sphere = new Sphere(new Vec3d(0, 0, 0), 0.1, Color.lightgray);
        
        scene.objects.add(leftPlane);
        scene.objects.add(rightPlane);
        scene.objects.add(upperPlane);
        scene.objects.add(lowerPlane);
        scene.objects.add(frontPlane);
        scene.objects.add(sphere);

        scene.lights.add(new Light(new Vec3d(1D, 1D, 0D), Color.white, Color.lightgray));
        */
    }
    
    
    /**
     * @param args no command line arguments
     */
    public static void main(String[] args) {
        int w=1024;
        int h=768;
        byte buffer[]=new byte[3*w*h];
        
        // origin of ray
        Vec3d p = new Vec3d(0, 0, 0);
        
        float dist = 20;
        Vec3d leftPlaneNormal = new Vec3d(1, 0, 0);
        Vec3d rightPlaneNormal = new Vec3d(-1, 0, 0);
        Vec3d upperPlaneNormal = new Vec3d(0, 1, 0);
        Vec3d lowerPlaneNormal = new Vec3d(0, -1, 0);
        Vec3d frontPlaneNormal = new Vec3d(0, 0, -1);
        
        Plane leftPlane = new Plane(leftPlaneNormal, dist, Color.blue);
        Plane rightPlane = new Plane(rightPlaneNormal, dist, Color.red);
        Plane upperPlane = new Plane(upperPlaneNormal, dist, Color.green);
        Plane lowerPlane = new Plane(lowerPlaneNormal, dist, Color.lightgray);
        Plane frontPlane = new Plane(frontPlaneNormal, -0.04F, Color.white);
        
        Sphere sphere = new Sphere(new Vec3d(0, 0, 0), 0.1, Color.lightgray);

        Intersection[] objects = new Intersection[] {
            leftPlane,
            rightPlane,
            upperPlane,
            lowerPlane,
            frontPlane,
            //sphere
        };
        
        
        
        Scene scene = new Scene();
        initScene(scene);
        
        
        
        for(int row = 0; row < h; row++){ // for each row of the image
            for(int col = 0; col < w; col++){ // for each column of the image
                
                int index = 3*((row*w)+col); // compute index of color for pixel (x,y) in the buffer
                
                // Ensure that the pixel is black
                buffer[index]=0; // blue : take care, blue is the first component !!!
                buffer[index+1]=0; // green
                buffer[index+2]=0; // red (red is the last component !!!)
                
                
                int nearestObjectIndex = 0;
                double minLambda = Double.MAX_VALUE;
                
                // each pixel is the direction of the ray
                float x = (col-w/2)*2;
                float y = (row-h/2)*2;
                float z = -1;
                Vec3d v = new Vec3d(x, y, z);
                Ray ray = new Ray(p, v);
                
                
                
                for (int objectIndex = 0; objectIndex < objects.length; ++objectIndex) {
                    double lambda = objects[objectIndex].getIntersection(ray.p, ray.v);
                    if (lambda > 0 && lambda < minLambda) {
                        minLambda = lambda;
                        nearestObjectIndex = objectIndex;
                    }
                }
                
                // Depending on the x position, select a color... 
                buffer[index]=(byte)objects[nearestObjectIndex].color.b;
                buffer[index+1]=(byte)objects[nearestObjectIndex].color.g;
                buffer[index+2]=(byte)objects[nearestObjectIndex].color.r;
                
                
                
                //Color color = scene.findColor(p, v, 1);
                /*
                // Depending on the x position, select a color... 
                buffer[index]=(byte)color.b;
                buffer[index+1]=(byte)color.g;
                buffer[index+2]=(byte)color.r;*/
            }
        }
        try {
            saveTGA("imagetest.tga",buffer,w,h);
        }
        catch(Exception e)
        {
            System.err.println("TGA file not created :"+e);
        }
    }
}
