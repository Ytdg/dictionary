package org.example;

import org.example.extract_data.ExtractData;
import org.example.net.ApiTranslate;
import org.example.net.DetailTranslate;
import org.example.net.Translate;
import org.example.storage.ApiStorage;
import org.example.storage.LocalStorage;
import org.example.util.CallBackBlockData;

import java.io.File;
import java.util.HashSet;
import java.util.List;

public class ImplBack implements ApiBack {

    //besides txt, there may be others.

    private final ApiTranslate apiTranslate = new Translate();
    private final ApiStorage apiStorage = new LocalStorage();
    private CallBackBlockData<List<DetailTranslate>> listener = s -> {
    };

    @Override
    public void loadData(File[] files) {
        HashSet<String> set = new HashSet<>(); //uniqueness for net-query
        try {
            ExtractData.extract(files, data -> {
                for (String datum : data) {
                    if (!set.contains(datum)) {
                        save(translate(datum, "en", "ru"));
                        set.add(datum);
                    }
                }
                set.clear();
            });
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public DetailTranslate translate(String word, String sl, String dl) {
        DetailTranslate dT = null;
        try {
            dT = apiTranslate.translate(word, sl, dl);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return dT;
    }

    //data send to listener
    // set listener for listen data
    @Override
    public void getAll() {
        listener.send(apiStorage.getAll());
    }

    @Override
    public void setListener(CallBackBlockData<List<DetailTranslate>> listener) {
        this.listener = listener;
    }


    @Override
    public void save(DetailTranslate detailTranslate) {
        try {
            apiStorage.save(detailTranslate);
            getAll();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void clear() {
        apiStorage.clear();
        getAll();
    }
    //when define

}
