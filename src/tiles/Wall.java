package tiles;

public class Wall extends Tile{
    final static private char WALL_CHAR = '#';

    public Wall(Position position){
        super(WALL_CHAR);
    }

}
