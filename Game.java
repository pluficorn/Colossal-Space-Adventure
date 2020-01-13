
import java.util.Deque;
import java.util.Stack;
import java.util.ArrayDeque;

/**
 * This class is the main class of the "World of Zuul" application. "World of
 * Zuul" is a very simple, text based adventure game. Users can walk around some
 * scenery. That's all. It should really be extended to make it more
 * interesting!
 * 
 * To play this game, create an instance of this class and call the "play"
 * method.
 * 
 * This main class creates and initialises all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates and executes the
 * commands that the parser returns.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game {
    private Parser parser;
    private Player player;
    private Room crater, open_field, cave_entrance, cave_area1, cave_area2, cave_area3, cave_area4, cave_area5,
    cave_area6, forest_entrance, forest_field1, forest_field2, forest_field3, tree1, tree2, tree3, road,
    village_entrance, marketplace, prison_entrance, prison_cafeteria, cellblock, cell1, cell2, cell3;
    private Deque<Room> history;
    private Item item;
    private Coin coin;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createRooms();
        parser = new Parser();
        history = new ArrayDeque<>();
        player = new Player(50000, crater);
        //item = new Item(0, "", "");
        //coin = new Coin(0);
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        // Create the rooms with descriptions
        crater = new Room("in the crater where you crashed with your ship");
        open_field = new Room("in an open field near the ship. Nothing else in sight..");
        cave_entrance = new Room("at the mouth of a cave. Looks dark");
        cave_area1 = new Room("in the cave. It's dark");
        cave_area2 = new Room("...");
        cave_area3 = new Room("...");
        cave_area4 = new Room("...");
        cave_area5 = new Room("...");
        cave_area6 = new Room("...");
        forest_entrance = new Room("at the entrance of a small forest.");
        forest_field1 = new Room("at a small open area within the forest with a tree in the centre of the area. The tree has a low hanging branch");
        forest_field2 = new Room("at a small open area within the forest with a tree in the centre of the area. The tree seems to have a small hole in it");
        forest_field3 = new Room("You arrive at a small open area within the forest. There is a tree standing in the middle with a small nest on one of its branches");
        tree1 = new Room("sitting on the low hanging branch branch");
        tree2 = new Room("sitting on a branch near the small hole");
        tree3 = new Room("sitting on a branch next to the small nest");
        road = new Room("near the village");
        village_entrance = new Room("...");
        marketplace = new Room("...");
        prison_entrance = new Room("...");
        prison_cafeteria = new Room("...");
        cellblock = new Room("...");
        cell1 = new Room("...");
        cell2 = new Room("...");
        cell3 = new Room("...");

        // Initialize room exits and add items/coins
        crater.setExit("north", road);
        crater.setExit("east", open_field);
        crater.setExit("south", cave_entrance);
        crater.setExit("west", forest_entrance);
        crater.setCoin(new Coin(3));

        open_field.setExit("west", crater);
        open_field.addItem(new Item(15000, "Metal_shielding", "the outside part of the rocket, also used as shielding"));
        open_field.setCoin(new Coin(4));

        cave_entrance.setExit("north", crater);
        cave_entrance.setExit("south", cave_area1);
        cave_entrance.setCoin(new Coin(3));

        cave_area1.setExit("north", cave_entrance);
        cave_area1.setExit("east", cave_area3);
        cave_area1.setExit("south", cave_area1);

        cave_area2.setExit("north", cave_area1);
        cave_area2.setCoin(new Coin(4));

        cave_area3.setExit("east", cave_area5);
        cave_area3.setExit("west", cave_area1);

        cave_area4.setExit("south", cave_area5);

        cave_area5.setExit("north", cave_area4);
        cave_area5.setExit("south", cave_area6);
        cave_area5.setExit("west", cave_area3);
        cave_area5.setCoin(new Coin(3));

        cave_area6.setExit("north", cave_area5);

        forest_entrance.setExit("east", crater);
        forest_entrance.setExit("south", forest_field3);
        forest_entrance.setExit("west", forest_field1);
        forest_entrance.setCoin(new Coin(3));

        forest_field1.setExit("east", forest_entrance);
        forest_field1.setExit("south", forest_field2);

        forest_field1.setExit("up", tree1);

        forest_field2.setExit("north", forest_field1);
        forest_field2.setExit("east", forest_field3);
        forest_field2.setExit("up", tree2);
        forest_field2.setCoin(new Coin(6));

        forest_field3.setExit("north", forest_entrance);
        forest_field3.setExit("west", forest_field2);
        forest_field3.setExit("up", tree3);

        tree1.setExit("down", forest_field1);

        tree2.setExit("down", forest_field2);

        tree3.setExit("down", forest_field3);
        tree3.setCoin(new Coin(7));

        road.setExit("north", village_entrance);
        road.setExit("south", crater);
        road.setCoin(new Coin(4));

        village_entrance.setExit("east", marketplace);
        village_entrance.setExit("south", road);
        village_entrance.setExit("west", prison_entrance);

        marketplace.setExit("west", village_entrance);
        marketplace.setCoin(new Coin(5));

        prison_entrance.setExit("east", village_entrance);
        prison_entrance.setExit("south", prison_cafeteria);
        prison_entrance.setExit("west", cellblock);

        prison_cafeteria.setExit("north", prison_entrance);
        prison_cafeteria.addItem(new Item(300, "Key", "a golden key used to get in to a closed room"));
        prison_cafeteria.setCoin(new Coin(2));

        cellblock.setExit("north", cell1);
        cellblock.setExit("east", prison_entrance);
        cellblock.setExit("south", cell3);
        cellblock.setExit("west", cell2);

        cell1.setExit("south", cellblock);

        cell2.setExit("east", cellblock);

        cell3.setExit("north", cellblock);
        cell3.setCoin(new Coin(6));
    }

    /**
     * Main play routine. Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop. Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Goodbye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly awesome adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(player.getRoom().getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * 
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
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
            look();
            break;

            case BACK:
            back();
            break;

            case TAKE:
            take(command);
            break;

            case DROP:
            drop(command);
            break;

            case INVENTORY:
            inventory();
            break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information. Here we print some stupid, cryptic message
     * and a list of the command words.
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around on an unkonwn planet.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * Try to go in one direction. If there is an exit, enter the new room,
     * otherwise print an error message.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else { // Room exists: proceed!
            // First, save current room to history so we can use it later with back
            history.push(player.getRoom());

            // Then go to next room and print description
            player.setRoom(nextRoom);
            System.out.println(player.getRoom().getLongDescription());
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see whether we really
     * quit the game.
     * 
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true; // signal that we want to quit
        }
    }

    /**
     * Look around the room. Get the current description
     */
    private void look() {
        System.out.println(player.getRoom().getLongDescription());
    }

    /**
     * Go back to the previous room according to the history This also removes the
     * room from history.
     */
    private void back() {
        if (!history.isEmpty()) {
            player.setRoom(history.pop());
            look();
        } else {
            System.out.println("There is nothing to go back to.");
        }
    }

    /**
     * Take an item from the ground, removing it from the room and
     * adding it to the inventory.
     *
     * @param command that was executed
     */
    private void take(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take?
            System.out.println("Take what?");
            return;
        }

        // If we reach this point, an item has been specified
        String itemName = command.getSecondWord();
        Item item = player.getRoom().getItemFromString(itemName);

        if(item instanceof Item) {
            if (player.getTotalWeight() + item.getWeight() <= player.getMaxWeight()){
                player.pickUpItem(item);
                player.getRoom().removeItem(item);
                System.out.println("You picked up the " + itemName);
            }else{
                System.out.println("You have reached the maximum weight, your backpack is full");
            }
        } else {
            System.out.println("Couldn't find specified item");
        }
    }

    private void drop(Command command)
    {
        if(!command.hasSecondWord())
        {
            // If there's no second word, we don't know what to drop.
            System.out.println("Drop what?");
            return;
        }

        // Specifying the item
        String itemName = command.getSecondWord();
        Item item = player.getInventoryItemFromString(itemName);

        if( item instanceof Item)
        {
            player.dropItem(item);
            player.getRoom().addItem(item);
            System.out.println("You've dropped " + itemName);
        }
    }

    private void inventory()
    {
        if (player.getInventory().isEmpty()) {
            System.out.println("Your inventory is empty");
        }else{ 
            for (Item myItem : player.getInventory()) {
                System.out.println("You have " + item.getName()  + "with you, with a weight of "  + item.getWeight() + "grams.");
            }
            System.out.println("The total weight is: " + player.getTotalWeight());
            System.out.println("Coins: " + player.getBalance());
        }
    }
}