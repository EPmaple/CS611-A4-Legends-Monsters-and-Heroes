import java.util.*;

public class LVGame {
    private IO io;
    private CharacterFactory cfInstance;
    private LVMapFactory mfInstance;

    private String msg = "";

    public LVGame(IO io) {
        this.io = io;
        cfInstance = CharacterFactory.getInstance();
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
                /*
                When creating a new world
                 */
                // query for which lane to put this new hero in
                heroes.get(i);

            }
        }
    } // end of gameStarts

}
