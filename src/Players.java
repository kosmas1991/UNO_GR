import java.util.ArrayList;

public class Players {
    private final ArrayList<Player> players;

    public Players(String playerName,int noOfBots) {
        this.players = createPLayers(playerName,noOfBots);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Player> createPLayers (String playerName,int noOfBots) {
        ArrayList<Player> pl = new ArrayList<>();
        pl.add(new Player(playerName,1));
        for (int i=1;i<=noOfBots;i++)
            pl.add(new Player("Bot " + i,i));
        return pl;
    }


}
