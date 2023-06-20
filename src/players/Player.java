package players;
import IO.InputReader;
import IO.MessageCallback;
import gameBoard.GameBoard;
import IO.DeathListener;
import movment.*;
import tiles.Unit;
import enemies.*;

import java.util.HashMap;
import java.util.Objects;

public abstract class Player extends Unit implements DeathListener {
    final static private char CHARACTER = '@';
    final static private char DEATH_CHARACTER = 'X';

    protected final String FAILED_ABILITY_CAST_MSG = "Failed to cast special ability";
    protected int experiencePts;
    protected int level;

    private InputReader reader;

    public Player(String name, int health, int attack, int defense){
        super(CHARACTER,name,health,attack,defense);
        experiencePts = 0;
        level = 1;
    }

    public void initialize(Position position, MessageCallback messageCallback, GameBoard gameBoard, InputReader reader) {
        super.initialize(position, messageCallback, gameBoard);
        this.reader = reader;
    }

    @Override
    public void onGameTick() {
        super.onGameTick();
        if (experiencePts >= 50 * level)
            uponLevelingUp();
    }

    public void uponLevelingUp(){
        int xpPts = Math.max((experiencePts - 50 * level), 0);
        this.level++;
        int healthPool =  10 * level;
        int attackToIncrease = 4 * level;
        int defenseToIncrease = level;
        messageCallback.send(String.format("%s reached level %d: +%d Health, +%d Attack, +%d Defense", name, level, healthPool, attackToIncrease, defenseToIncrease));
        experiencePts = xpPts;
        health.increaseHealthPool(healthPool);
        health.regenerate();
        attackPts += attackToIncrease;
        defensePts += defenseToIncrease;
    }
    public abstract void castSpecialAbility();
    public abstract void onAbilityCast();

    public void acceptMove(Unit unit) {
        unit.moveTo(this);
    }
    public void moveTo(Enemy enemy) {
        this.combat(enemy);
    }
    public void moveTo(Player player) {
        // Impossible scenario.
    }


    @Override
    public void acceptBoard(GameBoard board){
        board.receiveDeath(this);
    }

    @Override
    public void onDeath(){
        this.character = DEATH_CHARACTER;
        super.onDeath();
    }
    @Override
    public void receiveDeath(Unit unit) {
        unit.acceptKiller(this);
    }


    public void uponOpponentDeath(Enemy enemy){
        messageCallback.send(enemy.getName() + " gained " + enemy.getXpValue() + "experience");
        experiencePts += enemy.getXpValue();
    }

    @Override
    public Action determineAction() {
        String s = reader.read();
        while (!Action.actionDict.containsKey(s))
            s = reader.read();
        return Action.actionDict.get(s);
    }

    @Override
    public void acceptKiller(Player player) {
       //Impossible scenario
    }

    @Override
    public void acceptSpecialAbility(SpecialAbility ability){
        ability.act(this);
    }

}
