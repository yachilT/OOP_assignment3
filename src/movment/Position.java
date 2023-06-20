package movment;

public class Position implements Comparable<Position>{
    public int x;
    public int y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double range(Position p) {
        return Math.sqrt(Math.pow(this.x - p.x,2) + Math.pow(this.y - p.y,2));
    }

    @Override
    public int compareTo(Position p) {
        if(this.y - p.y != 0)
            return this.y - p.y;
        else
            return this.x - p.x;
    }

    public Position add(int x, int y) {
        return new Position(this.x + x, this.y + y);
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof Position)
            return x == ((Position) object).x & y == ((Position) object).y;
        return false;
    }
}
