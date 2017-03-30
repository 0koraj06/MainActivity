package com.example.a0koraj06.mainactivity;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class AddSongActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);

        Button addSongButton = (Button) findViewById(R.id.addSongButton);
        addSongButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        EditText songTitleEditText = (EditText) findViewById(R.id.songTitleEditText);
        String songTitle = songTitleEditText.getText().toString();

        EditText artistEditText = (EditText) findViewById(R.id.artistEditText);
        String artist = artistEditText.getText().toString();

        EditText yearEditText = (EditText) findViewById(R.id.yearEditText);
        String year = yearEditText.getText().toString();

        (new AddSongAsyncTask()).execute(songTitle, artist, year);
    }

    class AddSongAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            String postData = "song=" + params[0] + "artist=" + params[1] + "year=" + params[2];

            try {


                URL urlObject = new URL("http://www.free-map.org.uk/course/mad/ws/addhit.php");
            } catch (IOException e) {
                return "Error: " + e.getMessage();

            }

            return "Error: something went wrong";
        }

        try
        {
            String json = "[" +
                "{'song' :, 'artist':, 'year':, 'month':, 'chart':, 'ID':, 'quantity':}"
                + "]";
            JSONArray jsonArr = new JSONArray(json);
            String curSong, curArtist, curYear, curMonth, curChart, curID, curQuantity;

            TextView tv = (TextView)findViewById(R.id.tv1);
            String text = "";

            for(int i=0; i<jsonArr.length(); i++)
            {

            JSONObject curObj = jsonArr.getJSONObject(i);
             String song = curObj.getString("song"),
            artist = curObj.getString("artist"),
            year = curObj.getString("year"),
            month = curObj.getString("month"),
            chart = curObj.getString("chart"),
            ID = curObj.getString("ID"),
            quantity = curObj.getString("quantity");

                text +=" Song= "+ song + "Artist = " + artist + "Year = " + year + "Month = " + month + "Chart = " + chart + "ID = " + ID+ "Quantity = " + quantity +"\n";



            }

            catch (JSONException e)
        {

            new AlertDialog.Builder(this).setMessage(e.toString()).setPositiveButton("OK", null).show();
        }


    }
}

