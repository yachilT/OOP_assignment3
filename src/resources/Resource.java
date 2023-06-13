package resources;

public abstract class Resource {
    private int pool;
    private int amount;

    public Resource(int pool, int amount) {
        this.pool = pool;
        this.amount = amount;
    }

    public void addAmount(int amount) {
        this.amount = Math.min(this.amount + amount, pool);
    }

    public boolean reduceAmount(int amount) {
        this.amount = Math.max(this.amount - amount, 0);
        return this.amount == 0;
    }
    
    public void increasePool(int amount) {
        this.pool += amount;
    }
}
