/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negociacion;

import Objetos.objPolvo;
import javax.swing.JPanel;

/**
 *
 * @author Cris
 */
public class Polvo {
        private final objPolvo polvo = new objPolvo();
        
        public void generate(){
            polvo.GenerateDust();
        }
        public void set(JPanel panel) {
            polvo.setDust(panel);
        }
        
        public int[] getEspacios() {
            return polvo.getEspacios();
            
        }
}
