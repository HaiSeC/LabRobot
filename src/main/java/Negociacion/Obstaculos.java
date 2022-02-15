/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negociacion;

import Objetos.objObstaculos;
import java.util.ArrayList;
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
}
