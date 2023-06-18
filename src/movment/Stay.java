package movment;

public class Stay implements Step {
    @Override
    public Position calcNextPos(Position pos) {
        return pos;
    }
}
