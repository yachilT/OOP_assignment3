package movment;

import tiles.Position;

public class DownStep implements Step{
    @Override
    public Position calcNextPos(Position pos) {
        return new Position(pos.x, pos.y - 1);
    }
}