import java.util.*;

public class LVGame extends Game{
    private LVMapFactory mfInstance;

    private String msg = "";

    public LVGame(IO io) {
        super(io);
        mfInstance = new LVMapFactory();
    }

    public void gameStarts() {
        boolean continueToPlay = true;

        while (continueToPlay) {
            /*
            Query for the 3 heroes, and for each hero, also query for
            which lane the user wants to put that hero in
            Heroes spawn in the left space of their lane's Nexus
             */
            List<Hero> heroes = new ArrayList<Hero>();
            // third, make a for loop to select the number of heroes
            for (int i = 0; i < 3; i++) {
                boolean heroSelected = false;
                while (!heroSelected) {
                    // first ask which type of hero he wants to select
                    io.displayMsg("You are currently in the hero selection phase, "+
                            "and you will have to select " + (3-i) + " more heroes.");
                    HeroType herotype = io.queryForHeroType();
                    List<Hero> typedHeroes = cfInstance.getSpecifiedHeroes(herotype);

                    // second ask which hero he wants to select (w/ prior)
                    Hero selectedHero = io.queryForHeroSelection(typedHeroes, herotype);
                    if (selectedHero != null) {
                        heroSelected = true;
                        heroes.add(selectedHero);
                        typedHeroes.remove(selectedHero);
                        msg = "The " + herotype + ", " + selectedHero.getName() + " has been"+
                                " selected. \n";
                        io.displayMsg(msg);
                    } // null => go on to the next iteration of the while loop
                }
<<<<<<< Updated upstream:src/LVGame.java
                /*
                When creating a new world
                 */
                // query for which lane to put this new hero in
                heroes.get(i);

=======
>>>>>>> Stashed changes:LVGame.java
            }

            LVMap worldMap = (LVMap) mfInstance.createBoard(8, 8);
            LegendsOfValorWorld world = new LegendsOfValorWorld(heroes, worldMap);
            world.start();

            msg = "Would you like to play another round of Legends of Valor?";
            continueToPlay = io.queryBoolean(msg, "y", "n");
        }
    } // end of gameStarts

}
