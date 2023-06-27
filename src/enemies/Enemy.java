package enemies;

import movment.Action;
import players.Player;
import tiles.Unit;

public abstract class Enemy extends Unit {

    protected int xpValue;

    protected double visionRange;
    public Enemy(char character, String name, int health, int attackPts, int defencePts, int xpValue, double visionRange){
        super(character,name,health,attackPts,defencePts);
        this.xpValue = xpValue;
        this.visionRange = visionRange;
    }

    public int getXpValue(){
        return xpValue;
    }
    public void moveTo(Enemy enemy){
        // Do nothing...
    }
    public void moveTo(Player player){
        this.combat(player);
    }


    public abstract Action determineAction(Player player);


    @Override
    public void onAbilityCastAttempt() {
        // does nothing for now
    }

    @Override
    public void castSpecialAbility() {
        // does nothing for now
    }

    @Override
    public String describe() {
        return super.describe() + String.format("\t\tXP: %d\t\tvision range: %.2f" , this.xpValue, this.visionRange);
    }
}
