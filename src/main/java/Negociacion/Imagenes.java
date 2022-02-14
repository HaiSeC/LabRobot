/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negociacion;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Cris
 */
public class Imagenes {
    
    
    public ImageIcon getScaledImage(ImageIcon srcImg, int w, int h){
    Image image = srcImg.getImage(); 
    Image newimg = image.getScaledInstance(w, h,  java.awt.Image.SCALE_SMOOTH);
    srcImg = new ImageIcon(newimg); 

    return srcImg;
}


    
    
}
