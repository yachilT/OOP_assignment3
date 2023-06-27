package tiles;

import IO.DeathCallback;
import IO.DecoratedMessage;
import IO.Message;
import enemies.Enemy;
import gameBoard.*;
import IO.MessageCallback;
import movment.Action;
import movment.Position;
import movment.SpecialAbility;
import players.Mage;
import players.Player;
import resources.Health;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class Unit extends Tile {
    protected String name;
    protected int attackPts;
    protected int defensePts;
    protected Health health;
    protected Random random;
    protected MessageCallback messageCallback;

    protected DeathCallback deathCallback;

    protected Map<String, Action> actionMap;


    public Unit(char character, String name, int health, int attackPts, int defensePts) {
        super(character);
        this.name = name;
        this.attackPts = attackPts;
        this.defensePts = defensePts;
        this.health = new Health(health, health);
        this.random = new Random();


    }
    public void initialize(Position position, MessageCallback messageCallback, Map<String, Action> actionMap, DeathCallback deathCallback){
        super.initialize(position);
        this.messageCallback = messageCallback;
        this.actionMap = actionMap;
        this.deathCallback = deathCallback;
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
    private int attack() {
        int pts = random.nextInt(0, attackPts);
        this.messageCallback.send(new Message(this.name + " rolled " + pts + " attack pts."));
        return pts;
    }
    public int defend() {
        int pts = random.nextInt(0, defensePts);
        this.messageCallback.send(new Message(this.name + " rolled " + pts + " defend pts."));
        return pts;

    }

    public void dealDamage(double damage, Unit attacker){
        this.messageCallback.send(new Message(String.format("%s dealt %.2f pts to %s", attacker.name, damage, this.name)));
        if (this.health.decreaseHealth(damage) == 0) {
            onDeath();
        }
    }
    public void onDeath(){
        messageCallback.send(new Message(this.name + " has died"));
        deathCallback.onDeath();
    }
    public void combat(Unit defender){
        messageCallback.send(new Message(this.name + " engaged in combat with " + defender.name));
        messageCallback.send(new DecoratedMessage(this.describe() + "\nVS\n" + defender.describe()));
        double damage = this.attack() - defender.defend();
        if(damage > 0) {
            defender.dealDamage(damage, this);
        }
    }







    protected abstract void castSpecialAbility();
    public abstract void onAbilityCastAttempt();

    public String getName() {
        return name;
    }
    public String describe(){
        return String.format("%s\t\t%s\t\tAttack: %d\t\tDefense: %d", name, health.toString(), attackPts, defensePts);
    }

}
