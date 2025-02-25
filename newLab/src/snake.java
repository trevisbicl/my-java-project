import processing.core.PApplet;

public class snake extends PApplet {

    float circleX, circleY;
    float circleRadius = 15;
    float step = 20;

    int dirX = 1, dirY = 0; // Начальное движение вправо

    // Координаты еды
    float foodX, foodY;
    boolean foodEaten = true;

    // Очки
    int score = 0;

    // Хвост змеи
    float[][] trail = new float[1000][2]; // Максимальная длина хвоста
    int trailLength = 0; // Текущая длина хвоста

    public static void main(String[] args) {
        PApplet.main("snake");
    }

    @Override
    public void settings() {
        size(800, 600); // Размер окна
    }

    @Override
    public void setup() {
        frameRate(10);
        circleX = width / 2;
        circleY = height / 2;
        generateNewFood();
    }

    @Override
    public void draw() {
        background(0); // Чёрный фон
        drawGrid();

        fill(0, 255, 0);
        textSize(24);
        textAlign(CENTER, CENTER);
        text("Game: Snake with Growing Tail", width / 2, 30);
        text("Score: " + score, width / 2, height - 30);


        fill(255, 0, 0);
        ellipse(foodX, foodY, circleRadius * 2, circleRadius * 2);

        updateSnake();

        // Рисуем хвост змеи
        drawTrail();

        // Рисуем голову змеи
        fill(255, 255, 0);
        ellipse(circleX, circleY, circleRadius * 2, circleRadius * 2);

        // Проверяем столкновение
        checkCollision();
    }

    void updateSnake() {
        if (trailLength > 0) {
            for (int i = trailLength; i > 0; i--) {
                trail[i][0] = trail[i - 1][0];
                trail[i][1] = trail[i - 1][1];
            }
            trail[0][0] = circleX;
            trail[0][1] = circleY;
        }

        circleX += dirX * step;
        circleY += dirY * step;

        if (dist(circleX, circleY, foodX, foodY) < circleRadius * 2) {
            foodEaten = true;
            trailLength++; // Увеличиваем хвост
            score++; // Увеличиваем счёт
            generateNewFood();
        }
    }

    void drawTrail() {
        noStroke();
        for (int i = 0; i < trailLength; i++) {
            fill(255, 255, 0, 255 - (i * 255 / trailLength)); // Прозрачность хвоста
            ellipse(trail[i][0], trail[i][1], circleRadius * 2, circleRadius * 2); // Рисуем круг
        }
    }

    void checkCollision() {
        if (circleX < 0 || circleX >= width || circleY < 0 || circleY >= height) {
            gameOver();
        }

        for (int i = 0; i < trailLength; i++) {
            if (trail[i][0] == circleX && trail[i][1] == circleY) {
                gameOver();
            }
        }
    }

    void generateNewFood() {
        if (foodEaten) {
            foodX = floor(random(width / step)) * step + step / 2;
            foodY = floor(random(height / step)) * step + step / 2;
            foodEaten = false;
        }
    }

    void drawGrid() {
        stroke(50);
        for (int x = 0; x < width; x += step) {
            line(x, 0, x, height);
        }
        for (int y = 0; y < height; y += step) {
            line(0, y, width, y);
        }
    }

    @Override
    public void keyPressed() {
        if (keyCode == UP && dirY == 0) { // Вверх
            dirX = 0;
            dirY = -1;
        } else if (keyCode == DOWN && dirY == 0) { // Вниз
            dirX = 0;
            dirY = 1;
        } else if (keyCode == LEFT && dirX == 0) { // Влево
            dirX = -1;
            dirY = 0;
        } else if (keyCode == RIGHT && dirX == 0) { // Вправо
            dirX = 1;
            dirY = 0;
        }
    }

    void gameOver() {
        noLoop(); // Останавливаем игру
        fill(255, 0, 0);
        textSize(36);
        textAlign(CENTER, CENTER);
        text("Game Over!", width / 2, height / 2 - 20);
        textSize(24);
        text("Score: " + score, width / 2, height / 2 + 20);
        text("Press 'R' to Restart", width / 2, height / 2 + 60);
    }

    @Override
    public void keyReleased() {
        if (key == 'r' || key == 'R') {
            restartGame();
        }
    }

    void restartGame() {
        circleX = width / 2;
        circleY = height / 2;
        dirX = 1;
        dirY = 0;
        score = 0;
        trailLength = 0;
        foodEaten = true;
        generateNewFood();
        loop(); // Перезапускаем игру
    }
}
