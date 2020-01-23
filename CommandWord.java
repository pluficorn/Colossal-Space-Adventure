/**
 * Representations for all the valid command words for the game
 * along with a string and a description in a particular language.
 * 
 * @author  Michael Kölling, David J. Barnes, M. Kok, N. Verkade, E. Zigterman Rustenburg
 * @version 2020.01.18
 */

public enum CommandWord
{
    // A value for each command word along with its
    // corresponding user interface string.
    GO("go", "Move in the direction you chose, must be followed with a direction that has an exit to go somewhere (e.g. go east)" ), 

    HELP("help", "Shows the commando's and their functions, for more info about the functions type help + function (e.g. help look)" ), 

    UNKNOWN("?", "" ), 

    BACK("back", "Go back one room" ),

    LOOK("look", "Look around the room and show the items/coins in the room" ),

    TAKE("take", "Take an item or coins and put it in the inventory (e.g. take item)" ),

    DROP("drop", "Take an item out of the inventory and drops it in the room (e.g. drop item)" ),

    INVENTORY("inventory", "Shows your inventory, which consists of items and coins" ),

    TALK("talk", "Speak to a person or alien (e.g. talk personname)" ),

    USE("use", "Used to use a specified item like the sword or the book (e.g. use sword)" ),

    ATTACK("attack", "Attack specified enemy (e.g. attack enemyname"),

    GIVE("give", "Give something to an ally (e.g. give to allyname)"),

    MENU("menu", "Shows a menu with options, like quit and about");

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
