import java.util.HashMap;
/**
 * class MenuWords - This class holds an enumeration of all menu items known to the game.
 * It is used to recognise the menu Items when the player wants to select an item from a menu.
 *
 * @author E. Zigterman Rustenburg
 * @version 2020.01.18
 */
public class MenuWords
{
    // A mapping between a command word and the CommandWord
    // associated with it.
    private HashMap<String, MenuWord> validMenuItems;
    // private HashMap<String, CommandWord> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public MenuWords()
    {
        validMenuItems = new HashMap<>();
        for(MenuWord menu : MenuWord.values()) {
            if(menu != MenuWord.UNKNOWN) {
                validMenuItems.put(menu.toString(), menu);
            }
        }
    }

    /**
     * Find the MenuWord associated with a command word.
     * @param menuWord The word to look up.
     * @return The MenuWord correspondng to menuWord, or UNKNOWN if it is not a valid menu word.
     */
    public MenuWord getMenuWord(String menuWord)
    {
        if(menuWord != null) {
            MenuWord menu = validMenuItems.get(menuWord.toLowerCase());
            if(menu != null) {
                return menu;
            }
            else {
                return MenuWord.UNKNOWN;
            }
        } else {
            return MenuWord.UNKNOWN;    
        }
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if it is, false if it isn't.
     */
    public boolean isMenuCommand(String aString)
    {
        return validMenuItems.containsKey(aString);
    }

    /**
     * Print all valid commands (with descriptions of the commands to System.out.
     */
    public void showAll() 
    {
        for(String command : validMenuItems.keySet()) {
            String description = validMenuItems.get(command).toDescription();
            System.out.println(command + ": " + description);
        }
        System.out.println();
    }
}
