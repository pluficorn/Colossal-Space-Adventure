import java.util.ArrayList;
/**
 * Write a description of class Ally here.
 *
 * @author M. Kok
 * @version 2020.01.21
 */
public class Ally extends Actor
{
    private ArrayList<String> phaseMessages;

    /**
     * Constructor for objects of class Ally.
     * 
     * @param name        Name of the ally.
     * @param description Description of the ally.
     */
    public Ally(String name, String description)
    {
        super(name, description);
        phaseMessages = new ArrayList<>();
    }

    /**
     * Method to set a message for an ally, based on the phase a player is in.
     *
     * @param phase Value that states the phase of the message.
     * @param message Message that is stored for the given phase.
     */
    public void setMessage(int phase, String message)
    {
        phaseMessages.add(phase, message);
    }
        
    /**
     * Method to check if actor has message.
     * @return true if actor has message(s).
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
     * Returns the message based on the phase.
     * @param index phase connected to the message.
     * @return returns the message.
     */
    public String getMessage(int index)
    {
        while(phaseMessages.size() <= index)
        {
            index--;
        }
        
        // If message is empty or null, go back one index.
        if (phaseMessages.get(index).isEmpty() || phaseMessages.get(index) == null) {
            index--;
        }

        return phaseMessages.get(index);
    }
}
