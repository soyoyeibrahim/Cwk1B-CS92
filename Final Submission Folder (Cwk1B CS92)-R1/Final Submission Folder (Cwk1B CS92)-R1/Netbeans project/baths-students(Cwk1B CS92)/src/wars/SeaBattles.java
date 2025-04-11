package wars;
import java.util.*;
import java.io.*;

/**
 * This class implements the behaviour expected from the BATHS
 system as required for 5COM2007 Cwk1B BATHS - Feb 2025
 * 
 * @author A.A.Marczyk 
 * @version 16/02/25
 */

public class SeaBattles implements BATHS 
{
    // may have one HashMap and select on stat

    private String admiral;
    private double warChest;
    private List<Ship> allships;
    private List<Encounter> allencounters;
    


//**************** BATHS ************************** 
    /** Constructor requires the name of the admiral
     * @param admiral the name of the admiral
     */  
    public SeaBattles(String admiralname)
    {
      admiral=admiralname;
      warChest=1000;
      setupShips();
      setupEncounters();
    }
    
    /** Constructor requires the name of the admiral and the
     * name of the file storing encounters
     * @param admiral the name of the admiral
     * @param filename name of file storing encounters
     */  
    public SeaBattles(String admiralname, String filename)  //Task 3
    {
      admiral=admiralname;
      warChest=1000;
       setupShips();
       // setupEncounters();
       // uncomment for testing Task 
       readEncounters(filename);
    }
    
    
    /**Returns a String representation of the state of the game,including the name of the 
     * admiral, state of the warChest,whether defeated or not, and the ships currently in 
     * the squadron,(or, "No ships" if squadron is empty), ships in the reserve fleet
     * @return a String representation of the state of the game,including the name of the 
     * admiral, state of the warChest,whether defeated or not, and the ships currently in 
     * the squadron,(or, "No ships" if squadron is empty), ships in the reserve fleet
     **/
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Admiral and warChest info
        sb.append("Admiral: ").append(admiral).append("\n");
        sb.append("War Chest: GBP").append(warChest).append("\n");

        // Defeat status
        if(isDefeated())
        {
            sb.append("Defeated: Yes").append("\n");
        }
        else
        {
            sb.append("Defeated: No").append("\n");
        }
        

        // Ships in squadron (ACTIVE ships)
        sb.append("Squadron: \n");
        boolean hasActiveShips = false;
        for (Ship ship : allships) {
            if (ship.isActive()) {
                sb.append("  ").append(ship.toString()).append("\n");
                hasActiveShips = true;
            }
        }
        if (!hasActiveShips) {
            sb.append("No ships in Squadron\n");
        }

        // Ships in reserve fleet (RESERVE ships)
        sb.append("Reserve Fleet: \n");
        boolean hasReserveShips = false;
        for (Ship ship : allships) {
            if (ship.isInReserve()) {
                sb.append("  ").append(ship.toString()).append("\n");
                hasReserveShips = true;
            }
        }
        if (!hasReserveShips) {
            sb.append("  No ships in Reserve\n");
        }
        
        // Ships sunk during battle (SUNK ships)
        sb.append("Sunk: \n");
        boolean hasSunkShips = false;
        for (Ship ship : allships) {
            if (ship.isSunk()) {
                sb.append("  ").append(ship.toString()).append("\n");
                hasSunkShips = true;
            }
        }
        if (!hasSunkShips) {
            sb.append("  No Sunked ships\n");
        }
        
           // Ships Rested after winning battle (RESTED ships)
        sb.append("Resting: \n");
        boolean hasRestedShips = false;
        for (Ship ship : allships) {
            if (ship.isResting()) {
                sb.append("  ").append(ship.toString()).append("\n");
                hasRestedShips = true;
            }
        }
        if (!hasRestedShips) {
            sb.append("  No Resting ships\n");
        }

        return sb.toString();
    }
    
    
    /** returns true if War Chest <=0 and the admiral's squadron has no ships which 
     * can be retired. 
     * @returns true if War Chest <=0 and the admiral's fleet has no ships 
     * which can be retired. 
     */
    public boolean isDefeated()
    {
       if (warChest <= 0) {
            for (Ship ship : allships) {
                if (ship.isActive()) {
                    return false; // There is at least one active ship
                }
            }
            return true; // No active ships and war chest is empty
        }
        return false;
    }
    
    /** returns the amount of money in the War Chest
     * @returns the amount of money in the War Chest
     */
    public double getWarChest()
    {
        return warChest;
    }
    
    
    /**Returns a String representation of all ships in the reserve fleet
     * @return a String representation of all ships in the reserve fleet
     **/
    public String getReserveFleet()
    {   //assumes reserves is a Hashmap
       
        StringBuilder reserveFleet = new StringBuilder();
        for (Ship ship : allships) {
            if (ship.isInReserve()) {
                reserveFleet.append(ship.toString()).append("\n");
            }
        }
        return reserveFleet.length() > 0 ? reserveFleet.toString() : "No ships in reserve";
     
    }
    
    /**Returns a String representation of the ships in the admiral's squadron
     * or the message "No ships commissioned"
     * @return a String representation of the ships in the admiral's fleet
     **/
    public String getSquadron()
    {
          
        StringBuilder squadron = new StringBuilder();
        for (Ship ship : allships) {
            if (ship.isActive()) {
                squadron.append(ship.toString()).append("\n");
            }
        }
        return squadron.length() > 0 ? squadron.toString() : "No ships commissioned";
    }
    
    /**Returns a String representation of the ships sunk (or "no ships sunk yet")
     * @return a String representation of the ships sunkReturns a String representation of the ships sunk (or "no ships sunk yet")
     **/
    public String getSunkShips()
    {
       StringBuilder sunkShips = new StringBuilder();
        for (Ship ship : allships) {
            if (ship.isSunk()) {
                sunkShips.append(ship.toString()).append("\n");
            }
        }
        return sunkShips.length() > 0 ? sunkShips.toString() : "No ships sunk yet";
    }
    
    /**Returns a String representation of the all ships in the game
     * including their status
     * @return a String representation of the ships in the game
     **/
    public String getAllShips()
    {
  
        StringBuilder allShips = new StringBuilder();
        for (Ship ship : allships) {
            allShips.append(ship.toString()).append("\n");
        }
        return allShips.length() > 0 ? allShips.toString() : "No ships";
    }
    
    
    /** Returns details of any ship with the given name
     * @return details of any ship with the given name
     **/
    public String getShipDetails(String name)
    {
         for (Ship ship : allships) {
            if (ship.toString().contains(name)) {
                return ship.toString();
            }
        }
        return "Ship not found";
    }     
 
    // ***************** Fleet Ships ************************   
    /** Allows a ship to be comissioned to the admiral's squadron, if there 
     * is enough money in the War Chest for the commission fee.The ship's 
     * state is set to "active"
     * @param nme represents the name of the ship
     * @return "Ship commissioned" if ship is commissioned, "Not found" if 
     * ship not found, "Not available" if ship is not in the fleet, "Not 
     * enough money" if not enough money in the warChest
     **/        
    public String commissionShip(String nme)
    {
        for (Ship ship : allships)
        {
            if (ship.getName().equals(nme))
            {
                if (ship.isInReserve())
                {
                    if (warChest >= ship.getCommissionFee())
                    {
                        ship.setState(ShipState.ACTIVE);
                        warChest = warChest - ship.getCommissionFee();
                        return "Ship commissioned. Commission Fee deducted from WarChest ...";
                    }
                    else
                    {
                    return "Not enough money: Commission Fee is greater than amount in warChest";
                    }
                }
                else
                {
                    return "Ship is not in Reserve State and hence Not available for Commission";
                }
            }
                
        }
        return "- Ship Not found.";
    }
        
    /** Returns true if the ship with the name is in the admiral's squadron, false otherwise.
     * @param nme is the name of the ship
     * @return returns true if the ship with the name is in the admiral's squadron, false otherwise.
     **/
    public boolean isInSquadron(String nme)
    {
         for (Ship ship : allships)
         {
            if (ship.getName().equals(nme))
            {
                if (ship.isActive())
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }    
         }
         return false;
    }
    
    /** Decommissions a ship from the squadron to the reserve fleet (if they are in the squadron)
     * pre-condition: isInSquadron(nme)
     * @param nme is the name of the ship
     * @return true if ship decommissioned, else false
     **/
    public boolean decommissionShip(String nme)
    {
        for (Ship ship : allships)
        {
            if (ship.getName().equals(nme))
            {
                if (ship.isActive() || ship.isResting())
                {
                   ship.setState(ShipState.RESERVE);
                   warChest += ship.getCommissionFee()/2;
                   return true;
                }
                else
                {
                    return false;
                }
            }
        }
        return false;
    }
    
  
    /**Restores a ship to the squadron by setting their state to ACTIVE 
     * @param the name of the ship to be restored
     */
    public void restoreShip(String ref)
    {
        for (Ship ship : allships) {
            if (ship.getName().equals(ref) && ship.isResting()) {
                ship.setState(ShipState.ACTIVE);
            }
        }
        
    }
    
//**********************Encounters************************* 
    /** returns true if the number represents a encounter
     * @param num is the reference number of the encounter
     * @returns true if the reference number represents a encounter, else false
     **/
     public boolean isEncounter(int num)
     {
         for (Encounter encounter : allencounters) {
            if (encounter.getEncounterNo() == num) {
                return true;
            }
        }
        return false;
     }
     
     
    /** Retrieves the encounter represented by the encounter 
      * number.Finds a ship from the fleet which can fight the 
      * encounter. The results of fighting an encounter will be 
      * one of the following: Encounter won by...(ship reference and name) 
      *  add prize money to War Chest and ship's state is set to RESTING,  Encounter 
      * lost as no ship available  deduct prize money from the War Chest,Encounter 
      * lost on battle skill level and (ship name) sunk/lost" - deduct prize money from 
      * War Chest and ship state set to sunk. If an encounter is lost and admiral 
      * is completely defeated, add �You have been defeated  to the output.
      * Ensure that the state of the war chest is also included in the return message.
      * @param encNo is the number of the encounter
      * @return a String showing the result of fighting the encounter
      */ 
    public String fightEncounter(int encNo)
    {
       if(isDefeated())
       {
            return "Defeated: You have lost all your money and have no ships to decommission, you have lost your Job";
       }
       
       Encounter encounter = null;
        for (Encounter e : allencounters) {
            if (e.getEncounterNo() == encNo) {
                encounter = e;
                break;
            }
        }

        if (encounter == null) {
            return "No such Encounter exists!";
        }
            
        Ship availableShip = null;
        for (Ship ship : allships) {
            if (ship.isActive() && ship.participateInEncounter(encounter.getTypeAsString())) {
                if(ship.getBattleSkill() >= encounter.getBattleSkillRequired())
                {
                    availableShip = ship;
                    break;
                }
                else
                {
                    ship.setState(ShipState.SUNK);
                     warChest -= encounter.getPrizeMoney();
                     if(isDefeated())
                     {
                        return "Encounter lost on battle skill. War Chest: " + warChest +"Defeated: You have lost all your money and have no ships to decommission, you have lost your Job";
                     }
                     else
                     {
                    return "Encounter lost on battle skill. War Chest: " + warChest;
                     }
                    
                }
            }
        }
        
        if (availableShip == null) {
            warChest -= encounter.getPrizeMoney();
            if(isDefeated())
                     {
                        return "Encounter lost, no ship available. War Chest: " + warChest +"Defeated: You have lost all your money and have no ships to decommission, you have lost your Job";
                     }
                     else
                     {
                        return "Encounter lost, no ship available. War Chest: " + warChest;
                     }
            } else {
            warChest += encounter.getPrizeMoney();
            availableShip.setState(ShipState.RESTING);
            return "Encounter won by " + availableShip.toString() + ". War Chest: " + warChest;
        }
    }

    /** Provides a String representation of an encounter given by 
     * the encounter number
     * @param num the number of the encounter
     * @return returns a String representation of a encounter given by 
     * the encounter number
     **/
    public String getEncounter(int num)
    {
        for (Encounter encounter : allencounters) {
            if (encounter.getEncounterNo() == num) {
                return encounter.toString();
            }
        }
        return "\nNo such encounter";
    }
    
    /** Provides a String representation of all encounters 
     * @return returns a String representation of all encounters
     **/
    public String getAllEncounters()
    {
 
      StringBuilder allEncounters = new StringBuilder();
        for (Encounter encounter : allencounters) {
            allEncounters.append(encounter.toString()).append("\n");
        }
        return allEncounters.length() > 0 ? allEncounters.toString() : "No encounters";
    }
    

    //****************** private methods for Task 4 functionality*******************
    //*******************************************************************************
     private void setupShips()
     {
       
       Ship M1 = new ManOWar("Victory","Alan Aikin",3,30,3,ShipState.RESERVE);
       Ship F1 = new Frigate("Sophie","Ben Baggins",16,true,8,ShipState.RESERVE);
       Ship M2 = new ManOWar("Endeavour","Col Cannon",2,20,4,ShipState.RESERVE);
       Ship S1 = new Sloop("Arrow","Dan Dare",150,true,5,ShipState.RESERVE);
       Ship M3 = new ManOWar("Belerophon","Ed Evans",3,50,8,ShipState.RESERVE);
       Ship F2 = new Frigate("Surprise","Fred Fox",10,false,6,ShipState.RESERVE);
       Ship F3 = new Frigate("Jupiter","Gil Gamage",20,false,7,ShipState.RESERVE);
       Ship S2 = new Sloop("Paris","Hal Henry",200,true,5,ShipState.RESERVE);
       Ship S3 = new Sloop("Beast","Ian Idle",400,false,5,ShipState.RESERVE);
       Ship S4 = new Sloop("Athena","John Jones",100,true,5,ShipState.RESERVE);
       
       allships = new ArrayList<Ship>();
       
       allships.add(M1);
       allships.add(F1);
       allships.add(M2);
       allships.add(S1);
       allships.add(M3);
       allships.add(F2);
       allships.add(F3);
       allships.add(S2);
       allships.add(S3);
       allships.add(S4);
               

     }
     
    private void setupEncounters()
    {
        Encounter E1 = new Encounter(1,EncounterType.BATTLE,"Trafalgar",3,300);
        Encounter E2 = new Encounter(2,EncounterType.SKIRMISH,"Belle Isle",3,120);
        Encounter E3 = new Encounter(3,EncounterType.BLOCKADE,"Brest",3,150);
        Encounter E4 = new Encounter(4,EncounterType.BATTLE,"St Malo",9,200);
        Encounter E5 = new Encounter(5,EncounterType.BLOCKADE,"Dieppe",7,90);
        Encounter E6 = new Encounter(6,EncounterType.SKIRMISH,"Jersey",8,45);
        Encounter E7 = new Encounter(7,EncounterType.BLOCKADE,"Nantes",6,130);
        Encounter E8 = new Encounter(8,EncounterType.BATTLE,"Finisterre",4,100);
        Encounter E9 = new Encounter(9,EncounterType.SKIRMISH,"Biscay",5,200);
        Encounter E10 = new Encounter(10,EncounterType.BATTLE,"Cadiz",1,250);
        
        allencounters = new ArrayList<Encounter>();
         
        allencounters.add(E1);
        allencounters.add(E2);
        allencounters.add(E3);
        allencounters.add(E4);
        allencounters.add(E5);
        allencounters.add(E6);
        allencounters.add(E7);
        allencounters.add(E8);
        allencounters.add(E9);
        allencounters.add(E10);
        

    }
        
    // Useful private methods to "get" objects from collections/maps

    //*******************************************************************************
    //*******************************************************************************
  
    /************************ Task 3 ************************************************/

    
    //******************************** Task 3.5 **********************************
    /** reads data about encounters from a text file and stores in collection of 
     * encounters.Data in the file is editable
     * @param fileName name of the file to be read
     */
    public void readEncounters(String filename) {
        //List<Encounter> encounters = new ArrayList<>();
        
        allencounters = new ArrayList<Encounter>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int count=1;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    // Parse data into appropriate types and create an Encounter object
                    int encounterNo = count;
                    EncounterType type = EncounterType.valueOf(data[0].trim());
                    String location = data[1].trim();
                    int battleSkillRequired = Integer.parseInt(data[2].trim());
                    double prizeMoney = Double.parseDouble(data[3].trim());

                    allencounters.add(new Encounter(encounterNo, type, location, battleSkillRequired, prizeMoney));
                    count++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
 
    
    // ***************   file write/read  *********************
    /** Writes whole game to the specified file
     * @param fname name of file storing requests
     */
    public void saveGame(String fname)
    {   // uses object serialisation 
       try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fname))) 
       {
            out.writeObject(this); // Save the whole SeaBattles object
            System.out.println("Game saved to " + fname);
       } 
       catch (IOException e) 
       {
            e.printStackTrace();
       }
    }
    
    /** reads all information about the game from the specified file 
     * and returns 
     * @param fname name of file storing the game
     * @return the game (as an SeaBattles object)
     */
    public SeaBattles loadGame(String fname)
    {   // uses object serialisation 
       
        SeaBattles myBattlesload = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fname))) {
            myBattlesload = (SeaBattles) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading student data: " + e.getMessage());
        }
        return myBattlesload;
    } 
    
    
}



