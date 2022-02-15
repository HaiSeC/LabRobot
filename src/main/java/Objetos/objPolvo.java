/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import Negociacion.Imagenes;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author Cris
 */
public class objPolvo extends objSalon {
    
    public objPolvo(){
        super();
    }
    
    private Imagenes controlIMG = new Imagenes();
    
    public void GenerateDust() { //Gennera los espacion que ocupara el polvo
        ArrayList<Integer> array = getEspacios();
        int max=getTamSalon()*getTamSalon();
        int min = 8;
        int esp = new Double(max*0.50).intValue();
        for (int i = 0; i < esp; i++) {
        boolean newNum = true;
        int rand = 0;
        while(newNum == true) {
            rand = new Random().nextInt(max - min) + min;
            newNum = comprobarARR(rand, array);
        }
            setEspacios(i, rand);
        }  
    }
    
    public void setDust(JPanel panel){ //coloca el polvo en la matriz
        for (int i = 0; i < getEspacios().size(); i++) {
            int[] indice = getIndex(getEspacios().get(i));
            savePolvox(indice);
            setLabel(indice[0], indice[1], panel, "Dust");
        }
    }
    
    public void changeDust( int[] indice){ //elimina el polvo de la matriz
        getSalon()[indice[0]][indice[1]].setIcon(null);
    }
    
    private boolean comprobarARR(int num, ArrayList<Integer> list) { //Se asegura de no repetir los espacios del polvo
        for (int i = 0; i < list.size(); i++) {
             int espacio = list.get(i);
            if(espacio == num) {
                return true;
            }
        }
        return false;
    }
    

}
