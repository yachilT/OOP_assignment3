package players;

import enemies.Enemy;
import gameBoard.GameBoard;

import java.util.List;

public class Rogue extends Player{
    private int cost;
    private int currentEnergy;
    private final double RANGE = 2;
    private final int MAX_ENERGY = 100;
    public Rogue(String name, int health, int attackPts, int defensePts, int cost){
        super(name,health,attackPts,defensePts,"Fan of Knives");
        this.cost = cost;
        currentEnergy = 100;
    }

    @Override
    public void castSpecialAbility() {
        messageCallback.send(this.name + " cast " + SPECIAL_ABILITY_NAME);
        currentEnergy -= cost;
        for(Enemy e : gameBoard.getEnemiesInRange(this,RANGE))
            e.dealDamage(attackPts,this);
    }

    @Override
    public void uponLevelingUp(){
        super.uponLevelingUp();
        currentEnergy = 100;
        attackPts = attackPts + 3 * level;
    }
    @Override
    public void onGameTick(){
        currentEnergy = Math.min(currentEnergy + 10,100);
    }

    @Override
    public void onAbilityCast() {
        if(currentEnergy >= cost)
            castSpecialAbility();
        else
            messageCallback.send(name + " tried to cast " + SPECIAL_ABILITY_NAME + ", but there was not enough energy: " + currentEnergy + "/" + cost );
    }
    @Override
    public String describe(){
        return super.describe() + String.format("\t\tEnergy: %d/%d",currentEnergy,MAX_ENERGY);
    }
}
