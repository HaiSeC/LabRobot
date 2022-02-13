/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negociacion;

import Objetos.objRobot;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Cris
 */
public class Robot {
    private objRobot bot = new objRobot();
    private JLabel Robot;
    
    private Imagenes cnts = new Imagenes();
    
    public void aparecer(JPanel panel) {
       Robot = new JLabel();
        Robot.setBounds(panel.getSize().width-100, panel.getSize().height-100, 100,100);
        Robot.setIcon(cnts.getScaledImage(new ImageIcon("E:/Codigo U/Programacion 2/LP2.1-Robot/pvacuum.png"), 50, 50));
        panel.add(Robot, 1);
    }
    
    public int getX() {
        return Robot.getLocation().x;
    }
    public int getY() {
        return Robot.getLocation().y;
    }
    
    public void setLocation(int x, int y) {
        Robot.setLocation(x, y);
    }
}
