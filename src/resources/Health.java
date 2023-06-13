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


    public void setHealthAmount(int healthAmount) {
        this.healthAmount = healthAmount;
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
