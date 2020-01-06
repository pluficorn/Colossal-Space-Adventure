 /**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        
        // Room outside, theater, pub, lab, office;
        Room crater, open_field, cave_entrance, cave_area1, cave_area2, cave_area3, cave_area4, cave_area5, cave_area6,
            forest_entrance, forest_field1, forest_field2, forest_field3, tree1, tree2, tree3,
            road, village_entrance, marketplace,
            prison_entrance, prison_cafeteria, cellblock, cell1, cell2, cell3;
        
        // create the rooms
        crater = new Room("...");
        open_field = new Room("...");
        cave_entrance = new Room("...");
        cave_area1 = new Room("...");
        cave_area2 = new Room("...");
        cave_area3 = new Room("...");
        cave_area4 = new Room("...");
        cave_area5 = new Room("...");
        cave_area6 = new Room("...");
        forest_entrance = new Room("...");
        forest_field1 = new Room("...");
        forest_field2 = new Room("...");
        forest_field3 = new Room("...");
        tree1 = new Room("...");
        tree2 = new Room("...");
        tree3 = new Room("...");
        road = new Room("...");
        village_entrance = new Room("...");
        marketplace = new Room("...");
        prison_entrance = new Room("...");
        prison_cafeteria = new Room("...");
        cellblock = new Room("...");
        cell1 = new Room("...");
        cell2 = new Room("...");
        cell3 = new Room("...");
        
        //initialize room exits
        crater.setExit("north", road);
        crater.setExit("east", open_field);
        crater.setExit("south", cave_entrance);
        crater.setExit("west", forest_entrance);
        
        open_field.setExit("west", crater);
        
        cave_entrance.setExit("north", crater);
        cave_entrance.setExit("south", cave_area1);
        
        cave_area1.setExit("north", cave_entrance);
        cave_area1.setExit("east", cave_area3);
        cave_area1.setExit("south", cave_area1);
        
        cave_area2.setExit("north", cave_area1);
        //cave_area2.setExit("trap", cave_area4);
        
        cave_area3.setExit("east", cave_area5);
        cave_area3.setExit("west", cave_area1);
        
        cave_area4.setExit("south", cave_area5);
        
        cave_area5.setExit("north", cave_area4);
        cave_area5.setExit("south", cave_area6);
        cave_area5.setExit("west", cave_area3);
        
        cave_area6.setExit("north", cave_area5);
        
        forest_entrance.setExit("east", crater);
        forest_entrance.setExit("south", forest_field3);
        forest_entrance.setExit("west", forest_field1);
        
        forest_field1.setExit("east", forest_entrance);
        forest_field1.setExit("south", forest_field2);
        forest_field1.setExit("up", tree1);
        
        forest_field2.setExit("north", forest_field1);
        forest_field2.setExit("east", forest_field3);
        forest_field2.setExit("up", tree2);
        
        forest_field3.setExit("north", forest_entrance);
        forest_field3.setExit("west", forest_field2);
        forest_field3.setExit("up", tree3);
        
        tree1.setExit("down", forest_field1);
        
        tree2.setExit("down", forest_field2);
        
        tree3.setExit("down", forest_field3);
        
        road.setExit("north", village_entrance);
        road.setExit("south", crater);
        
        village_entrance.setExit("east", marketplace);
        village_entrance.setExit("south", road);
        village_entrance.setExit("west", prison_entrance);
        
        marketplace.setExit("west", village_entrance);
        
        prison_entrance.setExit("east", village_entrance);
        prison_entrance.setExit("south", prison_cafeteria);
        prison_entrance.setExit("west", cellblock);
        
        prison_cafeteria.setExit("north", prison_entrance);
        
        cellblock.setExit("north", cell1);
        cellblock.setExit("east", prison_entrance);
        cellblock.setExit("south", cell3);
        cellblock.setExit("west", cell2);
        
        cell1.setExit("south", cellblock);
        
        cell2.setExit("east", cellblock);
        
        cell3.setExit("north", cellblock);
        
        currentRoom = crater;               //start game
        
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case LOOK:
                System.out.println("Debug msg for LOOK.");
                //look();
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
