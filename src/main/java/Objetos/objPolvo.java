/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import Negociacion.Imagenes;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Cris
 */
public class objPolvo extends objSalon {
    
    public objPolvo(){
        
    }
    
    private Imagenes controlIMG = new Imagenes();
    
    public void GenerateDust() {
        int[] array = getEspacios();
        for (int i = 0; i < array.length; i++) {
        boolean newNum = true;
        int rand = 0;
        while(newNum == true) {
            rand = new Random().nextInt((getTamSalon()*getTamSalon()) - 0 + 1) + 0;
            newNum = comprobarARR(rand, array);
        }
            setEspacios(i, rand);
        }  
    }
    
    public void setDust(JPanel panel){
        for (int i = 0; i < getEspacios().length; i++) {
            int[] indice = getIndex(getEspacios()[i]);
            setLabel(indice[0], indice[1], panel, "Dust");
        }
    }
    
    private boolean comprobarARR(int num, int[] list) {
        for (int i = 0; i < list.length; i++) {
             int espacio = list[i];
            if(espacio == num) {
                return true;
            }
        }
        return false;
    }
}
