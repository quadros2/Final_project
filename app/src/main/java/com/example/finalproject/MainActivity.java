package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.media.Image;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView title = findViewById(R.id.title);
        title.setVisibility(View.VISIBLE);
        Button play = findViewById(R.id.Play);
        play.setVisibility(View.VISIBLE);
        TextView creators = findViewById(R.id.creators);
        creators.setVisibility(View.VISIBLE);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                changeView();
            }
        });
    }

    public void changeView() {
        Intent intent = new Intent(this, NewActivity.class);
        startActivity(intent);
    }
}
