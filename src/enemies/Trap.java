package enemies;

import movment.Action;
import movment.Stay;
import players.Player;
import tiles.Empty;
import tiles.Unit;

public class Trap extends Enemy{
    private final int VISIBILITY_TIME;
    private final int INVISIBILITY_TIME;
    private static final double DEFAULT_VISION_RANGE = 2;
    private int ticksCount;
    private boolean visible;

    public Trap(char character, String name, int health, int attackPts, int defencePts, int xpValue, int visibilityTime, int invisibilityTime) {
        super(character, name, health, attackPts, defencePts, xpValue, DEFAULT_VISION_RANGE);
        this.VISIBILITY_TIME = visibilityTime;
        this.INVISIBILITY_TIME = invisibilityTime;
        this.ticksCount = 0;
        this.visible = true;
    }

    @Override
    public void acceptMove(Unit unit) {
        unit.moveTo(this);
    }

    @Override
    public Action determineAction(Player player) {
        this.visible = ticksCount < VISIBILITY_TIME;
        if (ticksCount == (VISIBILITY_TIME + INVISIBILITY_TIME))
            this.ticksCount = 0;
        else
            ticksCount++;

        if (this.position.range(player.getPosition()) <= visionRange)
            super.combat(player);
        return new Stay();
    }

    @Override
    public String toString() {
        return visible ? character + "" : Empty.EMPTY_CHAR + "";
    }
}
