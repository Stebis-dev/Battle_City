public class Vectors {
    private int DY;
    private int DX;

    public Vectors(int DY, int DX) {
        this.DY = DY;
        this.DX = DX;
    }

    public int getDY() {
        return DY;
    }

    public int getDX() {
        return DX;
    }

    public void setDY(int DY) {
        this.DY = DY;
    }

    public void setDX(int DX) {
        this.DX = DX;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vectors that = (Vectors) obj;
        return DY == that.getDY() && DX == that.getDX();

    }
}
