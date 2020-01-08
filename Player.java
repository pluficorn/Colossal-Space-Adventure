
/**
 * Class Player -- all the information about the player, like a coin pouch and the inventory
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * The inventory carries all the items of the player, which is determined by a maxiumum weight.
 * The coin pouch carries all the coins, determined by count.
 * 
 * @author N.Verkade, M.Kok
 * @version 08-01-2019
 */

public class Player
{
    private int coinPouch;
    private String inventory;
    private int maxWeight;
    private Room currentRoom;

    /**
     * Constructor voor objects van class Player
     */
    public Player(int maxWeight, Room currentRoom)
    {
        this.maxWeight = maxWeight;
        this.currentRoom = currentRoom;
        //this.coinPounch = 10; // set initial money to ten
    }

    /**
     * Set the room the player is currently in.
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
     * Add money from the players balance.
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
}
