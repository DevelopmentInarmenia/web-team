package animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class HanoiFrame extends JFrame {

    public static int width = 1400;
    public static int height = 900;
    private final int MAX_DISCS_COUNT = 10;


    private Canvas canvas = new Canvas();

    public HanoiFrame() {

        JPanel jPanel = new JPanel();
        JButton move = new JButton("Move");
        JButton clean = new JButton("Clean discs");
        JButton pause = new JButton("Pause");
        JTextField field = new JTextField("Input discs count", 10);

        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.pauseControl();
            }
        });
        move.addActionListener(e -> {
            if (!field.getText().equals("")) {
                canvas.start();
            } else {
                JOptionPane.showMessageDialog(null, "Please enter number");
            }
        });
        clean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.clean();
            }
        });
        field.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                field.setText("");
            }
        });
        field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (Integer.parseInt(field.getText()) > MAX_DISCS_COUNT) {
                        JOptionPane.showMessageDialog(null, "Pleas enter number from 1 to 10");
                    } else {
                        canvas.addDisc(Integer.parseInt(field.getText()));
                    }

                } catch (NumberFormatException n) {
                    JOptionPane.showMessageDialog(null, "Please input number");
                    field.setText("");
                }
            }
        });

        field.setBackground(Color.lightGray);
        jPanel.add(move);
        jPanel.add(clean);
        jPanel.add(pause);
        jPanel.add(field);
        field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        add(jPanel, BorderLayout.NORTH);
        setSize(width, height);
        setTitle("Tower of Honoi");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        add(canvas);
    }

    public static void main(String[] args) {
        new HanoiFrame();

    }

}
