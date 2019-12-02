package com.example.finalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private String[] operation = {"addition", "subtraction", "multiplication", "division"};

    private int answer;

    private int operationIndex;

    TextView command;

    TextView number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        command = findViewById(R.id.command);
        number = findViewById(R.id.number);
        command.setVisibility(View.VISIBLE);
        number.setVisibility(View.VISIBLE);
        EditText answer = findViewById(R.id.answer);
        answer.setVisibility(View.GONE);
    }

    public void chooseOperation() {
        operationIndex = (int) Math.ceil((Math.random() * (3)));
        if ((operation[operationIndex]).equals("addition")) {
            command.setText("add");
        }
        if ((operation[operationIndex]).equals("subtraction")) {
            command.setText("subtract");
        }
        if ((operation[operationIndex]).equals("multiplication")) {
            command.setText("multiply");
        }
        if ((operation[operationIndex]).equals("division")) {
            command.setText("divide");
        }
    }
}