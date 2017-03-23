package com.example.a0koraj06.mainactivity;

import android.app.Activity;
import android.os.AsyncTask;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadActivity extends Activity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        Button btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(this);

    }

    public void onClick(View v)
    {
        EditText urlEditText = (EditText) findViewById(R.id.urlEditText);
        String url = urlEditText.getText().toString();

        EditText artistEditText = (EditText) findViewById(R.id.artistEditText);
        String artist = artistEditText.getText().toString();

        new DownloadSongsAsyncTask().execute(url, artist);
    }
    class DownloadSongsAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            String artist = params[1];

            HttpURLConnection connection;
            try {
                URL urlObject = new URL(url + "?artist=" + artist);
                connection = (HttpURLConnection) urlObject.openConnection();

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream in = connection.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));

                    String lines = "";
                    String line = br.readLine();
                    while (line != null) {

                        lines += line;
                        line = br.readLine();
                    }


                    return lines;
                }
            }
            catch (IOException e) {

                return  "Error: " + e.getMessage();
            }

            return "Error: something went wrong!";
        }

        @Override
        protected void onPostExecute(String songs) {
            TextView songsTextView = (TextView) findViewById(R.id.songsTextView);
            songsTextView.setText(songs);
        }
    }
}

