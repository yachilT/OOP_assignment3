package enemies;

import movment.*;
import tiles.Unit;

import java.util.Random;

public class Monster extends Enemy {

    private Random rand;
    private final double VISION_RANGE;
    public Monster(char character, String name, int health, int attackPts, int defencePts, int xpValue, int visionRange) {
        super(character, name, health, attackPts, defencePts, xpValue);
        this.VISION_RANGE = visionRange;
        this.random = new Random();
    }

    @Override
    public void acceptMove(Unit unit) {
        unit.moveTo(this);
    }

    @Override
    public void onGameTick() {
        super.onGameTick();
    }

    public Step determineStep() {
        if (this.position.range(player.getPosition()) < VISION_RANGE) {
            int dx = this.position.x - player.getPosition().x;
            int dy = this.position.y - player.getPosition().y;
            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0)
                    return new LeftStep();
                else
                    return new RightStep();
            }
            else
                if(dy > 0)
                    return new UpStep();
                else
                    return new DownStep();
        }
        else {
            Step[] steps = (Step[]) Step.stepsDict.values().toArray();
            return steps[random.nextInt(steps.length)];
        }
    }
}
