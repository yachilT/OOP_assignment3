package tiles;

import enemies.Enemy;
import gameBoard.*;
import messeages.MessageCallback;
import movment.DownStep;
import movment.Step;
import players.Player;
import resources.Health;

import java.util.Random;

public abstract class Unit extends Tile {
    protected String name;
    protected int attackPts;
    protected int defensePts;
    protected Health health;
    protected Random random;
    protected MessageCallback messageCallback;
    protected GameBoard gameBoard;

    public Unit(char character, String name,int health, int attackPts, int defensePts) {
        super(character);
        this.name = name;
        this.attackPts = attackPts;
        this.defensePts = defensePts;
        this.health = new Health(health,health);
        this.random = new Random();

    }
    public void initialize(Position position, MessageCallback messageCallback, GameBoard gameBoard){
        super.initialize(position);
        this.messageCallback = messageCallback;
        this.gameBoard = gameBoard;
    }

    public void onGameTick() {
        Step step = new DownStep(); // dummy step
        this.moveTo(gameBoard.get(getNextPos(step)));
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
        tile.acceptMove(this);
    }

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
    public void acceptKiller(Player player) {
        player.uponOpponentDeath(player);
    }

    public Position getNextPos(Step step){
        return step.calcNextPos(this.position);
    }
}
