package enemies;

import gameBoard.GameBoard;
import IO.MessageCallback;
import players.Player;
import movment.Position;
import tiles.Unit;

public abstract class Enemy extends Unit {
    protected int xpValue;
    protected Player player;
    public Enemy(char character, String name, int health, int attackPts, int defencePts, int xpValue){
        super(character,name,health,attackPts,defencePts);
        this.xpValue = xpValue;
    }

    public void initialize(Position position, MessageCallback messageCallback, GameBoard gameBoard, Player player) {
        super.initialize(position, messageCallback, gameBoard);
        this.registerDeathListener(player);
        this.player = player;
    }



    public int getXpValue(){
        return xpValue;
    }
    public void moveTo(Enemy enemy){
        // Do nothing...
    }
    public void moveTo(Player player){
        this.combat(player);
    }

    @Override
    public void acceptKiller(Player player) {
        player.uponOpponentDeath(this);
    }

    @Override
    public void acceptBoard(GameBoard board){
        board.receiveDeath(this);
    }
}
