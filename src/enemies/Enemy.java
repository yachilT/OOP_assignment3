package enemies;

import movment.Action;
import players.Player;
import tiles.Unit;

public abstract class Enemy extends Unit {

    protected int xpValue;
    public Enemy(char character, String name, int health, int attackPts, int defencePts, int xpValue){
        super(character,name,health,attackPts,defencePts);
        this.xpValue = xpValue;
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
}
