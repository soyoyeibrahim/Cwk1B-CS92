package wars; 


/**
 * Details of your team
 * 
 * @author Hamza Hasan Memon (22084720)
 * @version (1.0.0.0)
 */
public class Teamwork
{
    private String[] details = new String[12];
    
    public Teamwork()
    {   // in each line replace the contents of the String 
        // with the details of your team member
        // Please list the member details alphabetically by surname 
        // i.e. the surname of member1 should come alphabetically 
        // before the surname of member 2...etc
        details[0] = "Cwk1B CS92"; // team number
        
        details[1] = "Benny"; // Surname of Member-1
        details[2] = "Biben"; // First Name of Member-1
        details[3] = "21073767"; // SRN of Member-1

        details[4] = "Joy"; // Surname of Member-2
        details[5] = "Don"; // First Name of Member-2
        details[6] = "22104894"; // SRN of Member-2

        details[7] = "Memon"; // Surname of Member-3
        details[8] = "Hamza Hasan"; // First Name of Member-3
        details[9] = "22084720"; // SRN of Member-3


        details[10] = "Soyoye"; // Surname of Member-4
        details[11] = "Ibrahim Oladimeji"; // First Name of Member-4
        details[12] = "21001067"; // SRN of Member-4

	
	   // only if applicable
        details[13] = "surname of member5"; // Not Applicable
        details[14] = "first name of member5"; // Not Applicable
        details[15] = "SRN of member5"; // Not Applicable


    }
    
    public String[] getTeamDetails()
    {
        return details;
    }
    
    public void displayDetails()
    {
        for(String temp:details)
        {
            System.out.println(temp.toString());
        }
    }
}
        

