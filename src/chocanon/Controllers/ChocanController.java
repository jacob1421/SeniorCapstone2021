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

import chocanon.Main;
import chocanon.Models.Member;
import chocanon.Models.Provider;
import chocanon.Models.Service;
import chocanon.Models.Visit;
import chocanon.Views.ManageMemberView;
import chocanon.Views.ManageProvidersView;
import chocanon.Views.MenuView;
import chocanon.Views.RecordsView;
import chocanon.Views.ReportsView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author jakeb
 */
public class ChocanController {
    //Data Attributes
    Provider providers[] = null;
    Member members[] = null;
    Visit visits[] = null;
    Service services[] = null;
    
    
    //Views
    private Main mainView = null;
    final private MenuView menuView = new MenuView();
    final private ManageMemberView manageMembersView = new ManageMemberView();
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
                //Search database
                System.out.println(userInput);
            }
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
