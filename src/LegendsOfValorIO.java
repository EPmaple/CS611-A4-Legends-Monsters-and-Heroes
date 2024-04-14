import java.util.*;

public class LegendsOfValorIO extends IO{
    private static LegendsOfValorIO instance;

    private LegendsOfValorIO() {

    }

    public static LegendsOfValorIO getInstance() {
        if (instance == null) {
            instance = new LegendsOfValorIO();
        }
        return instance;
    }

    // ***************************************************
    // display

    public void displayTutorial() {
        String msg = "Tutorial for Legends of Valor has not been implemented yet!";
        displayMsg(msg);
    }

    public void displayMovementMsg(boolean isMovementSuccessful, LVCell tile) {
        if ((isMovementSuccessful && tile == null) ||
                !isMovementSuccessful && tile != null) {
            throw new IllegalArgumentException("The valid pairings are: "+
                    "false, null ; true, validTile");
        }

        String msg = "";

        if (isMovementSuccessful) {
            msg += "Successfully moved to tile at (" + tile.getRow() + "," + tile.getCol()
                    + ")";
        } else {
            msg += "Failed to move in the requested direction.";
        }

        displayMsg(msg);
    }

    public void displayHeroInfo(List<Hero> heroes, HashMap<Character, String> characterToIndex) {
        displayMsg("Below are information about heroes on the map: ");
        for (Hero hero : heroes) {
            String msg =
                    Colors.ANSI_GREEN + characterToIndex.get(hero) + Colors.ANSI_RESET +
                            hero.toString();
            displayMsg(msg);
        }
        displayMsg("Above are information about heroes on the map: ");
    }

    public void displayMonsterInfo(List<Monster> monsters,
                             HashMap<Character, String> characterToIndex) {
        displayMsg("Below are information about monsters on the map: ");
        for (Monster monster : monsters) {
            String msg =
                    Colors.ANSI_RED + characterToIndex.get(monster) + Colors.ANSI_RESET +
                            monster.toString();
            displayMsg(msg);
        }
        displayMsg("Above are information about monsters on the map: ");
    }

    // ***************************************************
    // query


    public String queryForCharacterCategory() {
        String msg = "Enter \'hero\' to display info of all heroes, \'monster\' to "+
                "display info of all monsters: ";
        List<String> commands = new ArrayList<String>();
        commands.addAll(Arrays.asList("HERO", "MONSTER"));

        String action = queryString(msg, commands);
        return action.toLowerCase(); // could return null for prior
    }

    public String queryForUserAction(char tileType) {
        String msg = "Enter W/w to move up, A/a to move left, S/s to move down, " +
                "D/d to move right, U/u to use the inventory of a hero," +
                " I/i to show information about the heroes, T/t for tutorial, "  +
                " P/p to teleport to another lane, R/r to recall back to nexus";
        List<String> commands = new ArrayList<String>();
        commands.addAll(Arrays.asList("W", "A", "S", "D", "U", "I", "T", "P", "R"));

        if (tileType == 'H') {
            msg += ", M/m to enter market";
            commands.add("M");
        }
        msg += ": ";

        String action = queryString(msg, commands);
        return action;
    }

    // assuming List<String> strs contains ("1","2","3")
    public Integer queryLaneForHeroPlacement(Hero hero, List<Integer> ints) {
        // queryString may return null, handle this case
        String msg = "Would you like to place " + hero.getName() + " on ";
        for (Integer laneNum : ints) {
            msg += ", lane " + laneNum + " ";
        }

        Integer laneSelection = null;
        while (laneSelection == null) {
            laneSelection = queryInt(msg, ints);
        }

        return laneSelection;
    }

    public Integer queryLaneForTeleportation(Hero hero, List<Integer> ints,
                                             HashMap<Character, String> characterToIndex) {
        // queryString may return null, handle this case
        String msg =
                "Enter the lane you want " + characterToIndex.get(hero) + " "
                        + hero.getName() + " to teleport to: ";
        for (Integer laneNum : ints) {
            msg += laneNum + ", ";
        }

        Integer laneSelection = queryIntWithPrior(msg, ints);

        return laneSelection;
    }

    public Hero queryHeroForTeleportation(List<Hero> heroes, int laneSelection,
                    HashMap<Character, String> characterToIndex,
                    HashMap<String, Character> indexToCharacter) {
        List<Hero> targetHeroes = new ArrayList<Hero>();
        for (Hero hero : heroes) {
            int heroCol = hero.getCol();
            System.out.println(heroCol);
            if (3 * (laneSelection - 1) + 1 == heroCol ||
                    3 * (laneSelection - 1) == heroCol) {
                targetHeroes.add(hero);
            }
        }
        // paladin

        if (targetHeroes.size() == 0) {
            throw new IllegalArgumentException("Method getLanesForTeleportation" +
                    " have correctly detected that there is a hero in lane " +
                    laneSelection + ", so the code here must be incorrect.");
        }

        String msg = "Which hero in lane " + laneSelection + " would you like to "+
                "teleport to: ";
        List<String> targetHeroIndexes = new ArrayList<String>();
        for (Hero hero : targetHeroes) {
            msg += characterToIndex.get(hero) + ", ";
            targetHeroIndexes.add(characterToIndex.get(hero));
        }

        String targetHeroIndexSelection = queryString(msg, targetHeroIndexes);

        if (targetHeroIndexSelection == null) {
            return null;
        } else {
            return (Hero)indexToCharacter.get(targetHeroIndexSelection);
        }
    }

    public LVCell queryForTpDirInRelationToTargetHero(LVMap worldMap,
                                                   LVCell targetHeroTile) {
        // can only teleport to right, left, down of the current tile

        HashMap<String, LVCell> directionToTiles = new HashMap<String, LVCell>();
        directionToTiles.put("right", worldMap.getRightTile(targetHeroTile));
        directionToTiles.put("left", worldMap.getLeftTile(targetHeroTile));
        directionToTiles.put("down", worldMap.getDownTile(targetHeroTile));

        List<String> directionsStr = new ArrayList<String>();

        for (Map.Entry<String, LVCell> entry : directionToTiles.entrySet()) {
            LVCell tile = entry.getValue();
            if (tile != null) {
                // tile is not NonAccessible and tile is not does not have a hero on it
                if (tile.getTileType() != '&' &&
                        tile.getPositions()[0].trim().isEmpty()) {
                    directionsStr.add(entry.getKey());
                }
            }
        }

        // directionsStr now have only valid tiles for teleportation
        String msg = "Enter the direction in relation to the targetHero you want " +
                "to teleport to: ";
        for (String direction : directionsStr) {
            msg += direction + ", ";
        }

        String selectedDirection = queryString(msg, directionsStr);

        if (selectedDirection == null) {
            return null;
        } else {
            return directionToTiles.get(selectedDirection);
        }
    }
}
