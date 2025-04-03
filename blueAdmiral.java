/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wars;

import java.util.ArrayList;

/**
 *
 * @author is21abb
 */
public class blueAdmiral {
    private String name;
    private ArrayList<ship> squadron;
    private WarChest WarChest;
   

    public blueAdmiral(String name,WarChest WarChest){
        this.name = name;
        this.squadron = new ArrayList<>();
        this.WarChest = WarChest;
    }

    public boolean commission(ship ships) {
        if (WarChest.getCurrentBalance() >= ships.commissionFee) {
            WarChest.deductPounds(ships.commissionFee);
            squadron.add(ships);
            ships.commission();
            return true;
        }
        return false;
    }

    public boolean decommission(ship ships) {
        if (squadron.contains(ships)) {
            squadron.remove(ships);
            ships.decommission();
            WarChest.addPounds(ships.commissionFee / 2); // Refund half of the commission fee
            return true;
        }
        return false;
    }

    public double getWarChestBalance() {
        return WarChest.getCurrentBalance();
    }

    public int getReserveCount() {
        return squadron.size();
    }

    @Override
    public String toString() {
        return "Admiral " + name + " has " + squadron.size() + " ships in the squadron. War Chest: " + WarChest.getCurrentBalance();
    }
}
    

