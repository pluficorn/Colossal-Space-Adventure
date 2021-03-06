/**
 * This class is the main class of the "World of Zuul" application. "World of
 * Zuul" is a very simple, text based adventure game. Users can explore the World of Zuul and 
 * can talk to actors and explor all the rooms.
 * Players will come across different challenges on their path to complete their mission.
 * 
 * To play this game, create an instance of this class and call the "play"
 * method. Or via the GameMain Class.
 * 
 * This main class creates and initialises all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates and executes the
 * commands that the parser returns.
 * 
 * @author Michael Kölling, David J. Barnes, N.Verkade, M.Kok, E.Zigterman
 *         Rustenburg
 * @version 2020.01.23
 */

public class Game {
    private Parser parser;
    private MenuWords menus;
    private CommandWord commandWord;
    private Player player;
    private Room crater, open_field, cave_entrance, cave_area1, cave_area2, cave_area3, cave_area4, cave_area5,
    cave_area6, forest_entrance, forest_field1, forest_field2, forest_field3, tree1, tree2, tree3, road,
    village_entrance, marketplace, prison_entrance, prison_cafeteria, cellblock, cell1, cell2, cell3;
    private Item item, landing_gear, lasersword, book, hyperdrive, motor, metal_shielding, potion;
    private Ally merchant, interpreter;
    private Enemy worm;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        hyperdrive = new Item(1, 10000, "hyperdrive", "Crucial part of the ships engine");
        metal_shielding = new Item(1, 15000, "metal_shielding", "The outside part of the spaceship, also used as shielding"); 
        landing_gear = new Item(1, 500, "landing_gear", "A crucial part to land the ship", true, true);
        motor = new Item(1, 10000, "motor", "Engines the spaceship");
        merchant = new Ally("merchant", "He can trade coins for usefull items");
        interpreter = new Ally("interpreter", "He is desperate to talk to you.");
        worm = new Enemy("worm", "A massive worm. He seems angry, it looks like he is holding something important, how do we get this?", 10, 2, 1);
        potion = new Item(1, 500, "potion", "Use to heal the player's HP by 5");

        createRooms();
        parser = new Parser();
        player = new Player(50000, crater, 10, 3, 2);

        createInventoryItems();
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
        System.out.println("Thank you for playing. Goodbye.");
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
        cave_area2 = new Room("moving further into the dark cave", true);
        cave_area3 = new Room("somewhere in the cave. It's still dark");
        cave_area4 = new Room("in the cave. I can hear the flapping of batwings");
        cave_area5 = new Room("somewhere in the cave");
        cave_area6 = new Room("in the cave. I can hear rocks falling");
        forest_entrance = new Room("at the entrance of a small forest, your eye catches something shining in one of the trees");
        forest_field1 = new Room("at a small open area within the forest with a tree in the centre of the area. The tree has a low hanging branch");
        forest_field2 = new Room("at a small open area within the forest with a tree in the centre of the area. The tree seems to have a small hole in it");
        forest_field3 = new Room(" at a small open area within the forest. There is a tree standing in the middle with a small nest on one of its branches");
        tree1 = new Room("sitting on the low hanging branch branch");
        tree2 = new Room("sitting on a branch near the small hole");
        tree3 = new Room("sitting on a branch next to the small nest");
        road = new Room("near the village");
        village_entrance = new Room("entering an alien village with weird looking architecture");
        marketplace = new Room("at the marketplace in the alien village");
        prison_entrance = new Room("at the prison, watch out for criminals!");
        prison_cafeteria = new Room("at the cafetaria in the prison");
        cellblock = new Room("entering the cellblock");
        cell1 = new Room("entering cell 1, an unknown prisoner is sitting in his cell. But does not pay any attention to you");
        cell2 = new Room("entering cell 2", false);
        cell3 = new Room("entering cell 3, the cell is empty");

        // Initialize room exits and add items/coins
        crater.setExit("north", road);
        crater.setExit("east", open_field);
        crater.setExit("south", cave_entrance);
        crater.setExit("west", forest_entrance);
        crater.addItem(new Item(1, 200000, "meteorite", "It looks really fragile", false));
        crater.addItem(new Item(4, 1, "coins", ""));

        open_field.setExit("west", crater);

        open_field.addItem(metal_shielding);
        open_field.addItem(new Item(5, 1, "coins", ""));

        cave_entrance.setExit("north", crater);
        cave_entrance.setExit("south", cave_area1);
        cave_entrance.addItem(new Item(4, 1, "coins", ""));

        cave_area1.setExit("north", cave_entrance);
        cave_area1.setExit("east", cave_area3);
        cave_area1.setExit("south", cave_area2);

        cave_area2.setExit("north", cave_area1);
        cave_area2.setTrapdoorLocation(cave_area1);
        cave_area2.setTrapdoorLocation(cave_area3);
        cave_area2.setTrapdoorLocation(cave_area4);
        cave_area2.setTrapdoorLocation(cave_area5);
        cave_area2.setTrapdoorLocation(cave_area6);

        cave_area3.setExit("east", cave_area5);
        cave_area3.setExit("west", cave_area1);
        cave_area3.addItem(new Item(5, 1, "coins", ""));

        cave_area4.setExit("south", cave_area5);

        cave_area5.setExit("north", cave_area4);
        cave_area5.setExit("south", cave_area6);
        cave_area5.setExit("west", cave_area3);
        cave_area5.addItem(new Item(4, 1, "coins", ""));
        cave_area6.setExit("north", cave_area5);
        cave_area6.setActor(worm);
        worm.addItem(hyperdrive);

        forest_entrance.setExit("east", crater);
        forest_entrance.setExit("south", forest_field3);
        forest_entrance.setExit("west", forest_field1);
        forest_entrance.addItem(new Item(3, 1, "coins", ""));

        forest_field1.setExit("east", forest_entrance);
        forest_field1.setExit("south", forest_field2);

        forest_field1.setExit("up", tree1);

        forest_field2.setExit("north", forest_field1);
        forest_field2.setExit("east", forest_field3);
        forest_field2.setExit("up", tree2);        
        forest_field2.addItem(new Item(6, 1, "coins", ""));

        forest_field3.setExit("north", forest_entrance);
        forest_field3.setExit("west", forest_field2);
        forest_field3.setExit("up", tree3);

        tree1.setExit("down", forest_field1);

        tree2.setExit("down", forest_field2);
        tree2.addItem(potion);

        tree3.setExit("down", forest_field3);

        landing_gear.setItemLocation(tree1);
        landing_gear.setItemLocation(tree2);
        landing_gear.setItemLocation(tree3);
        landing_gear.placeItem(landing_gear);

        road.setExit("north", village_entrance);
        road.setExit("south", crater);
        road.addItem(new Item(6, 1, "coins", ""));

        village_entrance.setExit("east", marketplace);
        village_entrance.setExit("south", road);
        village_entrance.setExit("west", prison_entrance);

        marketplace.setExit("west", village_entrance);
        marketplace.addItem(new Item(5, 1, "coins", ""));
        marketplace.setActor(merchant);
        merchant.setMessage(0, "Uryyb gurer! V'z gur zrepunag naq lbh pna genqr lbhe fuval pbvaf sbe orngvshy vgrzf. Evtug abj V bssre n fcnprfuvc zbgbe sbe 50 pbvaf! Vs lbh jnag gb genqr, glcr tvir zrepunag pbvaf.");
        merchant.setMessage(1, "Hello there! I'm the merchant and you can trade your shiny coins for beatiful items. Right now I offer a spaceship motor for 50 coins! If you want to trade, type give merchant coins.");
        merchant.setMessage(2, "Right now I don't have anything to trade, come back later!");
        merchant.addItem(motor);

        prison_entrance.setExit("east", village_entrance);
        prison_entrance.setExit("south", prison_cafeteria);
        prison_entrance.setExit("west", cellblock);

        prison_cafeteria.setExit("north", prison_entrance);
        Item golden_key = new Item(1, 300, "golden_key", "A golden key used to get in to a closed room", true);
        prison_cafeteria.addItem(golden_key);
        prison_cafeteria.addItem(new Item(7, 1, "coins", ""));

        cellblock.setExit("north", cell1);
        cellblock.setExit("east", prison_entrance);
        cellblock.setExit("south", cell3);
        cellblock.setExit("west", cell2);

        cell1.setExit("south", cellblock);
        cell1.addItem(new Item(2, 1, "coins", ""));
        cell2.setExit("east", cellblock);
        cell2.setRequiredKey(golden_key);
        cell2.setActor(interpreter);
        interpreter.setMessage(0, "Hello there! Thank you for freeing me. It's dangerous to go alone... without a translator. Take this.");
        interpreter.setMessage(1, "You will be able to talk to the merchant with the translator.");
        Item translator = new Item(1, 2000, "translator", "Translates alien languages");
        interpreter.addItem(translator);

        cell3.setExit("north", cellblock);
        cell3.addItem(new Item(6, 1, "coins", ""));
    }

    /**
     * Add starter items to players' inventory.
     */
    private void createInventoryItems() {
        // new item(count, wheight, name, description, *possible picked up *random
        // location *possible dropped *damage *content)
        // weapon
        lasersword = new Item(1, 0, "lasersword", "it can be used as a weapon", true, false, false, 1);
        player.pickUpItem(lasersword);
        // book with tips
        book = new Item(1, 0, "book", "a book with useful tips", true, false, false, 0, true);
        // adding content to the book
        book.addContent("When arriving on an new planet, it's best to always check your environment. You never know what you might find.");
        book.addContent("It is always good to find a way to communicate with the locals.");
        book.addContent("You will need to get 4 spaceship parts to fix your ship.");
        book.addContent("To fix the spaceship drop the 4 spaceship parts at the place were your spaceship is.");

        // player.pickUpItem(landing_gear);
        // player.pickUpItem(motor);
        // player.pickUpItem(metal_shielding);
        // player.pickUpItem(hyperdrive);
        player.pickUpItem(new Item (50, 1, "coins", ""));

        player.pickUpItem(book);
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("It is the year 2174. You’re on your way for a mission that will guarantee freedom in the whole galaxy.");
        System.out.println("So far, everything has been going smoothly. The traffic has been calm and nothing concerning has shown up.");
        //Thread.sleep(4000);
        System.out.println();
        System.out.println("BANG!!!");
        //Thread.sleep(4000);
        System.out.println();
        System.out.println("A shock goes through your ship. You seem to have been hit!");
        System.out.println("On the radar you see an unknown ship, because of the damage the ship is uncontrollable.");
        System.out.println("You end up getting caught in the gravity of a nearby planet and try to make a crash-landing.");
        System.out.println("But after you hit the ground, everything went black...");
        //Thread.sleep(4000);

        System.out.println();
        System.out.println("You wake up. You are lost and your space ship is broken. You must try to find the 4 missing spaceship parts to fix it.");
        System.out.println("To fix the spaceship drop the 4 spaceship parts at the place were your spaceship is.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println("Type '" + CommandWord.USE + " book' if you need tips/tricks.");
        System.out.println();
        look();
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
            printHelp(command);
            break;

            case GO:
            goRoom(command);
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
            wantToQuit = drop(command);
            break;

            case INVENTORY:
            inventory();
            break;

            case TALK:
            talk(command);
            break;

            case USE:
            use(command);
            break;

            case ATTACK:
            attack(command);
            break;

            case MENU:
            wantToQuit = menu(command);
            break;

            case GIVE:
            give(command);
            break;

        }
        return wantToQuit;
    }

    /**
     * Returns an int between the given range (inclusive)
     * 
     * @param low first value
     * @param high second value
     * @return int between the range of low and high
     */
    private int randomRange(int value1, int value2)
    {
        // Make sure value1 is always the lower number
        if(value1 > value2)
        {
            // Swap value1 and value2
            int tempHigh = value1;
            value1 = value2;
            value2 = tempHigh;
        }

        // Calculate a random number between the range
        double randomNumberPrecise = (Math.random()*(value2-value1)+value1);
        int randomNumber = (int)Math.round(randomNumberPrecise);
        return randomNumber;
    }

    // implementations of user commands:

    /**
     * Print out some help information. Here we print some stupid, cryptic message.
     * and a list of the command words.
     */
    private void printHelp(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("You are lost. You are alone. You wander");
            System.out.println("around on an unknown planet.");
            System.out.println();
            System.out.println("Type help commandword for information about the commandwords (e.g. help look). Your command words are:");
            parser.showCommands();
            return;
        }

        String commandHelp = command.getSecondWord();

        CommandWord commandword = new CommandWords().getCommandWord(commandHelp);
        System.out.println(commandword.toDescription());

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

        if (nextRoom != null) {
            // Room exists: proceed!
            // First, save current room to history so we can use it later with back
            player.addHistory(player.getRoom());

            // Then go to next room and print description (the history is thus one behind,
            // on purpose)
            player.setRoom(nextRoom);
            look();
        } else {
            System.out.println("Can't go there!");
        }
    }

    /**
     * Go back to the previous room according to the history This also removes the
     * room from history.
     */
    private void back() {
        if (!player.getHistory().isEmpty()) {
            player.setRoom(player.popHistory());
            look();
        } else {
            System.out.println("There is nothing to go back to.");
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
     * Look around the room. Get the current description.
     */
    private void look() {
        System.out.println(player.getRoom().getLongDescription());
    }

    /**
     * Take an item from the ground, removing it from the room and adding it to the
     * inventory.
     *
     * @param command that was executed.
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

        if (item instanceof Item) {
            if (item.canBePickedUp() == true) {
                if (player.calculateTotalWeight() + item.getWeight() <= player.getMaxWeight()) {
                    player.pickUpItem(item);
                    player.getRoom().removeItem(item);
                    System.out.println("You picked up the " + itemName);
                } else {
                    System.out.println("You have reached the maximum weight, your backpack is full");
                }
            } else {
                System.out.println("This item can't be picked up");
            }
        } else {
            System.out.println("Couldn't find specified item");
        }
    }

    /**
     * Method used to drop an item from the players' inventory.
     * @param command that was executed.
     */
    private boolean drop(Command command) {
        boolean wantToQuit = false;
        if (!command.hasSecondWord()) {
            // If there's no second word, we don't know what to drop.
            System.out.println("Drop what?");
            return wantToQuit;
        }

        // Specifying the item
        String itemName = command.getSecondWord();
        Item item = player.getInventoryItemFromString(itemName);

        if (item instanceof Item) {
            // check if item can be dropped
            if (item.canBeDropped()) {
                player.dropItem(item, 1);
                player.getRoom().addItem(item);
                System.out.println("You've dropped " + itemName);
            } else {
                System.out.println("This item can't be dropped");
            }
        }

        if(player.getRoom()  == crater) {
            if(player.getRoom().getItems().contains(hyperdrive) && player.getRoom().getItems().contains(motor) && player.getRoom().getItems().contains(metal_shielding) && player.getRoom().getItems().contains(landing_gear)){
                wantToQuit = win();
            }
        }
        return wantToQuit;
    }

    /**
     * method that starts the outro of the game and after this quits the game, the payer has won.
     */
    private boolean win()
    {
        boolean wantToQuit = false;

        System.out.println("You have all the parts you need to fix the spaceship");
        System.out.println();
        System.out.println("2 hours later...");
        System.out.println("You step into the spaceship, turn the key and it works!");
        System.out.println("You set off into the galaxy to save the universe, everything will be fine!");
        System.out.println();

        CommandWord commandWord = new CommandWords().getCommandWord("menu");

        // Manually execute menu quit command
        Command newCommand = new Command(commandWord, "quit", null);

        //wantToQuit = menu(newCommand);
        wantToQuit = processCommand(newCommand);

        return wantToQuit;

    }

    /**
     * method for the player to see what he has in his inventory.
     */
    private void inventory()
    {
        if (player.getInventory().isEmpty()) {
            System.out.println("Your inventory is empty");
        }else{ 
            for (Item myItem : player.getInventory()) {
                if(myItem.getName().equals("coins")) {
                    System.out.println("Coins: " + myItem.getCount() + ", with a weight of " + myItem.getWeight() + " grams per coin");
                }else{
                    System.out.println("You have " + myItem.getCount() + " " + myItem.getName()  + " with you, with a weight of " + myItem.getWeight() + " grams per item.");
                }
            }
            System.out.println("The total weight is: " + player.calculateTotalWeight());
            System.out.println("HP: " + player.getHealth());
        }
    }

    /**
     * Method used to talk to actors in the game.
     * @param command that was executed.
     */
    private void talk(Command command) {
        if (!command.hasSecondWord()) {
            // If there's no second word, we don't know who tot talk to.
            System.out.println("Talk to whom?");
            return;
        }

        // Specifying the actor
        String actorName = command.getSecondWord();

        // Check if actor is an ally
        if(player.getRoom().getActor(actorName) instanceof Ally)
        {
            // It's an ally; save it as such
            Ally ally = (Ally) player.getRoom().getActor(actorName);

            //check if ally has anything to say
            if(ally.hasMessage())
            {
                // Save the message
                String message = ally.getMessage(player.getPhase());

                //print the message of the actor
                ally.talk(message);

                // Check whether their inventory is not empty and that they are the interpreter
                if(!ally.getInventory().isEmpty() && ally.getName().equals("interpreter"))
                {
                    // If the actor has an item. it will give it to you. this will put the player in the next phase
                    while(ally.getInventory().size() > 0)
                    {
                        Item item  = ally.getInventory().get(0);
                        ally.removeItem(item);
                        player.pickUpItem(item);

                        System.out.println( "The " + ally.getName() + " gave you " + item.getCount() + " " + item.getName());
                        System.out.println("The " + ally.getName() + " left the room.");
                    }

                    // Moving player to next phase
                    player.incrementPhase();

                    // Check if character is an interpreter
                    if(ally.getName().equals("interpreter"))
                    {
                        player.getRoom().moveActor(ally.getName(), cellblock);

                        // Updating the descriptions for the story
                        ally.setDescription("The tolk is hanging around here. He seems quite relaxed now.");
                        cell2.setDescription("in cell 2. It's empty now that you've freed the tolk");
                        cellblock.setDescription("entering the cellblock.");
                        prison_entrance.setDescription("at the prison, watch out for criminals!");
                        prison_cafeteria.setDescription("at the cafetaria in the prison");

                    }
                }
                return;
            }
            System.out.println("He doesn't seem to want to talk to you.");
            return;

        } else {
            System.out.println("I don't know what you mean...");
            return;
        }
    }

    /**
     * method to use items however the item can be used.
     * @param command that was executed.
     */
    private void use(Command command) {
        if (!command.hasSecondWord()) {
            // If there's no second word, we don't know what to use.
            System.out.println("Use what?");
            return;
        }

        String itemName = command.getSecondWord();
        Item item = player.getInventoryItemFromString(itemName);

        // Check item
        if(item != null) {
            switch (item.getName()) {
                case "potion":
                player.addHealth(5);
                break;

                case "book":
                if (item.hasContent()) {
                    // read the content of the item
                    for (Object content : item.getContent()) {
                        System.out.println(content);
                    }
                    return;
                }
                break;
            }
        } else {
            System.out.println("The item is not in your inventory. Use " + commandWord.TAKE + " to pick up an item.");
        }
        // if none of the use options are applicable
        //System.out.println("We can't use this item here");
    }

    /**
     * Method used to use the menu Command. Second word selects the command available within the menu.
     * @param command that was executed.
     * @return true, false if the game should stop.
     */
    private boolean menu(Command command)
    {
        boolean wantToQuit = false;

        if (!command.hasSecondWord()) {
            // Print all possible second words
            System.out.println("To quit or look in about type: menu quit or menu about");
            parser.showMenuItems();
            return wantToQuit;
        }
        String secondWord = command.getSecondWord();

        //System.out.println(secondWord);

        CommandWord commandWord = new CommandWords().getCommandWord(secondWord);

        //System.out.println(commandWord);

        Command newCommand = new Command(commandWord, null, null);

        //System.out.println(newCommand.getCommandWord() + " " + newCommand.hasSecondWord());

        MenuWord menuWord = new MenuWords().getMenuWord(secondWord);

        switch (menuWord) {
            case UNKNOWN:
            System.out.println("I don't know what you want...");
            break;

            case ABOUT:
            about();
            break;

            case QUIT:
            wantToQuit = quit(newCommand);
            break;
        }

        return wantToQuit;
    }

    /**
     * method that gives the about information.
     */
    private void about()
    {
        System.out.println("The World of Zuul is produced by the following programmers:");
        System.out.println("Naomi Verkade");
        System.out.println();
        System.out.println("Marijn Kok");
        System.out.println();
        System.out.println("Esther Zigterman Rustenburg");
        System.out.println();
        System.out.println("Copyrights 2020");
    }

    /**
     * method that makes it possbile to give/trade with actors in the game.
     */
    private void give(Command command)
    {
        if (!command.hasSecondWord() || !command.hasThirdWord()) {
            System.out.println("Give what to whom?");
            return;
        }

        String secondWord = command.getSecondWord();
        String thirdWord = command.getThirdWord();

        CommandWord secondCommandWord = new CommandWords().getCommandWord(secondWord);
        CommandWord thirdCommandWord = new CommandWords().getCommandWord(thirdWord);

        if (player.getRoom().getActor(command.getSecondWord()) instanceof Ally) {
            Ally target = (Ally) player.getRoom().getActor(command.getSecondWord());
            if(target != merchant){
                System.out.println("Merchant not found.");
                return;
            }
        }else{
            System.out.println("Ally not found.");
            return;
        }

        Item specifiedItem = player.getInventoryItemFromString(command.getThirdWord());

        if(specifiedItem == null){

            System.out.println("Item does not exist" );
            return;
        }

        if(!specifiedItem.getName().equals("coins"))
        {
            System.out.println("You can only give coins" );
            return;
        }

        if(player.getPhase() == 1 && player.getInventoryItemFromString("coins").getCount() >= 50) {
            Item coin = player.getInventoryItemFromString("coins");
            player.dropItem(coin, 50);
            merchant.addItem(new Item(50, 1, "coins", ""));
            merchant.removeItem(motor);
            player.pickUpItem(motor);
            player.incrementPhase();
            
            System.out.println( "The " + merchant.getName() + " gave you " + motor.getCount() + " " + motor.getName());
        } else {
            System.out.println("The merchant doesn't seem to accept the offer. ");
        }
    }

    /**
     * Attacks enemy specified in the second word of the command. If either the player or the enemy health dips below zero,
     * they respectively respawn or die. TODO: Make Room method dropAllActorItems(of betere methode(s)?)
     * 
     * @param command the executed commandatt
     */
    private void attack(Command command)
    {
        if (!command.hasSecondWord()) {
            System.out.println("Attack whom?");
            return;
        }

        // check if the actor from the second commandword (a String) is an enemy
        if (player.getRoom().getActor(command.getSecondWord()) instanceof Enemy) {
            // We know it's an enemy from the previous if-statement, so cast and save it as such
            Enemy target = (Enemy) player.getRoom().getActor(command.getSecondWord());

            // Set attack damage for attack from the player
            int playerAttackDamage = player.getAttackDamage() + randomRange(-1*player.getAttackModifier(), player.getAttackModifier());
            int enemyAttackDamage  = target.getAttackDamage() + randomRange(-1*target.getAttackModifier(), target.getAttackModifier());

            // Exchange damages; target loses health, player loses health
            // Send damage stats
            // If the target or players health dips below or is equal to zero, kill or respawn them respectivelyz
            if(target.getHealth() - playerAttackDamage >= 0) {
                target.removeHealth(playerAttackDamage);
                System.out.println(target.getName() + ": -" + playerAttackDamage + "hp");
            } else {
                // If we get here, drop all items from the enemy and then terminate them
                System.out.println("You slayed the " + target.getName() + ".");

                // Keep removing items until the inventory is empty.

                //getRoom.dropAllActorItems(target); // Voor morgen

                int i = 0;
                while(target.getInventory().size() > 0)
                {
                    Item itemToRemove = target.getInventory().get(i);
                    System.out.println(target.getName() + " dropped a(n) " + itemToRemove.getName());
                    target.removeItem(itemToRemove);
                    player.getRoom().addItem(itemToRemove);
                }
                // Remove actor from room

                player.getRoom().removeActor(target.getName());

                // Enemy is dead and dropped items; that's all there is to it, thus return
                return;
            }

            if(player.getHealth() - enemyAttackDamage <= 0)
            {
                player.respawn();
            } else {
                System.out.println("You: -" + enemyAttackDamage + "hp");
                player.removeHealth(enemyAttackDamage);
            }
        } else {
            System.out.println("Enemy not found.");
        }
    }
}
