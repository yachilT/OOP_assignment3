package tiles;

public abstract class Tile {
    protected char character;
    protected Position position;

    public Tile(Position position, char character) {
        this.position = position;
        this.character = character;
    }
    @Override
    public String toString() {
        return character+"";
    }

    public abstract void accept(Unit unit);
}
