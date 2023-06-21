package tiles;

import movment.Position;

public class Empty extends Tile {
    final public static char EMPTY_CHAR = '.';
    public Empty() {
        super(EMPTY_CHAR);
    }

    public void initialize(Position pos){
        super.initialize(position);
    }
    @Override
    public void acceptMove(Unit unit){
        unit.moveTo(this);
    }

}
