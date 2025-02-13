package org.example.ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Optional;

public class MainUi {
    private static final int WIDTH_LESS = 500;
    private static final int HEIGHT_LESS = 300;


    public static void init() {
        JFrame frame = new JFrame("Dictionary");
        Dimension winSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(winSize.width - WIDTH_LESS, winSize.height - HEIGHT_LESS);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        toolBar(frame);
        frame.setVisible(true);
    }

    private static void toolBar(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(ComponentsUI.btnToolBar());
        panel.setBackground(Color.WHITE);
        frame.add(panel, BorderLayout.NORTH);
    }


    private MainUi() {
    }
}