package wars;
import java.util.*;
import java.io.*;/*

/**
 * @author Hamza Hasan Memon (22084720)
 */

/**
 * Frigate is a Sub-Class of Class Ship
 */
public class Frigate extends Ship {
    private int numberOfCannons;
    private boolean hasPinnace;
    
    /**Constructor of Class Sloop requires the following parameters
    *@parm name,captain,numberOfCannons,hasPinnace,battleSkill,ShipState state
    */
    public Frigate(String name, String captain, int numberOfCannons, boolean hasPinnace, int battleSkill, ShipState state) {
        super(name, captain, battleSkill, state,"Frigate");
        this.numberOfCannons = numberOfCannons;
        this.hasPinnace = hasPinnace;
        calculateCommissionFee(); // Calculate commission fee
    }
    
    /** Overridden toString method to show Frigate's details
     */
     @Override
    public String toString() {
        return super.toString() + ", numberOfCannons=" + numberOfCannons + ", hasPinnace=" + hasPinnace;
    }
    
    /** Overridden calculateCommissionFee method to calculate Frigate's commission fees 
     */
      @Override
    public void calculateCommissionFee() {
        super.setCommissionFee(numberOfCannons * 10); // Commission fee based on number of cannons
        super.setCanParticipateInBattle(true);
        super.setCanParticipateInSkirmish(true);
        if (this.hasPinnace)
        {
            super.setCanParticipateInBlockade(true);
        }
    }

    
}