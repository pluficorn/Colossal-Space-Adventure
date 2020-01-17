import java.util.HashMap;
/**
 * class MenuWords - geef hier een beschrijving van deze class
 *
 * @author (jouw naam)
 * @version (versie nummer of datum)
 */
public class MenuWords
{
    // A mapping between a command word and the CommandWord
    // associated with it.
    private HashMap<String, MenuWord> validMenuItems;

    /**
     * Constructor - initialise the command words.
     */
    public MenuWords()
    {
        validMenuItems = new HashMap<>();
        for(MenuWord command : MenuWord.values()) {
            if(command != MenuWord.UNKNOWN) {
                validMenuItems.put(command.toString(), command);
            }
        }
    }

    /**
     * Find the CommandWord associated with a command word.
     * @param commandWord The word to look up.
     * @return The CommandWord correspondng to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public MenuWord getMenuWord(String menuWord)
    {
        if(menuWord != null) {
            MenuWord command = validMenuItems.get(menuWord.toLowerCase());
            if(command != null) {
                return command;
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
    public boolean isCommand(String aString)
    {
        return validMenuItems.containsKey(aString);
    }

    /**
     * Print all valid commands to System.out.
     */
    public void showAll() 
    {
        for(String command : validMenuItems.keySet()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
