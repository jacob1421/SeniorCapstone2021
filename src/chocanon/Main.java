/* 
    File: Main.java
    Project: COSC-4360 Capstone Project Team #0
    University: McMurry University
    Course: COSC–4360 Spring 2021
    Instructor: Mr. Brozovic
    Programmer: Jacob Bremiller
    Created by: Jacob Bremiller
    Created: 2/13/2021
    Updated by: Jacob Bremiller
    Updated: 2/13/2021
    Compiler: Apache NetBeans IDE for Java SE
    Description: This is the main class that will display our teams name. Entry point of the program
 */
package chocanon;

import Logger.Log;
import ProviderTerminal.Controllers.TerminalController;
import chocanon.Controllers.ChocanController;
import static Logger.Log.*;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Main extends javax.swing.JFrame {
    //Data Attributes
    ChocanController cc = null;
    TerminalController tc = null;
    
    public Main() {
        initComponents();
        cc = new ChocanController(this);
        tc = new TerminalController(this);
        //Where do we want our logging set?
        Log.set(LEVEL_TRACE);
        Log.info("Main", "Main has been instantiated");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_SoftwareName = new javax.swing.JLabel();
        btn_StartChocoholics = new javax.swing.JButton();
        btn_Exit = new javax.swing.JButton();
        lbl_subHeadingClassYear = new javax.swing.JLabel();
        btn_StartProvider = new javax.swing.JButton();
        mnubr_NavProg = new javax.swing.JMenuBar();
        mnu_File = new javax.swing.JMenu();
        mnuItem_ManageMember = new javax.swing.JMenuItem();
        mnuItem_ManageProvider = new javax.swing.JMenuItem();
        mnuItem_Reports = new javax.swing.JMenuItem();
        mnuItem_Records = new javax.swing.JMenuItem();
        mnu_Help = new javax.swing.JMenu();
        mnuItem_About = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chocanon Software");
        setResizable(false);

        lbl_SoftwareName.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        lbl_SoftwareName.setText("Chocanon Software");

        btn_StartChocoholics.setText("Start Chocoholics Anonymous");
        btn_StartChocoholics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_StartChocoholicsActionPerformed(evt);
            }
        });

        btn_Exit.setText("Exit");
        btn_Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ExitActionPerformed(evt);
            }
        });

        lbl_subHeadingClassYear.setText("COSC-4360 Spring 2021");

        btn_StartProvider.setText("Start Provider Terminal");
        btn_StartProvider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_StartProviderActionPerformed(evt);
            }
        });

        mnu_File.setText("File");

        mnuItem_ManageMember.setText("Manage Member");
        mnu_File.add(mnuItem_ManageMember);

        mnuItem_ManageProvider.setText("Manage Provider");
        mnu_File.add(mnuItem_ManageProvider);

        mnuItem_Reports.setText("Reports");
        mnu_File.add(mnuItem_Reports);

        mnuItem_Records.setText("Records");
        mnu_File.add(mnuItem_Records);

        mnubr_NavProg.add(mnu_File);

        mnu_Help.setText("Help");

        mnuItem_About.setText("About");
        mnu_Help.add(mnuItem_About);

        mnubr_NavProg.add(mnu_Help);

        setJMenuBar(mnubr_NavProg);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(lbl_SoftwareName)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btn_Exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_StartChocoholics, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_StartProvider, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_subHeadingClassYear)
                        .addGap(162, 162, 162)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_SoftwareName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_subHeadingClassYear)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_StartChocoholics, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_StartProvider, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btn_Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btn_ExitActionPerformed

    private void btn_StartChocoholicsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_StartChocoholicsActionPerformed
        Log.info("Main", "Click StartChocoholics Button");
        //Show our intro view
        this.setVisible(false);
        cc.showMenuView();
    }//GEN-LAST:event_btn_StartChocoholicsActionPerformed

    private void btn_StartProviderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_StartProviderActionPerformed
        Log.info("Main", "Click StartProviderTerminal Button");
        this.setVisible(false);
        tc.showProviderView();
    }//GEN-LAST:event_btn_StartProviderActionPerformed
    
    /* SETTERS */
    public void setMenuItemMemberListener(ActionListener e){
        mnuItem_ManageMember.addActionListener(e);
    }
    public void setMenuItemProviderListener(ActionListener e){
        mnuItem_ManageProvider.addActionListener(e);
    }
    public void setMenuItemReportsListener(ActionListener e){
        mnuItem_Reports.addActionListener(e);
    }
    public void setMenuItemRecordsListener(ActionListener e){
        mnuItem_Records.addActionListener(e);
    }
    public void setMenuItemAboutListener(ActionListener e){
        mnuItem_About.addActionListener(e);
    }
    /* GETTERS */
    public void displayTeamInformation(){
        /*
                Team Members
        
                Lydia Clarke
                Stephen Dunn
                Trey Fambrough
                Jacob Bremiller
        */
        JPanel teamAboutPanel = new JPanel();
        JLabel teamLabel = new JLabel(
                "<html>"
                + "<b style='font-size: 24px;'>Team Members</b>"
                + "<ul>"
                + "<li style='font-size: 12px;margin-top:5px;margin-bottom:5px;'>Lydia Clarke</li>"        
                + "<li style='font-size: 12px;margin-top:5px;margin-bottom:5px;'>Stephen Dunn</li>"    
                + "<li style='font-size: 12px;margin-top:5px;margin-bottom:5px;'>Trey Fambrough</li>"    
                + "<li style='font-size: 12px;margin-top:5px;margin-bottom:5px;'>Jacob Bremiller</li>"      
                + "</ul>"
                + "</html>"
        );
        teamAboutPanel.add(teamLabel);
        JOptionPane.showMessageDialog(teamAboutPanel, teamLabel, "About Team", JOptionPane.INFORMATION_MESSAGE);
    }
    
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Exit;
    private javax.swing.JButton btn_StartChocoholics;
    private javax.swing.JButton btn_StartProvider;
    private javax.swing.JLabel lbl_SoftwareName;
    private javax.swing.JLabel lbl_subHeadingClassYear;
    private javax.swing.JMenuItem mnuItem_About;
    private javax.swing.JMenuItem mnuItem_ManageMember;
    private javax.swing.JMenuItem mnuItem_ManageProvider;
    private javax.swing.JMenuItem mnuItem_Records;
    private javax.swing.JMenuItem mnuItem_Reports;
    private javax.swing.JMenu mnu_File;
    private javax.swing.JMenu mnu_Help;
    private javax.swing.JMenuBar mnubr_NavProg;
    // End of variables declaration//GEN-END:variables
}
