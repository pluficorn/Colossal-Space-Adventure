import java.util.ArrayList;
/**
 * Write a description of class Ally here.
 *
 * @author M. Kok
 * @version 2020.01.20
 */
public class Ally extends Actor
{
    private ArrayList<String> phaseMessages;

    /**
     * Constructor for objects of class Ally
     */
    public Ally(String name, String description)
    {
        super(name, description);
        phaseMessages = new ArrayList<>();
    }

    /**
     * method to set a message for an ally, based on the phase a player is in
     *
     * @param phase value that states the phase of the message
     * @param message that is stored for the given phase
     */
    public void setMessage(int phase, String message)
    {
        phaseMessages.add(phase, message);
    }
        
    /**
     * method to check if actor has message
     * @return true if actor has message(s)
     */
    public boolean hasMessage()
    {
        if(phaseMessages.size() > 0)
        {
            return true;
        }
        
        return false;
    }

    /**
     * returns the message based on the phase
     * 
     * @param index phase connecte to the message
     * 
     * @return returns the message
     */
    public String getMessage(int index)
    {
        while(phaseMessages.size() <= index)
        {
            index--;
        }

        return phaseMessages.get(index);
    }
}
