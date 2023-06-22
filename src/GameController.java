import IO.CLI;
import IO.InputReader;
import IO.MessageCallback;
import enemies.Enemy;
import gameBoard.Level;
import gameBoard.LevelFactory;
import gameBoard.TileGetter;
import movment.*;
import players.Player;
import tiles.Empty;
import tiles.TileFactory;
import tiles.Wall;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameController {


    private TileFactory factory;
    private CLI cli;
    private Player player;

    private boolean isOver;


    public GameController() {

        factory = new TileFactory();
        this.cli = new CLI();
    }

    public void run(String levelDir){
        int idx = displayPlayerMenu();
        Player player = factory.producePlayer(idx);
        try {
            List<Path> files = Files.list(Paths.get(levelDir)).toList();
            if (files.size() == 0){
                cli.getMessageCallback().send(levelDir + " is empty");
            }
            else {
                Iterator<Path> levelsPaths = files.iterator();
                LevelFactory levelFactory = new LevelFactory();

                while (levelsPaths.hasNext() && !isOver) {
                    Level currLevel = levelFactory.produceLevel(levelsPaths.next(), cli.getMessageCallback(), cli.getInputReader(), () -> isOver = true, player);
                    currLevel.start(this::displayBoard);
                }

                if (isOver)
                    cli.getMessageCallback().send("Game over :(");
                else
                    cli.getMessageCallback().send("You've won!");
            }
        }
        catch (IOException e) {
            cli.getMessageCallback().send(e.getMessage() + "\n" + e.getStackTrace());

        }
    }

    public int displayPlayerMenu(){
        List<Player> players = factory.listPlayers();
        int i = 1;
        for (Player p: players) {
            cli.getMessageCallback().send(i + ". " + p.describe());
            i++;
        }
        String input = cli.getInputReader().read();
        while (true) {
            try {
                return Integer.parseInt(input);
            }
            catch (NumberFormatException e)
            {
                input = cli.getInputReader().read();
            }
        }
    }

    public void displayBoard(String board) {
        cli.getMessageCallback().send(board);
    }




}
