package org.example;

import org.example.net.DetailTranslate;

import java.util.List;

public class Mapper {
    //{{Alisa,23},{German,40}}
    public static Object[][] valuesToTable(List<DetailTranslate> list) {
        Object[][] matrix = new Object[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            matrix[i][0] = list.get(i).getTarget();
            matrix[i][1] = list.get(i).getTranslate();
        }
        return matrix;
    }
}
