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
import static java.lang.Integer.parseInt;
import java.text.ParseException;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

/**
 *
 * @author jakeb
 */
public class TerminalController {

    //Data attributes
    private Provider provider = null;
    private Member member = null;
    private Service service = null;
    private Visit visit = null;

    //Views
    private Main mainView = null;
    final private ProvideAServiceView provideServiceView = new ProvideAServiceView();
    final private ServiceDetailsView serviceDetailsView = new ServiceDetailsView();
    final private SwipeMemberCardView swipeMemberCardView = new SwipeMemberCardView();
    final private ValidateServiceCodeView validateServiceCodeView = new ValidateServiceCodeView();
    
    boolean dateValidate = true;

    public TerminalController(Main mainView) {
        //Set the main view
        this.mainView = mainView;

        //Set the button listeners
        provideServiceView.setSearchProviderButtonListener(new SearchProviderListener());
        provideServiceView.setBackProvideServiceButtonListener(new ProvideServiceBackButtonListener());
        swipeMemberCardView.setSearchButtonListener(new SearchMemberListener());
        swipeMemberCardView.setBackCardSwipeButtonListener(new CardSwipeBackButtonListener());
        serviceDetailsView.setServiceCodeTextListener(new ServiceCodeListener());
        serviceDetailsView.setServiceDetailsBackButtonListener(new ServiceDetailsBackButtonListener());
        serviceDetailsView.setSubmitBillChocanButtonListener(new SubmitBillChocanButtonListener());
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

        String currentEnteredServiceCode = "";

        @Override
        public void focusLost(FocusEvent e) {
            //We check to make sure we got a new service code when we lost focus.
            Log.debug("TerminalController", "Initial Service Code Text: " + currentEnteredServiceCode + " Service View Txt: " + serviceDetailsView.getServiceCodeTxt());
            if (serviceDetailsView.getServiceCodeTxt().equals(currentEnteredServiceCode) != true) {
                //Set the new service code
                currentEnteredServiceCode = serviceDetailsView.getServiceCodeTxt();
                Log.debug("TerminalController", "New Captured Service Code Text: " + currentEnteredServiceCode);
                //Look up the service code if the service code isnt found display error

                try {
                    parseInt(serviceDetailsView.getServiceCodeTxt());
                } catch (NumberFormatException a) {
                    serviceDetailsView.setMessageLabel("Please provide a valid service code", Color.RED);
                    return;
                }
                service = Service.getServiceByServiceCode(Integer.parseInt(currentEnteredServiceCode));
                if (serviceDetailsView.getServiceCodeTxt().length() != 6) {
                    serviceDetailsView.setMessageLabel("Please provide a valid service code", Color.RED);
                    return;
                }
                if (service == null) {
                    //Invalid service code
                    Log.info("TerminalController", "Set the MessageLabel to 'Invalid Service Code' and text color to red!");
                    serviceDetailsView.setMessageLabel("Invalid Service Code!", Color.RED);
                } else {
                    //Set validate service code view data - Service name will come from the database
                    Log.info("TerminalController", "Set the service code and service name in the ValidateServiceCode View");
                    validateServiceCodeView.setServiceCode(currentEnteredServiceCode);
                    Log.debug("TerminalController", "Service Code Set To: " + service.getServiceCode());
                    validateServiceCodeView.setServiceName(service.getServiceName());
                    Log.debug("TerminalController", "Service Name Set To: " + service.getServiceName());
                    
                    //Hide service details view and show validate service code view
                    Log.info("TerminalController", "Hiding ServiceDetails View");
                    serviceDetailsView.setVisible(false);
                    Log.info("TerminalController", "Showing ValidateServiceCode View");
                    validateServiceCodeView.setVisible(true);
                }
            }
        }

        @Override
        public void focusGained(FocusEvent e) {
            currentEnteredServiceCode = serviceDetailsView.getServiceCodeTxt();
            Log.info("TerminalController", "MessageLabel set to empty and text color black");
            serviceDetailsView.setMessageLabel("", Color.BLACK);
        }
    }

    class ServiceDetailsBackButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Log.info("TerminalController", "Hiding ServiceDetails View");
            serviceDetailsView.setVisible(false);
            serviceDetailsView.resetForm();
            Log.info("TerminalController", "Showing SwipeMemberCard View");
            swipeMemberCardView.setVisible(true);
        }
    }

    class SubmitBillChocanButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                /* Submit the visit data to the database*/
                if (serviceDetailsView.getDateOfService().equals("") == true) {
                    Log.info("TerminalController", "User did not provide a date of service");
                    serviceDetailsView.setMessageLabel("Please provide a date of service!", Color.RED);
                }
                
                //Date validations
                SimpleDateFormat sdfrmt = new SimpleDateFormat("MM-dd-yyyy");
                sdfrmt.setLenient(false);
                try {
                    sdfrmt.parse(serviceDetailsView.getDateOfService());
                    System.out.println(serviceDetailsView.getDateOfService() + " is valid date format");
                } catch (ParseException a) {
                    System.out.println(serviceDetailsView.getDateOfService() + " is Invalid Date format");
                    serviceDetailsView.setMessageLabel("Date Of Service must be MM-DD-YYYY!", Color.RED);
                    return;
                }
                
                if(serviceDetailsView.getAdditionalComment().length() > 100){
                    serviceDetailsView.setMessageLabel("Comment cant be longer than 100 characters!", Color.RED);
                    return;
                }
                
                if (service == null) {
                    Log.info("TerminalController", "User did not provide a service code");
                    serviceDetailsView.setMessageLabel("Please provide a service code!", Color.RED);
                } else if (provider != null && member != null && service != null && serviceDetailsView.getDateOfService().equals("") == false && dateValidate) {
                    //Save the visit to the database
                    visit = new Visit(provider, member, service, serviceDetailsView.getDateOfService(), serviceDetailsView.getAdditionalComment());
                    Log.debug("TerminalController", "Visit Saved To Database: " + visit.toString());
                    int dataSaved = visit.saveToDatabase();
                    if (dataSaved > 0) {
                        Log.info("TerminalController", "Visit Saved To Database successfully");
                        serviceDetailsView.showMessageBox("Visit was successfully sent to Chocan!");
                        //After save remove it from the controller
                        visit = null;
                        Log.info("TerminalController", "Removing the saved visit from the controller models");
                        //Hide service details and show swipe card
                        Log.info("TerminalController", "Hiding ServiceDetailsView");
                        serviceDetailsView.setVisible(false);
                        Log.info("TerminalController", "Resetting data fields in ServiceDetailsView");
                        serviceDetailsView.resetForm();
                        Log.info("TerminalController", "Showing SwipeMemberCardView");
                        swipeMemberCardView.setVisible(true);
                    } else {
                        Log.info("TerminalController", "Visit Saved To Database unsuccessfully");
                        serviceDetailsView.showMessageBox("Visit was unsuccessfully sent to Chocan!");
                    }
                }
            } catch (ParseException ex) {
                Log.error("TerminalController", ex);
            }
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

            try {
                parseInt(memberCardNumber);
            } catch (NumberFormatException a) {
                swipeMemberCardView.setMessageLabel("Please provide a valid card number", Color.RED);
                return;
            }

            if (memberCardNumber.length() != 9) {
                swipeMemberCardView.setMessageLabel("Please provide a valid card number", Color.RED);
                return;
            }

            if (memberCardNumber.equals("")) {
                Log.info("TerminalController", "User didnt enter anything in for card number. Asking user to provide a member card number");
                swipeMemberCardView.setMessageLabel("Please provide a member card number", Color.RED);
            } else {
                //Look up to see if the member number exists and the member is a active member in the database.
                member = Member.getMemberByCardNumber(Integer.parseInt(memberCardNumber));
                if (member == null) {
                    Log.info("TerminalController", "Member Card Number does not exist in the database at all");
                    swipeMemberCardView.setMessageLabel("Invalid Card Number", Color.RED);
                } else if (member.getMembershipStatus() == false) {
                    Log.info("TerminalController", "Member Card Number is suspened and displayed the amount they owe for the unpaid previous month.");
                    swipeMemberCardView.setMessageLabel("Member is suspended!", Color.RED); //Money is made up but we need to find out where to find this value
                }else if(member.getMembershipStatus() == true){
                    Log.info("TerminalController", "Card number exists and the member is active");
                    swipeMemberCardView.setMessageLabel("Validated", Color.GREEN);
                    if (swipeMemberCardView.showBillDialog() == JOptionPane.YES_OPTION) {
                        //Show the service details
                        Log.info("TerminalController", "Showing ServiceDetails View");
                        serviceDetailsView.setVisible(true);
                        Log.info("TerminalController", "Hiding SwipeMemberCard View");
                        //Hide the swipe card
                        swipeMemberCardView.setVisible(false);
                        //Clear the old SwipeMemberCardView label
                        swipeMemberCardView.setCardNumberText("");
                        swipeMemberCardView.setMessageLabel("", Color.BLACK);
                    }
                    Log.info("TerminalController", "Resetting the CardNumberTextBox back to empty");
                    //Reset for a new user input.
                    swipeMemberCardView.setCardNumberText("");
                    //Clear the old SwipeMemberCardView label
                    swipeMemberCardView.setMessageLabel("", Color.BLACK);
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
            try {
                parseInt(providerNumber);
            } catch (NumberFormatException a) {
                provideServiceView.showMessageBox("Please provide a valid provider number");
                return;
            }
            if (providerNumber.length() != 9) {
                provideServiceView.showMessageBox("Please provide a valid provider number");
                return;
            }

            if (providerNumber.equals("")) {
                Log.info("TerminalController", "Asking user to provide a provider number.");
                provideServiceView.showMessageBox("Please provide a provider number");
            } else {
                //Look up to see if the provider number exists in the database.Also populate the provider model instance with the provider that logged into the terminal
                provider = Provider.getProviderByProviderNumber(Integer.parseInt(providerNumber));
                if (provider != null) {
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
                } else {
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
