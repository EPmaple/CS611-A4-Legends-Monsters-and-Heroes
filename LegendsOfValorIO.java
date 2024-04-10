import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public void displayAllCharacterInfo() {

    }

    public void displayTutorial() {
        String msg = "Tutorial for Legends of Valor has not been implemented yet!";
        displayMsg(msg);
    }

    public void displayMovementMsg(boolean isMovementSuccessful, LVTile tile) {
        if ((isMovementSuccessful && tile == null) ||
                !isMovementSuccessful && tile != null) {
            throw new IllegalArgumentException("The valid pairings are: "+
                    "false, null ; true, validTile");
        }

        String msg = "";

        if (isMovementSuccessful) {
            msg += "Successfully moved to tile at " + tile.toString();
        } else {
            msg += "Failed to move in the requested direction.";
        }

        displayMsg(msg);
    }

    // ***************************************************
    // query

    public String queryForUserAction(TileBehavior tileBehavior) {
        String msg = "Enter W/w to move up, A/a to move left, S/s to move down, " +
                "D/d to move right, U/u to use the inventory of a hero," +
                " I/i to show information about the heroes, T/t for tutorial, "  +
                " P/p to teleport to another lane, R/r to recall back to nexus";
        List<String> commands = new ArrayList<String>();
        commands.addAll(Arrays.asList("W", "A", "S", "D", "U", "I", "T", "P", "R"));

        if (tileBehavior instanceof MarketTileBehavior) {
            msg += ", M/m to enter market";
            commands.add("M");
        }
        msg += ": ";

        String action = queryString(msg, commands);
        return action;
    }
}
