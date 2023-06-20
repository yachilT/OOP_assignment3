package tiles;

import enemies.Enemy;
import gameBoard.*;
import IO.DeathListener;
import IO.MessageCallback;
import movment.Action;
import movment.Position;
import movment.SpecialAbility;
import players.Player;
import resources.Health;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Unit extends Tile implements DeathListener {
    protected String name;
    protected int attackPts;
    protected int defensePts;
    protected Health health;
    protected Random random;
    protected MessageCallback messageCallback;
    protected GameBoard gameBoard;

    protected final List<DeathListener> deathListeners;

    public Unit(char character, String name, int health, int attackPts, int defensePts) {
        super(character);
        this.name = name;
        this.attackPts = attackPts;
        this.defensePts = defensePts;
        this.health = new Health(health, health);
        this.random = new Random();
        this.deathListeners = new ArrayList<>();

    }
    public void initialize(Position position, MessageCallback messageCallback, GameBoard gameBoard){
        super.initialize(position);
        this.messageCallback = messageCallback;
        this.gameBoard = gameBoard;
        this.registerDeathListener(gameBoard);
    }
    public abstract Action determineAction();
    public abstract void acceptBoard(GameBoard board);
    public abstract void moveTo(Enemy enemy);
    public abstract void moveTo(Player player);
    public abstract void  acceptKiller(Player player);


    public void onGameTick() {
        Action action = determineAction();
        action.act(this);

        if (health.getHealthAmount() == 0) {
            messageCallback.send(this.name + " has died");
            onDeath();
        }
    }
    private int attack() {
        int pts = random.nextInt(0, attackPts);
        this.messageCallback.send(this.name + " rolled " + pts + " attack pts.");
        return pts;
    }
    public int defend() {
        int pts = random.nextInt(0, defensePts);
        this.messageCallback.send(this.name + " rolled " + pts + " defend pts.");
        return pts;

    }

    public void dealDamage(double damage, Unit attacker){
        if (this.health.decreaseHealth(damage))
            attacker.registerDeathListener(this);
    }

    public void combat(Unit defender){
        double damage = this.attack() - defender.defend();
        if(damage > 0) {
            messageCallback.send(this.name + " dealt " + damage + " damage pts to " + defender.getName());
            defender.dealDamage(damage, this);
        }
    }


    public void onDeath(){
        deathListeners.forEach(l -> l.receiveDeath(this));
    }

    public void registerDeathListener(DeathListener deathListener){
        deathListeners.add(deathListener);
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


    public abstract void acceptSpecialAbility(SpecialAbility ability);

    public String getName() {
        return name;
    }
    public String describe(){
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", name, health.toString(), attackPts, defensePts);
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
