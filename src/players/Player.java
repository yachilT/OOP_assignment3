package players;

import tiles.Tile;
import tiles.Unit;
import enemies.*;

public abstract class Player extends Unit {
    final static private char CHARACTER = '@';
    protected int experiencePts;
    protected int level;
    public Player(String name, int health, int attack, int defense){
        super(CHARACTER,name,health,attack,defense);
        experiencePts = 0;
        level = 1;
    }
    public void LevelUp(){
        experiencePts = Math.max((experiencePts - 50 * level), 0);
        level++;
        health.setHealthPool(health.getHealthPool() + 10 * level);
        health.regenerate();
        attackPts = attackPts + 4 * level;
        defensePts = defensePts + level;
        uponLevelingUp();
    }
    public abstract void castSpecialAbility();
    public abstract void uponLevelingUp();
    public abstract void onGameTick();
    public abstract void onAbilityCast();
    public void moveTo(Enemy enemy){
        this.combat(enemy);
    }
    public void moveTo(Player player){
        // Impossible scenario.
    }

    @Override
     public void combat(Unit defender) {
        super.combat(defender);
        if (defender.isDead())
            uponOpponentDeath(defender);
    }

    public void uponOpponentDeath(Unit opponent) {
        opponent.acceptKiller(this);
    }
    public void uponOpponentDeath(Player player){
        //impossible scenario
    }

    public void uponOpponentDeath(Enemy enemy){
        experiencePts += enemy.getXpValue();

    }
}
