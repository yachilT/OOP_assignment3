package tiles;

import jdk.jshell.spi.ExecutionControl;
import messeages.MessageCallback;
import resources.Health;

import java.util.NoSuchElementException;

public abstract class Unit extends Tile {
    protected String name;
    protected int attackPts;
    protected int defensePts;
    protected Health health;

    public Unit(char character, String name,int health, int attackPts, int defensePts) {
        super(character);
        this.name = name;
        this.attackPts = attackPts;
        this.defensePts = defensePts;
        this.health = new Health(health,health);
    }
    public void initialize(Position position, MessageCallback messageCallback){
        super.initialize(position);

    }
    public int attack(){
        return -1;
    }
    public int defend(){
        return -1;
    }

    public void MoveTo(Tile tile) {
        tile.accept(this);
    } //He called it interact

    public void MoveTo(Empty empty){
        Position temp = this.position;
        this.position = empty.position;
        empty.position = temp;
    }

    public void MoveTo(Wall wall){
        // Do nothing
    }
    public String getName() {
        return name;
    }
    public String describe(){
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d",getName(),health.toString(),attackPts,defensePts);
    }

}
