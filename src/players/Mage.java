package players;

import enemies.Enemy;
import movment.Action;
import resources.*;

import java.util.Iterator;
import java.util.List;

public class Mage extends Player{
    private Mana mana;
    private int manaCost;
    private int spellPower;
    private int hitCount;
    private int abilityRange;

    public Mage(String name, int health, int attackPts, int defensePts, int manaPool,int manaCost, int spellPower, int hitsCount, int abilityRange){
        super(name,health,attackPts,defensePts, "Blizzard");
        this.mana = new Mana(manaPool, manaPool / 4);
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitCount = hitsCount;
        this.abilityRange = abilityRange;
    }
    public void uponLevelingUp(){
        super.uponLevelingUp();
        mana.increaseManaPool(25 * level);
        mana.increaseCurrentMana(mana.getCurrentMana() + mana.getManaPool() / 4);
        spellPower = spellPower + 10 * level;
    }

    @Override
    public Action determineAction() {
        mana.increaseCurrentMana(level);
        return super.determineAction();
    }

    public void onAbilityCastAttempt(){
        if(mana.getCurrentMana() >= manaCost)
            castSpecialAbility();
        else
            messageCallback.send(name + " tried to cast " + SPECIAL_ABILITY_NAME + ", but there was not enough mana: " + mana.getCurrentMana() + "/" + manaCost  );
    }
    protected   void castSpecialAbility(){
        messageCallback.send(name + " cast " + SPECIAL_ABILITY_NAME);
        mana.decreaseCurrentMana(manaCost);
        int hits = 0;
        List<Enemy> enemiesInRange = enemiesGetter.getInRange(this.position, abilityRange);
        while(hits < hitCount && !enemiesInRange.isEmpty()){
            enemiesInRange.get(random.nextInt(enemiesInRange.size() - 1)).dealDamage(attackPts);
            hits++;
            enemiesInRange = enemiesGetter.getInRange(this.position, abilityRange);
        }
    }
    @Override
    public String describe(){
        return super.describe() + String.format("\t\t%s\t\tSpell Power: %d", mana.toString(), spellPower);
    }
}
