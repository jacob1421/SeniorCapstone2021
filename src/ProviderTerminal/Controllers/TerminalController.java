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
import Logger.Log;
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
        Log.info("TerminalController", "Hiding the main view");
        this.mainView.setVisible(false);
        Log.info("TerminalController", "Showing the ProvideAService view");
        provideServiceView.setVisible(true);
    }
    
    /* LISTENERS FOR VIEWS */
  
    //Listeners For Validate ServiceCode View
    class CorrectServiceCodeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Log.info("TerminalController", "User Selected Correct Service Code Was Keyed In.");
            //Close the validate service code and show the service details.
            Log.info("TerminalController", "Hiding ServiceCodeView");
            validateServiceCodeView.setVisible(false);
            Log.info("TerminalController", "Showing ServiceDetailsView");
            serviceDetailsView.setVisible(true);
            //Set focus to comments
            Log.info("TerminalController", "Setting focus on additional comments");
            serviceDetailsView.setFocusAdditionalComments();
            
        }
    }
    
    class IncorrectServiceCodeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Log.info("TerminalController", "User Selected Incorrect Service Code Was Keyed In.");
            //Close the validate service code and show the service details.
            Log.info("TerminalController", "Hiding ValidateServiceCode View");
            validateServiceCodeView.setVisible(false);
            Log.info("TerminalController", "Showing ServiceDetails View");
            serviceDetailsView.setVisible(true);
            //Reset the service code and set the focus to the service code. 
            Log.info("TerminalController", "Resetting the service code and setting focus to the service code field");
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
            Log.debug("TerminalController", "Initial Service Code Text: " + currentEnteredServiceCode);
            if((!serviceDetailsView.getServiceCodeTxt().equals(currentEnteredServiceCode)) && (!currentEnteredServiceCode.equals(""))){
                //Set the new service code
                currentEnteredServiceCode = serviceDetailsView.getServiceCodeTxt();
                Log.debug("TerminalController", "New Captured Service Code Text: " + currentEnteredServiceCode);
                //Look up the service code if the service code isnt found display error
                if(true){
                    //Set validate service code view data - Service name will come from the database
                    Log.info("TerminalController", "Set the service code and service name in the ValidateServiceCode View");
                    validateServiceCodeView.setServiceCode(currentEnteredServiceCode);
                    Log.debug("TerminalController", "Service Code Set To: " + currentEnteredServiceCode);
                    validateServiceCodeView.setServiceName("Some Random Service Name");
                    Log.debug("TerminalController", "Service Name Set To: " + "UPDATE WHEN DB QUERY REQUEST IS ADDED");
                    
                    //Hide service details view and show validate service code view
                    Log.info("TerminalController", "Hiding ServiceDetails View");
                    serviceDetailsView.setVisible(false);
                    Log.info("TerminalController", "Showing ValidateServiceCode View");
                    validateServiceCodeView.setVisible(true);
                }else{
                   Log.info("TerminalController", "Set the MessageLabel to 'Invalid Service Code' and text color to red!");
                   serviceDetailsView.setMessageLabel("Invalid Service Code!", Color.RED);
                }
            }
        }

        @Override
        public void focusGained(FocusEvent e) {
            Log.info("TerminalController", "MessageLabel set to empty and text color black");
            serviceDetailsView.setMessageLabel("", Color.BLACK);
        }
    }
    
    
    //Listeners For SwipeMemberCardView View
    class CardSwipeBackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Log.info("TerminalController", "Hiding SwipeMemberCard View");
            swipeMemberCardView.setVisible(false);
            Log.info("TerminalController", "Showing ProvideService View");
            provideServiceView.setVisible(true);
            Log.info("TerminalController", "CardNumberTextBox set to empty and MessageLabel set to empty and text color black");
            swipeMemberCardView.setCardNumberText("");
            swipeMemberCardView.setMessageLabel("", Color.BLACK);
        }
    }
            
    class SearchMemberListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String memberCardNumber = swipeMemberCardView.getMemberCardNumber();
            Log.debug("TerminalController", "Captured Member Card Number: " + memberCardNumber);
            if(memberCardNumber.equals("")){
                Log.info("TerminalController", "User didnt enter anything in for card number. Asking user to provide a member card number");
                swipeMemberCardView.showMessageBox("Please provide a member card number");
            }else{
                //Look up to see if the member number exists and the member is a active member in the database.
                
                
                if(true){
                    //Member card number exists and the member is active
                    Log.info("TerminalController", "Card number exists and the member is active");
                    swipeMemberCardView.setMessageLabel("Validated", Color.GREEN);
                    if (swipeMemberCardView.showBillDialog() == JOptionPane.YES_OPTION) {
                        //Show the service details
                        Log.info("TerminalController", "Showing ServiceDetails View");
                        serviceDetailsView.setVisible(true);
                        Log.info("TerminalController", "Hiding SwipeMemberCard View");
                        //Hide the swipe card
                        swipeMemberCardView.setVisible(false);
                    }
                    Log.info("TerminalController", "Resetting the CardNumberTextBox back to empty");
                    //Reset for a new user input.
                    swipeMemberCardView.setCardNumberText("");
                }else{
                    //Member is valid but member does not have an active account
                    if(true){
                        Log.info("TerminalController", "Member Card Number is suspened and displayed the amount they owe for the unpaid previous month.");
                        swipeMemberCardView.setMessageLabel("Member suspended. Member owes $10.00 from previous month.", Color.RED); //Money is made up but we need to find out where to find this value
                    }else{
                        Log.info("TerminalController", "Member Card Number does not exist in the database at all");
                        swipeMemberCardView.setMessageLabel("Invalid", Color.RED);
                    }
                }
            }
        }
    }
    
    
    //Listeners For ProvideAService View
    class ProvideServiceBackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Log.info("TerminalController", "Hiding ProvideService View");
            provideServiceView.setVisible(false);
            Log.info("TerminalController", "Showing the Main View");
            mainView.setVisible(true);
            Log.info("TerminalController", "Resetting the ProviderNumberTextBox back to empty");
            //Clear old provider number
            provideServiceView.setProviderTextBox("");
        }
    }
    class SearchProviderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String providerNumber = provideServiceView.getProviderNumber();
            Log.debug("TerminalController", "Captured Provider Number: " + providerNumber);
            if(providerNumber.equals("")){
                Log.info("TerminalController", "Asking user to provide a provider number.");
                provideServiceView.showMessageBox("Please provide a provider number");
            }else{
                //Look up to see if the provider number exists in the database.Also populate the provider model instance with the provider that logged into the terminal
                provider = Provider.getProviderByProviderNumber(Integer.parseInt(providerNumber));
                if(provider != null){
                    //Found a provider
                    
                    Log.info("TerminalController", "Hiding ProvideService View");
                    //Hide the provide service view
                    provideServiceView.setVisible(false);
                    Log.info("TerminalController", "Showing SwipeMemberCard View");
                    //Show the next screen swipe card controller.
                    swipeMemberCardView.setVisible(true);
                    Log.info("TerminalController", "Setting the ProviderTextBox text to empty/nothing");
                    //Clear old provider number
                    provideServiceView.setProviderTextBox("");
                }else{
                    //Remove invalid provider number and set focus.
                    Log.info("TerminalController", "Invalid provider number. Not found in database.");
                    provideServiceView.showMessageBox("Invalid provider number.");
                    Log.info("TerminalController", "Setting focus on textbox for next provider number and clearing the providertextbox");
                    provideServiceView.setFocusProviderTextBox();
                    provideServiceView.setProviderTextBox("");
                }
            }
        }
    }
}
