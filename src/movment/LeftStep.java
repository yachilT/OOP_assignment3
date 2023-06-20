package movment;

import tiles.Unit;

public class LeftStep implements Step{


    @Override
    public void act(Unit unit) {
        Position pos = unit.getPosition().add(-1, 0);
        unit.moveTo(unit.getGameBoard().get(pos));
    }
    @Override
    public boolean equals(Object object){
        return object instanceof LeftStep;
    }
}
