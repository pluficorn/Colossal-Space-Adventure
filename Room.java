import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. "World of Zuul" is a
 * very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game. It is connected
 * to other rooms via exits. For each existing exit, the room stores a reference
 * to the neighboring room.
 * 
 * @author Michael Kölling, David J. Barnes, N. Verkade, M. Kok, E. Zigterman
 *         Rustenburg
 * @version 2020.01.16
 */

public class Room {
    private String description;
    private HashMap<String, Room> exits; // stores exits of this room.
    private boolean isTrapdoor = false;
    private ArrayList<Room> trapdoorLocations;
    private ArrayList<Item> items;
    private Item requiredKey;
    private HashMap<String, Actor> actorList; 

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "a kitchen" or "an open court yard".
     * 
     * This is by default not a trapdoor room. See the other constructor in this
     * class
     * 
     * @param description The room's description.
     * @param isTrapdoor true if room has trapdoor
     */
    public Room(String description, boolean isTrapdoor) {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>();
        this.isTrapdoor = isTrapdoor;
        trapdoorLocations = new ArrayList<>();
        actorList = new HashMap<>();
    }

    /**
     * creates room with a description
     * @param description The room's description.
     */
    public Room(String description) {
        this(description, false);
    }

    /**
     * @return true if room is a trapdoor
     */
    public boolean isTrapdoor() {
        return isTrapdoor;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * 
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    /**
     * Define an exit from this room.
     * 
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * adds a location that the trapdoor may lead to
     * @param location that trapdoor may lead to
     */
    public void setTrapdoorLocation(Room location) {
        trapdoorLocations.add(location);
    }

    /**
     * @returns ArrayList of rooms the trapdoor room may send the player to
     */
    public ArrayList<Room> getTrapdoorLocations() {
        return trapdoorLocations;
    }

    /**
     * Adds an item to a room
     * @param item that is added to the room
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * removes item from the room
     * @param item that is removed from the room
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * @returns ArrayList of items in the room
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Checks whether an item in the room has the specified name. It will only
     * return the first item with the matching name. May return null if no item was
     * found
     * 
     * @return item
     */
    public Item getItemFromString(String itemName) {
        for (Item item : items) {
            if (item.getName() != null && item.getName().equals(itemName)) {
                return item;
            }
        }

        // If no item was found, return null
        return null;
    }

    /**
     * @return The short description of the room (the one that was defined in the
     *         constructor).
     */
    public String getShortDescription() {
        return description;
    }

    /**
     * Return a description of the room in the form: You are in the kitchen. Exits:
     * north west
     * 
     * @return A long description of this room
     */
    public String getLongDescription() {
        // If there are both coins and items
        String longDescription = "You are " + description + ".\n";

        // Print any item and their descriptions
        for (Item item : items)
        {
            // Proper grammar for singular and plural
            if(item.getCount() != 1) {
                longDescription += "There are " + item.getName() + " laying around.";
            } else {
                longDescription += "There is a(n) " + item.getName() + " laying around.";
            }

            // Print description, if any
            if(!item.getItemDescription().trim().isEmpty()) {
                longDescription += ", " + item.getItemDescription() + ".\n";
            } else {
                longDescription += ".\n";
            }
        }

        longDescription += getExitString();

        return longDescription;
    }

    /**
     * Return a string describing the room's exits, for example "Exits: north west".
     * 
     * @return Details of the room's exits.
     */
    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * @returns a required item that is needed to go into a room
     */
    public Item getRequiredKey()
    {
        return requiredKey;
    }

    /**
     * sets the required item for a room
     * @param key item that is required to enter room
     */
    public void setRequiredKey(Item key)
    {
        requiredKey = key;
    }

    /**
     * @returns actors in the room
     */
    public Actor getActor(String name)
    {
        return actorList.get(name);
    }
}
