/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negociacion;

import Negociacion.Obstaculos;
import Negociacion.Polvo;
import Objetos.objSalon;
import static Objetos.objSalon.espacios;
import static Objetos.objSalon.obstaculos;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Cris
 */
public class Generador {
    private objSalon SALON = new objSalon();
    private Polvo polvo = new Polvo();
    private Obstaculos objetoMANAGE = new Obstaculos();
        private sonido sound = new sonido();
   
    public JLabel[][] generarjuego(JPanel panel) {
        panel = SALON.GenerateField(panel);
        polvo.generate();
        objetoMANAGE.generate(polvo.getPolvoEspacios());
        polvo.set(panel);
        objetoMANAGE.create(panel);
        JLabel[][] salon = SALON.getSalon();
        return salon;
    }
    
public boolean comprobar(JLabel bot, String statement) { //comprueba si el bot pasa por el polvo
    ArrayList<Integer> list1 = null;
    int botX = bot.getX();
    int botY = bot.getY();
    for (int i = 0; i < SALON.getSalon()[0].length; i++) {
            for (int j = 0; i < SALON.getSalon().length; i++) {
            JLabel label = SALON.getSalon()[i][j];
            int x = label.getX();
            int y = label.getY();
            if ((botX > x-25 && botX < x+25) && (botY > y-55 && botY < y)) {
                System.out.println("Matrix: bot:" + bot.getY() + " dust: " + y);
                int[] JLi = new int[2];
                JLi[0] = i;
                JLi[1] = i;
                SALON.setLugar(JLi);
                SALON.saveRecor();
                
            } 
    }
                    }
    switch (statement) {
            case "Polvo":
                    list1 = espacios;
                    break;
            case "Obstaculos":
                    list1 = obstaculos;
                    break;
            case "Salon":
                return true;
            }
    
        JLabel[][] list3 = SALON.getSalon();
        for (int i = 0; i < list1.size(); i++) {
               int[] indice = SALON.getIndex(list1.get(i));
               JLabel label = list3[indice[0]][indice[1]];
               int y = label.getY();
               int x = label.getX();
 //System.out.println((botX > x-10 && botX < x+10) && (botY > y-40 && botY < y));
               if ((botX > x-25 && botX < x+25) && (botY > y-55 && botY < y)) {
                   if (statement.equals("Polvo")) {
                   System.out.println("delete: bot:" + bot.getY() + " dust: " + y);
                   SALON.setLimpio(indice);
                   sound.ReproducirSonidook();
                   polvo.actualizar(indice);
                   SALON.deleteEspacio(i);        
                   SALON.saveCleaned();
                   } else if (statement.equals("Obstaculos")) {
                       sound.ReproducirSonidono();
                       return true;
                   }

            }
            
        }
        return false;
    }

public void CheckPolvo() {
    int lenght = SALON.getEspacios().size();
    if (lenght != 0) {
        float per = (lenght*100)/(SALON.getTamSalon());
        SALON.setNivelPolvo(per);
    }
}
   
    
    

}
