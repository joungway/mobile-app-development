package edu.northeastern.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    // comment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the About Me button
        Button button1 = (Button)findViewById(R.id.Button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"WeiZhang:zhang.wei12@northeastern.edu",Toast.LENGTH_SHORT).show();
            }
        });

        // Create the ClickyClicky button
        Button button2 = (Button)findViewById(R.id.Button2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        // Create the Link Collector button
        Button button3 = (Button)findViewById(R.id.Button3);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LinkCollector.class);
                startActivity(intent);
            }
        });

        // Create the Find Prime button
        Button button4 = (Button)findViewById(R.id.Button4);
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FindPrime.class);
                startActivity(intent);
            }
        });

        // Create the Locator button
        Button button5 = (Button)findViewById(R.id.Button5);
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Locator.class);
                startActivity(intent);
            }
        });



    }
}