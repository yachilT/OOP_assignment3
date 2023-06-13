package resources;

public class Health {
    private int healthPool;
    private int healthAmount;
    public Health(int healthPool, int healthAmount) {
        this.healthPool = healthPool;
        this.healthAmount = healthAmount;
    }
    public void setHealthPool(int healthPool) {
        this.healthPool = healthPool;
    }


    public void regenerate() {
        this.healthAmount = healthPool;
    }

    public void decreaseHealth(int damage) {
        this.healthAmount = Math.max(this.healthAmount - damage, 0);
    }

    public void heal(int amount) {
        this.healthAmount = Math.min(this.healthAmount + amount, healthPool);
    }

    public int getHealthPool() {
        return healthPool;
    }

    public int getHealthAmount() {
        return healthAmount;
    }

    @Override
    public String toString() {
        return "Health pool: "+ healthPool +" Current health: "+ healthAmount;
    }

}
