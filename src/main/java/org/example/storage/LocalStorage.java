package org.example.storage;

import org.example.DataBaseConnect;
import org.example.net.DetailTranslate;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LocalStorage implements ApiStorage {

    private Statement statement;

    public LocalStorage() {
        try {
            statement = DataBaseConnect.getDb().createStatement();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    private String setParamQuery(Object[] items) {
        StringBuilder builder = new StringBuilder("(");
        for (int i = 0; i < items.length; i++) {
            if (i == items.length - 1) {
                builder.append(items[i].toString());
                break;
            }
            builder.append(items[i].toString()).append(",");
        }
        return builder.append(");").toString();
    }

    @Override
    public void save(DetailTranslate detailTranslate) {
        String query = "INSERT INTO dict(word,translate_word) VALUES" + setParamQuery(
                new Object[]{"'" + detailTranslate.getTarget() + "'", "'" + detailTranslate.getTranslate() + "'"}
        );
        System.out.println(query);
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    @Override
    public void clear() {
        String query = "TRUNCATE TABLE dict;";
        try {
            statement.executeQuery(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<DetailTranslate> getAll() {
        String query = "select * from dict";
        ResultSet resultSet = null;
        List<DetailTranslate> list = new ArrayList<>();
        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String word = resultSet.getString(2);
                String translate = resultSet.getString(3);
                list.add(new DetailTranslate(word, translate));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return list;
    }
}
