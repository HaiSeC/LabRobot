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

        
        public void generate(){//Llama a la funcion que genera el polvo
            polvo.GenerateDust();
        }
        public void set(JPanel panel) { //Llama a la funcion que coloca el polvo en la matriz
            polvo.setDust(panel);
        }

        
        public ArrayList<Integer> getPolvoEspacios() { //Obtiene los espacion que ocupa el polvo
            return polvo.getEspacios();
            
        }
        
        public void actualizar(int[] indice) { //comprueba si el bot pasa por el polvo
            polvo.changeDust(indice);
 
    }
}
