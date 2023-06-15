package enemies;

import players.Player;
import tiles.Unit;

public abstract class Enemy extends Unit {
    protected int xpValue;
    protected Player player;
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

    @Override
    public void acceptKiller(Player player) {
        player.uponOpponentDeath(this);
    }
}
