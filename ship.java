/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wars;

/**
 *
 * @author is21abb
 */
public class ship {
    private String name;
    private String captain;
    private int battleSkill;
    double commissionFee;
    String state;
    
    public ship(String name, String captain, int battleSkill, double commissionFee) {
        this.name = name;
        this.captain = captain;
        this.battleSkill = battleSkill;
        this.commissionFee = commissionFee;
        this.state = "Active";
    }
    public String getName() {
        return name;
    }
    
    public String getState() {
        return state;
    }

    public void commission() {
        state = "Active";
    }

    public void decommission() {
        state = "Resting";
    }

    public boolean isAvailableForEncounter() {
        return "Active".equals(state);
    }

    public String getDetails() {
        return "Ship: " + name + ", Captain: " + captain + ", Battle Skill: " + battleSkill + ", State: " + state;
    }

    int getBattleSkill() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean isSunk() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    double getCommissionCost() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setActive() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setResting() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setSunk() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

