
import java.util.ArrayList;

/**
 * Class Item - an item in a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * An item represents an object of any kind. 
 * The items can be found in a room in the game.
 * 
 * @author N.Verkade, E. Zigterman Rustenburg
 * @version 2020.01.16
 */

public class Item
{
    // instance variables
    private String description;
    private int weight;
    private String name;
    private boolean canBePickedUp = true;
    private boolean randomLocation = false;
    private int count;
    private ArrayList<Room> itemLocations;

    /**
     * Constructor for class Item.
     * Creates an Item with a weight and description.
     */
    public Item(int count, int weight, String name, String description)
    {
        this.weight = weight;
        this.description = description;
        this.name = name;
        this.canBePickedUp = true;
        this.count = count;
        this.randomLocation = false;
    }
    
    /**
     * Constructor for class Item.
     * use this one to define whether item can be picked up
     */
    public Item(int count, int weight, String name, String description, boolean canBePickedUp)
    {
        this.weight = weight;
        this.description = description;
        this.name = name;
        this.canBePickedUp = canBePickedUp;
        this.count = count;
        this.randomLocation = false;
    }
    
    /**
     * Constructor for class Item
     * use this one to define whether item has random location
     */
    public Item(int count, int weight, String name, String description, boolean canBePickedUp, boolean randomLocation)
    {
        this.weight = weight;
        this.description = description;
        this.name = name;
        this.canBePickedUp = canBePickedUp;
        this.count = count;
        this.randomLocation = randomLocation;
        itemLocations = new ArrayList<>();
    }

    /**
     * Method to get the Name of the item
     */
    public String getName()
    {
        return name;
    }

    /**
     * Method to set the name of the item
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Method to get the weight of the item
     */
    public int getWeight()
    {
        return weight;
    }

    /**
     * Method to set the weight of the item
     */
    public void setWeight(int weight)
    {
        this.weight = weight;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    /**
     * Method to get the description of an item
     */
    public String getItemDescription()
    {
        return description;
    }

    /**
     * Method to set the description of an item
     */
    public void setItemDescription(String description)
    {
        this.description = description;
    }

    public boolean canBePickedUp()
    {
        return canBePickedUp;
    }
    
    public void setItemLocation(Room location)
    {
        itemLocations.add(location);
    }
    
    public ArrayList<Room> getItemLocation()
    {
        return itemLocations;
    }
    
    public void placeItem(Item item)
    {
        // Get ArrayList for Locations for Item
        ArrayList<Room> itemLocations = item.getItemLocation();
        
        // Check if arraylist contains at least 1 location (item);
        if(itemLocations.size() > 0)
        {
            int rand = (int) (Math.random() * item.getItemLocation().size());
            Room randomRoom = itemLocations.get(rand);
            randomRoom.addItem(item);
        }
    }
}

