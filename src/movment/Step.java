package movment;

import tiles.Position;

import java.util.Map;

public interface Step {
    public Position calcNextPos(Position pos);


    public static Map<String, Step> stepsDict = Map.of(
            "w", new UpStep(),
            "s" , new DownStep(),
            "a" , new LeftStep(),
            "d" , new RightStep(),
            "q", new Stay()
    );
}

