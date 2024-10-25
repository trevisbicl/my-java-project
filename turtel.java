import java.util.Scanner;

public class turtel {
    static final int UP = 0;
    static final int RIGHT = 1;
    static final int DOWN = 2;
    static final int LEFT = 3;

    static int direction = RIGHT;
    static boolean penDown = true;
    static int x = 0, y = 0;

    static final char[][] AREA_OF_TURTLE = new char[20][20];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        initializeArea();

        while (true) {
            String command = in.next();
            if (executeCommand(command, in)) {
                break;
            }
        }
    }

    private static boolean executeCommand(String command, Scanner in) {
        switch (command) {
            case "Print":
                printArea();
                break;
            case "Move":
                move(in);
                break;
            case "TurnRight":
                turnRight();
                break;
            case "TurnLeft":
                turnLeft();
                break;
            case "PenDown":
                setPenDown(true);
                break;
            case "PenUp":
                setPenDown(false);
                break;
            case "Exit":
                return true;
        }
        return false;
    }

    private static void initializeArea() {
        for (int i = 0; i < AREA_OF_TURTLE.length; i++) {
            for (int j = 0; j < AREA_OF_TURTLE.length; j++) {
                AREA_OF_TURTLE[i][j] = '.';
            }
        }
    }

    private static void printArea() {
        for (char[] row : AREA_OF_TURTLE) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    private static void move(Scanner in) {
        int steps = in.nextInt();
        for (int i = 0; i < steps; i++) {
            makeMove();
            if (penDown) {
                AREA_OF_TURTLE[x][y] = '*';
            }
        }
    }

    private static void makeMove() {
        switch (direction) {
            case UP:
                if (x > 0) x--;
                break;
            case RIGHT:
                if (y < AREA_OF_TURTLE[0].length - 1) y++;
                break;
            case DOWN:
                if (x < AREA_OF_TURTLE.length - 1) x++;
                break;
            case LEFT:
                if (y > 0) y--;
                break;
        }
    }

    private static void turnRight() {
        direction = (direction == LEFT) ? UP : direction + 1;
    }

    private static void turnLeft() {
        direction = (direction == UP) ? LEFT : direction - 1;
    }

    private static void setPenDown(boolean state) {
        penDown =state;
}
}
