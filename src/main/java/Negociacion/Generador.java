/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negociacion;

import Negociacion.Obstaculos;
import Negociacion.Polvo;
import Objetos.objSalon;
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

   
    public JLabel[][] generarjuego(JPanel panel) {
        panel = SALON.GenerateField(panel);
        polvo.generate();
        objetoMANAGE.generate(polvo.getPolvoEspacios());
        polvo.set(panel);
        objetoMANAGE.create(panel);
        JLabel[][] salon = SALON.getSalon();
        return salon;
    }
   
    
    

}
