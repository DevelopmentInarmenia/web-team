package animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;

import static animation.Disc.DiscDirection.*;

public class Canvas extends JPanel implements Runnable {

    private Towers tower1 = Towers.TOWER1;
    private Towers tower2 = Towers.TOWER2;
    private Towers tower3 = Towers.TOWER3;

    private int disc_X = tower1.getX() - tower1.getWidth() / 2;
    private int disc_Y = tower1.getY() + tower1.getHeight();
    private int disc_width = 2 * tower1.getWidth();
    private int discHeight = tower1.getWidth();

    private int stepCount;
    private double discsCount;
    private int mX;
    private int mY;

    private boolean isStart;
    private boolean isPaused;
    private boolean isMoving;

    private boolean isSelected = false;

    Canvas() {
        Color BackColor = new Color(49, 53, 59);
        setBackground(BackColor);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePressedPerformed(e);
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseDraggedPerformed(e);
            }
        });
    }

    private void select(int x, int y) {
        clearSelects();
        if (tower1.isBelong(x, y)) {
            isSelected = true;
            tower1.isSelected = true;
            return;
        } else if (tower2.isBelong(x, y)) {
            isSelected = true;
            tower2.isSelected = true;
            return;
        } else if (tower3.isBelong(x, y)) {
            isSelected = true;
            tower3.isSelected = true;
            return;
        }
        isSelected = false;
    }

    private void clearSelects() {
        tower1.isSelected = false;
        tower2.isSelected = false;
        tower3.isSelected = false;
    }

    private void mousePressedPerformed(MouseEvent e) {
        select(e.getX(), e.getY());
        if (isSelected) {
            repaint();
        }
        mX = e.getX();
        mY = e.getY();
    }

    private void mouseDraggedPerformed(MouseEvent e) {
        if (isSelected) {
            getSelected().move0(e.getX() - mX, e.getY() - mY);
            selectedTowerDiscs(getSelected(), e.getX() - mX, e.getY() - mY);
            repaint();
        }
        mX = e.getX();
        mY = e.getY();
        disc_X = tower1.getX() - tower1.getWidth() / 2;
        disc_Y = tower1.getY() + tower1.getHeight();
    }

    private void selectedTowerDiscs(Towers towers, int dX, int dY) {
        for (Disc d : towers.towerDiscs) {
            d.discDragger(dX, dY);
        }
    }

    private Towers getSelected() {
        if (tower1.isSelected) {
            return tower1;
        } else if (tower2.isSelected) {
            return tower2;
        } else if (tower3.isSelected) {
            return tower3;
        }
        return null;
    }

    public void addDisc(int discCount) {
        for (int i = 0; i < discCount; i++) {
            tower1.towerDiscs.add(new Disc(disc_X, disc_Y, disc_width, discHeight, this));
            discsCount++;
            for (Disc d : tower1.towerDiscs) {
                d.setY(d.getY() - d.getHeight());
            }
            disc_X -= tower1.getWidth() / 2;
            disc_width += tower1.getWidth();
        }
        repaint();
        Collections.reverse(tower1.towerDiscs);
    }

    public void clean() {
        stepCount = 0;
        tower1.towerDiscs.clear();
        tower2.towerDiscs.clear();
        tower3.towerDiscs.clear();
        disc_X = tower1.getX() - tower1.getWidth() / 2;
        disc_Y = tower1.getY() + tower1.getHeight();
        disc_width = 2 * tower1.getWidth();
        discHeight = tower1.getWidth();
        discsCount = 0;
        repaint();
    }

    public void start() {
        if (!isMoving || isPaused) {
            if (!isPaused) {
                new Thread(this).start();
            } else {
                synchronized (this) {
                    notify();
                    isPaused = false;
                }
            }
        }
    }

    private void moveDiscRecursion(int n, Towers sourceTower, Towers tempTower, Towers destinationTower) {
        if (n == 1) {
            moveDisc0(sourceTower, destinationTower, sourceTower.towerDiscs.get(sourceTower.towerDiscs.size() - 1));
            isStart = false;
        } else {
            moveDiscRecursion(n - 1, sourceTower, destinationTower, tempTower);
            moveDisc0(sourceTower, destinationTower, sourceTower.towerDiscs.get(sourceTower.towerDiscs.size() - 1));
            isStart = false;
            moveDiscRecursion(n - 1, tempTower, sourceTower, destinationTower);
        }
    }

    public void update(Graphics g) {
        paint(g);
    }

    private void moveDisc0(Towers startTower, Towers endTower, Disc disc) {
        ++stepCount;
        while (disc.getY() >= startTower.getY() - disc.getHeight()) {
            pause();
            disc.move(UP);
        }
        disc.inTheTower = false;
        while (disc.getX() + disc.getWidth() / 2 != endTower.getX() + endTower.getWidth() / 2) {
            if (disc.getX() + disc.getWidth() / 2 < endTower.getX() + endTower.getWidth() / 2) {
                disc.move(RIGHT);
                if (disc.getY() + disc.getHeight() > endTower.getY()) {
                    disc.move(UP);
                } else {
                    disc.move(DOWN);
                }
            } else {
                disc.move(LEFT);
                if (disc.getY() + disc.getHeight() > endTower.getY()) {
                    disc.move(UP);
                } else {
                    disc.move(DOWN);
                }
            }
        }
        while (disc.getY() + disc.getHeight() <= endTower.getY() + endTower.getHeight() - endTower.towerDiscs.size() * disc.getHeight()) {
            isSelected = false;
            pause();
            disc.move(DOWN);
        }
        isStart = true;
        disc.inTheTower = true;
        endTower.towerDiscs.add(startTower.towerDiscs.get(startTower.towerDiscs.size() - 1));
        startTower.towerDiscs.remove(startTower.towerDiscs.get(startTower.towerDiscs.size() - 1));
    }

    public void paint(Graphics g) {
        super.paint(g);

        tower1.draw(g);
        tower2.draw(g);
        tower3.draw(g);

        String str = "Minimal count / ";
        g.setColor(Color.GREEN);
        g.setFont(new Font("serif", 4, 30));
        g.drawString(str + ((int) Math.pow(2, discsCount) - 1) + "  -" + "  counts / " + stepCount, 50, 50);

        g.setColor(Color.RED);
        String str1 = "Tower source";
        g.setFont(new Font("serif", 2, 15));
        g.drawString(str1, tower1.getX() - 35, tower1.getY() - 20);

        g.setColor(Color.cyan);
        String str2 = "Tower temp";
        g.setFont(new Font("serif", 2, 15));
        g.drawString(str2, tower2.getX() - 30, tower2.getY() - 20);

        g.setColor(Color.ORANGE);
        String str3 = "Tower destination";
        g.setFont(new Font("serif", 2, 15));
        g.drawString(str3, tower3.getX() - 45, tower3.getY() - 20);

        if (isPaused) {
            g.setColor(Color.RED);
            String str4 = "Pause";
            g.setFont(new Font("serif", 1, 70));
            g.drawString(str4, HanoiFrame.width / 3 + 50, HanoiFrame.height / 6);
        }
        drawEndText(g);

        for (Disc disc : tower1.towerDiscs) {
            disc.draw(g);
        }
        for (Disc disc : tower2.towerDiscs) {
            disc.draw(g);
        }
        for (Disc disc : tower3.towerDiscs) {
            disc.draw(g);
        }
    }

    private void drawEndText(Graphics g) {
        Color color1 = Color.cyan;
        g.setColor(color1.darker());
        String str5 = " Authors ";
        g.setFont(new Font("serif", 1, 25));
        g.drawString(str5, HanoiFrame.width / 60, HanoiFrame.height / 6);

        g.setFont(new Font("serif", 2, 20));
        String str6 = "Hayk Abajyan.";
        String str7 = "Arshak Papoyan.";
        String str8 = "Armen Martirosyan.";
        String str9 = "Garik Tepanosyan.";
        g.drawString(str6, HanoiFrame.width / 60, HanoiFrame.height / 4);
        g.drawString(str7, HanoiFrame.width / 60, HanoiFrame.height / 4 + 50);
        g.drawString(str8, HanoiFrame.width / 60, HanoiFrame.height / 4 + 100);
        g.drawString(str9, HanoiFrame.width / 60, HanoiFrame.height / 4 + 150);

        g.drawLine(0, 100, HanoiFrame.width, 100);
        g.drawLine(2, 100 + 2, HanoiFrame.width, 100 + 2);
        g.drawLine(220, 100, 220, HanoiFrame.height);
        g.drawLine(220 + 2, 100 + 2, 220 + 2, HanoiFrame.height);

        String str10 = "Logical task ` tower of  hanoi";
        g.setFont(new Font("Arial", 2, 20));
        g.drawString(str10, HanoiFrame.width / 3 + 100, HanoiFrame.height / 10);
    }

    void pauseControl() {
        isPaused = true;
    }

    private void pause() {
        if (isPaused) {
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void run() {
        moveDiscRecursion(tower1.towerDiscs.size(), tower1, tower2, tower3);
    }


}