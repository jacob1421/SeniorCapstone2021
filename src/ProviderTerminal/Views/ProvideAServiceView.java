/* 
    File: ProvideAServiceView.java
    Project: COSC-4360 Capstone Project Team #0
    University: McMurry University
    Course: COSC–4360 Spring 2021
    Instructor: Mr. Brozovic
    Programmer: Jacob Bremiller
    Created by: Lydia Clarke
    Created: 2/02/2021
    Updated by: Jacob Bremiller
    Updated: 3/3/2021
    Compiler: Apache NetBeans IDE for Java SE
    Description: Provides a simulation of the provider logging into the terminal when its turned on.
 */
package ProviderTerminal.Views;

import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author ljcla
 */
public class ProvideAServiceView extends javax.swing.JFrame {

    /**
     * Creates new form ProvideAService
     */
    public ProvideAServiceView() {
        initComponents();
    }
    
    //Setters
    public void setSearchProviderButtonListener(ActionListener e){
        btn_Continue.addActionListener(e);
    }
    public void setBackProvideServiceButtonListener(ActionListener e){
        btn_ProvideServiceBack.addActionListener(e);
    }
    public void setProviderTextBox(String providerNumber){
        txt_ProviderNbr.setText(providerNumber);
    }
    public void setFocusProviderTextBox(){
        txt_ProviderNbr.requestFocus();
    }
    
    //Getters
    public String getProviderNumber(){
        return txt_ProviderNbr.getText();
    }
    
    //Methods
    public void showMessageBox(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_ChocAnTerminal = new javax.swing.JLabel();
        lbl_ProviderEnterNbr = new javax.swing.JLabel();
        txt_ProviderNbr = new javax.swing.JTextField();
        btn_Continue = new javax.swing.JButton();
        btn_ProvideServiceBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Provider Terminal");
        setResizable(false);

        lbl_ChocAnTerminal.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        lbl_ChocAnTerminal.setText("Provider Terminal");

        lbl_ProviderEnterNbr.setText("Please Enter Your Provider Number");
        lbl_ProviderEnterNbr.setToolTipText("");

        txt_ProviderNbr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ProviderNbrActionPerformed(evt);
            }
        });

        btn_Continue.setText("Continue");
        btn_Continue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ContinueActionPerformed(evt);
            }
        });

        btn_ProvideServiceBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/go-back-left-arrow.png"))); // NOI18N
        btn_ProvideServiceBack.setBorderPainted(false);
        btn_ProvideServiceBack.setContentAreaFilled(false);
        btn_ProvideServiceBack.setFocusPainted(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btn_ProvideServiceBack, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(lbl_ChocAnTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 116, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_Continue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_ProviderEnterNbr, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_ProviderNbr, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(104, 104, 104))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_ProvideServiceBack)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbl_ChocAnTerminal)))
                .addGap(18, 18, 18)
                .addComponent(lbl_ProviderEnterNbr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_ProviderNbr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Continue, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ContinueActionPerformed

    }//GEN-LAST:event_btn_ContinueActionPerformed

    private void txt_ProviderNbrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ProviderNbrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ProviderNbrActionPerformed

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
            java.util.logging.Logger.getLogger(ProvideAServiceView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProvideAServiceView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProvideAServiceView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProvideAServiceView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProvideAServiceView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Continue;
    private javax.swing.JButton btn_ProvideServiceBack;
    private javax.swing.JLabel lbl_ChocAnTerminal;
    private javax.swing.JLabel lbl_ProviderEnterNbr;
    private javax.swing.JTextField txt_ProviderNbr;
    // End of variables declaration//GEN-END:variables
}
