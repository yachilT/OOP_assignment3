package players;

import resources.*;

public class Mage extends Player{
    private Mana mana;
    private int manaCost;
    private int spellPower;
    private int hitCount;
    private int abilityRange;
    public Mage(String name, int health, int attackPts, int defensePts, int manaPool,int manaCost, int spellPower, int hitsCount, int abilityRange){
        super(name,health,attackPts,defensePts);
        this.mana = new Mana(manaPool, manaPool / 4);
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitCount = hitsCount;
        this.abilityRange = abilityRange;
    }
    public void uponLevelingUp(){
        mana.setManaPool(mana.getManaPool() + 25 * level);
        mana.setCurrentMana(Math.min(mana.getCurrentMana() + mana.getManaPool() / 4, mana.getManaPool()));
        spellPower = spellPower + 10 * level;
    }
    public void onGameTick(){
        mana.setCurrentMana(Math.min(mana.getManaPool(),mana.getCurrentMana() + level));
    }
    public void onAbilityCast(){
        mana.setCurrentMana(Math.max(mana.getCurrentMana() - manaCost,0));
        int hits = 0;
        //implementation
    }
    public  void castSpecialAbility(){

    }
}
