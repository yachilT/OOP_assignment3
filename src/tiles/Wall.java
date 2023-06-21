package tiles;

import movment.Position;

public class Wall extends Tile{
    final static public char WALL_CHAR = '#';

    public Wall(){
        super(WALL_CHAR);
    }

    public void initialize(Position pos){
        super.initialize(position);
    }

    @Override
    public void acceptMove(Unit unit){
        unit.moveTo(this);
    }
}
