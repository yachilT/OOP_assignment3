package movment;

import gameBoard.TileGetter;
import tiles.Unit;

public class UpStep extends Step {
    public UpStep(TileGetter tileGetter) {
        super(tileGetter, new Position(0, 1));
    }

    @Override
    public boolean equals(Object object){
        return object instanceof UpStep;
    }
}
