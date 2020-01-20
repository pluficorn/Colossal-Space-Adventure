import java.util.ArrayList;

/**
 * Class to create actors
 *
 * @author M. Kok, N. Verkade
 * @version 2020.16.01
 */
public class Actor
{
    private String name;
    private String description; // voor bij look()
    private int health;
    private ArrayList<Item> inventory;
    //private Room currentRoom; Locatie wordt opgeslagen in Room!!!
    private ArrayList<String> phaseMessages;

    public Actor(String name)
    {
        this.name = name;
        this.health = 5;
        phaseMessages = new ArrayList<>();
    }

    /**
     * method to print the message of the actor
     * @param message that will be printed for the actor
     */
    public void talk(String message)
    {
        System.out.println(name + ": " + message);
    }

    /**
     * 
     */
    public void addMessage(Room room, String message)
    {
        phaseMessages.add(message);
    }

    /**
     * @param index index value of the message to be returned
     * @return message based on index value
     */
    public String getMessage(int index)
    {
        return phaseMessages.get(index);
    }

}
