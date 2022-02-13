/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negociacion;

import Objetos.objObstaculos;
import javax.swing.JPanel;

/**
 *
 * @author Cris
 */
public class Obstaculos {
    private objObstaculos objeto = new objObstaculos();

    public void generate(int[] arr) {
        objeto.GenerateObject(arr);
    }
    
    public void create(JPanel panel) {
        objeto.setObjects(panel);
    }
    
    public int[] getObjEsp() {
            return objeto.getObstaculos();
            
        }
}
