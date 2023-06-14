package tiles;

public class Empty extends Tile {
    final private static char EMPTY_CHAR = '.';
    public Empty(Position position) {
        super(EMPTY_CHAR);
    }

    @Override
    public void acceptMove(Unit unit){
        unit.moveTo(this);
    }

}
