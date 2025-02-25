import processing.core.PApplet;

public class P1 extends PApplet {

    private float dynamicSize;
    private float messageSize;
    private  float r;
    private  float g;
    private  float b;

    @Override
    public void settings() {
        fullScreen();
    }

    @Override
    public void setup() {
        messageSize = 50f;
        dynamicSize = 1f;
        r=50f;
        g=20f;
        b=40f;
    }

    @Override
    public void draw() {
        background(0,0,0);
        fill(r,g,b);
        textSize(messageSize);
        textAlign(CENTER, CENTER);
        text("Hello, Processing!!!", width / 2f, height / 2f);
        messageSize += dynamicSize;
        if (messageSize >= 100){
            dynamicSize *= -1;
            float temp = r;
            r = g;
            g = temp;
            r = 173;
            g = 255;
        }
        if (messageSize <= 20){
            dynamicSize *= -1;
            float temp = r;
            r = g;
            g = temp;
            r = 205;
            g = 92;
        }
    }

    public static void main(String[] args) {
        PApplet.main("P1");
    }

}
