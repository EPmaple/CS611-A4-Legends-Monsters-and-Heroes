//import java.util.List;
//
//public class LegendsOfValorWorld implements World{
//    private List<Hero> heroes;
//    private List<Monster> monsters;
//    private LegendsOfValorIO io;
//    private int round = 0;
//    private LVTile currentTile;
//    private LVTile[][] worldMap;
//    public LegendsOfValorWorld(LVTile[][] worldMap, List<Hero> heroes) {
//        this.io = LegendsOfValorIO.getInstance();
//        // setworldmap
//        // setheroes
//        // set starting nexus
//    }
//
//    public void start() {
//        // initial hero placement and initial monster spawn
//        while(true) { // outer main game loop
//            round += 1;
//            // To spawn monsters every 8 rounds, another class, a monsterSpawner?
//            for (Hero hero : heroes) {
//                currentTile = (LVTile) hero.getCurrentTile();
//                // query for hero
//                heroMove(hero);
//            }
//
//            for (Monster monster : monsters) {
//                currentTile = (LVTile) monster.getCurrentTile();
//                // monster move and attack logic
//            }
//        }
//    }
//
//    private void heroMove(Hero hero) {
//        boolean turnConsumed = false;
//        while (turnConsumed) {
//            // display the map with the current hero highlighted
//            displayMap
//            String action =
//                    io.queryForUserAction(hero.getCurrentTile().getTileBehavior());
//            // query for user action, if
//            // hero is on
//            // the nexus
//            // then allow for the option to trade (enter market)
//            if (action.equalsIgnoreCase("W")) {
//                // logic for getting the corresponding tile
//                // try to move to that tile, and the corresponding movementMsg
//                LVTile nextTile = getUpTile();
//
//                if (move(nextTile, hero)) {
//                    turnConsumed = true;
//                }
//
//            } else if (action.equalsIgnoreCase("A")) {
//                LVTile nextTile = getLeftTile();
//                if (move(nextTile, hero)) {
//                    turnConsumed = true;
//                }
//
//            } else if (action.equalsIgnoreCase("S")) {
//                LVTile nextTile = getDownTile();
//                if (move(nextTile, hero)) {
//                    turnConsumed = true;
//                }
//
//            } else if (action.equalsIgnoreCase("D")) {
//                LVTile nextTile = getRightTile();
//                if (move(nextTile, hero)) {
//                    turnConsumed = true;
//                }
//
//            } else if (action.equalsIgnoreCase("U")) {
//                /*
//                 * Request which hero's inventory to use
//                 *
//                 * Then method to query for use of item from inventory:
//                 * armor, gear, potion
//                 */
//
//                if (hero.getInventory().isEmpty()) {
//                    String msg = hero.getName() + "'s inventory cannot be used " +
//                            "because it is currently empty.";
//                    io.displayMsg(msg);
//
//                } else {
//                    if (useInventory(hero) != null) { // true or false
//                        turnConsumed = true;
//                    }
//                    /*
//                    Else null, then the user exit out of inventoryUse without
//                    using the inventory at all, and thus, turnConsumed==false
//                     */
//                }
//
//            } else if (action.equalsIgnoreCase("I")) {
//                // Display info of all heroes
//                io.displayHeroInfoInWorld(this, heroes);
//                /*
//                On the map, a monster is given the abbreviation of m1, m2, etc.
//                Thus, a good idea in this case is to have HashMap<String, Character>
//                 */
//                /*
//                options:
//                1.) display info of all characters
//                2.) display info based on query: m1, m2, h3, etc.
//                3.) display info for all heroes, or for all monsters (this)
//                 */
//
//            } else if (action.equalsIgnoreCase("M")) {
//                // The option M/m is only shown when onMarketTile==true,
//                // meaning currentTile is MarketTile
//                currentTile.interact(heroes);
//
//            } else if (action.equalsIgnoreCase("T")) {
//                io.displayTutorial(); // need to change tutorial
//
//            } else if (action.equalsIgnoreCase("P")) {
//                // logic for teleporting hero to the other two lanes
//                /*
//                Current idea: (with option prior)
//                1.) get the lane of the current hero
//                2.) provide user with queries for the other two lanes
//                3.) after getting the lane from the user, query for targetHero
//                4.) after getting the targetHero, if no possible move, then repeat step
//                    3 for a valid choice; else, show possible tiles to move on to
//                5.) then hero.setCurrentTile(targetTeleportTile)
//                 */
//
//            } else if (action.equalsIgnoreCase("R")) {
//                // logic for teleporting hero back to nexus
//                /*
//                Current idea:
//                We do not want to modify the hero class to add another data
//                attribute of say, nexus. Thus, in LVWorld, we will have a HashMap
//                with key-value pair (Hero : Tile), where Tile/Coordinate is the
//                nexus associated with the Hero
//
//                1.) Tile nexus = HashMap.get(Hero)
//                2.) Tile heroCurrentTile = hero.getCurrentTile()
//                3.) heroCurrenTile.setHero(null)
//                4.) nexus.setHero(hero)
//                 */
//
//            } else {
//                throw new IllegalArgumentException("The action string: " + action +
//                        " has not been fully implemented for the correct functionality. " +
//                        "Please add another else if statement in World.start() to take "+
//                        "care of it.");
//            }
//        }
//    }
//
//    // currently only returns true and null
//    // have not decided on the case where false may be needed
//    public Boolean useInventory(Hero hero) {
//        Item selectedItem = null; // query for the item use
//
//        // null in this case represents the user's decision to not use anything from
//        // the inventory
//        if (selectedItem == null) {
//            return null;
//        }
//
//        if (selectedItem instanceof Armor) {
//            // Take off hero's current equipped armor and put it into the inventory
//            hero.equipArmor((Armor) selectedItem, io);
//
//        } else if (selectedItem instanceof Weapon) {
//            // Equip weapon
//            hero.equipWeapon((Weapon) selectedItem, io);
//
//        } else if (selectedItem instanceof Spell) {
//            // if the hero wants to use spell, is there any monster within range
//            // if yes there are monsters within range, query for which monster
//            // to attack
//
//        } else if (selectedItem instanceof Potion) {
//            hero.usePotion((Potion) selectedItem, io);
//
//        }
//        return true;
//    }
//
//
//    public boolean move(LVTile nextTile, Character character) {
//        if (nextTile == null) { // Meaning it is out of bounds
//            System.out.println("nextTile is null");
//            io.displayMovementMsg(false, null);
//            return false;
//
//        } else { // nextTile != null
//            if (nextTile.getTileBehavior() == null) {
//                throw new IllegalArgumentException("The tileBehavior of this tile "+
//                        "is null, which is not allowed, as all tiles should be "+
//                        "initialized with some tileBehavior.");
//            }
//        }
//
//        TileBehavior nextTileBehavior = nextTile.getTileBehavior();
//        if (nextTileBehavior instanceof InaccessibleTileBehavior) {
//            io.displayMovementMsg(false, null);
//            return false;
//
//        } else {
//            character.setCurrentTile(nextTile); // character has the tile
//            // tileMap has the character, for the purpose of printing and for other
//            // logic checks
//            if (character instanceof Hero) {
//                currentTile.setHero(null); // setting the single hero to be null
//                nextTile.setHero(character); // setting the single hero to be the hero passed in
//
//            } else { // the other instance of character is only Monster
//                currentTile.setMonster(null);
//                nextTile.setMonster(character);
//
//            }
//
//            io.displayMovementMsg(true, nextTile);
//            return true;
//        }
//    }
//
//    // *****************************************************************
//
//
//    protected LVTile getUpTile() {
//        int currX = currentTile.getCoordinate().getXCoordinate();
//        int currY = currentTile.getCoordinate().getYCoordinate();
//        if (isInBounds(currX, currY-1)) {
//            // return worldMap[currX][currY-1];
//            return worldMap[currY-1][currX];
//        }
//        return null;
//    }
//
//    protected LVTile getLeftTile() {
//        int currX = currentTile.getCoordinate().getXCoordinate();
//        int currY = currentTile.getCoordinate().getYCoordinate();
//        if (isInBounds(currX-1, currY)) {
//            // return worldMap[currX-1][currY];
//            return worldMap[currY][currX-1];
//        }
//        return null;
//    }
//    protected LVTile getDownTile() {
//        int currX = currentTile.getCoordinate().getXCoordinate();
//        int currY = currentTile.getCoordinate().getYCoordinate();
//        if (isInBounds(currX, currY+1)) {
//            // return worldMap[currX][currY+1];
//            return worldMap[currY+1][currX];
//        }
//        return null;
//    }
//    protected LVTile getRightTile() {
//        int currX = currentTile.getCoordinate().getXCoordinate();
//        int currY = currentTile.getCoordinate().getYCoordinate();
//        if (isInBounds(currX+1, currY)) {
//            // return worldMap[currX+1][currY];
//            return worldMap[currY][currX+1];
//        }
//        return null;
//    }
//
//    protected boolean isInBounds(int xIndex, int yIndex) {
//        if ((xIndex >= 0 && xIndex < worldMap[0].length ) &&
//                (yIndex >= 0 && yIndex < worldMap.length )) {
//            return true;
//        }
//        return false;
//    }
//
//    protected boolean isInBounds(Coordinate coodinate) {
//        int currX = coodinate.getXCoordinate();
//        int currY = coodinate.getYCoordinate();
//
//        return isInBounds(currX, currY);
//    }
//}