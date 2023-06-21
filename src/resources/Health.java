package resources;

public class Health {
    private int healthPool;
    private double healthAmount;
    public Health(int healthPool, double healthAmount) {
        this.healthPool = healthPool;
        this.healthAmount = healthAmount;
    }
    public void setHealthPool(int healthPool) {
        this.healthPool = healthPool;
    }

    public void increaseHealthPool(double toIncrease){
        this.healthPool += toIncrease;
    }
    public void regenerate() {
        this.healthAmount = healthPool;
    }

    public double decreaseHealth(double damage) {
        this.healthAmount = Math.max(this.healthAmount - damage, 0);
        return this.healthAmount;
    }

    public void heal(double amount) {
        this.healthAmount = Math.min(this.healthAmount + amount, healthPool);
    }

    public int getHealthPool() {
        return healthPool;
    }

    public double getHealthAmount() {
        return healthAmount;
    }

    @Override
    public String toString() {
        return String.format("Health: %d/%d",healthAmount,healthPool);
    }

}
