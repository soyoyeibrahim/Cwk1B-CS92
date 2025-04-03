/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wars;

/**
 *
 * @author is21abb
 */
public class WarChest {
    
    private double CurrentBalance;
    
    public WarChest (double intialPrize){
        this.CurrentBalance = intialPrize;
     
        
    }
    public void deductPounds(double amount){
        CurrentBalance-=amount;
    }
    public void addPounds(double amount) {
        CurrentBalance += amount;
    }

    public double getCurrentBalance() {
        return CurrentBalance;
    }
}
