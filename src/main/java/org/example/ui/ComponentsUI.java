package org.example.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

interface EventClick {
    void execute();
}

public final class ComponentsUI {

    public static Component btnToolBar(EventClick ecl, String text) {
        JButton btn = new JButton();
        btn.setText(text);

        btn.setBackground(Color.WHITE);

        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(5, 10, 5, 10));
        btn.addActionListener(e -> {
            ecl.execute();
        });
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(Color.GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(Color.WHITE);
            }
        });
        return btn;
    }

}
