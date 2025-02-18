package org.example.ui;

import org.example.ApiBack;
import org.example.Mapper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
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
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setLayout(new BorderLayout());
        toolBar(frame);
        table(frame);
        frame.setVisible(true);
        api.getAll();
    }

    private static final String[] NAME_COLUMN_TABLE = new String[]{"WORD", "TRANSLATE"};

    private static void updateDataTable(DefaultTableModel model, Object[][] data) {
        model.setDataVector(data, NAME_COLUMN_TABLE);
    }

    private static void table(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, NAME_COLUMN_TABLE);
        JTable table = new JTable(model);
        Font font = new Font("Arial", Font.PLAIN, 14);
        table.setFont(font);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        frame.add(panel, BorderLayout.WEST);

        api.setListener(
                detailTranslates -> {
                    updateDataTable(model, Mapper.valuesToTable(detailTranslates));
                }
        );
    }

    //when define
    private static void toolBar(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(ComponentsUI.btnToolBar(
                MainUi::openExplorer, "Загрузить"
        ));
        panel.add(ComponentsUI.btnToolBar(
                api::clear, "Стереть"
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
            api.loadData(fileChooser.getSelectedFiles());
        }
    }


    private MainUi() {
    }
}