package tiles;

public class Empty extends Tile {
    final private static char EMPTY_CHAR = '.';
    public Empty(Position position) {
        super(EMPTY_CHAR);
    }

    @Override
    public void accept(Unit unit) {
          unit.MoveTo(this);
    }
}
