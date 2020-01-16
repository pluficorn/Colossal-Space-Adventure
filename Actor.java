import java.util.ArrayList;

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
    private int Health;
    private ArrayList<Item> inventory;
    private Room currentRoom;
    private boolean canMove;

    public Actor(String name, Room currentRoom, boolean canMove)
    {
        this.name = name;
        this.currentRoom = currentRoom;
        this.canMove = canMove; 
    }
    
    public void setRoom(Room room) {
        currentRoom = room;
    }
    
    public Room getRoom()
    {
        return currentRoom;
    }

    public void sayMessage(String message)
    {
        System.out.println(name + ": " + message);
    }
}
