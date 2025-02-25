import processing.core.PApplet;

public class P4 extends PApplet {

    private int[][] gameField;
    private float x;
    private float y;
    private float extent;
    private int emptyX = 3;
    private int emptyY = 3;
    private int moves = 0;

    @Override
    public void settings() {
        fullScreen();
    }

    @Override
    public void setup() {
        resetGame();
    }

    @Override
    public void draw() {
        background(0);
        drawBorder();
        drawGrid();
        drawTiles();
        drawInfo();
    }

    void resetGame() {
        gameField = new int[4][4];
        int count = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (count <= 15) {
                    gameField[i][j] = count++;
                } else {
                    gameField[i][j] = 16;
                    emptyX = i;
                    emptyY = j;
                }
            }
        }
        shuffleBoard();
        moves = 0;
        x = width / 3f;
        y = 200f;
        extent = (width / 3f) / 4;
    }

    public void shuffleBoard() {
        for (int i = 0; i < 1000; i++) {
            int dir = (int) random(4);
            switch (dir) {
                case 0: moveTile(emptyX - 1, emptyY); break;
                case 1: moveTile(emptyX + 1, emptyY); break;
                case 2: moveTile(emptyX, emptyY - 1); break;
                case 3: moveTile(emptyX, emptyY + 1); break;
            }
        }
    }

    public void drawGrid() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                fill(255);
                stroke(255, 0, 0);
                strokeWeight(5);
                square(x + extent * j, y + i * extent, extent);
            }
        }
    }

    public void drawTiles() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (gameField[i][j] != 16) {
                    fill(255);
                    rect(x + extent * j, y + i * extent, extent, extent);

                    fill(0);
                    textSize(40);
                    textAlign(CENTER, CENTER);
                    text(gameField[i][j], x + extent * j + extent / 2, y + i * extent + extent / 2);
                }
            }
        }
    }

    public void drawInfo() {
        fill(255);
        textAlign(LEFT, TOP);
        textSize(20);
        text("Moves: " + moves, 10, 10);
        textAlign(CENTER, CENTER);
        text("Game 15", width / 2, y - 50);
        text("Start/Restart: ENTER", width / 2, height - 30);
    }

    @Override
    public void keyPressed() {
        boolean moved = false;

        switch (keyCode) {
            case UP:
                moved = moveTile(emptyX + 1, emptyY);
                break;
            case DOWN:
                moved = moveTile(emptyX - 1, emptyY);
                break;
            case LEFT:
                moved = moveTile(emptyX, emptyY + 1);
                break;
            case RIGHT:
                moved = moveTile(emptyX, emptyY - 1);
                break;
            case ENTER:
                resetGame();
                return;
        }

        if (moved) {
            moves++;
        }
    }

    public boolean moveTile(int newX, int newY) {
        if (newX >= 0 && newX < 4 && newY >= 0 && newY < 4) {
            gameField[emptyX][emptyY] = gameField[newX][newY];
            gameField[newX][newY] = 16;
            emptyX = newX;
            emptyY = newY;
            return true;
        }
        return false;
    }

    public void drawBorder() {
        stroke(255, 0, 0);
        strokeWeight(5);
        noFill();
        rect(0, 0, width, height);
    }

    public static void main(String[] args) {
        PApplet.main("P4");
    }
}
