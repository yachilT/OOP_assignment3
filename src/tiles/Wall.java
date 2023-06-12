package tiles;

public class Wall extends Tile{
    final static private char WALL_CHAR = '#';

    public Wall(Position position){
        super(position, WALL_CHAR);
    }

    @Override
    public void accept(Unit unit) {
        unit.MoveTo(this);
    }
}
