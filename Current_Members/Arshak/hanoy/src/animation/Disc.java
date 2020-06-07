package animation;

import java.awt.*;
import java.util.Random;

public class Disc {
    enum DiscDirection {
        UP(0, -1), LEFT(-1, 0), RIGHT(1, 0), DOWN(0, 1);

        int x;
        int y;

        DiscDirection(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    private int x;
    private int y;
    private int width;
    private int height;
    private Canvas canvas;
    public boolean inTheTower = true;

    public Disc(int x, int y, int width, int height, Canvas canvas) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.canvas = canvas;
    }

    private Color randomColor() {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return new Color(red, green, blue);
    }

    public void move(DiscDirection direction) {

        this.x += direction.x;
        this.y += direction.y;
        canvas.repaint();
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void discDragger(int dX, int dY) {
        if (inTheTower) {
            this.x += dX;
            this.y += dY;
            canvas.repaint();
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void draw(Graphics g) {
        g.setColor(randomColor());
        g.fill3DRect(x, y, width, height, true);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }
}

