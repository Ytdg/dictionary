package org.example.net;

import java.io.IOException;

public interface ApiTranslate {
    DetailTranslate translate(String word, String sl, String dl) throws IOException;

}
