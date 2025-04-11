/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wars;

/**
 *
 * @author Hasan Salim
 */
public class MyShipTest {
    public static void main(String[] args) {
        // Creating instances of each ship type
        Ship manOWar = new ManOWar("HMS Victory", "Lord Nelson", 3, 800, 8, ShipState.ACTIVE);
        Ship frigate = new Frigate("HMS Bounty", "Captain Bligh", 30, true, 7, ShipState.RESTING);
        Ship sloop = new Sloop("HMS Speedy", "Captain James", 250, true,5, ShipState.RESERVE);

        // Displaying ship details
        System.out.println(manOWar);
        System.out.println(frigate);
        System.out.println(sloop);

        // Checking participation in encounters
        if (manOWar.participateInBlockade())
        {
            System.out.println("HMS Victory Can participate in the Blockade.");
        }
        else
        {
            System.out.println("HMS Victory Cannot participate in the Blockade.");
        }
        
        if (manOWar.participateInBattle())
                {
            System.out.println("HMS Victory Can participate in the Battle.");
        }
        else
        {
            System.out.println("HMS Victory Cannot participate in the Battle.");
        }
        
        if(manOWar.participateInSkirmish())
        {
            System.out.println("HMS Victory Can participate in the Skirmish.");
        }
        else
        {
            System.out.println("HMS Victory Cannot participate in the Skirmish.");
        }

        
        if (frigate.participateInBlockade())
        {
            System.out.println("HMS Bounty Can participate in the Blockade.");
        }
        else
        {
            System.out.println("HMS Bounty Cannot participate in the Blockade.");
        }
        
        if (frigate.participateInBattle())
                {
            System.out.println("HMS Bounty Can participate in the Battle.");
        }
        else
        {
            System.out.println("HMS Bounty Cannot participate in the Battle.");
        }
        
        if(frigate.participateInSkirmish())
        {
            System.out.println("HMS Bounty Can participate in the Skirmish.");
        }
        else
        {
            System.out.println("HMS Bounty Cannot participate in the Skirmish.");
        }
        
        if (sloop.participateInBlockade())
        {
            System.out.println("HMS Speedy Can participate in the Blockade.");
        }
        else
        {
            System.out.println("HMS Speedy Cannot participate in the Blockade.");
        }
        
        if (sloop.participateInBattle())
                {
            System.out.println("HMS Speedy Can participate in the Battle.");
        }
        else
        {
            System.out.println("HMS Speedy Cannot participate in the Battle.");
        }
        
        if(sloop.participateInSkirmish())
        {
            System.out.println("HMS Speedy Can participate in the Skirmish.");
        }
        else
        {
            System.out.println("HMS Speedy Cannot participate in the Skirmish.");
        }
        
  
    }
}
