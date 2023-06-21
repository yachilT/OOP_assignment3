package gameBoard;

import IO.InputReader;
import IO.MessageCallback;
import enemies.Enemy;
import movment.*;
import players.Player;
import tiles.Empty;
import tiles.TileFactory;
import tiles.Wall;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LevelFactory {
    TileFactory tileFactory;

    public LevelFactory(){
        tileFactory = new TileFactory();
    }
    public Level produceLevel(String path, MessageCallback m, InputReader r, Player player){
        char[][] tiles = readLevel(path);
        Level level = new Level();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {

                if (tiles[i][j] == Wall.WALL_CHAR) {
                    Wall wall = tileFactory.produceWall();
                    wall.initialize(new Position(i, j));
                } else if (tiles[i][j] == Empty.EMPTY_CHAR) {
                    Empty empty = tileFactory.produceEmpty();
                    empty.initialize(new Position(i, j));
                } else if (tiles[i][j] == Player.CHARACTER) {
                    player.initialize(new Position(i, j), m, initActions(level), level::over, r, level::getEnemiesInRange);
                } else {
                    Enemy enemy = tileFactory.produceEnemy(String.valueOf(tiles[i][j]));
                    enemy.initialize(new Position(i, j), m, initActions(level),
                            () -> {
                                player.receiveXP(enemy.getXpValue());
                                level.remove(enemy);
                            }
                    );
                }


                // insert tiles
            }
        }
    }

    private char[][] readLevel(String path) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" +
                    e.getStackTrace());
        }

        char[][] tiles = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = lines.get(i).charAt(j);
            }
        }
        return tiles;
    }
    private Map<String, Action> initActions(Level level){
        TileGetter tileGetter = level::get;
        return Map.of(
                "w", new UpStep(tileGetter),
                "s", new DownStep(tileGetter),
                "a", new LeftStep(tileGetter),
                "d", new RightStep(tileGetter),
                "q", new Stay(),
                "e", new SpecialAbility()
        );
    }

}
