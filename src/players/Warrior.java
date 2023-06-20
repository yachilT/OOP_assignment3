package players;

import IO.InputReader;
import enemies.Enemy;

import java.util.List;
import java.util.Random;

public class Warrior extends Player{
    private final int ABILITY_COOLDOWN;
    private int remainingCooldown;

    private final double ABILITY_RANGE = 3;
    private Random rand;
    public Warrior(String name, int health, int attackPts, int defensePts, int abilityCooldown){
        super(name, health, attackPts, defensePts);
        this.ABILITY_COOLDOWN = abilityCooldown;
        this.remainingCooldown = 0;
    }
    public void uponLevelingUp(){
        super.uponLevelingUp();
        remainingCooldown = 0;
        health.increaseHealthPool( 5 * level);
        attackPts += 2 * level;
        defensePts += level;
    }
    public void onGameTick(){
        super.onGameTick();
        remainingCooldown = Math.max(0,remainingCooldown - 1);
    }
    public void onAbilityCast() {
        if (remainingCooldown == 0)
            castSpecialAbility();
        else
            messageCallback.send("Failed to cast special ability. Remaining cooldown: " + remainingCooldown);
    }
    @Override
    public void castSpecialAbility() {
        remainingCooldown = ABILITY_COOLDOWN;
        health.heal(10 * defensePts);
        List<Enemy> enemies = gameBoard.getEnemiesInRange(this, ABILITY_RANGE);
        Enemy enemyToHit = enemies.get(rand.nextInt(enemies.size()));
        enemyToHit.dealDamage(this.health.getHealthAmount() * 0.1, this);
    }
}
