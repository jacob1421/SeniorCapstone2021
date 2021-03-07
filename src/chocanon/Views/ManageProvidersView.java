/* 
    File: ManageProvidersView.java
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
    Description: Allows providers to be populated in a table and searched from a database.
 */
package chocanon.Views;

import chocanon.Models.Provider;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jakeb
 */
public class ManageProvidersView extends javax.swing.JFrame {
    ProviderCustomJTable provCustJTable;
    /**
     * Creates new form ManageProvider
     */
    public ManageProvidersView() {
        String[] columns = new String[] {
            "databaseId", "First Name", "Last Name", "Street Address", "City", "State", "Zip Code", "Provider Number", "Email Address", "Provider Type"
        };
        Object[][] data = new Object[][] {
        };
        
        provCustJTable = new ProviderCustomJTable(data, columns){
            Class[] types = new Class [] {Integer.class, String.class, String.class, String.class, String.class, String.class, Integer.class, Integer.class, String.class, String.class};
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

        tbl_Providers.getColumnModel().getColumn(1).setPreferredWidth(60);
        tbl_Providers.getColumnModel().getColumn(2).setPreferredWidth(60);
        tbl_Providers.getColumnModel().getColumn(3).setPreferredWidth(180);
        tbl_Providers.getColumnModel().getColumn(4).setPreferredWidth(80);
        tbl_Providers.getColumnModel().getColumn(5).setPreferredWidth(4);
        tbl_Providers.getColumnModel().getColumn(6).setPreferredWidth(20);
        tbl_Providers.getColumnModel().getColumn(7).setPreferredWidth(80);
        tbl_Providers.getColumnModel().getColumn(8).setPreferredWidth(140);
        tbl_Providers.getColumnModel().getColumn(9).setPreferredWidth(80);
        
        //Hide the databaseId column. This is needed for delete and update
        tbl_Providers.getColumnModel().getColumn(0).setMinWidth(0);
        tbl_Providers.getColumnModel().getColumn(0).setMaxWidth(0);
        tbl_Providers.getColumnModel().getColumn(0).setResizable(false);
        tbl_Providers.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    //Setters
    public void setProviderSearchButtonListener(ActionListener e){
        btn_SearchProvider.addActionListener(e);
    }
    public void setManageProvidersBackButtonListener(ActionListener e){
        btn_ProvidersBack.addActionListener(e);
    }
    
    //Getters
    public String getSearchInput(){
        return txt_ProviderSearchText.getText();
    }
    
    public void setManageProvidersEditButtonListener(ActionListener e){
        btn_Edit.addActionListener(e);
    }
    public void setManageProvidersAddButtonListener(ActionListener e){
        btn_Add.addActionListener(e);
    }
    public void setManageProvidersDeleteButtonListener(ActionListener e){
        btn_Delete.addActionListener(e);
    }
    
    public int getProviderDatabaseIdSelected(){
        int selectedRow = tbl_Providers.getSelectedRow();
        if(selectedRow == -1){
            return selectedRow;
        }else{
            return (Integer)tbl_Providers.getValueAt(selectedRow, 0);
        }
    }
    
    public int removeSelectedProvider(){
        int selectedRow = tbl_Providers.getSelectedRow();
        if(selectedRow == -1){
            return selectedRow;
        }else{
            provCustJTable.removeRow(selectedRow);
            return 1;
        }
    }
    
    public void addRowProvider(Provider prov){
        provCustJTable.addRow(prov);
    }
    
    public void clearTable(){
        provCustJTable.setRowCount(0);
    }
    
    public void clearSearchText(){
        txt_ProviderSearchText.setText("");
    }
   
    
    //Methods
    public void showMessageBox(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    public int showDeleteDialog(){
        int selectedChoice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the selected provider?", "Delete Provider", JOptionPane.YES_NO_OPTION);
        return selectedChoice;
    }
    
    //Inner class
    class ProviderCustomJTable extends DefaultTableModel{
        public ProviderCustomJTable(Object[][] data, Object[] columnNames){
            super(data, columnNames);
        }

        public void addRow(Provider newProvider){
            this.addRow(new Object[]{newProvider.getDatabaseId(),newProvider.getFirstName(), newProvider.getLastName(), newProvider.getStreetAddress(), newProvider.getCity(), newProvider.getState(), newProvider.getZipCode(), newProvider.getProviderNumber(), newProvider.getEmailAddress(), newProvider.getProviderType()});
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
        tbl_Providers = new javax.swing.JTable();
        btn_SearchProvider = new javax.swing.JButton();
        txt_ProviderSearchText = new javax.swing.JTextField();
        btn_ProvidersBack = new javax.swing.JButton();
        btn_Edit = new javax.swing.JButton();
        btn_Add = new javax.swing.JButton();
        btn_Delete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        tbl_Providers.setModel(provCustJTable);
        tbl_Providers.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbl_Providers);

        btn_SearchProvider.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/search.png"))); // NOI18N
        btn_SearchProvider.setText("Search");

        btn_ProvidersBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/go-back-left-arrow.png"))); // NOI18N
        btn_ProvidersBack.setBorderPainted(false);
        btn_ProvidersBack.setContentAreaFilled(false);
        btn_ProvidersBack.setFocusPainted(false);

        btn_Edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/edit.png"))); // NOI18N
        btn_Edit.setText("Edit Provider");

        btn_Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/add.png"))); // NOI18N
        btn_Add.setText("Add New Provider");

        btn_Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/delete.png"))); // NOI18N
        btn_Delete.setText("Delete Provider");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_ProvidersBack, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(btn_SearchProvider, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_ProviderSearchText, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btn_ProvidersBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btn_SearchProvider, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_ProviderSearchText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            java.util.logging.Logger.getLogger(ManageProvidersView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageProvidersView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageProvidersView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageProvidersView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new ManageProvidersView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Add;
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_Edit;
    private javax.swing.JButton btn_ProvidersBack;
    private javax.swing.JButton btn_SearchProvider;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_Providers;
    private javax.swing.JTextField txt_ProviderSearchText;
    // End of variables declaration//GEN-END:variables
}
