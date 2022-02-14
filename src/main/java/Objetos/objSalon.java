/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import Negociacion.Imagenes;
import Negociacion.sonido;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Cris
 */
public class objSalon {
    private static int tamSalon = 8;
    private static JLabel[][] salon = new JLabel[tamSalon][tamSalon]; 
    public static ArrayList<Integer> espacios = new ArrayList();
    public static ArrayList<Integer> obstaculos = new ArrayList();
    public static ArrayList<int[]> limpiados = new ArrayList<>();
    private sonido sound = new sonido();
    private Imagenes controlIMG = new Imagenes();
    
    
    
    public JPanel GenerateField(JPanel panel) { //Genera una matriz de labol para el salon y devuelve un panel con los objetos agregados
        panel.setLayout(null);
        for (int i = 0; i < salon[0].length; i++) {
            for (int j = 0; j < salon.length; j++) {
                panel = setLabel(j, i, panel, "Blank");
            }
        }
   
        return panel;
       
    }
    
    public int[] getIndex(int num) { //Obtiene el indice dentro de la matriz de un objeto en pantalla
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
    
    public JPanel setLabel(int x, int y, JPanel panel, String status) { //Coloca los label eb el panel de juego
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
    


    public  static int getTamSalon() { //obtiene el tamano de la matriz
        return tamSalon;
    }

    
    
    public JLabel[][] getSalon() { //Obtiene la matriz del salon
        return salon;
    }

    public void setTamSalon(int tamSalon) { //Modifica el tamano del juego (No usado)
        this.tamSalon = tamSalon;
    }

    public ArrayList<Integer> getEspacios() { //Obtiene los espacion ocupados por el polvo
        return espacios;
    }

    public ArrayList<Integer>  getObstaculos() { //Obtiene los espacios ocupaos por los obstaculos
        return obstaculos;
    }

    public void setEspacios(int i, int valor) { //Guarda los espacios ocupados por polvo
        this.espacios.add(i, valor);
    }

    public void setObstaculos(int i, int valor) { //Guarda los espacios ocupados por los objetos
        this.obstaculos.add(i, valor);
    }
    
    public void setLimpio(int[] Index) {
        this.limpiados.add(Index);
    }
    
    public void deleteEspacio(int indx) {
        this.espacios.remove(indx);
    }
    
    
}
