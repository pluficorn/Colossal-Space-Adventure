
/**
 * Enumeration class MenuWord - Representations of all valid Menu commands along with a string and a description in a particular language.
 *
 * @author E. Zigterman Rustenburg
 * @version 2020.01.18
 */
public enum MenuWord
{
    QUIT("quit", "leave the game"),

    HELP("help", "get all the commands you can use in the game"),

    UNKNOWN("?", ""),
    
    COMMANDS("commands", "get a list of all the commands in the game with a description."),

    ABOUT("about", "info about the producers of The World of Zuul" );

    private String menuString;
    private String menuContent;

    /**
     * Initialise with the corresponding command string.
     * @param commandString The command string.
     */
    MenuWord(String menuString, String menuContent)
    {
        this.menuString = menuString;
        this.menuContent = menuContent;
    }

    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return menuString;
    }

    /**
     * @return The command description as a string.
     */
    public String toDescription()
    {
        return menuContent;
    }
}
