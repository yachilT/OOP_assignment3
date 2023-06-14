package gameBoard;

import enemies.Enemy;
import tiles.Empty;
import tiles.Position;
import tiles.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameBoard {
    private List<Tile> tiles;
    public GameBoard(Tile[][] board){
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
}
