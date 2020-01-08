
/**
 * Class Item - an item in a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * An item represents an object of any kind. 
 * The items can be found in a room in the game.
 * 
 * @author N.Verkade
 * @version 06-01-2019
 */

public class Item
{
    // instance variables
    private String description;
    private int weight;
    private String name;

    /**
     * Constructor for class Item.
     * Creates an Item with a weight and description.
     */
    public Item(int weight, String name, String description)
    {
        this.weight = weight;
        this.description = description;
        this.name = name;
    }

    /**
     * Method to get the Name of the item
     */
    public String getName()
    {
        return name;
    }

    /**
     * Method to get the weight of the item
     */
    public int getWeight()
    {
        return weight;
    }
    
    /**
     * Method to get the description of an item
     */
    public String getItemDescription()
    {
        return description;
    }
    
}
