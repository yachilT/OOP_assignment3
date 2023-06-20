package players;

public class Rogue extends Player{
    private int cost;
    private int currentEnergy;
    public Rogue(String name, int health, int attackPts, int defensePts, int cost){
        super(name,health,attackPts,defensePts);
        this.cost = cost;
        currentEnergy = 100;
    }

    @Override
    public void castSpecialAbility() {
        //implementation...
    }

    @Override
    public void uponLevelingUp(){
        super.uponLevelingUp();
        currentEnergy = 100;
        attackPts = attackPts + 3 * level;
    }
    @Override
    public void onGameTick(){
        currentEnergy = Math.min(currentEnergy + 10,100);
    }

    @Override
    public void onAbilityCast() {

    }
}
