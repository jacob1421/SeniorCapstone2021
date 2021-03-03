/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProviderTerminal.Controllers;

import ProviderTerminal.Views.ProvideAServiceView;
import ProviderTerminal.Views.ServiceDetailsView;
import ProviderTerminal.Views.SwipeMemberCardView;
import ProviderTerminal.Views.ValidateServiceCodeView;
import chocanon.Main;
import chocanon.Models.Member;
import chocanon.Models.Provider;
import chocanon.Models.Service;
import chocanon.Models.Visit;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JOptionPane;

/**
 *
 * @author jakeb
 */
public class TerminalController {
    //Data attributes
    Provider provider = null;
    Member member = null;
    Service service = null; 
    Visit visit = null;
    
    //Views
    private Main mainView = null;
    final private ProvideAServiceView provideServiceView = new ProvideAServiceView();
    final private ServiceDetailsView serviceDetailsView = new ServiceDetailsView();
    final private SwipeMemberCardView swipeMemberCardView = new SwipeMemberCardView();
    final private ValidateServiceCodeView validateServiceCodeView = new ValidateServiceCodeView();
  
    public TerminalController(Main mainView){
        //Set the main view
        this.mainView = mainView;
        
        //Set the button listeners
        provideServiceView.setSearchProviderButtonListener(new SearchProviderListener());
        provideServiceView.setBackProvideServiceButtonListener(new ProvideServiceBackButtonListener());
        swipeMemberCardView.setSearchButtonListener(new SearchMemberListener());
        swipeMemberCardView.setBackCardSwipeButtonListener(new CardSwipeBackButtonListener());
        serviceDetailsView.setServiceCodeTextListener(new ServiceCodeListener());
        validateServiceCodeView.setCorrectServiceCodeButtonListener(new CorrectServiceCodeListener());
        validateServiceCodeView.setIncorrectServiceCodeButtonListener(new IncorrectServiceCodeListener());
        
        //Show our intro view
        this.mainView.setVisible(false);
        provideServiceView.setVisible(true);
    }
    
    /* LISTENERS FOR VIEWS */
  
    //Listeners For Validate ServiceCode View
    class CorrectServiceCodeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Close the validate service code and show the service details.
            validateServiceCodeView.setVisible(false);
            serviceDetailsView.setVisible(true);
            //Set focus to comments
            serviceDetailsView.setFocusAdditionalComments();
        }
    }
    
    class IncorrectServiceCodeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Close the validate service code and show the service details.
            validateServiceCodeView.setVisible(false);
            serviceDetailsView.setVisible(true);
            //Reset the service code and set the focus to the service code. 
            serviceDetailsView.setServiceCodeTxt("");
            serviceDetailsView.setFocusServiceCode();
        }
    }
    
    
    //Listeners For ServiceDetails View
    class ServiceCodeListener implements FocusListener {
        String currentEnteredServiceCode = serviceDetailsView.getServiceCodeTxt();
        @Override
        public void focusLost(FocusEvent e) {
            //We check to make sure we got a new service code when we lost focus.
            if((!serviceDetailsView.getServiceCodeTxt().equals(currentEnteredServiceCode)) && (!currentEnteredServiceCode.equals(""))){
                //Set the new service code
                currentEnteredServiceCode = serviceDetailsView.getServiceCodeTxt();
                //Look up the service code if the service code isnt found display error
                if(true){
                    //Set validate service code view data - Service code name will come from the database
                    validateServiceCodeView.setServiceCode(currentEnteredServiceCode);
                    validateServiceCodeView.setServiceName("Some Random Service Name");
                    
                    //Hide service details view and show validate service code view
                    serviceDetailsView.setVisible(false);
                    validateServiceCodeView.setVisible(true);
                }else{
                   serviceDetailsView.setMessageLabel("Invalid Service Code!", Color.RED);
                }
                System.out.println(currentEnteredServiceCode);
            }
        }

        @Override
        public void focusGained(FocusEvent e) {
            serviceDetailsView.setMessageLabel("", Color.BLACK);
        }
    }
    
    
    //Listeners For SwipeMemberCardView View
    class CardSwipeBackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            swipeMemberCardView.setVisible(false);
            provideServiceView.setVisible(true);
            swipeMemberCardView.setCardNumberText("");
            swipeMemberCardView.setMessageLabel("", Color.BLACK);
        }
    }
            
    class SearchMemberListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String memberCardNumber = swipeMemberCardView.getMemberCardNumber();
            if(memberCardNumber.equals("")){
                swipeMemberCardView.showMessageBox("Please provide a member card number");
            }else{
                //Look up to see if the member number exists and the member is a active member in the database.
                
                
                if(true){
                    //Member card number exists and the member is active
                    swipeMemberCardView.setMessageLabel("Validated", Color.GREEN);
                    if (swipeMemberCardView.showBillDialog() == JOptionPane.YES_OPTION) {
                        //Show the service details
                        serviceDetailsView.setVisible(true);
                        //Hide the swipe card
                        swipeMemberCardView.setVisible(false);
                    }
                    //Reset for a new user input.
                    swipeMemberCardView.setCardNumberText("");
                }else{
                    //Member is valid but member does not have an active account
                    if(true){
                        swipeMemberCardView.setMessageLabel("Member suspended. Member owes $10.00 from previous month.", Color.RED); //Money is made up but we need to find out where to find this value
                    }else{
                        swipeMemberCardView.setMessageLabel("Invalid", Color.RED);
                    }
                }
                System.out.println(memberCardNumber);
            }
        }
    }
    
    
    //Listeners For ProvideAService View
    class ProvideServiceBackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            provideServiceView.setVisible(false);
            mainView.setVisible(true);
            //Clear old provider number
            provideServiceView.setProviderTextBox("");
        }
    }
    class SearchProviderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String providerNumber = provideServiceView.getProviderNumber();
            if(providerNumber.equals("")){
                provideServiceView.showMessageBox("Please provide a provider number");
            }else{
                //Look up to see if the provider number exists in the database.
                if(true){
                    //We need to populate the provider with the provider that logged into the terminal
                    provider = Provider.getProviderByProviderId(Integer.parseInt(providerNumber));
                    
                    //Hide the provide service view
                    provideServiceView.setVisible(false);
                    //Show the next screen swipe card controller.
                    swipeMemberCardView.setVisible(true);
                    //Clear old provider number
                    provideServiceView.setProviderTextBox("");
                }else{
                    provideServiceView.showMessageBox("Invalid provider number.");
                }
                System.out.println(providerNumber);
            }
        }
    }
}
