package movment;

import java.util.Map;

public interface Step extends Action {
    public static Map<String, Step> stepsDict = Map.of(
            "w", new UpStep(),
            "s" , new DownStep(),
            "a" , new LeftStep(),
            "d" , new RightStep(),
            "q", new Stay()
    );
}

