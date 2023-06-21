package movment;

import gameBoard.TileGetter;
import tiles.Unit;

public class DownStep extends Step {

    public DownStep(TileGetter tileGetter) {
        super(tileGetter, new Position(0, -1));
    }

    @Override
    public boolean equals(Object object){
        return object instanceof DownStep;
    }
}
