import processing.core.PApplet;

public class Main extends PApplet {

    private LogicFor2048 logicFor2048;
    private boolean gameOver;
    private boolean gameWon;

    @Override
    public void settings() {
        fullScreen();
    }

    @Override
    public void setup() {
        logicFor2048 = new LogicFor2048();
        gameOver = false;
        gameWon = false;
    }

    @Override
    public void draw() {
        background(0);

        if (gameOver) {
            fill(255, 0, 0);
            textSize(60);
            textAlign(CENTER, CENTER);
            text("You Lose", width / 2, height / 2);
        } else if (gameWon) {
            fill(0, 255, 0);
            textSize(60);
            textAlign(CENTER, CENTER);
            text("You Win", width / 2, height / 2);
        } else {
            drawBoard();
        }

        fill(255);
        textSize(40);
        textAlign(LEFT, TOP);
        text("Score: " + logicFor2048.getScore(), 50, 50);

        textSize(30);
        text("Press R for New Game", 50, 100);
    }

    private void drawBoard() {
        float leftBorder = width / 3f;
        float topBorder = 200;
        float rectWidthSize = leftBorder / 4;
        float rectHeightSize = leftBorder / 4;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                fill(150, 75, 0);
                rect(leftBorder + j * rectWidthSize, topBorder + i * rectHeightSize, rectWidthSize, rectHeightSize);

                int value = logicFor2048.getCoordinates(i, j);
                if (value != 0) {
                    fill(255);
                    textSize(50);
                    textAlign(CENTER, CENTER);
                    text(value, leftBorder + j * rectWidthSize + rectWidthSize / 2, topBorder + i * rectHeightSize + rectHeightSize / 2);
                }
            }
        }
    }

    @Override
    public void keyPressed() {
        if (gameOver || gameWon) {
            return;  // Не реагировать на клавиши, если игра закончена
        }

        switch (keyCode) {
            case RIGHT:
                logicFor2048.moveRight();
                logicFor2048.randomNumber();
                break;
            case LEFT:
                logicFor2048.moveLeft();
                logicFor2048.randomNumber();
                break;
            case UP:
                logicFor2048.moveUp();
                logicFor2048.randomNumber();
                break;
            case DOWN:
                logicFor2048.moveDown();
                logicFor2048.randomNumber();
                break;
            case 'R':  // Перезагрузка игры
                setup();  // Вызовем setup для перезапуска игры
                break;
            default:
                break;
        }

        // Проверяем условия для выигрыша или проигрыша
        checkGameStatus();
    }

    private void checkGameStatus() {
        if (logicFor2048.checkWin()) {
            gameWon = true;
        } else if (logicFor2048.checkLose()) {
            gameOver = true;
        }
    }

    public static void main(String[] args) {
        PApplet.main("Main");
    }
}
