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
import java.util.Arrays;
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
    
    
    public void getPosition(JLabel bot, String direc, String antDirec) {
    int botX = bot.getX();
    int botY = bot.getY();
    for (int i = 0; i < SALON.getSalon()[0].length; i++) {
            for (int j = 0; i < SALON.getSalon().length; i++) {
            JLabel label = SALON.getSalon()[i][j];
            int x = label.getX();
            int y = label.getY();
            if ((botX > x-26 && botX < x+26) && (botY > y-55 && botY < y)) {
                boolean decis;
                int[] JLi = {i,j};
                if(direc == antDirec) {
                    decis = SALON.comprobarExis(JLi);
                } else {
                    decis = true;
                }
                if(decis) {
                    //System.out.println("Matrix: bot:" + bot.getY() + " dust: " + y);
                    SALON.setLugar(JLi);
                    SALON.saveRecor();                   
                }

                
            } 
    }
                    }        
    }
public boolean comprobar(JLabel bot, String statement) { //comprueba si el bot pasa por el polvo
    ArrayList<Integer> list1 = null;
    int botX = bot.getX();
    int botY = bot.getY();

    switch (statement) {
            case "Polvo":
                    list1 = espacios;
                    break;
            case "Obstaculos":
                    list1 = obstaculos;
                    break;
            }
        JLabel[][] list3 = SALON.getSalon();
        for (int i = 0; i < list1.size(); i++) {
               int[] indice = SALON.getIndex(list1.get(i));
               JLabel label = list3[indice[0]][indice[1]];
               int y = label.getY();
               int x = label.getX();
               if ((botX > x-26 && botX < x+26) && (botY > y-55 && botY < y)) {
                   if (statement.equals("Polvo")) {
                   System.out.println(SALON.getPercentaje());
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

public int CheckPolvo() {
    int lenght = SALON.getEspacios().size();
    int per = 0; 
    if (lenght != 0) {
        per = (lenght*100)/((SALON.getTamSalon()*SALON.getTamSalon())/2);
        SALON.setNivelPolvo(per);
        SALON.savePer();
    } else {
        SALON.setNivelPolvo(per);
    }
    
    return per;
}
   
    public int getObstaculosL() {
        return SALON.getObstaculos().size();
    }
    

}
