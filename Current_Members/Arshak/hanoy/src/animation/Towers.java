package animation;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public enum Towers {
    TOWER1(HanoiFrame.width/4, HanoiFrame.height/3,
            HanoiFrame.width/100,
            HanoiFrame.height*2/5, Color.RED,new CopyOnWriteArrayList<Disc>()),
    TOWER2(HanoiFrame.width/2, HanoiFrame.height/3,
            HanoiFrame.width/100,
            HanoiFrame.height*2/5, Color.CYAN,new CopyOnWriteArrayList<Disc>() ),
    TOWER3(HanoiFrame.width*3/4, HanoiFrame.height/3,
            HanoiFrame.width/100,
            HanoiFrame.height*2/5, Color.ORANGE,new CopyOnWriteArrayList<Disc>());


    Color color;
    private int x;
    private int y;
    private int width;
    private int height;
    public CopyOnWriteArrayList<Disc> towerDiscs;
    public boolean isSelected;




    Towers(int x, int y, int width, int height, Color color, CopyOnWriteArrayList towerDiscs){
         this.x = x;
         this.y = y;
         this.width = width;
         this.height = height;
         this.color = color;
         this.towerDiscs = towerDiscs;
    }

    public boolean isBelong(int x, int y) {
        return x >= getX() && x <= getX() + getWidth()
                && y >= getY() && y <= getY() + getHeight();
    }

    public void move0(int dX, int dY) {
        this.x += dX;
        this.y += dY;
    }

    public void draw(Graphics g){

         g.setColor(color);
         g.fill3DRect(x,y,width,height,true);
         g.fill3DRect(x-7*width, y+height,15*width,width,true);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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

    public void setHeight(int height) {
        this.height = height;
    }
}
