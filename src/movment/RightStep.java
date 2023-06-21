package movment;

import gameBoard.TileGetter;
import tiles.Unit;

public class RightStep extends Step {


    public RightStep(TileGetter tileGetter) {
        super(tileGetter, new Position(1, 0));
    }

    @Override
    public boolean equals(Object object){
        return object instanceof RightStep;
    }
}
