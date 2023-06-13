package tiles;

public abstract class Tile implements Comparable<Tile>{
    protected char character;
    protected Position position;

    protected Tile(char character){this.character = character;}
    protected void initialize(Position position){this.position = position;}
    public char getTile(){return character;}
    public Position getPosition(){return position;}
    public void setPosition(Position position){this.position = position;}
    public abstract void accept(Unit unit);
    public int compareTo(Tile tile){return getPosition().compareTo(tile.getPosition());}
    @Override
    public String toString() {
        return character+"";
    }
}