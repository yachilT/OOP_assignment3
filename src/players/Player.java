package players;

import tiles.Tile;
import tiles.Unit;

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
        health.setHealthAmount(health.getHealthPool());
        health.setHealthPool(health.getHealthPool() + 10 * level);
        attackPts = attackPts + 4 * level;
        defensePts = defensePts + level;
        uponLevelingUp();
    }
    public abstract void castSpecialAbility();
    public abstract void uponLevelingUp();
    public abstract void onGameTick();
    public abstract void onAbilityCast();
    @Override
    public void accept(Unit unit) {

    }

    @Override
    public int compareTo(Tile o) {
        return 0;
    }
}
