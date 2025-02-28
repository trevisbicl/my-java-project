import java.util.Random;

public class LogicFor2048 {

    private int[][] gameBoard;
    private int score;

    public LogicFor2048() {
        gameBoard = new int[4][4];
        score = 0;
        // Инициализация игрового поля
        gameBoard[0][3] = 2;
        gameBoard[0][2] = 2;
        gameBoard[0][1] = 2;
    }

    // Движение влево
    public void moveLeft() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int temp = gameBoard[i][j];
                for (int k = j - 1; k >= 0; k--) {
                    if (gameBoard[i][k] == 0) {
                        gameBoard[i][k] = temp;
                        gameBoard[i][k + 1] = 0;
                    }
                }
            }

            for (int j = 0; j < 4; j++) {
                for (int k = j - 1; k >= 0; k--) {
                    if (gameBoard[i][j] == gameBoard[i][k] && gameBoard[i][j] != 0) {
                        gameBoard[i][k] *= 2;
                        score += gameBoard[i][k];  // Добавление очков
                        gameBoard[i][j] = 0;
                    }
                }
            }

            for (int j = 0; j < 4; j++) {
                int temp = gameBoard[i][j];
                for (int k = j - 1; k >= 0; k--) {
                    if (gameBoard[i][k] == 0) {
                        gameBoard[i][k] = temp;
                        gameBoard[i][k + 1] = 0;
                    }
                }
            }
        }
    }

    // Движение вправо
    public void moveRight() {
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j > 0; j--) {
                for (int k = j - 1; k >= 0; k--) {
                    if (gameBoard[i][j] == 0 && gameBoard[i][k] != 0) {
                        gameBoard[i][j] = gameBoard[i][k];
                        gameBoard[i][k] = 0;
                        break;
                    }
                }
            }

            for (int j = 3; j > 0; j--) {
                if (gameBoard[i][j] == gameBoard[i][j - 1] && gameBoard[i][j] != 0) {
                    gameBoard[i][j] *= 2;
                    score += gameBoard[i][j];  // Добавление очков
                    gameBoard[i][j - 1] = 0;
                }
            }

            for (int j = 3; j > 0; j--) {
                for (int k = j - 1; k >= 0; k--) {
                    if (gameBoard[i][j] == 0 && gameBoard[i][k] != 0) {
                        gameBoard[i][j] = gameBoard[i][k];
                        gameBoard[i][k] = 0;
                        break;
                    }
                }
            }
        }
    }

    // Движение вверх
    public void moveUp() {
        for (int j = 0; j < 4; j++) {
            for (int i = 1; i < 4; i++) {
                if (gameBoard[i][j] != 0) {
                    int temp = gameBoard[i][j];
                    int k = i - 1;
                    while (k >= 0 && gameBoard[k][j] == 0) {
                        gameBoard[k][j] = temp;
                        gameBoard[k + 1][j] = 0;
                        k--;
                    }
                }
            }

            for (int i = 1; i < 4; i++) {
                if (gameBoard[i][j] == gameBoard[i - 1][j] && gameBoard[i][j] != 0) {
                    gameBoard[i - 1][j] *= 2;
                    score += gameBoard[i - 1][j];  // Добавление очков
                    gameBoard[i][j] = 0;
                }
            }

            for (int i = 1; i < 4; i++) {
                if (gameBoard[i][j] != 0) {
                    int temp = gameBoard[i][j];
                    int k = i - 1;
                    while (k >= 0 && gameBoard[k][j] == 0) {
                        gameBoard[k][j] = temp;
                        gameBoard[k + 1][j] = 0;
                        k--;
                    }
                }
            }
        }
    }

    // Движение вниз
    public void moveDown() {
        for (int j = 0; j < 4; j++) {
            for (int i = 2; i >= 0; i--) {
                if (gameBoard[i][j] != 0) {
                    int temp = gameBoard[i][j];
                    int k = i + 1;
                    while (k < 4 && gameBoard[k][j] == 0) {
                        gameBoard[k][j] = temp;
                        gameBoard[k - 1][j] = 0;
                        k++;
                    }
                }
            }

            for (int i = 2; i >= 0; i--) {
                if (gameBoard[i][j] == gameBoard[i + 1][j] && gameBoard[i][j] != 0) {
                    gameBoard[i + 1][j] *= 2;
                    score += gameBoard[i + 1][j];  // Добавление очков
                    gameBoard[i][j] = 0;
                }
            }

            for (int i = 2; i >= 0; i--) {
                if (gameBoard[i][j] != 0) {
                    int temp = gameBoard[i][j];
                    int k = i + 1;
                    while (k < 4 && gameBoard[k][j] == 0) {
                        gameBoard[k][j] = temp;
                        gameBoard[k - 1][j] = 0;
                        k++;
                    }
                }
            }
        }
    }

    // Метод для генерации случайного числа на пустой ячейке
    public void randomNumber() {
        boolean emptyIsFound = true;
        Random rnd = new Random();

        while (emptyIsFound) {
            int x = rnd.nextInt(4);
            int y = rnd.nextInt(4);
            if (gameBoard[x][y] == 0) {
                gameBoard[x][y] = rnd.nextInt(10) < 9 ? 2 : 4;  // Вероятность 9 к 1 для 2 и 4
                emptyIsFound = false;
            }
        }
    }

    public int getScore() {
        return score;
    }

    public int getCoordinates(int x, int y) {
        return gameBoard[x][y];
    }

    // Проверка на победу
    public boolean checkWin() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (gameBoard[i][j] == 2048) {
                    return true;  // Игрок выиграл, если одно из значений 2048
                }
            }
        }
        return false;
    }

    // Проверка на проигрыш (если нет доступных ходов)
    public boolean checkLose() {
        // Если на доске нет пустых ячеек и нет возможных слияний, игра заканчивается
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (gameBoard[i][j] == 0) {
                    return false; // Есть пустая ячейка, игра не завершена
                }
                if (i < 3 && gameBoard[i][j] == gameBoard[i + 1][j]) {
                    return false; // Можно сделать слияние вниз
                }
                if (j < 3 && gameBoard[i][j] == gameBoard[i][j + 1]) {
                    return false; // Можно сделать слияние вправо
                }
            }
        }
        return true; // Нет возможных ходов
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(int[][] gameBoard) {
        this.gameBoard = gameBoard;
    }
}
