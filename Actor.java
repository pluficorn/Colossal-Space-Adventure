
import java.util.ArrayList;
import java.util.HashMap;

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
    private HashMap<Room, String> messages;

    public Actor(String name, Room currentRoom)
    {
        this.name = name;
        this.currentRoom = currentRoom;
        messages = new HashMap<>();
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

    public void addMessage(Room room, String message)
    {
        messages.put(room, message);
    }

    public String getMessage(Room room)
    {
        return messages.get(room);
    }

}
