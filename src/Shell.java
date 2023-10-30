public class Shell {
    private Coordinates position;
    private Vectors direction;
    private boolean isShot;

    public Shell(Coordinates position, Vectors direction, boolean isShot) {
        this.position = position;
        this.direction = direction;
        this.isShot = isShot;
    }

    public Coordinates getPosition() {
        return position;
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }

    public Vectors getDirection() {
        return direction;
    }

    public void setDirection(Vectors direction) {
        this.direction = direction;
    }

    public boolean isShot() {
        return isShot;
    }

    public void shot(Coordinates position, Vectors direction) {
        this.position = new Coordinates(position.getY(), position.getX());
        this.direction = new Vectors(direction.getDY(), direction.getDX());
        this.isShot = true;
        return;
    }

    public void setShot(boolean shot) {
        isShot = shot;
    }

    public int getY() {
        return this.position.getY();
    }

    public int getX() {
        return this.position.getX();
    }

    public void moving() {

        if (this.direction.equals(new Vectors(1, 0))) {
            this.position.setY(getY() - 1);
        } else if (this.direction.equals(new Vectors(-1, 0))) {
            this.position.setY(getY() + 1);
        } else if (this.direction.equals(new Vectors(0, 1))) {
            this.position.setX(getX() + 1);
        } else if (this.direction.equals(new Vectors(0, -1))) {
            this.position.setX(getX() - 1);
        }
        return;
    }

    public void destroyed() {
        this.position = new Coordinates(-1, -1);
        this.direction = new Vectors(0, 0);
        this.isShot = false;
        return;
    }

}
