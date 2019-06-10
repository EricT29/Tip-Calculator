package com.example.tipcalcseekbar;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // textviews which show...
        // ...the total bill
        final TextView billText = findViewById(R.id.billText);
        // ...the calculated tip
        final TextView tipText = findViewById(R.id.tipText);
        // ...the desired percentage of the bill
        final TextView percentText = findViewById(R.id.percentText);
        // ...the total bill and tip together
        final TextView totalText = findViewById(R.id.totalText);
        final double oneHundred = 100.0;

        // create a seekbar and a handler for the seekbar
        Handler seekBarHandler = new Handler(); // must be created in the same thread that created the SeekBar
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);

        // the method that runs whenever the seekbar's progress is changed
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int tipPercentage, boolean fromUser) {
                // clean up and take in the bill
                String bill = String.valueOf(billText.getText()).replaceAll("[^0-9.]", "");
                // throw an error if the bill is empty
                if (bill.isEmpty()) {
                    billText.setText("ERROR");
                } else {
                    // put the bill into a double
                    double currentBill = Double.parseDouble(bill);

                    // state the current percentage as taken from the seek bar's progress
                    percentText.setText(tipPercentage + "%");

                    // calculate the tip
                    double tip = (currentBill * (tipPercentage / oneHundred));

                    // output the calculated tip
                    tipText.setText("$" + String.format("%.2f", tip));
                    // output the total cost of the bill plus the tip
                    totalText.setText("$" + String.format("%.2f", currentBill + tip));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}
