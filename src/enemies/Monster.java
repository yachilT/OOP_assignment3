package enemies;

import movment.*;
import players.Player;
import tiles.Unit;

import java.util.List;
import java.util.Random;

public class Monster extends Enemy {

    public Monster(char character, String name, int health, int attackPts, int defencePts, int xpValue, int visionRange) {
        super(character, name, health, attackPts, defencePts, xpValue, visionRange);
        this.random = new Random();
    }

    @Override
    public void acceptMove(Unit unit) {
        unit.moveTo(this);
    }


    public Action determineAction(Player player) {
        if (this.position.range(player.getPosition()) <= visionRange) {
            int dx = this.position.x - player.getPosition().x;
            int dy = this.position.y - player.getPosition().y;
            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0)
                    return actionMap.get("a");
                else
                    return actionMap.get("d");
            }
            else
                if(dy > 0)
                    return actionMap.get("s");
                else
                    return actionMap.get("w");
        }
        else {
            List<Action> actions = actionMap.values().stream().toList();
            return actions.get(random.nextInt(actions.size() - 1));
        }
    }

    @Override
    public String describe() {
        return super.describe() + String.format("");
    }
}
