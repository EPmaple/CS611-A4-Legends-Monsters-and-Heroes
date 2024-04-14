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

    public Integer queryLaneForTeleportation(Hero hero, List<Integer> ints) {
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
}
