package org.example.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class ComponentsUI {

    public static Component btnToolBar() {
        JButton btn = new JButton();
        btn.setText("Загрузить");

        btn.setBackground(Color.LIGHT_GRAY);

        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(5, 10, 5, 10));
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(Color.GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(Color.LIGHT_GRAY);
            }
        });
        return btn;
    }

}
