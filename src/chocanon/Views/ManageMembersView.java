/* 
    File: ManageMembersView.java
    Project: COSC-4360 Capstone Project Team #0
    University: McMurry University
    Course: COSC–4360 Spring 2021
    Instructor: Mr. Brozovic
    Programmer: Jacob Bremiller
    Created by: Jacob Bremiller
    Created: 2/25/2021
    Updated by: Jacob Bremiller
    Updated: 3/3/2021
    Compiler: Apache NetBeans IDE for Java SE
    Description: Allows members to be populated in a table and searched from a database.
 */
package chocanon.Views;

import chocanon.Models.Member;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jakeb
 */
public class ManageMembersView extends javax.swing.JFrame {
    MemberCustomJTable memCustJTable;
    /**
     * Creates new form ManageMember
     */
    public ManageMembersView() {
        String[] columns = new String[] {
            "databaseId", "First Name", "Last Name", "Street Address", "City", "State", "Zip Code", "Card Number", "Email Address", "Membership Status"
        };
        Object[][] data = new Object[][] {
        };
        
        memCustJTable = new MemberCustomJTable(data, columns){
            Class[] types = new Class [] {Integer.class, String.class, String.class, String.class, String.class, String.class, Integer.class, Integer.class, String.class, Boolean.class};
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        initComponents();
        //tbl_Members.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tbl_Members.getColumnModel().getColumn(1).setPreferredWidth(60);
        tbl_Members.getColumnModel().getColumn(2).setPreferredWidth(60);
        tbl_Members.getColumnModel().getColumn(3).setPreferredWidth(180);
        tbl_Members.getColumnModel().getColumn(4).setPreferredWidth(80);
        tbl_Members.getColumnModel().getColumn(5).setPreferredWidth(4);
        tbl_Members.getColumnModel().getColumn(6).setPreferredWidth(20);
        tbl_Members.getColumnModel().getColumn(7).setPreferredWidth(40);
        tbl_Members.getColumnModel().getColumn(8).setPreferredWidth(140);
        tbl_Members.getColumnModel().getColumn(9).setPreferredWidth(80);
        
        //Hide the databaseId column. This is needed for delete and update
        tbl_Members.getColumnModel().getColumn(0).setMinWidth(0);
        tbl_Members.getColumnModel().getColumn(0).setMaxWidth(0);
        tbl_Members.getColumnModel().getColumn(0).setResizable(false);
        tbl_Members.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    //Setters
    public void setMemberSearchButtonListener(ActionListener e){
        btn_SearchMember.addActionListener(e);
    }
    public void setManageMembersBackButtonListener(ActionListener e){
        btn_MembersBack.addActionListener(e);
    }
    public void setManageMembersEditButtonListener(ActionListener e){
        btn_Edit.addActionListener(e);
    }
    public void setManageMembersAddButtonListener(ActionListener e){
        btn_Add.addActionListener(e);
    }
    public void setManageMembersDeleteButtonListener(ActionListener e){
        btn_Delete.addActionListener(e);
    }
    
    public int getMemberDatabaseIdSelected(){
        int selectedRow = tbl_Members.getSelectedRow();
        if(selectedRow == -1){
            return selectedRow;
        }else{
            return (Integer)tbl_Members.getValueAt(selectedRow, 0);
        }
    }
    
    public int removeSelectedMember(){
        int selectedRow = tbl_Members.getSelectedRow();
        if(selectedRow == -1){
            return selectedRow;
        }else{
            memCustJTable.removeRow(selectedRow);
            return 1;
        }
    }
    
    public void addRowMember(Member mem){
        memCustJTable.addRow(mem);
    }
    
    public void clearTable(){
        memCustJTable.setRowCount(0);
    }
    
    public void clearSearchText(){
        txt_MemberSearchText.setText("");
    }
    
    //Getters
    public String getSearchInput(){
        return txt_MemberSearchText.getText();
    }
    
    //Methods
    public void showMessageBox(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    public int showDeleteDialog(){
        int selectedChoice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the selected member?", "Delete Member", JOptionPane.YES_NO_OPTION);
        return selectedChoice;
    }
    
    //Inner class
    class MemberCustomJTable extends DefaultTableModel{
        public MemberCustomJTable(Object[][] data, Object[] columnNames){
            super(data, columnNames);
        }

        public void addRow(Member newMem){
            this.addRow(new Object[]{newMem.getDatabaseId(),newMem.getFirstName(), newMem.getLastName(), newMem.getStreetAddress(), newMem.getCity(), newMem.getState(), newMem.getZipCode(), newMem.getCardNumber(), newMem.getEmailAddress(), newMem.getMembershipStatus()});
        }
        public void deleteRow(int rowIndex){
            this.removeRow(rowIndex);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Members = new javax.swing.JTable();
        btn_SearchMember = new javax.swing.JButton();
        txt_MemberSearchText = new javax.swing.JTextField();
        btn_MembersBack = new javax.swing.JButton();
        btn_Edit = new javax.swing.JButton();
        btn_Add = new javax.swing.JButton();
        btn_Delete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Manage Members");
        setResizable(false);

        tbl_Members.setModel(memCustJTable);
        tbl_Members.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbl_Members);

        btn_SearchMember.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/search.png"))); // NOI18N
        btn_SearchMember.setText("Search");

        btn_MembersBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/go-back-left-arrow.png"))); // NOI18N
        btn_MembersBack.setBorderPainted(false);
        btn_MembersBack.setContentAreaFilled(false);
        btn_MembersBack.setFocusPainted(false);

        btn_Edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/edit.png"))); // NOI18N
        btn_Edit.setText("Edit Member");

        btn_Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/add.png"))); // NOI18N
        btn_Add.setText("Add New Member");

        btn_Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/delete.png"))); // NOI18N
        btn_Delete.setText("Delete Member");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_MembersBack, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btn_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btn_SearchMember, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_MemberSearchText, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btn_MembersBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btn_SearchMember, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_MemberSearchText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_Add, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Delete)
                    .addComponent(btn_Edit))
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ManageMembersView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageMembersView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageMembersView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageMembersView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageMembersView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Add;
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_Edit;
    private javax.swing.JButton btn_MembersBack;
    private javax.swing.JButton btn_SearchMember;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_Members;
    private javax.swing.JTextField txt_MemberSearchText;
    // End of variables declaration//GEN-END:variables
}
