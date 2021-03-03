/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
        manageMembersView.setMemberSearchButtonListener(new MemberSearchListener());
        manageProvidersView.setProviderSearchButtonListener(new ProviderSearchListener());
                
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
    
     //Listeners For ManageMembersView View
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
}
