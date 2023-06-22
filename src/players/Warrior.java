package players;

import IO.InputReader;
import enemies.Enemy;
import movment.Action;

import java.util.List;
import java.util.Random;

public class Warrior extends Player{
    private final int ABILITY_COOLDOWN;
    private int remainingCooldown;

    private final double ABILITY_RANGE = 3;
    private final Random rand;
    public Warrior(String name, int health, int attackPts, int defensePts, int abilityCooldown){
        super(name, health, attackPts, defensePts, "Avenger's Shield");
        this.ABILITY_COOLDOWN = abilityCooldown;
        this.remainingCooldown = 0;
        this.rand = new Random();
    }
    public void uponLevelingUp(){
        super.uponLevelingUp();
        remainingCooldown = 0;
        health.increaseHealthPool(5 * level);
        attackPts += 2 * level;
        defensePts += level;
    }

    @Override
    public Action determineAction() {
        this.remainingCooldown = Math.max(0, remainingCooldown - 1);
        return super.determineAction();
    }

    public void onAbilityCastAttempt() {
        if (remainingCooldown == 0)
            castSpecialAbility();
        else
            messageCallback.send(name + " tried to cast " + SPECIAL_ABILITY_NAME + ", but there is a cooldown:" + remainingCooldown);
    }
    @Override
    protected void castSpecialAbility() {
        int toHeal = 10 * defensePts;
        messageCallback.send(this.name + " casts " + SPECIAL_ABILITY_NAME + ", healing for " + toHeal);
        remainingCooldown = ABILITY_COOLDOWN;
        health.heal(toHeal);
        List<Enemy> enemies = enemiesGetter.getInRange(this.position, ABILITY_RANGE);
        Enemy enemyToHit = enemies.get(rand.nextInt(enemies.size()));
        enemyToHit.dealDamage(this.health.getHealthAmount() * 0.1, this);
    }
    @Override
    public String describe(){
        return super.describe() + String.format("\t\tCooldown: %d/%d", remainingCooldown, ABILITY_COOLDOWN);
    }
}
