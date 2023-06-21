package gameBoard;

import movment.Position;
import tiles.Tile;

public interface TileGetter {
    public Tile getTile(Position pos);
}
