package players;
import IO.DeathCallback;
import IO.InputReader;
import IO.MessageCallback;
import gameBoard.EnemiesGetter;
import movment.*;
import tiles.Unit;
import enemies.*;

import java.util.Map;

public abstract class Player extends Unit {
    final static public char CHARACTER = '@';
    final static private char DEATH_CHARACTER = 'X';
    protected final String SPECIAL_ABILITY_NAME;

    protected final int MAX_EXPERIENCE = 50;
    protected int experiencePts;
    protected int level;

    protected EnemiesGetter enemiesGetter;

    private InputReader reader;


    public Player(String name, int health, int attack, int defense, String specialAbilityName){
        super(CHARACTER,name,health,attack,defense);
        experiencePts = 0;
        level = 1;
        SPECIAL_ABILITY_NAME = specialAbilityName;
    }

    public void initialize(Position position, MessageCallback messageCallback, Map<String, Action> actions, DeathCallback deathCallback, InputReader reader, EnemiesGetter enemiesGetter) {
        super.initialize(position, messageCallback, actions, deathCallback);
        this.reader = reader;
        this.enemiesGetter = enemiesGetter;
    }


    public void uponLevelingUp(){
        int xpPts = Math.max((experiencePts - MAX_EXPERIENCE * level), 0);
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

    public void acceptMove(Unit unit) {
        unit.moveTo(this);
    }
    public void moveTo(Enemy enemy) {
         this.combat(enemy);
    }
    public void moveTo(Player player) {
        // Impossible scenario.
    }


    public void receiveXP(int XP){
        messageCallback.send(this.name + " gained " + XP + "experience");
        experiencePts += XP;
        if (experiencePts >= MAX_EXPERIENCE * level)
            uponLevelingUp();
    }



    public Action determineAction() {
        String s = reader.read();
        while (!actionMap.containsKey(s))
            s = reader.read();
        return actionMap.get(s);
    }


    @Override
    public String describe(){
        return super.describe() + String.format("\t\tLevel: %d\t\tExperience: %d/%d", level, experiencePts, MAX_EXPERIENCE);
    }


}
