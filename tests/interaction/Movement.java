package interaction;

import IO.Message;
import enemies.Enemy;
import gameBoard.LevelFactory;
import gameBoard.TileGetter;
import movment.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import tiles.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Movement {
    private Unit unitPlayer;
    private Unit unitEnemy;
    private Tile wall;
    private Tile empty;

    private Enemy enemy;
    private TileFactory tileFactory;
    private LevelFactory levelFactory;
    @BeforeEach
    public void initEach(){
        tileFactory = new TileFactory();
        levelFactory = new LevelFactory();
        unitPlayer = tileFactory.producePlayer(1);
        unitEnemy = tileFactory.produceEnemy("s");
        wall = new Wall();
        empty = new Empty();
    }
    private Map<String,Action> initActionMap(TileGetter tileGetter){
        return Map.of(
                "w", new UpStep(tileGetter),
                "s", new DownStep(tileGetter),
                "a", new LeftStep(tileGetter),
                "d", new RightStep(tileGetter),
                "q", new Stay(),
                "e", new SpecialAbility()
        );
    }
    @Test
    public void unitMoveToEmpty(){
        Position emptyPos = new Position(0,0);
        Position unitPos = new Position(1,0);

        empty.initialize(emptyPos);
        unitPlayer.initialize(unitPos, null, null, null);

        unitPlayer.moveTo(empty);
        Assert.assertTrue("Move to Empty\nAssert positions: ", unitPlayer.getPosition().equals(emptyPos) && empty.getPosition().equals(unitPos) );
    }

    @Test
    public void unitMoveToWall(){
        wall.initialize(new Position(0, 0));
        unitPlayer.initialize(new Position(1, 0), null,  null, null);

        unitPlayer.moveTo(wall);
        Assert.assertTrue("Move to wall\nAssert positions: ", unitPlayer.getPosition().equals(new Position(0, 0)) & wall.getPosition().equals(new Position(1, 0)));
    }

    @Test
    public void playerMoveToEnemy() {
        List<Message> messages = new ArrayList<>();
        unitPlayer.initialize(new Position(0, 0), messages::add, null, null);
        unitEnemy.initialize(new Position(1, 0), messages::add, null, null);

        unitPlayer.moveTo(enemy);
        Message engagement = new Message(unitPlayer.getName() + " engaged in combat with " + unitEnemy.getName());

        Assert.assertTrue(messages.contains(engagement));
    }
    @Test
    public void enemyMoveToPlayer(){
        String engagement = unitEnemy.getName() + " engaged in combat with " + unitPlayer.getName();
        List<Message> messages = new ArrayList<>();

        unitEnemy.initialize(new Position(0,0), messages::add, null ,null);
        unitPlayer.initialize(new Position(1,0), messages::add, null, null);

        unitEnemy.moveTo(unitPlayer);
        Assert.assertTrue("Enemy interact with player: ",messages.contains(new Message(engagement)));
    }
}