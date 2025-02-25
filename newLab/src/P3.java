import processing.core.PApplet;

public class P3 extends PApplet {
    int x, y;
    int xSpeed, ySpeed;
    int distance;

    public void settings() {
        fullScreen();
    }

    public void setup() {
        x = width / 2;
        y = height / 2;

        xSpeed = 3;
        ySpeed = 2;

        distance = 200;
        textSize(200);
    }

    public void draw() {
        background(0);

        x += xSpeed;
        y += ySpeed;

        if (x - 100 <= 0 || x + 100 >= width) {
            xSpeed *= -1;
            x = constrain(x, 100, width - 100);
        }
        if (y - 100 <= 0 || y + 100 >= height) {
            ySpeed *= -1;
            y = constrain(y, 100, height - 100);
        }

        int x2 = x + distance;
        int y2 = y;

        fill(200);
        noStroke();
        text("D", x, y);

        fill(200);
        text("V", x + 100, y);

        fill(200);
        text("D", x2, y2);

        int ovalX = (x + x2) / 2 + 50;
        int ovalY = max(y, y2) + 100;
        int ovalWidth = 300;
        int ovalHeight = 60;

        fill(200);
        ellipse(ovalX, ovalY, ovalWidth, ovalHeight);

        if (keyPressed) {
            xSpeed *= -1;
            ySpeed *= -1;
        }
    }

    public static void main(String[] args) {
        PApplet.main("P3");
    }
}
