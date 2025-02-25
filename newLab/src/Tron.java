import processing.core.PApplet;

public class Tron extends PApplet {
    int gridSize = 20;
    int cols, rows;
    int[][] grid;
    int[][] tailTimer;
    int p1X, p1Y, p2X, p2Y;
    int p1DirX = 1, p1DirY = 0, p2DirX = -1, p2DirY = 0;
    boolean gameOver = false;
    int frameDelay = 10;
    int tailDuration = 40; // Средняя скорость исчезновения хвоста

    public static void main(String[] args) {
        PApplet.main("Tron");
    }

    public void settings() {
        size(800, 800);
    }

    public void setup() {
        cols = width / gridSize;
        rows = height / gridSize;
        resetGame();
    }

    public void draw() {
        if (frameCount % frameDelay != 0) return; // Уменьшение скорости
        background(0);

        if (gameOver) {
            drawGameOverScreen();
        } else {
            updateTailTimers();
            movePlayer(1, p1DirX, p1DirY);
            movePlayer(2, p2DirX, p2DirY);
            drawGrid();
            drawPlayers();
        }
    }

    void resetGame() {
        grid = new int[cols][rows];
        tailTimer = new int[cols][rows];
        p1X = cols / 4;
        p1Y = rows / 2;
        p2X = 3 * cols / 4;
        p2Y = rows / 2;
        p1DirX = 1;
        p1DirY = 0;
        p2DirX = -1;
        p2DirY = 0;
        gameOver = false;

        grid[p1X][p1Y] = 1;
        grid[p2X][p2Y] = 2;
        tailTimer[p1X][p1Y] = tailDuration;
        tailTimer[p2X][p2Y] = tailDuration;
    }

    void drawGameOverScreen() {
        fill(0, 150);
        rect(0, 0, width, height);

        fill(255);
        textSize(32);
        textAlign(CENTER, CENTER);
        text("Game Over", width / 2, height / 2 - 20);
        text("Press 'R' to Restart", width / 2, height / 2 + 20);
    }

    void movePlayer(int player, int dirX, int dirY) {
        int x = player == 1 ? p1X : p2X;
        int y = player == 1 ? p1Y : p2Y;

        x += dirX;
        y += dirY;

        if (x < 0 || x >= cols || y < 0 || y >= rows || grid[x][y] != 0) {
            gameOver = true;
            return;
        }

        grid[x][y] = player;
        tailTimer[x][y] = tailDuration;

        if (player == 1) {
            p1X = x;
            p1Y = y;
        } else {
            p2X = x;
            p2Y = y;
        }
    }

    void updateTailTimers() {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if (tailTimer[i][j] > 0) {
                    tailTimer[i][j]--;
                    if (tailTimer[i][j] == 0) {
                        grid[i][j] = 0; // Очистить клетку, если таймер истёк
                    }
                }
            }
        }
    }

    void drawGrid() {
        stroke(50);
        for (int i = 0; i < cols; i++) {
            line(i * gridSize, 0, i * gridSize, height);
        }
        for (int j = 0; j < rows; j++) {
            line(0, j * gridSize, width, j * gridSize);
        }
    }

    void drawPlayers() {
        noStroke();
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if (grid[i][j] == 1) {
                    fill(0, 0, 255);
                } else if (grid[i][j] == 2) {
                    fill(255, 0, 0);
                } else {
                    continue;
                }
                int alpha = (int) map(tailTimer[i][j], 0, tailDuration, 50, 255); // Прозрачность хвоста
                fill(grid[i][j] == 1 ? color(0, 0, 255, alpha) : color(255, 0, 0, alpha));
                rect(i * gridSize, j * gridSize, gridSize, gridSize);
            }
        }
    }

    public void keyPressed() {
        if (key == 'w' && p1DirY == 0) {
            p1DirX = 0;
            p1DirY = -1;
        } else if (key == 's' && p1DirY == 0) {
            p1DirX = 0;
            p1DirY = 1;
        } else if (key == 'a' && p1DirX == 0) {
            p1DirX = -1;
            p1DirY = 0;
        } else if (key == 'd' && p1DirX == 0) {
            p1DirX = 1;
            p1DirY = 0;
        } else if (keyCode == UP && p2DirY == 0) {
            p2DirX = 0;
            p2DirY = -1;
        } else if (keyCode == DOWN && p2DirY == 0) {
            p2DirX = 0;
            p2DirY = 1;
        } else if (keyCode == LEFT && p2DirX == 0) {
            p2DirX = -1;
            p2DirY = 0;
        } else if (keyCode == RIGHT && p2DirX == 0) {
            p2DirX = 1;
            p2DirY = 0;
        } else if (key == 'r' || key == 'R') {
            resetGame();
        }
    }
}
