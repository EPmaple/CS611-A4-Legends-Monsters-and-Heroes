import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LegendsOfValorWorld implements World{
    private List<Hero> heroes;
    private List<Monster> monsters;
    private LegendsOfValorIO io;
    private int round = 0;
    private LVCell currentTile;
    private LVMap worldMap;
    private CharacterFactory cfInstance;

    public LegendsOfValorWorld(List<Hero> heroes) {
        this.io = LegendsOfValorIO.getInstance();
        // setworldmap
        this.heroes = heroes;
        cfInstance = CharacterFactory.getInstance();
        // setheroes
        // set starting nexus
    }

    HashMap<String, Character> indexToCharacter = new HashMap<String, Character>();
    HashMap<Character, String> characterToIndex = new HashMap<Character, String>();

    private void init() {
        // initialize the heroes onto nexus
        List<Integer> lanesAvailable = new ArrayList<Integer>();
        lanesAvailable.add(1);
        lanesAvailable.add(2);
        lanesAvailable.add(3);

        for (Hero hero : heroes) {
            // query: do you want to place hero on lane 1, 2, or 3
            // need to pass in heroPositions

            Integer i = io.queryLaneForHeroPlacement(hero, lanesAvailable);
            lanesAvailable.remove(i); // so no lane can be chosen twice
            // assume i is returned, representing the lane number
            // 1,4,7  3*(i-1)+1
            int heroNexusCol = 3*(i-1)+1;
            LVCell heroNexus = worldMap.getCell(Constants.LV_BOTTOMMOST_ROW, heroNexusCol);
            // current position of the hero, for adjacency?
            heroPositions.put(hero, heroNexus);
            // fixed nexus for the hero, for RECALL
            heroToNexus.put(hero, heroNexus);

            // correspond H1 to a hero, M12 to a monster, for ex.
            indexToCharacter.put("H" + i, hero);
            characterToIndex.put(hero, "H" + i);

            // place the hero on the LVCell
            String[] heroNexusPositions = {"H" + i,
                    Constants.DEFAULT_LVCELL_MONSTERPOSITION};
            heroNexus.setPositions(heroNexusPositions);

            // also take advantage of the for loop to cache the monster's nexus
            // 2,5,8
            int monsterNexusCol = 3*(i-1)+2;
            LVCell monsterNexus = worldMap.getCell(Constants.LV_TOPMOST_ROW,
                    monsterNexusCol);
            monsterNexuses.add(monsterNexus);
        }

        // now need to spawn monsters at the beginning of the game, and also
        // every 8 rounds
        generateMonsters();
    }

    int monsterIndex = 0;
    // given monsters needed to be spawn every 8 rounds, then the CharacterFactory
    // has to exist in LVWorld as well
    private void generateMonsters() {
        // to obtain the highestHeroLvl and thus to spawn similar level monsters
        int highestHeroLvl = 0;
        for (Hero hero : heroes) {
            int currentHeroLvl = hero.getLevel();
            if (currentHeroLvl > highestHeroLvl) {
                highestHeroLvl = currentHeroLvl;
            }
        }

        for (LVCell monsterNexus : monsterNexuses) {
            if (monsterNexus.getPositions()[1].trim().isEmpty()) {
                // only if the spawn point is empty then we will spawn the monster
                Monster monster = cfInstance.createMonster(highestHeroLvl);
                // give it an encode
                indexToCharacter.put("M" + monsterIndex, monster);
                characterToIndex.put(monster, "M" + monsterIndex);
                // put it into monsterPositions for caching
                monsterPositions.put(monster, monsterNexus);
                // put it onto the LVCell by setPositions
                String[] monsterNexusPositions = {Constants.DEFAULT_LVCELL_HEROPOSITION,
                        "M" + monsterIndex++}; // also increment monsterIndex
                monsterNexus.setPositions(monsterNexusPositions);
            }
        }

    }

    HashMap<Hero, LVCell> heroPositions = new HashMap<Hero, LVCell>();
    HashMap<Hero, LVCell> heroToNexus = new HashMap<Hero, LVCell>();
    HashMap<Monster, LVCell> monsterPositions = new HashMap<Monster, LVCell>();
    List<LVCell>  monsterNexuses = new ArrayList<LVCell>();


    public void start() {
        // initial hero placement and initial monster spawn
        while(true) { // outer main game loop
            round += 1;
            System.out.println("round: " + round);
            // To spawn monsters every 8 rounds, another class, a monsterSpawner?
            for (Hero hero : heroes) {
                // instead of storing the tile in a character
                // we will have a HashMap to get the
                currentTile = heroPositions.get(hero);
                // query for hero
                heroMove(hero);
            }

            for (Monster monster : monsters) {
                currentTile = monsterPositions.get(monster);
                // monster move and attack logic
            }
        }
    }

    private void heroMove(Hero hero) {
        boolean turnConsumed = false;
        while (turnConsumed) {
            // display the map with the current hero highlighted
            System.out.println(worldMap.toString());
            io.displayMsg("It is currently " + hero.getName() + "'s turn.");
            String action = io.queryForUserAction(currentTile.getTileType());
            // query for user action, if
            // hero is on
            // the nexus
            // then allow for the option to trade (enter market)
            if (action.equalsIgnoreCase("W")) {
                // logic for getting the corresponding tile
                // try to move to that tile, and the corresponding movementMsg
                LVCell nextTile = worldMap.getUpTile(currentTile);

                if (move(nextTile, hero)) {
                    turnConsumed = true;
                }

            } else if (action.equalsIgnoreCase("A")) {
                LVCell nextTile = worldMap.getLeftTile(currentTile);
                if (move(nextTile, hero)) {
                    turnConsumed = true;
                }

            } else if (action.equalsIgnoreCase("S")) {
                LVCell nextTile = worldMap.getDownTile(currentTile);
                if (move(nextTile, hero)) {
                    turnConsumed = true;
                }

            } else if (action.equalsIgnoreCase("D")) {
                LVCell nextTile = worldMap.getRightTile(currentTile);
                if (move(nextTile, hero)) {
                    turnConsumed = true;
                }

            } else if (action.equalsIgnoreCase("U")) {
                /*
                 * Request which hero's inventory to use
                 *
                 * Then method to query for use of item from inventory:
                 * armor, gear, potion
                 */

                if (hero.getInventory().isEmpty()) {
                    String msg = hero.getName() + "'s inventory cannot be used " +
                            "because it is currently empty.";
                    io.displayMsg(msg);

                } else {
                    if (useInventory(hero) != null) { // true or false
                        turnConsumed = true;
                    }
                    /*
                    Else null, then the user exit out of inventoryUse without
                    using the inventory at all, and thus, turnConsumed==false
                     */
                }

            } else if (action.equalsIgnoreCase("I")) {
                /*
                On the map, a monster is given the abbreviation of m1, m2, etc.
                Thus, a good idea in this case is to have HashMap<String, Character>
                 */
                /*
                3.) display info for all heroes, or for all monsters (this)
                 */
                String characterCategory = io.queryForCharacterCategory();
                if (characterCategory != null) {
                    if (characterCategory.equals("hero")) {
                        io.displayHeroInfo(heroes, characterToIndex);

                    } else { // .equals("monster")
                        io.displayMonsterInfo(monsters, characterToIndex);

                    }
                }

            } else if (action.equalsIgnoreCase("M")) {
                // The option M/m is only shown when onMarketTile==true,
                // meaning currentTile is MarketTile
                List<Hero> tempHeroes = new ArrayList<Hero>();
                tempHeroes.add(hero);
                ((LVHeroNexusTile)currentTile).interact(tempHeroes);

            } else if (action.equalsIgnoreCase("T")) {
                io.displayTutorial(); // need to change tutorial

            } else if (action.equalsIgnoreCase("P")) {
                // logic for teleporting hero to the other two lanes
                /*
                Current idea: (with option prior)
                1.) get the lane of the current hero
                2.) provide user with queries for the other two lanes
                3.) after getting the lane from the user, query for targetHero
                4.) after getting the targetHero, if no possible move, then repeat step
                    3 for a valid choice; else, show possible tiles to move on to
                5.) then hero.setCurrentTile(targetTeleportTile)
                 */
                LVCell currentHeroTile = heroPositions.get(hero);
                List<Integer> lanesAvailable =
                        worldMap.getLanesForTeleportation(currentHeroTile);



            } else if (action.equalsIgnoreCase("R")) {
                // logic for teleporting hero back to nexus
                /*
                Current idea:
                We do not want to modify the hero class to add another data
                attribute of say, nexus. Thus, in LVWorld, we will have a HashMap
                with key-value pair (Hero : Tile), where Tile/Coordinate is the
                nexus associated with the Hero
                 */
                LVCell nexus = heroToNexus.get(hero);
                if (move(nexus, hero)) {
                    turnConsumed = true;
                } else {
                    // This means that there is another hero that is currently on
                    // this hero's nexus, and thus we cannot recall
                    io.displayMsg("Failed to recall as there is currently another hero "+
                            "on this hero's nexus.");
                }


            } else {
                throw new IllegalArgumentException("The action string: " + action +
                        " has not been fully implemented for the correct functionality. " +
                        "Please add another else if statement in World.start() to take "+
                        "care of it.");
            }
        }
    }

    // currently only returns true and null
    // have not decided on the case where false may be needed
    public Boolean useInventory(Hero hero) {
        Item selectedItem = null; // query for the item use

        // null in this case represents the user's decision to not use anything from
        // the inventory
        if (selectedItem == null) {
            return null;
        }

        if (selectedItem instanceof Armor) {
            // Take off hero's current equipped armor and put it into the inventory
            hero.equipArmor((Armor) selectedItem, io);

        } else if (selectedItem instanceof Weapon) {
            // Equip weapon
            hero.equipWeapon((Weapon) selectedItem, io);

        } else if (selectedItem instanceof Spell) {
            // if the hero wants to use spell, is there any monster within range
            // if yes there are monsters within range, query for which monster
            // to attack
            ...

        } else if (selectedItem instanceof Potion) {
            hero.usePotion((Potion) selectedItem, io);

        }
        return true;
    }


    public boolean move(LVCell nextTile, Character character) {
        /*
        what are the conditions for which a character cannot move onto to the next tile

        Hero cannot move to a tile if:
        1.) there is currently another hero on the tile that the current hero is trying
        to move to
        2.) the tile is out of bounds
        3.) the tile is NonAccessible
        4.) there is a monster in the same row as the currentTile of the hero, and the
        hero is trying to move up

        Monster cannot move to a tile if:
        1.) there is currently another monster on the tile that the current monster is
        trying to move to
        2.) the tile is out of bounds
        3.) the tile is NonAccessible

        4.) there is a hero in the same row as the currentTile of the hero, and the
        hero is trying to move up
        However, the above 4th condition should not be necessary, as the monsters
        prioritize attack over moving. Thus, if there is any hero within the attack
        range of the monster, then it will not attempt to move down; else there is no
        heroes within the attack range of the monster, and thus the monster may
        move with the above three conditions
         */
        if (nextTile == null) { // Meaning it is out of bounds
            System.out.println("nextTile is null");
            io.displayMovementMsg(false, null);
            return false;

        } else if (String.valueOf(nextTile.getTileType()).equals(Constants.LVNonAccessibleCellType)) {
            io.displayMovementMsg(false, null);
            return false;

        } else {
            // move the character onto this nextTile
            if (character instanceof Hero) {
                /*
                priority: first check if there is a monster in the same row as the
                current tile of the hero

                then second, check if there is a hero in the nextTile
                 */

                // first, monster in the same row and the hero is trying to move pass
                // the monster
                if (worldMap.checkRowForMonster(currentTile) && nextTile.getRow() -
                        currentTile.getRow() < 0) {
                    // display message that the hero cannot move to that position as
                    // there is a monster in the same row
                    io.displayMovementMsg(false, null);
                    return false;

                } else {
                    String[] positions = nextTile.getPositions();
                    // meaning that the nextTile is not occupied by a hero already
                    if (positions[0].trim().isEmpty()) {
                        // the character has moved on to the next tile
                        positions[0] = characterToIndex.get(character);
                        nextTile.setPositions(positions);

                        // the character has moved off the last tile
                        positions = currentTile.getPositions();
                        positions[0] = Constants.DEFAULT_LVCELL_HEROPOSITION;
                        currentTile.setPositions(positions);

                        io.displayMovementMsg(true, nextTile);
                        return true;

                    } else {
                        io.displayMovementMsg(false, null);
                        return false;
                    }
                }

            } else { // the other instance of character is only Monster
                // first check if there is a monster in the nextTile
                String[] positions = nextTile.getPositions();
                // meaning that the nextTile is not occupied by a monster already
                if (positions[1].trim().isEmpty()) {
                    // the character has moved on to the next tile
                    positions[1] = characterToIndex.get(character);
                    nextTile.setPositions(positions);

                    // the character has moved off the last tile
                    positions = currentTile.getPositions();
                    positions[1] = Constants.DEFAULT_LVCELL_MONSTERPOSITION;
                    currentTile.setPositions(positions);

                    io.displayMovementMsg(true, nextTile);
                    return true;

                } else {
                    io.displayMovementMsg(false, null);
                    return false;
                }

            }
        }
    } // end of method()

    // *****************************************************************

}