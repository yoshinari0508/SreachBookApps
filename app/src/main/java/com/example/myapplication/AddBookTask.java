package com.example.myapplication;

import android.os.AsyncTask;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddBookTask extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... params) {
        try {
            // 実際のエンドポイントに合わせてURLを指定
            URL url = new URL("http://www.cm.is.ritsumei.ac.jp/cgi-bin/saproglab/booksearch_json.py");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // データをJSON形式に変換
            String jsonData = "{"
                    + "ID\":\""+ 9999 + "\","
                    + "\"title\":\"" + params[0] + "\","
                    + "\"author\":\"" + params[1] + "\","
                    + "\"publish\":\"" + params[2] + "\","
                    + "\"price\":\"" + params[3] + "\","
                    + "\"isbn\":\"" + params[4] + "\""
                    + "}";

            try (OutputStream out = connection.getOutputStream()) {
                out.write(jsonData.getBytes("UTF-8"));
                out.flush();
            }

            // サーバーからのレスポンスを取得
            int responseCode = connection.getResponseCode();
            System.out.println("HTTP Response Code: " + responseCode);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
