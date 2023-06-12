package players;

import jdk.jshell.spi.ExecutionControl;

public class Warrior extends Player{
    private int abilityCooldown;
    private int remainingCooldown;
    public Warrior(String name, int health, int attackPts, int defensePts, int abilityCooldown){
        super(name,health,attackPts,defensePts);
        this.abilityCooldown = abilityCooldown;
        this.remainingCooldown = 0;
    }
    public void uponLevelingUp(){
        remainingCooldown = 0;
        health.setHealthPool(health.getHealthPool() + 5 * level);
        attackPts += 2 * level;
        defensePts += level;
    }
    public void onGameTick(){
        remainingCooldown = Math.max(0,remainingCooldown - 1);
    }
    public void onAbilityCast(){
        remainingCooldown = abilityCooldown;
        health.setHealthAmount(Math.min(health.getHealthAmount() + 10 * defensePts, health.getHealthPool()));
        // Randomly hits one enemy within range < 3 ...
    }

    @Override
    public void castSpecialAbility() {

    }
}
