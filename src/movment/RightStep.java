package movment;

import tiles.Position;

public class RightStep implements Step{

    @Override
    public Position calcNextPos(Position pos) {
        return new Position(pos.x + 1,pos.y);
    }
}
