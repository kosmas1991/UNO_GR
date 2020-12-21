import java.util.ArrayList;

public class Players {
    private final ArrayList<Player> players;
    private static int currentPlayer = 0;

    public Players(String playerName, int noOfBots) {
        this.players = createPLayers(playerName, noOfBots);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Player> createPLayers(String playerName, int noOfBots) {
        ArrayList<Player> pl = new ArrayList<>();
        pl.add(new Player(playerName, 1, false));
        for (int i = 1; i <= noOfBots; i++)
            pl.add(new Player("Bot " + i, i, true));
        return pl;
    }

    public static int getNextPlayerPosition(boolean forward) {
        if (forward)
            return ++currentPlayer;
        else
            return --currentPlayer;
    }
}
