import java.util.ArrayList;

/**
 * Class Player -- all the information about the player, like a coin pouch and the inventory
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * The inventory carries all the items of the player, which is determined by a maxiumum weight.
 * The coin pouch carries all the coins, determined by count.
 * 
 * @author N.Verkade, M.Kok, E.Zigterman Rustenburg
 * @version 2020.01.15
 */

public class Player
{
    private ArrayList<Item> inventory;
    private int maxWeight;
    private Room currentRoom;

    /**
     * Constructor voor objects van class Player
     */ 
    public Player(int maxWeight, Room currentRoom)
    {
        this.maxWeight = maxWeight;
        this.currentRoom = currentRoom;
        this.inventory = new ArrayList<>();
        //this.coinPounch = 10; // set initial money to ten
    }

    /**
     * Move player to specified room
     */
    public void setRoom(Room room)
    {
        this.currentRoom = room;
    }

    /**
     * Get the room the player is currently in.
     * @return current room
     */
    public Room getRoom()
    {
        return this.currentRoom;
    }

    /**
     * Method that drops a chosen item by the player
     */
    public void dropItem(Item item)
    {
        inventory.remove(item);
    }

    /**
     * return the item from inventory based on index
     */
    public Item getInventoryItemFromString(String itemName)
    {
        for (Item item : inventory) {
            if(item.getName() != null && item.getName().equals(itemName)) {
                return item;
            }
        }

        // If no item was found, return null
        return null;
    }

    /**
     * Method that picks up a chosen item by the player
     */
    public void pickUpItem(Item item)
    {
        // Check if is already in inventory
        if(inventory.contains(item))
        {
            int itemIndex = inventory.indexOf(item);
            //increase the count of an item with 
            inventory.get(itemIndex).setCount((inventory.get(itemIndex).getCount())+item.getCount());
        }
        // If in inventory, increase amount and wheight of item
        inventory.add(item);
    }

    /**
     * Set maximum weight the player can carry in grams.
     */
    public void setMaxWeight(int weight)
    {
        this.maxWeight = weight;
    }

    /**
     * Get the maximum weight the player can carry in grams.
     */
    public int getMaxWeight()
    {
        return maxWeight;
    }

    /**
     * Get the total weight the player is carrying in grams.
     */
    public int calculateTotalWeight()
    {
        int totalWeight = 0;
        for(Item item : inventory){
            totalWeight += (item.getWeight() * item.getCount());
        }
        return totalWeight;
    }

    /**
     * Get the inventory of the player
     */
    public ArrayList<Item> getInventory()
    {
        return inventory;
    }
}

