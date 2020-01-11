import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;       // stores exits of this room.
    private ArrayList<Item> items;
    //private ArrayList<Coin> coins;
    // private Item item;
    private Coin coin;
    //private int coin;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>();
        //coins = new ArrayList<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public void addItem(Item item)
    {
        items.add(item);
    }
    
    public void removeItem(Item item)
    {
        items.remove(item);
    }
    
    public ArrayList<Item> getItems()
    {
        return items;
    }
    
    /**
     * Checks whether an item in the room has the specified name.
     * It will only return the first item with the matching name.
     * May return null if no item was found
     * @return item
     */
    public Item getItemFromString(String itemName)
    {
        for (Item item : items) {
            if(item.getName() != null && item.getName().equals(itemName)) {
                return item;
            }
        }
        
        // If no item was found, return null
        return null;
    }

    public void setCoin(Coin coin)
    {
        this.coin = coin;
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        // If there are both coins and items
        String longDescription = "You are " + description + "\n";

        if( coin.getCount() > 0 )
        {
            longDescription += coin.getCoinDescription() + "\n";
        }
        //If there are only items
        for(Item item : items)                 // moet nog gefixed worden
        {
            longDescription += "It looks like " + item.getItemDescription() + "\n";
        }

        longDescription += getExitString();

        return longDescription;
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}

