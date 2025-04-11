package wars;
import java.util.*;
import java.io.*;/*
/**
 * @author Hamza Hasan Memon (22084720)
 */

/**
 * Sloop is a Sub-Class of Class Ship
 */
public class Sloop extends Ship {
    
     private boolean hasShipDoctor;
    
    /**Constructor of Class Sloop requires the following parameters
    *@parm name,captain,commissionFee,hasShipDoctor,battleSkill,ShipState state
    */
    public Sloop(String name, String captain, double commissionFee, boolean hasShipDoctor, int battleSkill, ShipState state) {
        super(name, captain, battleSkill, state,"Sloop"); // Sloops have a fixed battle skill of 5
        super.setCommissionFee(commissionFee); // Set commission fee
        this.hasShipDoctor = hasShipDoctor;
        super.setCanParticipateInBattle(true); // Sloops can always participate in Battle
        super.setCanParticipateInSkirmish(true); // Sloops can always participate in skirmishes
    }
    
    /** Overridden toString method to show Sloop's details
     */
    @Override
    public String toString() {
        return super.toString() + ", hasShipDoctor=" + hasShipDoctor;
    }
    
    /** Overridden calculateCommissionFee method to calculate Sloop's commission fees 
     */
    @Override
    public void calculateCommissionFee() {
        // Commission fee is already set in the constructor for Sloops
    }
    
}
