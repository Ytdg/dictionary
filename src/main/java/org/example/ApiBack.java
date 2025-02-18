package org.example;

import org.example.net.DetailTranslate;
import org.example.util.CallBackBlockData;

import java.io.File;
import java.util.List;

//When define
public interface ApiBack {

    void loadData(File[] files);

    DetailTranslate translate(String word, String sl, String dl);

    void getAll();

    void setListener(CallBackBlockData<List<DetailTranslate>> listener);

    void save(DetailTranslate detailTranslate);

    void clear();
}
