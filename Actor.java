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
    private ArrayList<Item> inventory;
    
    public Actor(String name, String description)
    {
        this.name = name;
        this.description = description;
        
        inventory = new ArrayList<>();
    }
    
    public void addItem(Item item)
    {
        inventory.add(item);
    }
    
    /**
     * method to get the name of an actor
     */

    public String getName()
    {
        return name;
    }

    /**
    * @return the description
    */
    public String getDescription()
    {
        return description;
    }
    
    /**
    * method that makes an actor 'talk'
    * @param message the message the actor will 'say'
    */
    public void talk(String message)
    {
        System.out.println(name + ": " + message);
    }
}
