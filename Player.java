import java.util.ArrayList;

// For Math.random
import java.lang.Math;

// For Stack (history)
import java.util.Deque;
import java.util.Stack;
import java.util.ArrayDeque;

/**
 * Class Player -- all the information about the player, like a coin pouch and
 * the inventory
 *
 * This class is part of the "World of Zuul" application. "World of Zuul" is a
 * very simple, text based adventure game.
 * 
 * The inventory carries all the items of the player, which is determined by a
 * maxiumum weight. The coin pouch carries all the coins, determined by count.
 * 
 * @author N.Verkade, M.Kok, E.Zigterman Rustenburg
 * @version 2020.01.15
 */

public class Player {
    // General player info
    private int maxWeight;
    private Room currentRoom;
    private Room respawnLocation;
    private int health;
    private int initialHealth;

    // damage the player can deal. attackModifier is added or removed from attackDamage when attacking for randomness
    private int attackDamage, attackModifier;

    // Inventory
    private ArrayList<Item> inventory;

    // Location/Room history
    private Deque<Room> history;

    // Phase (for storyline progress)
    private int phase;

    /**
     * Constructor voor objects van class Player
     */
    public Player(int maxWeight, Room spawn, int health, int attackDamage, int attackModifier) {
        this.maxWeight = maxWeight;
        this.currentRoom = spawn;
        this.health = health;
        this.attackDamage = attackDamage;
        this.attackModifier = attackModifier;

        // set respawnlocation to initial spawn location
        this.initialHealth = health;
        this.respawnLocation = spawn;

        inventory = new ArrayList<>();
        history = new ArrayDeque<>();

        this.phase = 0; // Start at phase 0
    }

    public void respawn()
    {
        // Print dramatic message
        System.out.println();
        System.out.println();
        System.out.println("You died.");
        System.out.println();
        System.out.println();

        // Teleport player to spawn location
        setRoom(respawnLocation);
        sethealth(initialHealth);
        history.clear();
    }

    /**
     * Moves player to specified room
     * 
     * @param room to move to
     */
    public void setRoom(Room room) {
        // Check if player has access to specified room
        if (room.getRequiredKey() == null || hasRequiredKey(room)) {
            this.currentRoom = room;

            // If the room is a trapdoor, perform trapdoor method
            if (room.isTrapdoor()) {
                goTrapdoor();
            }
        }else{
            System.out.println("The room is locked, you need a(n) " + room.getRequiredKey().getName());
        }
    }

    public int getHealth()
    {
        return health;
    }
    
    public void sethealth(int health)
    {
        this.health = health;
    }

    /**
     * Get the room the player is currently in.
     * 
     * @return current room
     */
    public Room getRoom() {
        return currentRoom;
    }

    /**
     * Add a room to the history (used for the back command)
     */
    public void addHistory(Room room)
    {
        history.push(room);
    }

    /**
     * returns the room history of a player
     * @return room history of player
     */
    public Deque<Room> getHistory() {
        return history;
    }

    /**
     * Will delete the last entry of the history and return it.
     * @return returns last entry of the room (and deletes it from the history)
     */
    public Room popHistory() {
        return history.pop();
    }

    /**
     * Teleport player to random room, saved in the randomRooms ArrayList.
     */
    public void goTrapdoor() {
        // Get ArrayList of trapdoors
        ArrayList<Room> trapdoorLocations = getRoom().getTrapdoorLocations();

        // Check if that ArrayList contains at least one item
        if (trapdoorLocations.size() > 0) {
            // Generate a random index for which room to pick. -1 because size is always 1 more than the highest index value
            int rand = (int) (Math.round(Math.random() * getRoom().getTrapdoorLocations().size()-1));
            
            // Save this room
            Room randomRoom = trapdoorLocations.get(rand);
            System.out.println("Wow, you tripped and fell in a hole! You are all turned around.");

            // Teleport player to the randomly picked room and clear players history (so they can't use back)
            setRoom(randomRoom);
            history.clear();
        } else {
            System.out.println("Well, the hole ends up leading nowhere. Awkward. Maybe the game developers should add some trapdoor locations...");
        }
    }

    /**
     * Method that removes an item from the inventory
     */
    public void dropItem(Item item, int amount) {

        if(inventory.contains(item))
        {
            int index = inventory.indexOf(item);
            int count = inventory.get(index).getCount();
            inventory.get(index).setCount(count - amount);

            if(inventory.get(index).getCount() <= 0)
            {
                inventory.remove(item);
            }

        }

    }

    /**
     * return the item from inventory based on index
     * @return item from inventory that was requested
     */
    public Item getInventoryItemFromString(String itemName) {
        for (Item item : inventory) {
            if (item.getName() != null && item.getName().equals(itemName)) {
                return item;
            }
        }

        // If no item was found, return null
        return null;
    }

    /**
     * Method that adds an item to the inventory of the player
     */
    public void pickUpItem(Item item) {
        // Check if the item is already in the inventory
        for(Item inventoryItem : inventory){
            //if item in the inventory (by checking name equal to item)
            if (inventoryItem.getName().equals(item.getName())){
                //retrieves the index of the item in the inventory
                int itemIndex = inventory.indexOf(inventoryItem);
                //gets the current item count and adds the new item count
                int newCount = inventory.get(itemIndex).getCount() + item.getCount();

                // increase the count of an item with
                inventory.get(itemIndex).setCount(newCount);  
                return;
            }
        }
        // If not in inventory, add item to inventory
        inventory.add(item);   
    }

    /**
     * Set maximum weight the player can carry in grams.
     */
    public void setMaxWeight(int weight) {
        this.maxWeight = weight;
    }

    /**
     * Get the maximum weight the player can carry in grams.
     * @return the max wheight
     */
    public int getMaxWeight() {
        return maxWeight;
    }

    /**
     * Get the total weight the player is carrying in grams.
     * @returns total inventory wheight 
     */
    public int calculateTotalWeight() {
        int totalWeight = 0;
        for (Item item : inventory) {
            totalWeight += (item.getWeight() * item.getCount());
        }
        return totalWeight;
    }

    /**
     * Get the inventory of the player
     * @return inventory items
     */
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    /**
     * Checks if player has required key to enter a room
     * @return boolean. True if player has required item
     */
    public boolean hasRequiredKey(Room room)
    {
        if (inventory.contains(room.getRequiredKey())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * removes health from the player
     * @param health amount substracted from health player
     */
    public void removeHealth(int health) {
        this.health -= health;
    }

    /**
     * increases the health of a player
     * @param health amount added to health player
     */
    public void addHealth(int health) {
        this.health += health;
    }

    /**
     * move the phase up,incrementing it with 1
     */
    public void incrementPhase()
    {
        phase += 1;
    }

    /**
     * @return current phase
     */
    public int getPhase()
    {
        return phase;
    }

    /**
     * @return players attack damage (without random modifier)
     */
    public int getAttackDamage()
    {
        return attackDamage;
    }

    /**
     * Set the new attack damage
     * 
     * @param new attack damage
     */
    public void setAttackDamage(int attackDamage)
    {
        this.attackDamage = attackDamage;
    }

    /**
     * get the attackModifier (used to randomize damage - is added or removed from attackDamage)
     */
    public int getAttackModifier()
    {
        return attackModifier;
    }

    public void setAttackModifier(int modifier)
    {
        attackModifier = modifier;
    }
}