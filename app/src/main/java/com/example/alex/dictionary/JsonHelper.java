
package com.example.alex.dictionary;

import android.net.ParseException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class JsonHelper {
    private static JsonHelper helper;
    private static String key = "trnsl.1.1.20180821T123608Z.266179947af465ba.641d3675275d3e122fcda62b8e9cfedff81f2c07";
    private static String lang = "en-ru";

    // private String uRl ="https://translate.yandex.net/api/v1.5/tr.json/translate?key="+key+"&lang="+lang+"&text=";
    private JsonHelper() {

    }

    public static JsonHelper getInstance() {
        if (helper == null) {
            helper = new JsonHelper();
        }
        return helper;
    }

    public static String getJsonStringYandex(String text) throws IOException {
        String uRl = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + key + "&lang=" + lang + "&text=";
        URL url = new URL(uRl + text);
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.connect();
        int rc = httpConnection.getResponseCode();

        if (rc == 200) {
            String line = null;
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            StringBuilder strBuilder = new StringBuilder();
            while ((line = buffReader.readLine()) != null) {
                strBuilder.append(line + '\n');
            }

            return strBuilder.toString();

        }
        return "Подключитесь и интернету";
    }

/*
    public static List<String> getTranslateFromJSON(String str) {

        return ;
    }
*/
}