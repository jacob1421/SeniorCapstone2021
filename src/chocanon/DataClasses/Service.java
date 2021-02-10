/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chocanon.DataClasses;
import java.math.BigDecimal;
/**
 *
 * @author jakeb
 */

public class Service {
    //Data attributes
    private String name = "";
    private int code = 0;
    private BigDecimal fee = null;
    
    //Constructor
    public Service(String name, int code, String fee){
        this.name = name;
        this.code = code;
        this.fee = new BigDecimal(fee);
    }
    
    public String getServiceName(){
        return this.name;
    }
    
    public int getServiceCode(){
        return this.code;
    }
    
    public BigDecimal getServiceFee(){
        return this.fee;
    }
        
}
