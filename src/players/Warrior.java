package players;

public class Warrior extends Player{
    private int abilityCooldown;
    private int remainingCooldown;
    public Warrior(String name, int health, int attackPts, int defensePts, int abilityCooldown){
        super(name,health,attackPts,defensePts);
        this.abilityCooldown = abilityCooldown;
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
        remainingCooldown = Math.max(0,remainingCooldown - 1);
    }
    public void onAbilityCast(){
        remainingCooldown = abilityCooldown;
        health.heal(10 * defensePts);
        // Randomly hits one enemy within range < 3 ...
    }

    @Override
    public void castSpecialAbility() {

    }
}
