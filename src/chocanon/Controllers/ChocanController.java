/* 
    File: ChocanController.java
    Project: COSC-4360 Capstone Project Team #0
    University: McMurry University
    Course: COSC–4360 Spring 2021
    Instructor: Mr. Brozovic
    Programmer: Jacob Bremiller
    Created by: Jacob Bremiller
    Created: 3/2/2021
    Updated by: Jacob Bremiller
    Updated: 3/3/2021
    Compiler: Apache NetBeans IDE for Java SE
    Description: Provides the logic, view and model updates.
 */
package chocanon.Controllers;

import Logger.Log;
import chocanon.Main;
import chocanon.Models.Member;
import chocanon.Models.Provider;
import chocanon.Models.Service;
import chocanon.Models.Visit;
import chocanon.Views.EditAddMemberView;
import chocanon.Views.ManageMembersView;
import chocanon.Views.ManageProvidersView;
import chocanon.Views.MenuView;
import chocanon.Views.RecordsView;
import chocanon.Views.ReportsView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author jakeb
 */
public class ChocanController {
    //Data Attributes
    private Provider providers[] = null;
    private Member members[] = null;
    private Visit visits[] = null;
    private Service services[] = null;
    private Member editMember = null;
    
    //Views
    private Main mainView = null;
    final private MenuView menuView = new MenuView();
    final private ManageMembersView manageMembersView = new ManageMembersView();
    final private EditAddMemberView editAddMemberView = new EditAddMemberView();
    final private ManageProvidersView manageProvidersView = new ManageProvidersView();
    final private ReportsView reportsView = new ReportsView();
    final private RecordsView recordsView = new RecordsView();
    
    public ChocanController(Main mainView){
        //Set the main view
        this.mainView = mainView;
        
        //Set the button listeners
        menuView.setManageMembersButtonListener(new ManageMembersButtonListener());
        menuView.setManageProvidersButtonListener(new ManageProvidersButtonListener());
        menuView.setManageRecordsButtonListener(new ManageRecordsButtonListener());
        menuView.setManageReportsButtonListener(new ManageReportsButtonListener());
        menuView.setMenuBackButtonListener(new menuBackButtonListener());
        
        manageMembersView.setMemberSearchButtonListener(new MemberSearchListener());
        manageMembersView.setManageMembersBackButtonListener(new ManageMembersBackButtonListener());
        manageMembersView.setManageMembersAddButtonListener(new AddMemberButtonListener());
        manageMembersView.setManageMembersDeleteButtonListener(new DeleteMemberButtonListener());
        manageMembersView.setManageMembersEditButtonListener(new EditMemberButtonListener());
        
        editAddMemberView.setEditAddMemberCancelListener(new CancelMemberButtonListener());
        
        manageProvidersView.setProviderSearchButtonListener(new ProviderSearchListener());
        manageProvidersView.setManageProvidersBackButtonListener(new ManageProvidersBackButtonListener());
        
        reportsView.setReportsBackButtonListener(new ReportsBackButtonListener());
        recordsView.setRecordsBackButtonListener(new RecordsBackButtonListener());
        
        
        //Show our intro view
        this.mainView.setVisible(false);
        menuView.setVisible(true);
    }
   
    /* LISTENERS FOR VIEWS */
    
    //Listeners For MenuView View
    class ManageMembersButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            menuView.setVisible(false);
            manageMembersView.setVisible(true);
        }
    }
    class ManageProvidersButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            menuView.setVisible(false);
            manageProvidersView.setVisible(true);
        }
    }
    class ManageRecordsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           menuView.setVisible(false);
           recordsView.setVisible(true); 
        }
    }
    class ManageReportsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            menuView.setVisible(false);
            reportsView.setVisible(true);
        }
    }
    class menuBackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            menuView.setVisible(false);
            mainView.setVisible(true);
        }
    }
    
     //Listeners For ManageMembersView View
    class ManageMembersBackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            manageMembersView.setVisible(false);
            //Clear the search text and table
            manageMembersView.clearSearchText();
            manageMembersView.clearTable();
            members = null;
            menuView.setVisible(true);
        }
    }
    class MemberSearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userInput = manageMembersView.getUserInput();
            if(userInput.equals("")){
                manageMembersView.showMessageBox("Please provide search input");
            }else{
                //Clear the search text and table
                manageMembersView.clearSearchText();
                manageMembersView.clearTable();
                members = null;
                Log.debug("ChocanController", "Search Text: " + userInput);
                //Search database
                members = Member.getMembersByNameOrCardNumber(userInput);
                //Add members to the table
                for(int i = 0;i < members.length;i++){
                    manageMembersView.addRowMember(members[i]);
                }
            }
        }
    }
    
    class SaveMemberButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Saving member");
        }
    }
    class CancelMemberButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            editAddMemberView.setVisible(false);
            manageMembersView.setVisible(true);
            editAddMemberView.resetForm();
        }
    }
    class EditMemberButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int memberDatabaseId = manageMembersView.getMemberDatabaseIdSelected();
            if(memberDatabaseId == -1){
                manageMembersView.showMessageBox("Please select a member to edit!");
            }else{
                editAddMemberView.setVisible(true);
                editMember = Member.findMemberbyId(members, memberDatabaseId);
                if(editMember == null){
                    //Error. Something went wrong. We couldnt find the member in the members array.
                    manageMembersView.showMessageBox("Unexpected error, could not find the member!");
                    Log.error("ChocanController", "We couldnt find the member in the members array.");
                }else{
                    //Set everything in the editAddMemberView
                    editAddMemberView.setMemberFirstName(editMember.getFirstName());
                    editAddMemberView.setMemberLastName(editMember.getLastName());
                    editAddMemberView.setMemberStreetAddress(editMember.getStreetAddress());
                    editAddMemberView.setMemberCity(editMember.getCity());
                    editAddMemberView.setMemberZipCode(String.valueOf(editMember.getZipCode()));
                    editAddMemberView.setMemberEmailAddress(editMember.getEmailAddress());
                    editAddMemberView.setMemberCardNumber(String.valueOf(editMember.getCardNumber()));
                    editAddMemberView.setMemberState(Member.biTranslateState(editMember.getState(), "FULL"));
                    editAddMemberView.setMembershipStatus(editMember.getMembershipStatus());
                    
                    manageMembersView.setVisible(false);
                    editAddMemberView.setVisible(true);
                }
                Log.info("ChocanController", "Editing member: " + memberDatabaseId);
            }
        }
    }
    class DeleteMemberButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int memberDatabaseId = manageMembersView.getMemberDatabaseIdSelected();
            if(memberDatabaseId == -1){
                manageMembersView.showMessageBox("Please select a member to delete!");
            }else{
                if (manageMembersView.showDeleteDialog() == JOptionPane.YES_OPTION) {
                    if(manageMembersView.removeSelectedMember() == -1){
                        manageMembersView.showMessageBox("Please select a member to delete!");
                    }else{
                        Log.debug("Member", "Deleting member: " + memberDatabaseId);
                        if(Member.deleteMemberByDatabaseId(memberDatabaseId) > 0){
                            manageMembersView.showMessageBox("Member was successfully deleted!");
                        }else{
                            manageMembersView.showMessageBox("Member was unsuccessfully deleted!");
                        }
                    }
                } 
            }
        }
    }
    class AddMemberButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Adding member");
        }
    }
    
    
    
    
    //Listeners For ManageProvidersView View
    class ManageProvidersBackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            manageProvidersView.setVisible(false);
            menuView.setVisible(true);
        }
    }
    class ProviderSearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userInput = manageProvidersView.getUserInput();
            if(userInput.equals("")){
                manageProvidersView.showMessageBox("Please provide search input");
            }else{
                //Search database
                System.out.println(userInput);
            }
        }
    }
    
    //Listeners For RecordsView View
    class RecordsBackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            recordsView.setVisible(false);
            menuView.setVisible(true);
        }
    }
    
    //Listeners For ReportsView View
    class ReportsBackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            reportsView.setVisible(false);
            menuView.setVisible(true);
        }
    }
}
