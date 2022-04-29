
import utils.Scene;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hylia
 */
public class Main {
    
    /**
     * @param args no command line arguments
     */
    public static void main(String[] args) {
        
        int w = 1080;
        int h = 720;
        int d = 3;
        
        try {
            
            w = Integer.parseInt(args[0]);
            h = Integer.parseInt(args[1]);
            d = Integer.parseInt(args[2]);
            
        } catch (Exception e) {
            // continue with default value
        }
        
        Scene scene = new Scene(w, h, d);
        scene.saveScene();
        
    }
    
}
