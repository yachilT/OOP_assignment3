package gameBoard;

import IO.InputReader;
import IO.MessageCallback;
import enemies.Enemy;
import movment.*;
import players.Player;
import tiles.Empty;
import tiles.Tile;
import tiles.TileFactory;
import tiles.Wall;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LevelFactory {
    TileFactory tileFactory;

    public LevelFactory(){
        tileFactory = new TileFactory();
    }
    public Level produceLevel(String path, MessageCallback m, InputReader r, Player player){
        char[][] tilesChars = readLevel(path);
        Level level = new Level();
        List<Enemy> enemies = new ArrayList<>();
        List<Tile> tiles = new ArrayList<>();

        for (int i = 0; i < tilesChars.length; i++) {
            for (int j = 0; j < tilesChars[i].length; j++) {

                if (tilesChars[i][j] == Wall.WALL_CHAR) {
                    Wall wall = tileFactory.produceWall();
                    wall.initialize(new Position(i, j));
                    tiles.add(wall);
                } else if (tilesChars[i][j] == Empty.EMPTY_CHAR) {
                    Empty empty = tileFactory.produceEmpty();
                    empty.initialize(new Position(i, j));
                    tiles.add(empty);
                } else if (tilesChars[i][j] == Player.CHARACTER) {
                    player.initialize(new Position(i, j), m, initActions(level), level::over, r, level::getEnemiesInRange);
                    tiles.add(player);
                } else {
                    Enemy enemy = tileFactory.produceEnemy(String.valueOf(tilesChars[i][j]));
                    enemy.initialize(new Position(i, j), m, initActions(level),
                            () -> {
                                player.receiveXP(enemy.getXpValue());
                                level.remove(enemy);
                            }
                    );
                    enemies.add(enemy);
                    tiles.add(enemy);
                }
            }
        }

        level.initialize(player, enemies, tiles);
        return level;
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
