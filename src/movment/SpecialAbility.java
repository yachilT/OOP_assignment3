package movment;

import enemies.Enemy;
import players.Player;
import tiles.Unit;

public class SpecialAbility implements Action{
    @Override
    public void act(Unit unit) {
        unit.acceptSpecialAbility(this);
    }

    public void act(Player player) {
        player.onAbilityCast();
    }

    public void act(Enemy enemy){
        //do nothing for now
        //leaves an option for special ability for enemies
    }
}
