package raytracing;

import tools.SceneBuilder;
import tools.Vec3d;
import tools.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author P. Meseure based on a Java Adaptation of a C code by B. Debouchages (M1, 2018-2019)
 */
public class JavaTga {

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

        final int w = 1024;
        final int h = 726;
        final int minResolution = Math.min(w, h);

        byte[] buffer = new byte[3 * w * h];

        SceneBuilder sb = new SceneBuilder(2);
        
        for (int row = 0; row < h; row++) { // for each row of the image
            for (int col = 0; col < w; col++) { // for each column of the image
                
                int index = 3 * ((row * w) + col); // compute index of color for pixel (x,y) in the buffer
                
                double x = (col - w / 2.0D) / minResolution;
                double y = (row - h / 2.0D) / minResolution;
                double z = -1.25D;
                Vec3d v = new Vec3d(x, y, z);
               
                Color color = sb.scene.findColor(new Vec3d(), v, 3);
                
                // Depending on the x position, select a color... 
                buffer[index] = (byte) color.b;
                buffer[index + 1] = (byte) color.g;
                buffer[index + 2] = (byte) color.r;
            }
        }

        try { saveTGA("imagetest.tga", buffer, w, h); }
        catch(Exception e) { System.err.println("TGA file not created :" + e); }
    }
}
