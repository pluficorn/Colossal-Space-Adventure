/**
 * Write a description of class Actor here.
 *
 * @author M. Kok, N. Verkade
 * @version 2020.16.01
 */
public class Actor
{
    private String name;
    private String description; // voor bij look()
    //private Room currentRoom; Locatie wordt opgeslagen in Room!!!

    public Actor(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public String getName()
    {
        return name;
    }
    
    public String getDescription()
    {
        return description;
    }

    public void talk(String message)
    {
        System.out.println(name + ": " + message);
    }



}
