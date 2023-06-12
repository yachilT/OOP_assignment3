package enemies;

import tiles.Unit;

public class Enemy extends Unit {
    public Enemy(char character, String name, int health, int attackPts, int defencePts){
        super(character,name,health,attackPts,defencePts);
    }

    @Override
    public void accept(Unit unit) {

    }
}
