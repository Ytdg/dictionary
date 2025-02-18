package org.example.extract_data;

import org.example.util.CallBackBlockData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class ExtractData {
    private ExtractData() {

    }

    public static void extract(File[] data, CallBackBlockData<String[]> callBack) throws IOException {
        for (int i = 0; i < data.length; i++) {
            switch (getExtension(data[i].toPath())) {
                case "txt":
                    TxtFormat.extractWord(data[i].getPath(), callBack);
            }
        }
    }


    private static String getExtension(Path path) {
        String name = path.getFileName().toString();
        return name.substring(name.lastIndexOf('.') + 1);
    }


    protected static class TxtFormat {

        //separator " "
        static void extractWord(String path, CallBackBlockData<String[]> callBack) throws IOException {
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String line;
                while ((line = br.readLine()) != null) {
                    callBack.send(line.split("\\s+"));
                }
            }

        }

    }
}
