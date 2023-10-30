import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        int[][] map = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 2, 0, 2, 0, 2, 0, 2, 0, 1},
                {1, 0, 2, 0, 0, 0, 0, 0, 2, 0, 1},
                {1, 0, 0, 0, 2, 2, 2, 0, 0, 0, 1},
                {1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                {1, 0, 0, 0, 2, 2, 2, 0, 0, 0, 1},
                {1, 0, 2, 0, 0, 0, 0, 0, 2, 0, 1},
                {1, 0, 2, 0, 2, 2, 2, 0, 2, 0, 1},
                {1, 0, 0, 0, 2, 0, 2, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };


        Tank player = new Tank(new Coordinates(9, 3), new Vectors(1, 0), true);
        Shell playerShot = new Shell(new Coordinates(-1, -1), new Vectors(0, 0), false);

        Enemy enemy = new Enemy(new Coordinates(1, 1), new Vectors(0, 1), true);
        Shell enemyShot = new Shell(new Coordinates(-1, -1), new Vectors(0, 0), false);

        Coordinates base = new Coordinates(9, 5);
        boolean isBaseDestroyed = false;

        while (true) {
            // rendering
            Coordinates renderPosition;
            for (int i = 0; i < map.length; i++) {
                for (int ii = 0; ii < map[0].length; ii++) {
                    renderPosition = new Coordinates(i, ii);
                    if (player.getPosition().equals(renderPosition)) {
                        System.out.print("T");
                    } else if (enemy.getPosition().equals(renderPosition)) {
                        System.out.print("E");
                    } else if (playerShot.getPosition().equals(renderPosition) || enemyShot.getPosition().equals(renderPosition)) {
                        System.out.print("+");
                    } else if (map[i][ii] == 1) {
                        System.out.print("#");
                    } else if (map[i][ii] == 2) {
                        System.out.print("#");
                    } else if (base.equals(renderPosition)) {
                        System.out.print("A");
                    } else {
                        System.out.print(" ");
                    }
                    System.out.print(" ");
                }
                System.out.print('\n');
            }

            //player
            String userInputLine = scanner.nextLine();
            Character userInput = null;
            if (!userInputLine.isEmpty()) {
                userInput = userInputLine.charAt(0);
            }
            if (userInput != null) {
                if (userInput == 'w' && map[player.getY() - 1][player.getX()] == 0) {
                    player.moveUp();
                } else if (userInput == 's' && map[player.getY() + 1][player.getX()] == 0) {
                    player.moveDown();
                } else if (userInput == 'a' && map[player.getY()][player.getX() - 1] == 0) {
                    player.moveLeft();
                } else if (userInput == 'd' && map[player.getY()][player.getX() + 1] == 0) {
                    player.moveRight();
                } else if (userInput == 'q') {
                    System.exit(0);
                } else if (userInput == 'x' && !playerShot.isShot()) {
                    playerShot.shot(player.getPosition(), player.getDirection());
                }
            }

            if (!player.isAlive() || !enemy.isAlive() || isBaseDestroyed) {
                if (isBaseDestroyed) {
                    System.out.println("Base destroyed");
                } else if (!player.isAlive()) {
                    System.out.println("Player destroyed");
                } else if (!enemy.isAlive()) {
                    System.out.println("Enemy destroyed");
                }
                System.out.println("GAME OVER");
                System.exit(0);
            }


            // player shell
            if (playerShot.isShot()) {

                playerShot.moving();
                if (map[playerShot.getY()][playerShot.getX()] == 1) {
                    playerShot.destroyed();
                } else if (map[playerShot.getY()][playerShot.getX()] == 2) {
                    map[playerShot.getY()][playerShot.getX()] = 0;
                    playerShot.destroyed();
                } else if (enemy.getPosition().equals(playerShot.getPosition())) {
                    enemy.destroyed();
                    playerShot.destroyed();
                } else if (base.equals(playerShot.getPosition())) {
                    base = new Coordinates(-1, -1);
                    isBaseDestroyed = true;
                    playerShot.destroyed();
                }
            }

            // enemyAI
            if (enemy.isAlive()) {
                int randomDecision = 1 + rand.nextInt(5);
                switch (randomDecision) {
                    case 1:
                        if (map[enemy.getY() - 1][enemy.getX()] == 0) {
                            enemy.moveUp();
                        }
                        break;
                    case 2:
                        if (map[enemy.getY() + 1][enemy.getX()] == 0) {
                            enemy.moveDown();
                        }
                        break;
                    case 3:
                        if (map[enemy.getY()][enemy.getX() - 1] == 0) {
                            enemy.moveLeft();
                        }
                        break;
                    case 4:
                        if (map[enemy.getY()][enemy.getX() + 1] == 0) {
                            enemy.moveRight();
                        }
                        break;
                    case 5:
                        if (!enemyShot.isShot()) {
                            enemyShot.shot(enemy.getPosition(), enemy.getDirection());
                        }
                        break;
                }
            }

            // enemyAI shell
            if (enemyShot.isShot()) {
                enemyShot.moving();

                if (map[enemyShot.getY()][enemyShot.getY()] == 1) {
                    enemyShot.destroyed();
                } else if (map[enemyShot.getY()][enemyShot.getY()] == 2) {
                    map[enemyShot.getY()][enemyShot.getY()] = 0;
                    enemyShot.destroyed();
                } else if (player.getPosition().equals(enemyShot.getPosition())) {
                    player.destroyed();
                    enemyShot.destroyed();
                } else if (base.equals(enemyShot.getPosition())) {
                    base = new Coordinates(-1, -1);
                    isBaseDestroyed = true;
                    enemyShot.destroyed();
                }
            }


        }
    }
}