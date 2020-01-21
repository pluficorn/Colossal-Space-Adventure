import java.util.ArrayList;
/**
 * Class to create actors
 *
 * @author M. Kok, N. Verkade
 * @version 2020.01.21
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
    
    public void removeItem(Item item)
    {
        inventory.remove(item);
    }
    
    public ArrayList<Item> getInventory()
    {
        return inventory;
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
     * method to set the description of the room
     * @param description to be set to an actor
     */
    public void setDescription(String description)
    {
        this.description = description;
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
