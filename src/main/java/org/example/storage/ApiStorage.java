package org.example.storage;

import org.example.net.DetailTranslate;

import java.sql.SQLException;
import java.util.List;

public interface ApiStorage {
    void save(DetailTranslate detailTranslate);

    void clear();

    List<DetailTranslate> getAll();
}
