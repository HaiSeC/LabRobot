/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import Negociacion.Generador;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import Negociacion.Imagenes;
import Negociacion.Obstaculos;
import Negociacion.Polvo;


/**
 *
 * @author Cris
 */
public class SalonPrincipal extends javax.swing.JFrame implements Runnable {
    private static Thread seguidor;
    private int speed = 100;
    private String Mov = "";
    JLabel Robot;
    private Imagenes cnts = new Imagenes();
    private Generador gen = new Generador();
    private Polvo dts = new Polvo();
    private Obstaculos objs = new Obstaculos();
    /**
     * Creates new form MainBoard
     */
    public SalonPrincipal() {
        initComponents();
        //jPanel1.setSize(500,500);
        //GAMEBOARD = gen.generarjuego(jPanel1);
        gen.generarjuego(jPanel1);
        Robot = new JLabel();
        //Robot.setLocation(jPanel1.getSize().width-100, jPanel1.getSize().height-100);
        //Robot.setBounds(jPanel1.getSize().width-100, jPanel1.getSize().height-100, 100,100);
        Robot.setBounds(-5, -20, 100,100);
        Robot.setIcon(cnts.getScaledImage(new ImageIcon("E:/Codigo U/Programacion 2/LP2.1-Robot/1.png"), 50, 50));
        jPanel1.add(Robot, 1);

        this.seguidor = new Thread(this);
        


    }
    
    public static void Empezar() {
       seguidor.start();
    }

    public void Actualizar(Thread ct) {
        if (ct == seguidor) {
            dts.comprobar(Robot);
            boolean game = objs.comprobarOBJ(Robot);
            if (game) {
                resetMovement();
               
            }
        }
        if (((Robot.getX()) <= -6) || ((Robot.getY()) <= -21) || ((Robot.getY()) >= 350) ) {
           resetMovement();
        }  
    }
    
    public void setMovement(){
        switch (Mov) {
                 case "Izquierda": 
                    Robot.setLocation(Robot.getX()-5, Robot.getY());
                    break;
                case "Arriba":
                    Robot.setLocation(Robot.getX(),Robot.getY()-5);
                    break;
                case "Derecha":
                    Robot.setLocation(Robot.getX()+5, Robot.getY());
                    break;
                case "Abajo":
                    Robot.setLocation(Robot.getX(), Robot.getY()+5);
                    break;
             }
    }
    
    public void resetMovement() {
        switch (Mov) {
                 case "Izquierda": 
                     Mov = "Derecha";
                     break;
                case "Arriba":
                    Mov = "Abajo";
                    break;
                case "Derecha":
                    Mov = "Izquierda";
                    break;
                case "Abajo":
                    Mov = "Arriba";
                    break;
             }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
                       switch (evt.getKeyCode()) {
                
                case 37:
                    Mov = "Izquierda"; 
                    break;
                case 38:
                    Mov = "Arriba";
                    break;
                case 39:
                    Mov = "Derecha";
                    break;
                case 40:
                    Mov = "Abajo";
                    break;
            }// TODO add your handling code here:        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SalonPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SalonPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SalonPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SalonPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SalonPrincipal().setVisible(true);
                Empezar();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
         int limiteX = jPanel1.getSize().width;
         int limiteY = jPanel1.getSize().height;
         
  
 
         
         Thread CT = Thread.currentThread();
         
         while(CT == seguidor) {
         int botX = Robot.getX();
         int botY = Robot.getY();
            Actualizar(CT);
            setMovement();
            System.out.println(limiteX + " y " + botX);
            System.out.println(botX == -15);  
             try {
                 Thread.sleep(100);
             } catch (Exception e) {
             }
         }
    }
}