import java.util.Objects;

 
public class Coordinate {

    private int x, y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return String.format("x: %d, y: %d", this.getX(), this.getY());
    }

    @Override
    public boolean equals(Object obj) { 
        if (obj == this) { 
            return true; 
        }
        if (!(obj instanceof Coordinate)) { 
            return false; 
        } 

        Coordinate coord = (Coordinate) obj; 

        return this.x == coord.getX() && this.y == coord.getY();
    }


    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    public String toString(String message) {
        if (message == null) {
            // You can add a custom exception message to make debugging easier!
            throw new IllegalArgumentException("Message String cannot be null!");
        }

        return String.format("%s %s", message, this.toString());
    }
}
