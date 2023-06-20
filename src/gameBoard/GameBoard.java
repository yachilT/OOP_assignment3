package gameBoard;

import enemies.Enemy;
import IO.DeathListener;
import players.Player;
import tiles.Empty;
import movment.Position;
import tiles.Tile;
import tiles.Unit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameBoard implements DeathListener {
    private List<Tile> tiles;
    private List<Enemy> enemies;
    public GameBoard(Tile[][] board, List<Enemy> enemies){
        this.enemies = enemies;
        tiles = new ArrayList<>();
        for(Tile[] line: board)
            tiles.addAll(Arrays.asList(line));
    }
    public Tile get(Position pos){
        for(Tile t : tiles){
            if(t.getPosition().equals(pos))
                return t;
        }
        throw new IndexOutOfBoundsException("The position is out of bounds");
    }
    public void remove(Enemy e){
        tiles.remove(e);
        this.enemies.remove(e);
        tiles.add(new Empty(e.getPosition()));
    }
    public String toString(){
        tiles = tiles.stream().sorted().collect(Collectors.toList());
        String output = "";
        int y = tiles.get(0).getPosition().y;
        for(Tile t: tiles){
            if(t.getPosition().y == y)
                output += t.toString();
            else{
                output += "\n";
                y = t.getPosition().y;
            }
        }
        return output;
    }


    @Override
    public void receiveDeath(Unit unit) {
        unit.acceptBoard(this);
    }
    public void receiveDeath(Enemy e){
        remove(e);
    }
    public void receiveDeath(Player p) {
        //stop the game
    }

    public List<Enemy> getEnemiesInRange(Unit unit, double range) {
        return enemies.stream().filter(e -> !e.equals(unit) && unit.getPosition().range(e.getPosition()) < range).toList();
    }
}
