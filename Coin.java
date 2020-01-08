
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
    private int count;

    /**
     * Constructor for class Coin.
     * Creates coins with a count and description.
     */
    public Coin(int count)
    {
        this.count = count;
    }

    /**
     * Method to get the weight of the item
     */
    public int getCount()
    {
        return count;
    }
    
    public String getCoinDescription()
    {
        return "There are " + getCount() + " coins lying around";
    }
}
