/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProviderTerminal.Controllers;

import ProviderTerminal.Views.ProvideAService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author jakeb
 */
public class ProvideAServiceController {
    private ProvideAService view = new ProvideAService();
    
    public ProvideAServiceController(){
        this.view.setVisible(true);
        view.setSearchButtonListener(new SearchListener());
    }
    
    //Inner Classes
    class SearchListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String userInput = view.getUserInput();
            if(userInput.equals("")){
                view.showMessageBox("Please provide a provider number");
            }else{
                //Look up to see if the provider number exists in the database.
                System.out.println(userInput);
            }
        }
    }
}
