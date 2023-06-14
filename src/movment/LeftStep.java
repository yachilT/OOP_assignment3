package movment;

import tiles.Position;

public class LeftStep implements Step{

    @Override
    public Position calcNextPos(Position pos) {
        return new Position(pos.x - 1,pos.y);
    }
}
