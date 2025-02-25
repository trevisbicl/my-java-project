import processing.core.PApplet;
import java.util.ArrayList;

public class PacMan extends PApplet {
    // Положение Pac-Man
    float pacmanX, pacmanY;
    float pacmanSize = 40;
    float speed = 2;
    int direction = 0; // 0 - вправо, 1 - вниз, 2 - влево, 3 - вверх

    // Список призраков
    ArrayList<Ghost> ghosts;

    // Размеры карты
    int cols = 15;
    int rows = 15;
    int cellSize = 40;
    int[][] map;

    public static void main(String[] args) {
        PApplet.main("PacMan");
    }

    @Override
    public void settings() {
        size(cols * cellSize, rows * cellSize); // Размер окна, в зависимости от карты
    }

    @Override
    public void setup() {
        pacmanX = width / 2;
        pacmanY = height / 2;

        // Создаем карту (1 - стена, 0 - проходимый участок)
        map = new int[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if (i == 0 || j == 0 || i == cols - 1 || j == rows - 1) {
                    map[i][j] = 1; // Стены по краям карты
                } else {
                    map[i][j] = (random(1) > 0.8) ? 1 : 0; // Случайные стены
                }
            }
        }

        // Создаем список призраков с разными цветами и добавляем их
        ghosts = new ArrayList<>();
        ghosts.add(new Ghost(random(width), random(height), color(255, 0, 0))); // Красный
        ghosts.add(new Ghost(random(width), random(height), color(0, 255, 0))); // Зеленый
        ghosts.add(new Ghost(random(width), random(height), color(0, 0, 255))); // Синий
        ghosts.add(new Ghost(random(width), random(height), color(255, 255, 0))); // Желтый
    }

    @Override
    public void draw() {
        background(0); // Черный фон
        drawMap();
        drawPacman();
        movePacman();
        drawAndMoveGhosts();
    }

    void drawMap() {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if (map[i][j] == 1) {
                    fill(100); // Цвет стены
                    noStroke();
                    rect(i * cellSize, j * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    void drawPacman() {
        fill(255, 255, 0); // Желтый цвет
        noStroke();
        float startAngle = radians(30); // Угол раскрытия
        float stopAngle = radians(330);
        if (frameCount % 20 > 10) { // Анимация "открытия" рта
            startAngle = radians(15);
            stopAngle = radians(345);
        }
        arc(pacmanX, pacmanY, pacmanSize, pacmanSize, startAngle + direction * HALF_PI, stopAngle + direction * HALF_PI, PIE);
    }

    void movePacman() {
        float newX = pacmanX;
        float newY = pacmanY;

        if (direction == 0) newX += speed; // Вправо
        if (direction == 1) newY += speed; // Вниз
        if (direction == 2) newX -= speed; // Влево
        if (direction == 3) newY -= speed; // Вверх

        // Проверка на столкновение с стенами
        int col = (int) (newX / cellSize);
        int row = (int) (newY / cellSize);

        if (map[col][row] == 0) { // Если клетка проходимая
            pacmanX = newX;
            pacmanY = newY;
        }

        // Проверка на выход за границы окна
        if (pacmanX < 0) pacmanX = width - cellSize;
        if (pacmanX >= width) pacmanX = 0;
        if (pacmanY < 0) pacmanY = height - cellSize;
        if (pacmanY >= height) pacmanY = 0;
    }

    @Override
    public void keyPressed() {
        if (keyCode == RIGHT) direction = 0; // Вправо
        if (keyCode == DOWN) direction = 1;  // Вниз
        if (keyCode == LEFT) direction = 2;  // Влево
        if (keyCode == UP) direction = 3;    // Вверх
    }

    void drawAndMoveGhosts() {
        for (Ghost ghost : ghosts) {
            ghost.display();
            ghost.move(ghosts);
        }
    }

    // Класс для создания призрака
    class Ghost {
        float x, y;
        float size = 40;
        float speed = 1.5f;
        int color;

        Ghost(float startX, float startY, int color) {
            this.x = startX;
            this.y = startY;
            this.color = color; // Уникальный цвет для каждого призрака
        }

        void display() {
            // Рисуем тело призрака (круг)
            fill(color);
            noStroke();
            ellipse(x, y, size, size);

            // Рисуем глаза
            float eyeSize = size / 6;
            float leftEyeX = x - size / 4;
            float rightEyeX = x + size / 4;
            float eyeY = y - size / 4;

            fill(255); // Белый цвет глаз
            ellipse(leftEyeX, eyeY, eyeSize, eyeSize);
            ellipse(rightEyeX, eyeY, eyeSize, eyeSize);

            // Рисуем зрачки (черные точки) внутри глаз
            float pupilSize = eyeSize / 2;
            float leftPupilX = leftEyeX;
            float rightPupilX = rightEyeX;
            float pupilY = eyeY;

            fill(0); // Черный цвет
            ellipse(leftPupilX, pupilY, pupilSize, pupilSize);
            ellipse(rightPupilX, pupilY, pupilSize, pupilSize);
        }

        void move(ArrayList<Ghost> otherGhosts) {
            // Призрак движется к Pac-Man
            float dx = pacmanX - x;
            float dy = pacmanY - y;
            float angle = atan2(dy, dx);

            // Двигаем призрака в направлении к Pac-Man
            float newX = x + speed * cos(angle);
            float newY = y + speed * sin(angle);

            // Проверка на столкновение с другими призраками
            for (Ghost other : otherGhosts) {
                if (other != this) {
                    float distance = dist(newX, newY, other.x, other.y);
                    if (distance < size) { // Если они слишком близко друг к другу
                        newX = x; // Остаемся на месте или отклоняемся
                        newY = y;
                        break;
                    }
                }
            }

            // Проверка на столкновение с картой
            int col = (int) (newX / cellSize);
            int row = (int) (newY / cellSize);
            if (col >= 0 && col < cols && row >= 0 && row < rows && map[col][row] == 0) {
                x = newX;
                y = newY;
            }

            // Проверка на выход за границы окна
            if (x < 0) x = width - cellSize;
            if (x >= width) x = 0;
            if (y < 0) y = height - cellSize;
            if (y >= height) y = 0;
        }
    }
}
