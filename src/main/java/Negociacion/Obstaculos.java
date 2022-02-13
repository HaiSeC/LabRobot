/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negociacion;

import Objetos.objObstaculos;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Cris
 */
public class Obstaculos {
    private objObstaculos objeto = new objObstaculos();
    private Negociacion.sonido sound = new Negociacion.sonido();
    public void generate(ArrayList<Integer> arr) { //llama la funcion que genera los obstaculos
        objeto.GenerateObject(arr);
    }
    
    public void create(JPanel panel) { //llana la funcion que coloca los obstaculos0
        objeto.setObjects(panel);
    }
    
    public ArrayList<Integer> getObjEsp() { //obtiene los espacios de los obstaculos
            return objeto.getObstaculos();
            
        }
    
            public boolean comprobarOBJ(JLabel bot) { //comprueba si el bot pasa por el polvo
        ArrayList<Integer> list1;
            
        JLabel[][] list3;
        list1 = objeto.getObstaculos();
        list3 = objeto.getSalon();
        for (int i = 0; i < list1.size(); i++) {
               int[] indice = objeto.getIndex(list1.get(i));
               JLabel label = list3[indice[0]][indice[1]];
               int y = label.getY();
               int x = label.getX();
               int botX = bot.getX();
               int botY = bot.getY(); //System.out.println((botX > x-10 && botX < x+10) && (botY > y-40 && botY < y));
               if ((botX > x-20 && botX < x+20) && (botY > y-40 && botY < y)) {
                   System.out.println("has chocado");
                   sound.ReproducirSonidono();
                   return true;
                   
            }
            
        }       
        return false;       
    }
}
