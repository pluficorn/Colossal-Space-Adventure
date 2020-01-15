import java.util.ArrayList;
import java.lang.Math;

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
 * @version 11-01-2019
 */

public class Player
{
    private int coinPouch; 
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
        //pickupItem(new Item("Handbook"); fixthis
    }

    /**
     * Move player to specified room
     */
    public void setRoom(Room room)
    {
        if (room.isTrapdoor()) {
            this.currentRoom = room;
            goTrapdoor();
        } else {
            this.currentRoom = room;
        }
    }

    /**
     * Teleport player to random room, saved in the randomRooms ArrayList.
     */
    public void goTrapdoor() {
        // Get ArrayList of trapdoors
        ArrayList<Room> trapdoorLocations = getRoom().getTrapdoorLocations();

        // Check if that ArrayList contains at least one item
        if (trapdoorLocations.size() > 0) {
            int rand = (int)(Math.random() * getRoom().getTrapdoorLocations().size());
            Room randomRoom = trapdoorLocations.get(rand);
            System.out.println("Wow, you tripped and fell in a hole! You are all turned around.");

            // Teleport player to room and clear players history
            setRoom(randomRoom);
            //history.clear();
        } else {
            System.out.println("Well, the hole ends up leading nowhere. Awkward. Maybe the game developers should add some trapdoor locations...");
        }
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
        inventory.add(item);
    }

    /**
     * Add money to the players balance.
     */
    public void addMoney(int count)
    {
        this.coinPouch += count;
    }

    /**
     * Remove money from the players balance.
     */
    public void substractMoney(int count)
    {
        this.coinPouch -= count;
    }

    /**
     * Get balance of the coinPouch.
     */
    public int getBalance()
    {
        return coinPouch;
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
    public int getTotalWeight()
    {
        int totalWeight = 0;
        for(Item item : inventory){
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }

    public ArrayList<Item> getInventory()
    {
        return inventory;
    }
}

