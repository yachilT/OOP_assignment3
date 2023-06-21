package movment;

import gameBoard.TileGetter;
import tiles.Tile;
import tiles.Unit;

public abstract class Step implements Action {
    protected TileGetter tileGetter;
    protected Position posToAdd;
    public Step(TileGetter tileGetter, Position posToAdd){
        this.tileGetter = tileGetter;
        this.posToAdd = posToAdd;
    }

    @Override
    public void act(Unit unit) {
        Position pos = unit.getPosition().add(posToAdd);
        unit.moveTo(tileGetter.getTile(pos));
    }
}
