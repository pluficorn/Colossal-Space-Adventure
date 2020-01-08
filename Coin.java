
/**
 * Class Coin - a coin system, which can used in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * A coin represents the currency used on the planet. 
 * The coins can be found in a room in the game.
 * 
 * @author N.Verkade
 * @version 08-01-2019
 */

public class Coin
{
    // Instance variables
    private String description;
    private int count;
    
    /**
     * Constructor for class Coin.
     * Creates coins with a count and description.
     */
    public Coin(int count, String description)
    {
        this.count = count;
        this.description = description;
    }
    
    /**
     * Method to get the description of the coins
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Method to get the weight of the item
     */
    public int getCount()
    {
        return count;
    }
}
