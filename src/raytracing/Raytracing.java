package raytracing;

import tools.SceneBuilder;
import tools.Vec3d;
import tools.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import tools.Config;

/**
 *
 * @author P. Meseure based on a Java Adaptation of a C code by B. Debouchages (M1, 2018-2019)
 */
public class Raytracing {

    /**
     * 
     * @param fout : output file stream
     * @param n : int to write to disc in little endian
     */
    private static void writeShort(FileOutputStream fout, int n) throws IOException
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
    
    /**
     * @param args no command line arguments
     */
    public static void main(String[] args) {
        
        Config.setConfig(args);
        
        final int width = Config.WIDTH;
        final int height = Config.HEIGHT;
        
        final int minResolution = Math.min(width, height);
        byte[] buffer = new byte[3 * width * height];
        Vec3d p = new Vec3d();

        Runnable myRunnable =  () -> {
            for (int i = 1; i <=3; ++ i) {
                SceneBuilder sb = new SceneBuilder();
                //System.out.println("Building scene " + i);
                sb.buildScene(i);

                for (int row = 0; row < height; row++) { // for each row of the image
                    for (int col = 0; col < width; col++) { // for each column of the image

                        int index = 3 * ((row * width) + col); // compute index of color for pixel (x,y) in the buffer

                        double x = (col - width / 2.0D) / minResolution;
                        double y = (row - height / 2.0D) / minResolution;
                        double z = Config.DISTANCE;
                        Vec3d v = new Vec3d(x, y, z);

                        Color color = sb.scene.findColor(p, v, Config.DEPTH);

                        // Depending on the x position, select a color... 
                        buffer[index] = (byte) color.b;
                        buffer[index + 1] = (byte) color.g;
                        buffer[index + 2] = (byte) color.r;

                    }
                }

                try {
                    saveTGA("Scene_" + sb.scene.sceneNumber + "_" + width +"x" + height + ".tga", buffer, width, height);
                }
                catch(Exception e) {
                    System.err.println("TGA file not created :" + e);
                }
            }
        };
        
        Thread thread = new Thread(myRunnable);
        thread.start();
    }
}
