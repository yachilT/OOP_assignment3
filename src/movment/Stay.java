package movment;

import tiles.Position;

public class Stay implements Step {
    @Override
    public Position calcNextPos(Position pos) {
        return pos;
    }
}
