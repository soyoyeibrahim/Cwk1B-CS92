package wars;

import java.util.*;
import java.io.*;

/**
 * This class implements the behaviour expected from the BATHS system 
 * as required for 5COM2007 Cwk1B BATHS - Feb 2025.
 * 
 * @author A.A.Marczyk 
 * @version 16/02/25
 */

public class SeaBattles implements BATHS {
    private final String admiral;
    private double warChest;
    private final List<ship> reserveFleet;
    private final List<ship> squadron;
    private final List<encounter> encounters;

    // **************** Constructor **************** 
    public SeaBattles(String adm) {
        this.admiral = adm;
        this.warChest = 1000; // Starting balance
        this.reserveFleet = new ArrayList<>();
        this.squadron = new ArrayList<>();
        this.encounters = new ArrayList<>();

        setupShips();
        setupEncounters();
    }

    public SeaBattles(String admir, String filename) {  
        this(admir);
        readEncounters(filename);
    }

    // **************** To String **************** 
    @Override
    public String toString() {
        return "Admiral: " + admiral + 
               "\nWar Chest: " + warChest + 
               "\nDefeated: " + isDefeated() + 
               "\nReserve Fleet: " + getReserveFleet() + 
               "\nSquadron: " + getSquadron();
    }

    // **************** Game Status Methods **************** 
    @Override
    public boolean isDefeated() {
        return warChest <= 0 && squadron.isEmpty();
    }

    @Override
    public double getWarChest() {
        return warChest;
    }

    @Override
    public String getReserveFleet() {
        if (reserveFleet.isEmpty()) {
            return "No ships";
        }
        StringBuilder sb = new StringBuilder();
        for (ship ship : reserveFleet) {
            sb.append(ship.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String getSquadron() {
        if (squadron.isEmpty()) {
            return "No ships commissioned";
        }
        StringBuilder sb = new StringBuilder();
        for (ship ship : squadron) {
            sb.append(ship.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     *
     * @return
     */
    public String getSunkShips() {
        List<ship> sunkShips = new ArrayList<>();
        for (ship ship : squadron) {
            if (ship.isSunk()) {
                sunkShips.add(ship);
            }
        }
        return sunkShips.isEmpty() ? "No ships sunk yet" : sunkShips.toString();
    }

    /**
     *
     * @return
     */
    public String getAllShips() {
        return "Reserve Fleet:\n" + getReserveFleet() + 
               "\nSquadron:\n" + getSquadron();
    }

    /**
     *
     * @param name
     * @return
     */
    public String getShipDetails(String name) {
        for (ship ship : reserveFleet) {
            if (ship.getName().equalsIgnoreCase(name)) {
                return ship.toString();
            }
        }
        for (ship ship : squadron) {
            if (ship.getName().equalsIgnoreCase(name)) {
                return ship.toString();
            }
        }
        return "\nNo such ship";
    }

    // **************** Fleet Ship Management ****************  

    /**
     *
     * @param name
     * @return
     */
    public String commissionShip(String name) {
        for (ship ship : reserveFleet) {
            if (ship.getName().equalsIgnoreCase(name)) {
                if (warChest >= ship.getCommissionCost()) {
                    warChest -= ship.getCommissionCost();
                    reserveFleet.remove(ship);
                    squadron.add(ship);
                    ship.setActive();
                    return "Ship commissioned: " + ship.getName();
                } else {
                    return "Not enough money";
                }
            }
        }
        return "Ship not found";
    }

    /**
     *
     * @param name
     * @return
     */
    public boolean isInSquadron(String name) {
        for (ship ship : squadron) {
            if (ship.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param name
     * @return
     */
    public boolean decommissionShip(String name) {
        Iterator<ship> iterator = squadron.iterator();
        while (iterator.hasNext()) {
            ship ship = iterator.next();
            if (ship.getName().equalsIgnoreCase(name)) {
                iterator.remove();
                reserveFleet.add(ship);
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param name
     */
    public void restoreShip(String name) {
        for (ship ship : squadron) {
            if (ship.getName().equalsIgnoreCase(name)) {
                ship.setActive();
                return;
            }
        }
    }

    // **************** Encounter Handling **************** 

    /**
     *
     * @param num
     * @return
     */
    public boolean isEncounter(int num) {
        return encounters.stream().anyMatch(encounter -> encounter.getId() == num);
    }

    /**
     *
     * @param encNo
     * @return
     */
    public String fightEncounter(int encNo) {
        for (encounter encounter : encounters) {
            if (encounter.getId() == encNo) {
                if (squadron.isEmpty()) {
                    warChest -= encounter.getPrize();
                    return "Encounter lost as no ship available. War Chest: " + warChest;
                }

                ship bestShip = squadron.get(0);
                if (bestShip.getBattleSkill() >= encounter.getDifficulty()) {
                    warChest += encounter.getPrize();
                    bestShip.setResting();
                    return "Encounter won by " + bestShip.getName() + ". War Chest: " + warChest;
                } else {
                    warChest -= encounter.getPrize();
                    bestShip.setSunk();
                    return "Encounter lost on battle skill, " + bestShip.getName() + " sunk. War Chest: " + warChest;
                }
            }
        }
        return "No such encounter";
    }

    /**
     *
     * @param num
     * @return
     */
    public String getEncounter(int num) {
        for (encounter encounter : encounters) {
            if (encounter.getId() == num) {
                return encounter.toString();
            }
        }
        return "\nNo such encounter";
    }

    /**
     *
     * @return
     */
    public String getAllEncounters() {
        return encounters.isEmpty() ? "No encounters" : encounters.toString();
    }

    // **************** Setup Methods **************** 
    private void setupShips() {
        reserveFleet.add(new ship("HMS Victory", "Nelson", 90, 2000));
        reserveFleet.add(new ship("HMS Repulse", "Smith", 85, 1800));
    }

    private void setupEncounters() {
        encounters.add(new encounter(1, "Straits of Gibraltar", 80, 1000));
        encounters.add(new encounter(2, "Cape of Good Hope", 90, 1500));
    }

    public void readEncounters(String filename) {  
        // Implement file reading logic (if needed)
    }

    // **************** File Operations **************** 
    public void saveGame(String fname) {   
        // Implement file saving logic (if needed)
    }

    public SeaBattles loadGame(String fname) {   
        // Implement file loading logic (if needed)
        return null;
    }
}
