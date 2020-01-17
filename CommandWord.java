/**
 * Representations for all the valid command words for the game
 * along with a string in a particular language.
 * 
 * @author  Michael Kölling, David J. Barnes, M. Kok, N. Verkade
 * @version 2020.01.13
 */

public enum CommandWord
{
    // A value for each command word along with its
    // corresponding user interface string.
    GO("go", "" ), 

    QUIT("quit", "" ), 

    HELP("help", "shows the commando's and their functions" ), 

    UNKNOWN("?", "" ), 

    BACK("back", "go back one room" ),

    LOOK("look", "looks around the room and shows the items/coins in the room" ),

    TAKE("take", "take an item or coins and put it in the inventory" ),

    DROP("drop", "take in item out of the inventory and drops it in the room" ),

    INVENTORY("inventory", "shows your inventory, which consists of items and coins" ),

    TALK("talk", "speak to a person or alien" ),

    USE("use", "Used to use a specified item like the sword or the book" );

    // The command string.
    private String commandString;
    private String commandDescription;

    /**
     * Initialise with the corresponding command string.
     * @param commandString The command string.
     */
    CommandWord(String commandString, String commandDescription)
    {
        this.commandString = commandString;
        this.commandDescription = commandDescription;

    }

    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }

    /**
     * @return The command description as a string.
     */
    public String toDescription()
    {
        return commandDescription;
    }
}
