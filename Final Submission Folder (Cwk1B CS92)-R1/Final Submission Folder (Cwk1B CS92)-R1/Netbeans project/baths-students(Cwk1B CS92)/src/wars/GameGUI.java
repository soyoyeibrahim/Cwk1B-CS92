package wars;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * Provide a GUI interface for the game
 * 
 * @author A.A.Marczyk
 * @version 20/02/12
 */
public class GameGUI 
{
    private BATHS gp = new SeaBattles("Fred");
    private JFrame myFrame = new JFrame("Game GUI");
    private Container contentPane = myFrame.getContentPane();
    private JTextArea listing = new JTextArea();
    private JLabel codeLabel = new JLabel ();
    private JButton fightBtn = new JButton("Fight Encounter");
    private JButton viewBtn = new JButton("View State");
    private JButton clearBtn = new JButton("Clear");
    private JButton quitBtn = new JButton("Quit");
    private JPanel eastPanel = new JPanel();

    
    public GameGUI()
    {
        makeFrame();
        makeMenuBar(myFrame);
    }
    

    // Main method to start the program
    public static void main(String[] args) {
        // Create an instance of GameGUI to display the GUI
        new GameGUI();
    }
    
    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {    
        myFrame.setLayout(new BorderLayout());
        myFrame.add(listing,BorderLayout.CENTER);
        listing.setVisible(false);
        contentPane.add(eastPanel, BorderLayout.EAST);
        // set panel layout and add components
        eastPanel.setLayout(new GridLayout(6,1));

        eastPanel.add(fightBtn);
        fightBtn.addActionListener(new FightHandler());
        
        eastPanel.add(viewBtn);
        viewBtn.addActionListener(new ViewStateHandler());
        
        eastPanel.add(clearBtn);
        clearBtn.addActionListener(new ClearHandler());
        eastPanel.add(quitBtn);

        clearBtn.setVisible(true);
        quitBtn.setVisible(true);
        // building is done - arrange the components and show        
        myFrame.pack();
        myFrame.setVisible(true);
    }
    
    /**
     * Create the main frame's menu bar.
     */
    private void makeMenuBar(JFrame frame)
    {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        // create the File menu
        JMenu fileMenu = new JMenu("Ships");
        menubar.add(fileMenu);
        
        JMenuItem listShipItem = new JMenuItem("List reserve Ships");
        listShipItem.addActionListener(new ListFleetHandler());
        fileMenu.add(listShipItem);
        
        JMenuItem viewShipItem = new JMenuItem("View Ship Details");
        viewShipItem.addActionListener(new ViewShipHandler());
        fileMenu.add(viewShipItem);
        
        JMenuItem decommission = new JMenuItem("De-ommission Ship");
        decommission.addActionListener(new DecommissionHandler());
        fileMenu.add(decommission);
        
        
       
    }

private class ViewShipHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            String result = "";
            String inputValue = JOptionPane.showInputDialog("Ship Name ?: ");
            
            result = gp.getShipDetails(inputValue);
            JOptionPane.showMessageDialog(myFrame,result);    
        }
    }
    
    private class ListFleetHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            listing.setVisible(true);
            String xx = gp.getReserveFleet();
            listing.setText(xx);
            
        }
    }

    
    private class ClearHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            listing.setText("");
            listing.setVisible(false);            
        }
    }


    
    private class DecommissionHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            String result = "";
            String inputValue = JOptionPane.showInputDialog("Ship code ?: ");
            
            if(gp.isInSquadron(inputValue)) 
            {
                gp.decommissionShip(inputValue);
                result = inputValue + " is decommissioned";
            }
            else
            {
                result = inputValue + " not in fleet";
            }
            JOptionPane.showMessageDialog(myFrame,result);    
        }
    }
    
    // Handler for "Fight Encounter" button
    private class FightHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(myFrame, "Fight Encounter triggered!");
            String result = "";
            int number = 0;
            String inputValue = JOptionPane.showInputDialog("Enter Encounter number: ");
            try{
            number = Integer.parseInt(inputValue);  // Converts the string to an integer
            }
            catch (NumberFormatException f)
            {
                JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid number.");
            }
            result = gp.fightEncounter(number);
            JOptionPane.showMessageDialog(myFrame,result); 
        }
    }

    // Handler for "View State" button
    private class ViewStateHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String state = gp.toString();  // Display State of Admiral
            JOptionPane.showMessageDialog(myFrame, state);
        }
    }
   
    private class ClearButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {            
            listing.setVisible(false);
            clearBtn.setVisible(false);
        }
    }
    
}
   
