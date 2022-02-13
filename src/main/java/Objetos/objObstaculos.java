/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author Cris
 */
public class objObstaculos extends objSalon{
        public void GenerateObject(int[] list) {
            int[] array = getObstaculos();
        for (int i = 0; i < array.length; i++) {
            boolean newNum = true;
            int rand = 0;
            while(newNum == true) {
                rand = new Random().nextInt((getTamSalon()*getTamSalon()) - 0 + 1) + 0;
                newNum = comprobarA(rand, array, list);
        }
            setObstaculos(i, rand);
        }  
    }
        
        public boolean comprobarA(int num, int[] list1, int[] list2){
            for (int i = 0; i < list1.length; i++) {
                int obstaculo = list1[i];
                if(obstaculo == num) {
                    return true;
                } else {
                    for (int j = 0; j < list2.length; j++) {
                        int k = list2[j];
                        if (num == k) {
                            return true;
                        } 
                    }
                }
            }
            return false;
        }
        
        public void setObjects(JPanel panel) {
            for (int i = 0; i < getObstaculos().length; i++) {
                int[] indice = getIndex(getObstaculos()[i]);
                setLabel(indice[0], indice[1], panel, "Object");
            }
        }
        
        
        
}
