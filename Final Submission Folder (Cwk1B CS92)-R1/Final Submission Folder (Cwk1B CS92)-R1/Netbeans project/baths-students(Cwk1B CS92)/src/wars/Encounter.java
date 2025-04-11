package wars;
import java.util.*;
import java.io.*;

/**
 * @author Hamza Hasan Memon (22084720)
 */

// Encounter class
public class Encounter implements Serializable{
    // Fields
    private int encounterNo; 
    private EncounterType type;
    private String location;
    private int battleSkillRequired;
    private double prizeMoney;

    /**Constructor of Class Encounter requires the following parameters
     *@parm encounterNo,EncounterType type,location,battleSkillRequired,prizeMoney
     */
    public Encounter(int encounterNo, EncounterType type, String location, int battleSkillRequired, double prizeMoney) {
        this.encounterNo = encounterNo;
        this.type = type;
        this.location = location;
        this.battleSkillRequired = battleSkillRequired;
        this.prizeMoney = prizeMoney;
    }

    /** Overridden toString method to show Encounter details
     */
    @Override
    public String toString() {
        return "Encounter -- "+
                "Encounter No: " + encounterNo +
                "\nType: " + type +
                "\nLocation: " + location +
                "\nBattle Skill Required: " + battleSkillRequired +
                "\nPrize Money: $" + prizeMoney;
    }
    
    
    /** Method to get Encounter Number
    */
    public int getEncounterNo() {
        return encounterNo;
    }
    
    /** Method to set Encounter Number
    */
    //public void setEncounterNo(int encounterNo) {
        //this.encounterNo = encounterNo;
    //}
    
    /** Method to get type of Encounter 
    */
    public EncounterType getType() {
        return type;
    }
    
    /** Method to return the name of the enum constant as a String
    */
    public String getTypeAsString() {
        return type.name(); // Returns the name of the enum constant as a String
    }
    
    /** Method to set type of Encounter
    * @parm EncounterType type 
    */
    public void setType(EncounterType type) {
        this.type = type;
    }
    
    /** Method to get location of Encounter
    */
    public String getLocation() {
        return location;
    }
    
    /** Method to set location of Encounter
    * @parm location
    */
    public void setLocation(String location) {
        this.location = location;
    }
    
    /** Method to get BattleSkill Required for Encounter
    */
    public int getBattleSkillRequired() {
        return battleSkillRequired;
    }
    
    /** Method to set BattleSkill Required for Encounter
    * @parm battleSkillRequired
    */
    public void setBattleSkillRequired(int battleSkillRequired) {
        this.battleSkillRequired = battleSkillRequired;
    }
    
    /** Method to get Prize Money of Encounter
    */
    public double getPrizeMoney() {
        return prizeMoney;
    }
    
    /** Method to set Prize Money of Encounter
    * @parm prizeMoney 
    */
    public void setPrizeMoney(double prizeMoney) {
        this.prizeMoney = prizeMoney;
    }

    

}

