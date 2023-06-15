package players;
import messeages.DeathListener;
import tiles.Unit;
import enemies.*;

public abstract class Player extends Unit implements DeathListener {
    final static private char CHARACTER = '@';
    protected int experiencePts;
    protected int level;

    public Player(String name, int health, int attack, int defense){
        super(CHARACTER,name,health,attack,defense);
        experiencePts = 0;
        level = 1;
    }
    public void uponLevelingUp(){
        experiencePts = Math.max((experiencePts - 50 * level), 0);
        level++;
        health.increaseHealthPool( 10 * level);
        health.regenerate();
        attackPts = attackPts + 4 * level;
        defensePts = defensePts + level;
    }
    public abstract void castSpecialAbility();
    public abstract void onAbilityCast();

    public void acceptMove(Unit unit) {
        unit.moveTo(this);
    }
    public void moveTo(Enemy enemy){
        this.combat(enemy);
    }
    public void moveTo(Player player){
        // Impossible scenario.
    }

    @Override
     public void combat(Unit defender) {
        super.combat(defender);
    }

    @Override
    public void receiveDeath(Unit unit) {
        unit.acceptKiller(this);
    }

    public void uponOpponentDeath(Player player){
        //impossible scenario
    }

    public void uponOpponentDeath(Enemy enemy){
        experiencePts += enemy.getXpValue();
    }
}
