package movment;

import tiles.Unit;

import java.util.Map;

public interface Action {
    public void act(Unit unit);

    public static Map<String, Action> actionDict = Map.of(
            "w", new UpStep(),
            "s" , new DownStep(),
            "a" , new LeftStep(),
            "d" , new RightStep(),
            "q", new Stay(),
            "e", new SpecialAbility()
    );
}
