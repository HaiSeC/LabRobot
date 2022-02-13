/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negociacion;

import Objetos.objPolvo;
import Objetos.objSalon;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Cris
 */
public class Polvo {
        private final objPolvo polvo = new objPolvo();
        private sonido sound = new sonido();
        
        public void generate(){//Llama a la funcion que genera el polvo
            polvo.GenerateDust();
        }
        public void set(JPanel panel) { //Llama a la funcion que coloca el polvo en la matriz
            polvo.setDust(panel);
        }

        
        public ArrayList<Integer> getPolvoEspacios() { //Obtiene los espacion que ocupa el polvo
            return polvo.getEspacios();
            
        }
        
        public void comprobar(JLabel bot) { //comprueba si el bot pasa por el polvo
        ArrayList<Integer> list1;
            
        JLabel[][] list3;
        list1 = polvo.getEspacios();
        list3 = polvo.getSalon();
        for (int i = 0; i < list1.size(); i++) {
               int[] indice = polvo.getIndex(list1.get(i));
               JLabel label = list3[indice[0]][indice[1]];
               int y = label.getY();
               int x = label.getX();
               int botX = bot.getX();
               int botY = bot.getY(); //System.out.println((botX > x-10 && botX < x+10) && (botY > y-40 && botY < y));
               if ((botX > x-20 && botX < x+20) && (botY > y-40 && botY < y)) {
         
                   System.out.println("delete: bot:" + bot.getY() + " dust: " + y);
                   polvo.setLimpio(indice);
                   sound.ReproducirSonidook();
                   polvo.changeDust(indice);
                   polvo.deleteEspacio(i);
            }
            
        }
        
    }
}
