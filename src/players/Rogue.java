package players;

import enemies.Enemy;
import movment.Action;

import java.util.List;

public class Rogue extends Player{
    private int cost;
    private int currentEnergy;

    private final double ABILITY_RANGE = 2;
    private final int MAXIMUM_ENERGY = 100;
    public Rogue(String name, int health, int attackPts, int defensePts, int cost){
        super(name,health,attackPts,defensePts, "Fan of Knives");
        this.cost = cost;
        currentEnergy = MAXIMUM_ENERGY;
    }


    @Override
    public void uponLevelingUp(){
        super.uponLevelingUp();
        currentEnergy = 100;
        attackPts = attackPts + 3 * level;
    }

    @Override
    public Action determineAction() {
        currentEnergy = Math.min(currentEnergy + 10, MAXIMUM_ENERGY);
        return super.determineAction();
    }

    @Override
    public void onAbilityCastAttempt() {
        if (currentEnergy >= cost)
            castSpecialAbility();
        else
            messageCallback.send("Failed to cast " + SPECIAL_ABILITY_NAME + " " + name + " needs additional " + (cost - currentEnergy) + " enrgy points.");

    }

    @Override
    protected void castSpecialAbility() {
        List<Enemy> enemies = enemiesGetter.getInRange(this.position, ABILITY_RANGE);
        for (Enemy e : enemies) {
            int defense = e.defend();
            if (this.attackPts - defense > 0)
                e.dealDamage(this.attackPts - defense);
        }
    }

    @Override
    public String describe(){
        return super.describe() + String.format("\t\tenergy: %d/%d", currentEnergy, MAXIMUM_ENERGY);
    }
}
