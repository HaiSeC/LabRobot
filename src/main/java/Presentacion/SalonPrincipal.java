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
import Negociacion.sonido;
import Objetos.objSalon;
import Datos.Archivos;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;


/**
 *
 * @author Cris
 */
public class SalonPrincipal extends javax.swing.JFrame implements Runnable, KeyListener {
    private Thread seguidor;
    private int speed = 100;
    private String Mov = "";
    private String MovPers = " ";
    private String MovPers2 = " ";
    private String MovPers3 = " ";
    JLabel Robot;
    private Imagenes cnts = new Imagenes();
    private Generador gen = new Generador();
    private Polvo dts = new Polvo();
    private Obstaculos objs = new Obstaculos();
    private sonido snd = new sonido();
    private objSalon SC = new objSalon();
    private Archivos save = new Archivos();
    private static boolean juego = true;
    /**
     * Creates new form MainBoard
     */
    public SalonPrincipal() {
        initComponents();
        gen.generarjuego(jPanel1);
        Robot = new JLabel();
        Robot.setBounds(-5, -20, 100,100);
        Robot.setIcon(cnts.getScaledImage(new ImageIcon(".\\1.png"), 50, 50));
        jPanel1.add(Robot, 1);
        setFocusable(true);
        requestFocus();
        addKeyListener(this);
        Empezar();
        SC.saveDirty();
        SC.saveObs();
        SC.saveCleaned();
        save.guardarDatos();
        JLobs.setText(String.valueOf(gen.getObstaculosL()));;
    }
    

    
    public void Empezar() {
        if(juego) {
          seguidor = new Thread(this);
          seguidor.start(); 
        }
             
    }
    
    public void Finalizar() {
        juego = false;
        snd.ReproducirSonidoyay();
        jPanel1.removeAll();
        JOptionPane.showMessageDialog(null, "¡El salon esta limpio!");
    }

    public void Actualizar() {
        if (juego) {
            int porcentaje = gen.CheckPolvo();
            JLprc.setText(String.valueOf(porcentaje) + "%");
            gen. getPosition(Robot, Mov, MovPers);
            MovPers = Mov;
            gen.comprobar(Robot, "Polvo");
            boolean game = gen.comprobar(Robot, "Obstaculos");
            if (game) {
                resetMovement();
               
            }
            
            if(porcentaje == 0) {
                Finalizar();
            } 
            
        }
        if (((Robot.getX()) <= -10) || ((Robot.getY()) <= -30) || ((Robot.getY()) >= 350) || ((Robot.getX()) >= 370) ) {
            snd.ReproducirSonidono();
            resetMovement();
        }  
    }
    
    public void setMovement(){
        switch (Mov) {
                 case "Izquierda": 
                    Robot.setLocation(Robot.getX()-10, Robot.getY());
                    break;
                case "Arriba":
                    Robot.setLocation(Robot.getX(),Robot.getY()-10);
                    break;
                case "Derecha":
                    Robot.setLocation(Robot.getX()+10, Robot.getY());
                    break;
                case "Abajo":
                    Robot.setLocation(Robot.getX(), Robot.getY()+10);
                    break;
                    
                case "Quieto":
                    Robot.setLocation(Robot.getX(), Robot.getY());
                    break;
             }
    }
    
    public String resetMov2(String location) {
                
        switch (location) {
                 case "Izquierda": 
                     Robot.setLocation(Robot.getX()+10, Robot.getY());
                     //location = "Derecha";
                     break;
                case "Arriba":
                    Robot.setLocation(Robot.getX(),Robot.getY()+10);
                     //location = "Abajo";
                    break;
                case "Derecha":
                    Robot.setLocation(Robot.getX()-10, Robot.getY());
                     //location = "Izquierda";
                    break;
                case "Abajo":
                    Robot.setLocation(Robot.getX(), Robot.getY()-10);
                    //location = "Arriba";
                    break;

             }
        
        return location;
    }
    
    public String getOtherDic(String location) {
                switch (location) {
                 case "Izquierda": 
                     location = "Derecha";
                     break;
                case "Arriba":
                    location = "Abajo";
                    break;
                case "Derecha":
                    location = "Izquierda";
                    break;
                case "Abajo":
                    location = "Arriba";
                    break;

             }
        return location;
    }
    
    public void resetMovement() {
        
        if (Mov != MovPers2 && Mov.equals("Quieto") && !MovPers2.equals("Quieto")) {
            resetMov2(MovPers2);
            //MovPers3 = MovPers;
            MovPers2 = "Quieto";  
        }   else if (Mov != MovPers2 && Mov.equals("Quieto")) {
            
            String value = MovPers3;
            Mov = MovPers2;
            resetMovement();
            MovPers3 = value;

        }  else if (Mov == MovPers2 && Mov.equals("Quieto") && MovPers2.equals("Quieto") && !MovPers3.equals("Quieto") ) {
            resetMov2(MovPers3);
            MovPers3 = "Quieto";
        } 

        switch (Mov) {
                 case "Izquierda": 
                     Robot.setLocation(Robot.getX()+10, Robot.getY());
                    MovPers3= getOtherDic(Mov);
                     Mov = "Quieto";
                     break;
                case "Arriba":
                    Robot.setLocation(Robot.getX(),Robot.getY()+10);
                    MovPers3= getOtherDic(Mov);
                     Mov = "Quieto";
                    break;
                case "Derecha":
                    Robot.setLocation(Robot.getX()-10, Robot.getY());
                    MovPers3= getOtherDic(Mov);
                    Mov = "Quieto";
                    break;
                case "Abajo":
                    Robot.setLocation(Robot.getX(), Robot.getY()-10);
                    MovPers3= getOtherDic(Mov);
                    Mov = "Quieto";
                    break;

             }
        //Robot.setLocation(Robot.getX(), Robot.getY());
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
        JLobs = new java.awt.Label();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JLprc = new java.awt.Label();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(null);

        jLabel1.setText("Suciedad:");

        jLabel2.setText("Obstaculos:");

        jButton1.setText("Guardar Datos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jList1.setEnabled(false);
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(JLprc, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(JLobs, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLprc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLobs, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
       /* if(!juego) {
            Empezar();
            juego = true;
        }               
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
            }*/// TODO add your handling code here:        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        snd.ReproducirSonidono();
        //setFocusable(true);
        requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
                //Empezar();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Label JLobs;
    private java.awt.Label JLprc;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {

 
         
         //Thread CT = Thread.currentThread();
         
         while(juego) {
            Actualizar();
            setMovement();
             try {
                 Thread.sleep(90);
             } catch (Exception e) {
             }
         }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        MovPers2 = Mov; 
        int tecla = e.getKeyCode();
               
        switch (tecla) {
                
                case KeyEvent.VK_LEFT:
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
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {
 }
    
    
}
