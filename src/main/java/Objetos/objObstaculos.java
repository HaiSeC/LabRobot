/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author Cris
 */
public class objObstaculos extends objSalon{
    
    public objObstaculos(){
        super();
    }
    
        public void GenerateObject(ArrayList<Integer> list) { //genera los obtaculos
        ArrayList<Integer> array = getObstaculos();
        int esp = new Double((getTamSalon()*getTamSalon())*0.15).intValue();
        for (int i = 0; i < esp; i++) {
            boolean newNum = true;
            int rand = 0;
            while(newNum == true) {
                rand = new Random().nextInt((((getTamSalon()*getTamSalon())-1) - 1) + 1) + 1;
                newNum = comprobarA(rand, array, list);
        }
            setObstaculos(i, rand);
        }  
    }
        
        public boolean comprobarA(int num, ArrayList<Integer> list1, ArrayList<Integer> list2){ //evita que los obstaculos se posicionen sobre el polvo o sobre otro
            for (int i = 0; i < list1.size(); i++) {
                int obstaculo = list1.get(i);
                if(obstaculo == num) {
                    return true;
                }
            }
            for (int j = 0; j < list2.size(); j++) {
                int k = list2.get(j);
                if (num == k) {
                    return true;
                } 
            }
            return false;
        }
        
        public void setObjects(JPanel panel) {
            for (int i = 0; i < getObstaculos().size(); i++) {
                int[] indice = getIndex(getObstaculos().get(i));
                setLabel(indice[0], indice[1], panel, "Object");
            }
        }
        
        
        
}
