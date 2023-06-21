package movment;

import enemies.Enemy;
import players.Player;
import tiles.Unit;

public class SpecialAbility implements Action{
    @Override
    public void act(Unit unit) {
        unit.onAbilityCastAttempt();
    }


}
