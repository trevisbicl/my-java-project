import processing.core.PApplet;

public class P5 extends PApplet {

    int[][] board = new int[4][4];
    boolean moved = false;

    public static void main(String[] args) {
        PApplet.main("P5");
    }

    @Override
    public void settings() {
        size(400, 400);
    }

    @Override
    public void setup() {
        textAlign(CENTER, CENTER);
        textSize(32);
        spawnTile();
        spawnTile();
    }

    @Override
    public void draw() {
        background(187, 173, 160); // цвет фона
        drawBoard();
    }

    void drawBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                fill(getTileColor(board[i][j]));
                rect(j * 100, i * 100, 100, 100, 10);

                if (board[i][j] != 0) {
                    fill(0);
                    text(board[i][j], j * 100 + 50, i * 100 + 50);
                }
            }
        }
    }

    int getTileColor(int value) {
        switch (value) {
            case 2: return color(238, 228, 218);
            case 4: return color(237, 224, 200);
            case 8: return color(242, 177, 121);
            case 16: return color(245, 149, 99);
            case 32: return color(246, 124, 95);
            case 64: return color(246, 94, 59);
            case 128: return color(237, 207, 114);
            case 256: return color(237, 204, 97);
            case 512: return color(237, 200, 80);
            case 1024: return color(237, 197, 63);
            case 2048: return color(237, 194, 46);
            default: return color(205, 193, 180);
        }
    }

    @Override
    public void keyPressed() {
        moved = false;
        switch (keyCode) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
        }
        if (moved) {
            spawnTile();
        }
        if (isGameOver()) {
            println("Game Over!");
            noLoop();
        }
    }

    void moveUp() {
        for (int col = 0; col < 4; col++) {
            int[] column = new int[4];
            for (int row = 0; row < 4; row++) {
                column[row] = board[row][col];
            }
            int[] merged = mergeTiles(column);
            for (int row = 0; row < 4; row++) {
                if (board[row][col] != merged[row]) moved = true;
                board[row][col] = merged[row];
            }
        }
    }

    void moveDown() {
        for (int col = 0; col < 4; col++) {
            int[] column = new int[4];
            for (int row = 0; row < 4; row++) {
                column[row] = board[3 - row][col];
            }
            int[] merged = mergeTiles(column);
            for (int row = 0; row < 4; row++) {
                if (board[3 - row][col] != merged[row]) moved = true;
                board[3 - row][col] = merged[row];
            }
        }
    }

    void moveLeft() {
        for (int row = 0; row < 4; row++) {
            int[] merged = mergeTiles(board[row]);
            for (int col = 0; col < 4; col++) {
                if (board[row][col] != merged[col]) moved = true;
                board[row][col] = merged[col];
            }
        }
    }

    void moveRight() {
        for (int row = 0; row < 4; row++) {
            int[] reversed = new int[4];
            for (int col = 0; col < 4; col++) {
                reversed[col] = board[row][3 - col];
            }
            int[] merged = mergeTiles(reversed);
            for (int col = 0; col < 4; col++) {
                if (board[row][3 - col] != merged[col]) moved = true;
                board[row][3 - col] = merged[col];
            }
        }
    }

    int[] mergeTiles(int[] line) {
        int[] result = new int[4];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            if (line[i] != 0) {
                result[index++] = line[i];
            }
        }
        for (int i = 0; i < 3; i++) {
            if (result[i] != 0 && result[i] == result[i + 1]) {
                result[i] *= 2;
                result[i + 1] = 0;
                moved = true;
            }
        }
        int[] compacted = new int[4];
        index = 0;
        for (int i = 0; i < 4; i++) {
            if (result[i] != 0) {
                compacted[index++] = result[i];
            }
        }
        return compacted;
    }

    void spawnTile() {
        int emptyCount = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) emptyCount++;
            }
        }
        if (emptyCount == 0) return;

        int randPos = (int) random(emptyCount);
        int value = random(1) < 0.9 ? 2 : 4;

        emptyCount = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) {
                    if (emptyCount == randPos) {
                        board[i][j] = value;
                        return;
                    }
                    emptyCount++;
                }
            }
        }
    }

    boolean isGameOver() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) return false;
                if (j < 3 && board[i][j] == board[i][j + 1]) return false;
                if (i < 3 && board[i][j] == board[i + 1][j]) return false;
            }
        }
        return true;
    }
}
