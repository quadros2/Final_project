package com.example.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.se.omapi.Session;
import android.text.InputType;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class NewActivity extends AppCompatActivity {
    private int startNum; // COULD MAKE DIFFERENT LEVELS AND CHANGE THE DIFFICULTY HERE(ie 10 instead of 100)
    private int secondNum;
    private int ans;
    private String operation;
    private static int score = 0;
    //private String data;
    private String user = "";
    //MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game); // app/src/main/res/layout/activity_new_game.xml
        //Instructions: perform the given operation on your number and round down to the nearest int
        generateNumber();
        Button checkAnswer = findViewById(R.id.checkAnswer);
        checkAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userAnswer = findViewById(R.id.userAnswer);
                int compare = Integer.parseInt(userAnswer.getText().toString());
                if (compare == ans) {
                    soundEffect("dub");
                    score++;
                    correct();
                } else {
                    soundEffect("nope");
                    endGame();
                }
            }
        });
    }

    private void generateNumber() {
        startNum = (int) (Math.random() * 100);
        secondNum = (int) (Math.random() * 100);
        switch (pemdas()) {
            case (1):
                operation = " multiplied by ";
                ans = (startNum * secondNum);
                break;
            case (2):
                operation = " divided by ";
                ans = (int) (startNum / secondNum);
                break;
            case (3):
                operation = " minus ";
                if (secondNum > startNum) {
                    int swap = secondNum;
                    secondNum = startNum;
                    startNum = swap;
                }
                ans = (startNum - secondNum);
                break;
            default:
                ans = (startNum + secondNum);
                operation = " plus ";
        }
        TextView tellTheUser = findViewById(R.id.inst);
        String instIntro = "Instructions - enter the answer to the following question below rounded down to the nearest integer: ";
        String tellUser = instIntro + Integer.toString(startNum) + operation + String.valueOf(secondNum) + " equals?";
        tellTheUser.setText(tellUser);
    }

    private int pemdas() {
        double decimal = (4 * Math.random());
        return (int) decimal;
    }


    private void endGame() {
        final Intent intent = new Intent(this, MainActivity.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You were wrong :(... upload score?");//need to put webAPI stuff
        builder.setPositiveButton("Go back to home", (dialogInterface, i) -> startActivity(intent)); //just go back home
        builder.create().show();
    }

    private void correct() {
        final Intent intent = new Intent(this, MainActivity.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Correct!");
        builder.setNegativeButton("Yes, continue!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                generateNumber();
            }
        }); //refresh somehow
        builder.setPositiveButton("Go back to home", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(intent);
            }
        }); //just go back home
        builder.create().show();
    }

    private void soundEffect(String a) {
        if (a.equals("dub")) {
            int resID = getResources().getIdentifier("ding", "raw", getPackageName());
            MediaPlayer mediaPlayer = MediaPlayer.create(this, resID);
            mediaPlayer.start();
        }
        if (a.equals("nope")) {
            int resID = getResources().getIdentifier("wrong", "raw", getPackageName());
            MediaPlayer mediaPlayer = MediaPlayer.create(this, resID);
            mediaPlayer.start();
        }
    }
}


