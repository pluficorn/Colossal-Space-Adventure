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

    public void setMessage(int phase, String message)
    {
        phaseMessages.add(phase, message);
    }

    public String getMessage(int index)
    {
        while(phaseMessages.size() <= index)
        {
            index--;
        }

        return phaseMessages.get(index);
    }
}
