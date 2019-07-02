package c346.rp.edu.mybmicalculator;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    EditText etWeight;
    EditText etHeight;
    Button btnCalc;
    Button btnReset;
    TextView tvDate;
    TextView tvBMI;

    @Override
    protected void onPause() {
        super.onPause();


    }

    @Override
    protected void onResume() {
        super.onResume();

        // Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Step 2b: Retrieve the saved data with the key "greeting" from the SharedPreference object
        float bmi = prefs.getFloat("BMI", 0);

        tvBMI.setText(String.format("Last Calculated BMI: %.3f", bmi));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWeight = findViewById(R.id.editTextWeight);
        etHeight = findViewById(R.id.editTextHeight);
        btnCalc = findViewById(R.id.buttonCalculate);
        btnReset = findViewById(R.id.buttonReset);
        tvDate = findViewById(R.id.displayDate);
        tvBMI = findViewById(R.id.displayBMI);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double weight = Double.parseDouble(etWeight.getText().toString());
                double height = Double.parseDouble(etHeight.getText().toString());

                double BMI = weight / (height * height);

                tvBMI.setText(String.format("Last Calculated BMI: %.3f", BMI));

                Calendar now = Calendar.getInstance();  //Create a Calendar object with current date and time
                String datetime = now.get(Calendar.DAY_OF_MONTH) + "/" +
                        (now.get(Calendar.MONTH)+1) + "/" +
                        now.get(Calendar.YEAR) + " " +
                        now.get(Calendar.HOUR_OF_DAY) + ":" +
                        now.get(Calendar.MINUTE);

                tvDate.setText("Last Calculated Date: " + datetime);

                // Step 1a: Obtain an instance of the SharedPreferences
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                // Step 1b: Obtain an instance of the SharedPreferences Editor for update later
                SharedPreferences.Editor prefEdit = prefs.edit();

                /// Step 1c: Add the key-value pair
                prefEdit.putFloat("BMI", 0);


                //Step 1d: Call commit() to save the changes into SharedPreferences
                prefEdit.commit();


            }
        });



        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                //Step 2b: Retrieve the saved data with the key "greeting" from the SharedPreference object
                float bmi = prefs.getFloat("BMI", 0);

                tvBMI.setText("Last Calculated BMI: ");
            }
        });


    }
}
