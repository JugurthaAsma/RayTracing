/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author JUGURTHA
 */
public class Config {
    
    // default values
    public static int WIDTH = 1920;
    public static int HEIGHT = 1080;
    public static double DISTANCE = -1D;
    public static int DEPTH = 3;
    
    public static void setConfig(String[] args) {
        try {
            
            WIDTH = Integer.parseInt(args[0]);
            HEIGHT = Integer.parseInt(args[1]);
            DISTANCE = Double.parseDouble(args[2]);
            DEPTH = Integer.parseInt(args[3]);
            
        } catch (Exception e) {
            System.out.println("Usage: java raytracing.Raytracing <WIDTH> <HEIGHT> <DISTANCE> <DEPTH>");
        }
    }
    
}
