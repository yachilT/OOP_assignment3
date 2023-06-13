package tiles;

import enemies.Enemy;
import messeages.MessageCallback;
import players.Player;
import resources.Health;

import java.util.Random;

public abstract class Unit extends Tile {
    protected String name;
    protected int attackPts;
    protected int defensePts;
    protected Health health;
    protected Random random;

    public Unit(char character, String name,int health, int attackPts, int defensePts) {
        super(character);
        this.name = name;
        this.attackPts = attackPts;
        this.defensePts = defensePts;
        this.health = new Health(health,health);
        this.random = new Random();
    }
    public void initialize(Position position, MessageCallback messageCallback){
        super.initialize(position);
    }

    private int attack() {
        return random.nextInt(0, attackPts);
    }
    public int defend(){
        return random.nextInt(0, defensePts);
    }

    public void combat(Unit defender){
        int damage = this.attack() - defender.defend();
        if(damage > 0)
            defender.health.decreaseHealth(damage);
    }

    public boolean isDead(){
        return health.getHealthAmount() == 0;
    }
    public void moveTo(Tile tile) {
        tile.accept(this);
    } //He called it interact

    public void moveTo(Empty empty){
        Position temp = this.position;
        this.position = empty.position;
        empty.position = temp;
    }

    public void moveTo(Wall wall){
        // Do nothing
    }

    public abstract void moveTo(Enemy enemy);

    public abstract void moveTo(Player player);


    public String getName() {
        return name;
    }
    public String describe(){
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", name, health.toString(), attackPts, defensePts);
    }

}
