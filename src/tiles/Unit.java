package tiles;

public abstract class Unit extends Tile {
    private String name;
    private int attackPts;
    private int defensePts;

    public Unit(char character, Position pos, String name, int attackPts, int defensePts) {
        super(pos, character);
        this.name = name;
        this.attackPts = attackPts;
        this.defensePts = defensePts;
    }

    public void MoveTo(Tile tile) {
        tile.accept(this);
    }

    public void MoveTo(Empty empty){
        Position temp = this.position;
        this.position = empty.position;
        empty.position = temp;
    }

    public void MoveTo(Wall wall){
        // Do nothing
    }


}
