
/**
 * class Menu - This class holds information about a menu Item that was issued by the user.
 * it uses the second word of the command Menu if there is one.
 *
 * @author E. Zigterman Rustenburg
 * @version 2020.01.18
 */
public class Menu
{
    private MenuWord menuWord;

    /**
     * Create a menu object. First word must be supplied.
     * 
     * @param menuWord The menuWord. UNKNOWN if the command word was not recognised.
     */
    public Menu(MenuWord menuWord)
    {
        this.menuWord = menuWord;

    }

    /**
     * @return Returns the MenuWord.
     */
    public MenuWord getMenuWord()
    {
        return menuWord;
    }

    /**
     * @return Description of menuword.
     */
    public String getMenuDescription()
    {
        return menuWord.toDescription();
    }

    /**
     * @return True if this command was not understood.
     */
    public boolean isMenuUnknown()
    {
        return (menuWord == MenuWord.UNKNOWN);
    }

    /**
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord()
    {
        return (menuWord != null);
    }
}
