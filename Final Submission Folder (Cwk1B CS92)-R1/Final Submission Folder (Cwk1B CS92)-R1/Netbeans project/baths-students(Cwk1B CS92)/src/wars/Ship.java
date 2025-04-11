package wars;

import java.util.*;
import java.io.*;

/**
 * @author Hamza Hasan Memon (22084720)
 */
// Abstract class Ship
abstract class Ship implements Serializable{
    private String name;
    private String captain;
    private double commissionFee;
    private int battleSkill;
    private ShipState state;
    private String ShipType; 

    // Fields for participation in different encounters
    private boolean canParticipateInBlockade;
    private boolean canParticipateInBattle;
    private boolean canParticipateInSkirmish;
    
    /**Constructor of Class Ship requires the following parameters
     *@parm name,captain,battleSkill,ShipState state,ShipType
     */
    public Ship(String name, String captain, int battleSkill, ShipState state, String ShipType) {
        this.name = name;
        this.captain = captain;
        this.battleSkill = battleSkill;
        this.state = state;
        this.canParticipateInBattle = false;
        this.canParticipateInBlockade = false;
        this.canParticipateInSkirmish = false;
        this.ShipType = ShipType;
    }
    
    /** Overridden toString method to show ship details
     */
    @Override
    public String toString() {
        return "Ship - " +
                "name='" + name + '\'' +
                ", captain='" + captain + '\'' +
                ", commissionFee=" + commissionFee +
                ", battleSkill=" + battleSkill +
                ", state=" + state +
                ", canParticipateInBattle=" + canParticipateInBattle +
                ", canParticipateInBlockade=" + canParticipateInBlockade +
                ", canParticipateInSkirmish=" + canParticipateInSkirmish +
                ", ShipType="+ShipType;
    }
    
    /** Abstract method for calculating commission fee 
     */
    public abstract void calculateCommissionFee();
    
    /** Method to update if Ship can participate in Blockade 
    */
    public void setCanParticipateInBlockade(boolean canParticipateInBlockade) {
        this.canParticipateInBlockade = canParticipateInBlockade;
    }
    
    /** Method to update if Ship can participate in Battle 
    */
    public void setCanParticipateInBattle(boolean canParticipateInBattle) {
        this.canParticipateInBattle = canParticipateInBattle;
    }
    
    /** Method to update if Ship can participate in Skirmish 
    */
    public void setCanParticipateInSkirmish(boolean canParticipateInSkirmish) {
        this.canParticipateInSkirmish = canParticipateInSkirmish;
    }
    
    /** Method to set Commission Fee of the Ship
    */
    public void setCommissionFee(double commissionFee) {
        this.commissionFee = commissionFee;
    }
    
    /** Methods to check if Ship can participate in Blockade
    */
    public boolean participateInBlockade() {
        return canParticipateInBlockade;
    }
    
    /** Methods to check if Ship can participate in Battle
    */
    public boolean participateInBattle() {
        return canParticipateInBattle;
    }
    
    /** Methods to check if Ship can participate in Skirmish
    */
    public boolean participateInSkirmish() {
        return canParticipateInSkirmish;
    }
    
    /** Methods to check if Ship can participate in the given type of encounters
    *@parm type
    */
    public boolean participateInEncounter(String type) {
        switch (type) {
            case "BATTLE":
                return canParticipateInBattle;
            case "BLOCKADE":
                return canParticipateInBlockade;
            case "SKIRMISH":
                return canParticipateInSkirmish;
            default:
                return false;
        }
    }
    
    /** Method to check if Ship is in Reserve State
    */
    public boolean isInReserve() {
        return state == ShipState.RESERVE;
    }
    
    /** Method to check if Ship is in Active State
    */
    public boolean isActive() {
        return state == ShipState.ACTIVE;
    }
    
    /** Method to check if Ship is in Resting State
    */
    public boolean isResting() {
        return state == ShipState.RESTING;
    }
    
    /** Method to check if Ship is in Sunk State
    */
    public boolean isSunk() {
        return state == ShipState.SUNK;
    }
    
    /** Method to update the state of the Ship
    * @parm state
    */
    public void setState(ShipState state) {
        this.state = state;
    }
    
    /** Method to get name of the Ship
    */
   public String getName() {
        return name;
    }
   
    /** Method to get Commission Fee of the Ship
    */
   public double getCommissionFee() {
        return commissionFee;
    }
    
    /** Method to get Battle Skill of the Ship
    */
   public int getBattleSkill() {
        return battleSkill;
    }

}
