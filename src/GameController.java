import IO.InputReader;
import IO.MessageCallback;
import enemies.Enemy;
import gameBoard.Level;
import gameBoard.TileGetter;
import movment.*;
import players.Player;
import tiles.Empty;
import tiles.TileFactory;
import tiles.Wall;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GameController {
    private Level level;

    private MessageCallback m;
    private InputReader r;
    private TileFactory factory;
    private Map<String, Supplier<Action>> actions;
    private Player player;


    public GameController(MessageCallback m, InputReader r) {
        factory = new TileFactory();
        actions = initActions();
        this.m = m;
        this.r = r;
    }


    private Map<String, Action> getActions(){
        return actions.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().get()));
    }


    public void initLevel(char[][] level, Player player){

        }
    }
}
