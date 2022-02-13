/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import Negociacion.Imagenes;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Cris
 */
public class objSalon {
    private int tamSalon = 8;
    private JLabel[][] salon = new JLabel[tamSalon][tamSalon]; 
    public int[] espacios = new int[new Double((getTamSalon()*getTamSalon())*0.50).intValue()];
    public int[] obstaculos = new int[new Double((getTamSalon()*getTamSalon())*0.15).intValue()];
    //private objPolvo polvo = new objPolvo();
   // private objObstaculos objeto = new objObstaculos();
    private objRobot robot = new objRobot();
    private Imagenes controlIMG = new Imagenes();
    
    public JPanel GenerateField(JPanel panel) {
        panel.setLayout(null);
        //polvo.GenerateDust();
        //objeto.GenerateObject(espacios);
        for (int i = 0; i < salon[0].length; i++) {
            for (int j = 0; j < salon.length; j++) {
                panel = setLabel(j, i, panel, "Blank");
            }
        }

        //robot.iniciar();
        
        return panel;
       
    }
    
    public int[] getIndex(int num) {
        int[] index = new int[2];
        int uno = 0;
        for (int i = 0; i < salon[0].length; i++) {
            for (int j = 0; j < salon.length; j++) {
                int dos = j;

                int k = uno+dos;
                if (num == k) {
                    index[0] = i;
                    index[1] = j;
                    return index;
                }
            }
            uno += getTamSalon();
            
        }
        return index;
    }
    
    public JPanel setLabel(int x, int y, JPanel panel, String status) {
        salon[x][y] = new JLabel();
        switch(status){
            case "Blank":
                salon[x][y].setText(" ");
                break;
            
            case "Dust":
                salon[x][y].setIcon(controlIMG.getScaledImage(new ImageIcon("E:/Codigo U/Programacion 2/LP2.1-Robot/dust.png"), 50, 50));
                break;
                
            case "Object":
                salon[x][y].setIcon(controlIMG.getScaledImage(new ImageIcon("E:/Codigo U/Programacion 2/LP2.1-Robot/obstacle.png"), 50, 50));
                break;
        }
        
        salon[x][y].setBounds(x*50,y*50,50,50);
        panel.add(salon[x][y]);
        
        return panel;
    }

    public int getTamSalon() {
        return tamSalon;
    }

    public JLabel[][] getSalon() {
        return salon;
    }

    public void setTamSalon(int tamSalon) {
        this.tamSalon = tamSalon;
    }

    public int[] getEspacios() {
        return espacios;
    }

    public int[] getObstaculos() {
        return obstaculos;
    }

    public void setEspacios(int i, int valor) {
        this.espacios[i] = valor;
    }

    public void setObstaculos(int i, int valor) {
        this.obstaculos[i] = valor;
    }
    
    
    
}
