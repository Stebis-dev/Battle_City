public class Coordinates {
    private int Y;
    private int X;

    public Coordinates(int y, int x) {
        Y = y;
        X = x;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordinates that = (Coordinates) obj;
        return Y == that.getY() && X == that.getX();
    }

    public int getY() {
        return Y;
    }

    public int getX() {
        return X;
    }

    public void setY(int y) {
        Y = y;
    }

    public void setX(int x) {
        X = x;
    }
}
