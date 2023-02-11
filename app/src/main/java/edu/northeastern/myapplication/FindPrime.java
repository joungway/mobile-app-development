package edu.northeastern.myapplication;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class FindPrime extends AppCompatActivity {
    private volatile boolean searchRunning = false;
    private volatile boolean terminateSearch = false;
    private volatile int currentNumber = 3;
    private volatile int latestPrime = 0;
    private volatile boolean pacifierChecked = false;

    private TextView currentNumberView;
    private TextView latestPrimeView;
    private CheckBox pacifierCheckbox;

//    PrimeNumberReceiver receiver = new PrimeNumberReceiver();
//
//    private PrimeNumberReceiver mPrimeFinder;
//    private boolean mIsPrimeSearchRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_prime);

        currentNumberView = findViewById(R.id.current_number);
        latestPrimeView = findViewById(R.id.latest_prime);
        pacifierCheckbox = findViewById(R.id.pacifier_checkbox);

        Button findPrimesButton = findViewById(R.id.find_primes_button);
        findPrimesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!searchRunning) {
                    searchRunning = true;
                    terminateSearch = false;
                    currentNumber = 3;
                    Thread searchThread = new Thread(new PrimeNumberSearch());
                    searchThread.start();
                }
            }
        });

        Button terminateSearchButton = findViewById(R.id.terminate_search_button);
        terminateSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                terminateSearch = true;
            }
        });

        pacifierCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                pacifierChecked = isChecked;
            }
        });

        if (savedInstanceState != null) {
            currentNumber = savedInstanceState.getInt("current_number");
            latestPrime = savedInstanceState.getInt("latest_prime");
            pacifierChecked = savedInstanceState.getBoolean("pacifier_checked");
            currentNumberView.setText(String.valueOf(currentNumber));
            latestPrimeView.setText(String.valueOf(latestPrime));
            pacifierCheckbox.setChecked(pacifierChecked);
        }
    }

    // Broadcast Receiver
//    @Override
//    protected void onResume() {
//        super.onResume();
//        // Register the broadcast receiver
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(Intent.ACTION_CONFIGURATION_CHANGED);
//        receiver = new PrimeNumberReceiver();
//        registerReceiver(receiver, filter);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        // Unregister the broadcast receiver
//        unregisterReceiver(receiver);
//    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putInt("current_number", currentNumber);
//        outState.putInt("latest_prime", latestPrime);
//        outState.putBoolean("pacifier_checked", pacifierChecked);
//    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("currentNumber", currentNumber);
        savedInstanceState.putInt("latestPrime", latestPrime);
        savedInstanceState.putBoolean("isPacifierChecked", pacifierCheckbox.isChecked());
        super.onSaveInstanceState(savedInstanceState);
    }


    @Override
    public void onBackPressed() {
        if (searchRunning) {
            new AlertDialog.Builder(this)
                    .setTitle("Confirm Exit")
                    .setMessage("Are you sure you want to exit while the search is running?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            terminateSearch=true;
                            FindPrime.super.onBackPressed();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        } else {
            super.onBackPressed();
        }
    }

    // Broadcast receiver in the worker thread
//    private void searchForPrimes() {
//
//        while (!isCancelled()) {
//            if (isPrime(currentNumber)) {
//                latestPrime = currentNumber;
//                Intent intent = new Intent(FindPrime);
//                intent.putExtra("latestPrime", latestPrime);
//                intent.putExtra("currentNumber", currentNumber);
//                sendBroadcast(intent);
//            }
//        }
//    }


    private class PrimeNumberSearch implements Runnable {
        @Override
        public void run() {
            while (!terminateSearch) {
                boolean isPrime = true;
                for (int i = 2; i < currentNumber; i++) {
                    if (currentNumber % i == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime) {
                    latestPrime = currentNumber;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            latestPrimeView.setText(String.valueOf(latestPrime));
                        }
                    });
                }
                currentNumber += 2;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        currentNumberView.setText(String.valueOf(currentNumber));
                    }
                });
            }
            searchRunning = false;
        }
    }
}