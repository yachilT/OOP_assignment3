package resources;

public class Mana {
    private int manaPool;
    private int currentMana;

    public Mana(int manaPool, int currentMana) {
        this.manaPool = manaPool;
        this.currentMana = currentMana;
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
