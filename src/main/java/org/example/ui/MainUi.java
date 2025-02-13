package org.example.ui;

import org.example.ApiBack;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

public final class MainUi {
    private static final int WIDTH_LESS;
    private static final int HEIGHT_LESS;

    static {
        WIDTH_LESS = 500;
        HEIGHT_LESS = 300;
    }

    private static ApiBack api;

    public static void init(ApiBack apiBack) {
        api = apiBack;
        JFrame frame = new JFrame("Dictionary");
        Dimension winSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(winSize.width - WIDTH_LESS, winSize.height - HEIGHT_LESS);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        toolBar(frame);
        frame.setVisible(true);
    }

    //when define
    private static void toolBar(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(ComponentsUI.btnToolBar(
                MainUi::openExplorer, "Загрузить"
        ));
        panel.setBackground(Color.WHITE);
        frame.add(panel, BorderLayout.NORTH);
    }

    private static void openExplorer() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Выберите файлы");
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text files (*.txt)", "txt"));
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            api.parseFiles(fileChooser.getSelectedFiles());
        }
    }


    private MainUi() {
    }
}