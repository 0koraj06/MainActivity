package com.example.a0koraj06.mainactivity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddSongActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);

        Button addSongButton = (Button) findViewById(R.id.addSongButton);
        addSongButton.setOnClickListener(this);
    }

    @Override
    public void onClick( View view) {
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
            protected  String postData = "song=" + params[0] + "&artist" + params[1] + "&year" + params[2];


        }


    }
}
