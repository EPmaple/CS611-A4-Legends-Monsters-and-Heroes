

public class LVSummary extends Summary {
    private static LVSummary instance;

    private LVSummary(IO io) {
        super(io);
    }

    public static LVSummary getInstance() {
        if (instance == null) {
            instance = new LVSummary(new IO());  // Ensuring only one instance
        }
        return instance;
    }

    public void printVictorySummary(int round) {
        io.displayMsg(Colors.ANSI_YELLOW + "Victory! " + Colors.ANSI_RESET +
                "You've won the game in " + Colors.ANSI_YELLOW + round +
                Colors.ANSI_RESET + " rounds");
        super.printSummary();
    }

    public void printLossSummary(int round) {
        io.displayMsg(Colors.ANSI_RED + "Loss! " + Colors.ANSI_RESET +
                "You've loss the game in " + Colors.ANSI_RED + round +
                Colors.ANSI_RESET + " rounds");
        super.printSummary();
    }
}
