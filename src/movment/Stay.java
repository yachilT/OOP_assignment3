package movment;

import tiles.Unit;

public class Stay implements Step {

    @Override
    public void act(Unit unit) {
        //Do nothing
    }
    @Override
    public boolean equals(Object object){
        return object instanceof Stay;
    }
}
