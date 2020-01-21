import java.util.ArrayList;
/**
 * Class to create actors.
 *
 * @author M. Kok, N. Verkade, E. Zigterman Rustenburg
 * @version 2020.01.21
 */
public class Actor
{
    private String name;
    private String description; // voor bij look()
    private ArrayList<Item> inventory;

    /**
     * Creates an actor with a description. All actors start with an empty inventory.
     * @param name Name of the actor
     * @param description Description of the actor
     */
    public Actor(String name, String description)
    {
        this.name = name;
        this.description = description;

        inventory = new ArrayList<>();
    }

    /**
     * Add an item to inventory.
     * @param item Item to be added to inventory.
     */
    public void addItem(Item item)
    {
        inventory.add(item);
    }

    /**
     * removes item from inventory.
     * @param item Item to be removed from inventory.
     */
    public void removeItem(Item item)
    {
        inventory.remove(item);
    }

    /**
     * @return ArrayList of items.
     */
    public ArrayList<Item> getInventory()
    {
        return inventory;
    }

    /**
     * Method to get the name of an actor.
     * @return Name of actor.
     */

    public String getName()
    {
        return name;
    }

    /**
     * @return the description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * method to set the description of the room.
     * @param description to be set to an actor.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * method that makes an actor 'talk'.
     * @param message the message the actor will 'say'.
     */
    public void talk(String message)
    {
        System.out.println(name + ": " + message);
    }
}
