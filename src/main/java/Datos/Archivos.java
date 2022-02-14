/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Guerrero
 */
public class Archivos {
    private Date fecha = new Date();
    private static String listEsCle = "";
    private static String listEsDir = "";
    private static String listEsObs = "";
    private static String cantPosRec = "";
    private static String cantClean = "";
    private static String porEspDir = "";
    
    public String getListEsCle() {
        return listEsCle;
    }

    public void setListEsCle(String listEsCle) {
        this.listEsCle = listEsCle;
    }

    public String getListEsDir() {
        return listEsDir;
    }

    public void setListEsDir(String listEsDir) {
        this.listEsDir = listEsDir;
    }

    public String getListEsObs() {
        return listEsObs;
    }

    public void setListEsObs(String listEsObs) {
        this.listEsObs = listEsObs;
    }

    public String getCantPosRec() {
        return cantPosRec;
    }

    public void setCantPosRec(String cantPosRec) {
        this.cantPosRec = cantPosRec;
    }

    public String getCantClean() {
        return cantClean;
    }

    public void setCantClean(String cantClean) {
        this.cantClean = cantClean;
    }

    public String getPorEspDir() {
        return porEspDir;
    }

    public void setPorEspDir(String porEspDir) {
        this.porEspDir = porEspDir;
    }
    
    public void guardarDatos() {//Almacena los datos del juego
        try {
            File archivo = new File("HistorialRobot.txt");
            try (FileWriter writez = new FileWriter(archivo, true)) {
                writez.write("<------------------->\nFecha: " + fecha.toLocaleString()
                        + "\nEspacios Limpiados: " + listEsCle 
                                + "\nLista de Espacios Sucios: " + listEsDir
                                + "\nEspacios con Obstaculos: " + listEsObs + 
                        "\nCantidad de posiciones Recorridas: " + cantPosRec +
                        "\nCantidad de Posiciones Limpias: " + cantClean +
                        "\nPorcentaje de Espacios Sucios en el salón: " + porEspDir + "\n");
            }
        } catch (IOException e) {
            //JOptionPane.showMessageDialog(null, "Error al guardar datos!");
            JOptionPane.showMessageDialog(null, "Error al guardar datos!", "Error", 0);
        }
    }
}
