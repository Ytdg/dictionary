package org.example.net;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpRequest;


public class Translate implements ApiTranslate {
    private HttpURLConnection connection;
    //init connect

    @Override
    public DetailTranslate translate(String word, String sl, String dl) throws IOException {


        connect(new URL("https://ftapi.pythonanywhere.com/translate?" + "sl=" + sl + "&" + "dl=" + dl + "&text=" + word));

        String res = read();

        return new DetailTranslate(word, res);
    }

    private String read() throws IOException {
        String result = null;
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String input;

            while ((input = in.readLine()) != null) {
                response.append(input);
            }
            JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();
            var d = jsonObject.get("translations").getAsJsonObject().get("possible-translations").getAsJsonArray();
            response.setLength(0);
            for (JsonElement item : d) {
                response.append(item.getAsString()).append(",");
            }
            result = response.toString();
        }
        connection.disconnect();
        connection = null;
        return result;
    }

    private void connect(URL url) throws IOException {
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

    }
}
