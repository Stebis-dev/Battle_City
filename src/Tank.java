public class Tank {
    private Coordinates position;
    private Vectors direction;
    private boolean isAlive;

    public Tank(Coordinates position, Vectors direction, boolean isAlive) {
        this.position = position;
        this.direction = direction;
        this.isAlive = isAlive;
    }

    public Coordinates getPosition() {
        return position;
    }

    public int getY() {
        return this.position.getY();
    }

    public int getX() {
        return this.position.getX();
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }

    public void moveUp() {
        this.position.setY(getY() - 1);
        this.direction.setDY(1);
        this.direction.setDX(0);
    }

    public void moveDown() {
        this.position.setY(getY() + 1);
        this.direction.setDY(-1);
        this.direction.setDX(0);
    }

    public void moveLeft() {
        this.position.setX(getX() - 1);
        this.direction.setDY(0);
        this.direction.setDX(-1);
    }

    public void moveRight() {
        this.position.setX(getX() + 1);
        this.direction.setDY(0);
        this.direction.setDX(1);
    }

    public Vectors getDirection() {
        return direction;
    }

    public void setDirection(Vectors direction) {
        this.direction = direction;
    }

    public boolean isAlive() {
        if (!isAlive) {
            this.position = new Coordinates(-1, -1);
        }
        return isAlive;
    }

    public void destroyed() {
        this.isAlive = false;
    }

}
