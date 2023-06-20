package movment;

import tiles.Unit;

public class UpStep implements Step{

    @Override
    public void act(Unit unit) {
        Position pos = unit.getPosition().add(0, 1);
        unit.moveTo(unit.getGameBoard().get(pos));
    }
    @Override
    public boolean equals(Object object){
        return object instanceof UpStep;
    }
}
