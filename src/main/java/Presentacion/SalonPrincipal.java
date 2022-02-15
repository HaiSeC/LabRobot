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
import javax.swing.DefaultListModel;
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
    private Archivos LD = new Archivos();
    private static boolean juego = true;
    /**
     * Creates new form MainBoard
     */
    public SalonPrincipal() {
        initComponents();
        Empezar();
    }
    
    public final void Empezar() {
        DefList();
        BtnAD.setEnabled(false);
        BtnRestart.setEnabled(false);
        BtnSaveData.setEnabled(false);
        DefList();
        gen.generarjuego(PlnGame);
        Robot = new JLabel();
        Robot.setBounds(-5, -20, 100,100);
        Robot.setIcon(cnts.getScaledImage(new ImageIcon(".\\1.png"), 50, 50));
        PlnGame.add(Robot, 1);
        setFocusable(true);
        requestFocus();
        addKeyListener(this);
        JLobs.setText(String.valueOf(gen.getObstaculosL()));
        SC.saveDirty();
        if(juego) {
          seguidor = new Thread(this);
          seguidor.start(); 
        }
             
    }
    
    public void Finalizar() {       
        save.mLis();
        ListAnt.setModel(save.AddModel);
        SC.saveCleaned();
        SC.setPerce(JLprc.getText());
        BtnRestart.setEnabled(true);
        //BtnSaveData.setEnabled(true);
        BtnAD.setEnabled(true);
        juego = false;
        snd.ReproducirSonidoyay();
        PlnGame.removeAll();
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
    
    public DefaultListModel DefList(){
        DefaultListModel DefModel = new DefaultListModel();
        ListAnt.setModel(DefModel);
        return DefModel;
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
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ActualData = new javax.swing.JFrame();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        LblFecha = new javax.swing.JLabel();
        LblEL = new javax.swing.JLabel();
        LblES = new javax.swing.JLabel();
        LblEO = new javax.swing.JLabel();
        LblCER = new javax.swing.JLabel();
        LblCEL = new javax.swing.JLabel();
        LblPES = new javax.swing.JLabel();
        PlnGame = new javax.swing.JPanel();
        JLobs = new java.awt.Label();
        LblSucs = new javax.swing.JLabel();
        LblObss = new javax.swing.JLabel();
        JLprc = new java.awt.Label();
        BtnSaveData = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListAnt = new javax.swing.JList<>();
        BtnRestart = new javax.swing.JButton();
        BtnAD = new javax.swing.JButton();

        ActualData.setTitle("Datos Actuales");
        ActualData.setPreferredSize(new java.awt.Dimension(900, 220));
        ActualData.setSize(new java.awt.Dimension(900, 220));

        jLabel1.setText("Fecha:");

        jLabel2.setText("Espacios Limpiados:");

        jLabel3.setText("Lista de Espacios Sucios:");

        jLabel4.setText("Espacios con Obstaculos:");

        jLabel5.setText("Cant. Espacio Recorridos:");

        jLabel6.setText("Cant. Espacios Limpios:");

        jLabel7.setText("Porcentaje de Espacios Sucios:");

        LblFecha.setText("jLabel8");

        LblEL.setText("jLabel9");

        LblES.setText("jLabel10");

        LblEO.setText("jLabel11");

        LblCER.setText("jLabel12");

        LblCEL.setText("jLabel13");

        LblPES.setText("jLabel14");

        javax.swing.GroupLayout ActualDataLayout = new javax.swing.GroupLayout(ActualData.getContentPane());
        ActualData.getContentPane().setLayout(ActualDataLayout);
        ActualDataLayout.setHorizontalGroup(
            ActualDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ActualDataLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(ActualDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(ActualDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblFecha)
                    .addComponent(LblEL)
                    .addComponent(LblES)
                    .addComponent(LblEO)
                    .addComponent(LblCER)
                    .addComponent(LblCEL)
                    .addComponent(LblPES))
                .addContainerGap(463, Short.MAX_VALUE))
        );
        ActualDataLayout.setVerticalGroup(
            ActualDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ActualDataLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(ActualDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(LblFecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ActualDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(LblEL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ActualDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(LblES))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ActualDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(LblEO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ActualDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(LblCER))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ActualDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(LblCEL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ActualDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(LblPES))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aspiradora Loca");
        setResizable(false);

        PlnGame.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        PlnGame.setLayout(null);

        LblSucs.setText("Suciedad:");

        LblObss.setText("Obstaculos:");

        BtnSaveData.setText("Guardar Datos");
        BtnSaveData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSaveDataActionPerformed(evt);
            }
        });

        ListAnt.setEnabled(false);
        ListAnt.setName(""); // NOI18N
        jScrollPane1.setViewportView(ListAnt);

        BtnRestart.setText("Reiniciar");
        BtnRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRestartActionPerformed(evt);
            }
        });

        BtnAD.setText("Datos Actuales");
        BtnAD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnADActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(PlnGame, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblSucs, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblObss, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnSaveData))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(BtnAD)
                                .addGap(18, 18, 18)
                                .addComponent(BtnRestart, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JLprc, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JLobs, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblSucs, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLprc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JLobs, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblObss, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnAD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(BtnSaveData, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(BtnRestart, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PlnGame, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void LoadList(){
        SC.saveObs();
        SC.saveCleaned();
        LblFecha.setText(LD.getFecha().toLocaleString());
        LblEL.setText(LD.getListEsCle());
        LblCEL.setText(LD.getCantClean());
        LblES.setText(LD.getListEsDir());       
        LblEO.setText(LD.getListEsObs()); 
        LblCER.setText(LD.getCantPosRec());                   
        LblPES.setText(SC.getPerce());
    }     
    
    private void BtnSaveDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSaveDataActionPerformed
        DefList();
        SC.saveCleaned();
        SC.setPerce(JLprc.getText());
        save.mLis();
        ListAnt.setModel(save.AddModel);
        snd.ReproducirSonidono();
        SC.savedata();
        save.guardarDatos();
        requestFocus(); 
    }//GEN-LAST:event_BtnSaveDataActionPerformed

    private void BtnRestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRestartActionPerformed
        SC.restart();
        PlnGame.repaint();
        juego = true;       
        Empezar();
    }//GEN-LAST:event_BtnRestartActionPerformed

    private void BtnADActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnADActionPerformed
        LoadList();
        ActualData.setVisible(true);
        ActualData.setLocationRelativeTo(null);
    }//GEN-LAST:event_BtnADActionPerformed

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
    private javax.swing.JFrame ActualData;
    private javax.swing.JButton BtnAD;
    private javax.swing.JButton BtnRestart;
    private javax.swing.JButton BtnSaveData;
    private java.awt.Label JLobs;
    private java.awt.Label JLprc;
    private javax.swing.JLabel LblCEL;
    private javax.swing.JLabel LblCER;
    private javax.swing.JLabel LblEL;
    private javax.swing.JLabel LblEO;
    private javax.swing.JLabel LblES;
    private javax.swing.JLabel LblFecha;
    private javax.swing.JLabel LblObss;
    private javax.swing.JLabel LblPES;
    private javax.swing.JLabel LblSucs;
    private javax.swing.JList<String> ListAnt;
    private javax.swing.JPanel PlnGame;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
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
