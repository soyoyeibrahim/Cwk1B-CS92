/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wars;

/**
 *
 * @author is21abb
 */
public class encounter {
    
    private int encounterNumber;
    private String location;
    private int skill;
    private double reward;
    private double prize;

    public encounter(int encounterNumber, String location, int skill, double reward) {
        this.encounterNumber = encounterNumber;
        this.location = location;
        this.skill = skill;
        this.reward = reward;
    }

    public boolean isShipEligible(ship ship) {
        return ship.getState().equals("Active") && ship.getBattleSkill() >= skill;
    }

    public void winEncounter(ship ship) {
        ship.decommission(); // Ship rests after winning
        System.out.println("Ship " + ship.getName() + " won the encounter at " + location + " and gained " + reward);
    }

    public void loseEncounter(ship ship) {
        ship.state = "Sunk";
        System.out.println("Ship " + ship.getName() + " lost the encounter and is sunk.");
    }

    public String getDetails() {
        return "Encounter " + encounterNumber + " at " + location + " requires skill: " + skill + " and reward: " + reward;
    }

    int getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public double getPrize() {  // 👈 This is where getPrize() is implemented!
        return prize;
    }

    int getDifficulty() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
