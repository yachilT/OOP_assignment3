package movment;

public class UpStep implements Step{

    @Override
    public Position calcNextPos(Position pos) {
        return new Position(pos.x,pos.y + 1);
    }
}
