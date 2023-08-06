package players;

import IO.Message;
import enemies.Enemy;
import movment.Action;
import movment.Position;

import java.util.function.Function;

public class Hunter extends Player{
    private int shootingRange;
    private int arrowsCount;
    private int ticksCount;
    public Hunter(String name, int health, int attack, int defense, int shootingRange) {
        super(name, health, attack, defense, "Shoot");
        this.shootingRange = shootingRange;
        this.arrowsCount = 10 * level;
        this.ticksCount = 0;
    }

    @Override
    public void uponLevelingUp() {
        super.uponLevelingUp();
        arrowsCount += 10 * level;
        attackPts += 2 * level;
        defensePts += level;
    }

    @Override
    public Action determineAction() {
        if (ticksCount == 10){
            arrowsCount++;
            ticksCount = 0;
        }
        else
            ticksCount++;

        return super.determineAction();
    }



    @Override
    protected void castSpecialAbility() {
        messageCallback.send(new Message(name + " casts " + SPECIAL_ABILITY_NAME));
        arrowsCount--;
        Function<Position, Integer> dist = pos -> (int)Math.round(pos.range(this.position));
        Enemy e = enemiesGetter.getInRange(this.position, shootingRange).stream().min(
                (e1, e2) -> Math.min(
                        dist.apply(e1.getPosition()),
                        dist.apply(e2.getPosition())
                )
        ).get();
        e.dealDamage(attackPts, this);
    }

    @Override
    public void onAbilityCastAttempt() {
        if (arrowsCount == 0){
            messageCallback.send(new Message(name + " tried to cast " + SPECIAL_ABILITY_NAME + ", but there aren't any arrows"));
        } else if (enemiesGetter.getInRange(this.position, shootingRange).isEmpty()) {
            messageCallback.send(new Message(name + " tried to cast " + SPECIAL_ABILITY_NAME + ", but there aren't any enemies nearby"));
        }
        else
            castSpecialAbility();
    }

    @Override
    public String describe() {
        return super.describe() + "\t\tArrows: " + arrowsCount;
    }
}
