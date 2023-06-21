package gameBoard;

import enemies.Enemy;
import players.Player;
import tiles.Empty;
import movment.Position;
import tiles.Tile;

import java.util.List;
import java.util.stream.Collectors;

public class Level {
    private List<Tile> tiles;
    private List<Enemy> enemies;
    private Player player;
    public Level(){
    }

    public void over(){

    }
    public void initialize(Player player, List<Enemy> enemies, List<Tile> tiles){
        this.player = player;
        this.tiles = tiles;
        this.enemies = enemies;
    }
    public Tile get(Position pos){
        for(Tile t : tiles){
            if(t.getPosition().equals(pos))
                return t;
        }
        throw new IndexOutOfBoundsException("The position is out of bounds");
    }
    public void remove(Enemy e){
        this.enemies.remove(e);
        Empty emptyTile = new Empty();
        emptyTile.initialize(e.getPosition());
        tiles.add(emptyTile);
    }


    public void addTile(Tile t) {
        tiles.add(t);
    }
    public void addEnemy(Enemy e) {
        addTile(e);
        enemies.add(e);
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




    public List<Enemy> getEnemiesInRange(Position pos, double range) {
        return enemies.stream().filter(e -> !e.getPosition().equals(pos) && pos.range(e.getPosition()) < range).toList();
    }
}