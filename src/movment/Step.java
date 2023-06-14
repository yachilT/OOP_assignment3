package movment;

import tiles.Position;

public interface Step {
    public Position calcNextPos(Position pos);
}
