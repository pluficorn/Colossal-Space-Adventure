
/**
 * Enumeration class MenuWord - geef hier een beschrijving van de enum class
 *
 * @author (jouw naam)
 * @version (versie nummer of datum)
 */
public enum MenuWord
{
    QUIT("quit"), 

    HELP("help"),
    
    UNKNOWN("?"),
    
    MENU("menu"),
    
    ABOUT("about");
    
    private String menuString;
    
    /**
     * Initialise with the corresponding command string.
     * @param commandString The command string.
     */
    MenuWord(String menuString)
    {
        this.menuString = menuString;
    }
    
    public String toString()
    {
        return menuString;
    }
}
