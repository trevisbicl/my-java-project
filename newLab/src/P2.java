import processing.core.PApplet;

public class P2 extends PApplet {

    private String hoverText = "";

    @Override
    public void settings() {
        fullScreen();
    }

    @Override
    public void setup() {
        textSize(50);
        textAlign(CENTER, CENTER);
    }

    @Override
    public void draw() {
        background(225);

        drawButton(width / 3f, 200, "book");
        if (isMouseOver(width / 3f, 200, width / 3f * 2, 300)) {
            hoverText = "READING";
        }

        drawButton(width / 3f, 300, "music");
        if (isMouseOver(width / 3f, 300, width / 3f * 2, 400)) {
            hoverText = "PLAYING";
        }

        drawButton(width / 3f, 400, "movie");
        if (isMouseOver(width / 3f, 400, width / 3f * 2, 500)) {
            hoverText = "WATCHING";
        }

        drawButton(width / 3f, 500, "game");
        if (isMouseOver(width / 3f, 500, width / 3f * 2, 600)) {
            hoverText = "PLAYING";
        }

        if (!hoverText.isEmpty()) {
            fill(250, 0, 0);
            text(hoverText, width / 2f, height - 100);
        }
    }

    private void drawButton(float x, float y, String label) {
        fill(0, 0, 225);
        rect(x, y, width / 3f, 100);
        fill(255, 0, 0);
        text(label, x + width / 6f, y + 50);
    }

    private boolean isMouseOver(float x1, float y1, float x2, float y2) {
        return mouseX >= x1 && mouseY >= y1 && mouseX <= x2 && mouseY <= y2;
    }

    @Override
    public void mouseClicked() {
        fill(0, 0, 0);
        text("dead", width - 100, height - 50);
    }

    public static void main(String[] args) {
        PApplet.main("P2");
    }
}
