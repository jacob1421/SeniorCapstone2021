/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chocanon.Controllers;

import chocanon.Models.Member;
import chocanon.Views.MemberView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author jakeb
 */
public class MemberController{
    private Member model;
    private MemberView view;
    
    public MemberController(Member model, MemberView view){
        this.model = model;
        this.view = view;
        view.setSearchButtonListener(new SearchListener());
    }

    //Setters for Model
    public void setMemberFirstName(String firstName){
        this.model.setFirstName(firstName);
    }
    
    //Setters for View
    public void updateView(){
        
    }
    
    //Inner Classes
    class SearchListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String userInput = "";
            try {
                userInput = view.getUserInput();
                System.out.println(userInput);
//                m_model.multiplyBy(userInput);
//                m_view.setTotal(m_model.getValue());
                
            } catch (NumberFormatException nfex) {
                //m_view.showError("Bad input: '" + userInput + "'");
            }
        }
    }
    
}
