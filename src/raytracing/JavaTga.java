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
        
        //scene.objects.add(new Plane(new Vec3d(-1D, 0D, 0D), 400D, Color.RED, Color.LIGHTGREY, 20D, 0.1D)); // droite
        scene.objects.add(new Plane(new Vec3d(0D, 0D, 1D), 250D, Color.GREEN, Color.LIGHTGREY, 100.0D, 0.0D, 0.0D, 0.0D)); // front
        //scene.objects.add(new Plane(new Vec3d(1.0D, 0.0D, 0.0D), 400D, Color.BLUE, Color.LIGHTGREY, 20.0D, 0.1D)); // gauche
        //scene.objects.add(new Plane(new Vec3d(0.0D, 1.0D, 0.0D), 400D, Color.WHITE, Color.LIGHTGREY, 20.0D, 0.1D)); // bottom
        //scene.objects.add(new Plane(new Vec3d(0.0D, -1.0D, 0.0D), 500D, Color.LIGHTGREY, Color.LIGHTGREY, 20.0D, 0.1D)); // top

        scene.objects.add(
                new Sphere(new Vec3d(0.0D, 0.0D, -125.0D), 75D, Color.BLUE, Color.WHITE, 10.0D, 0.0D, 0.75D, 1.0D));
/*        scene.objects.add(
                new Sphere(new Vec3d(2.0D, 1.0D, -4.0D), 0.5D, Color.RED, Color.WHITE, 10.0D, 0.1D, 0.75D, 1.1D));
        scene.objects.add(
                new Sphere(new Vec3d(-2.0D, -1.0D, -4.0D), 0.5D, Color.BLUE, Color.WHITE, 10.0D, 0.35D, 0.15D, 1.0D));
*/
        scene.lights.add(new Light(new Vec3d(1D, 1D, 0D), Color.WHITE, Color.LIGHTGREY));
  
    }
    
    
    /**
     * @param args no command line arguments
     */
    public static void main(String[] args) {
        int w=1024;
        int h=726;
        int minResolution = Math.min(w, h);
        byte buffer[]=new byte[3*w*h];
        
        // origin of ray
        Vec3d p = new Vec3d();
        
        Scene scene = new Scene();
        initScene(scene);
        
        for(int row = 0; row < h; row++){ // for each row of the image
            for(int col = 0; col < w; col++){ // for each column of the image
                
                int index = 3*((row*w)+col); // compute index of color for pixel (x,y) in the buffer
                
                double x = (col-w/2)/minResolution;
                double y = (row-h/2)/minResolution;
                double z = -0.25D;
                Vec3d v = new Vec3d(x, y, z);
               
                Color color = scene.findColor(new Vec3d(), v, 3);
                
                // Depending on the x position, select a color... 
                buffer[index]=(byte)color.b;
                buffer[index+1]=(byte)color.g;
                buffer[index+2]=(byte)color.r;
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
