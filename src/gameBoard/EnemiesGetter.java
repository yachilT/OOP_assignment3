package gameBoard;

import enemies.Enemy;
import movment.Position;

import java.util.List;

public interface EnemiesGetter {
    List<Enemy> getInRange(Position pos, double range);

}
