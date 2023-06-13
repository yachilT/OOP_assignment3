package resources;

public class Mana {
    private int manaPool;
    private int currentMana;

    public Mana(int manaPool, int currentMana) {
        this.manaPool = manaPool;
        this.currentMana = currentMana;
    }
    public void increaseCurrentMana(int toIncrease){
        this.currentMana = Math.min(currentMana + toIncrease, manaPool);
    }
    public void decreaseCurrentMana(int toDecrease){
        this.currentMana = Math.max(currentMana - toDecrease, 0);
    }
    public void increaseHealthPool(int toIncrease){
        this.manaPool += toIncrease;
    }
    public int getManaPool() {
        return manaPool;
    }

    public void setManaPool(int manaPool) {
        this.manaPool = manaPool;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    @Override
    public String toString() {
        return "Mana pool: "+ manaPool + " Current mana: "+ currentMana;
    }
}
