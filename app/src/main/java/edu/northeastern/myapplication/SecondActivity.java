package edu.northeastern.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Create buttons and textview
        Button button_A = findViewById(R.id.button_A);
        Button button_B = findViewById(R.id.button_B);
        Button button_C = findViewById(R.id.button_C);
        Button button_D = findViewById(R.id.button_D);
        Button button_E = findViewById(R.id.button_E);
        Button button_F = findViewById(R.id.button_F);
        TextView pressedTV = findViewById(R.id.myTextView);

        // Set listener to button A
        button_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedTV.setText(getString(R.string.button_A));
            }
        });

        // Set listener to button B
        button_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedTV.setText(getString(R.string.button_B));
            }
        });

        // Set listener to button C
        button_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedTV.setText(getString(R.string.button_C));
            }
        });

        // Set listener to button D
        button_D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedTV.setText(getString(R.string.button_D));
            }
        });

        // Set listener to button E
        button_E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedTV.setText(getString(R.string.button_E));
            }
        });

        // Set listener to button F
        button_F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedTV.setText(getString(R.string.button_F));
            }
        });


    }


}