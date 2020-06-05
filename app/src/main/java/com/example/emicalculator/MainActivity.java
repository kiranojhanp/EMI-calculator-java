package com.example.emicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get instances of widgets
        final EditText principal = (EditText) findViewById(R.id.editTextweight);
        final EditText rate = (EditText) findViewById(R.id.editTextheight);
        final EditText time = (EditText) findViewById(R.id.editTextTime);
        final TextView result = (TextView) findViewById(R.id.textView3);

        findViewById(R.id.buttonclc).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String pl = principal.getText().toString();
                String rt = rate.getText().toString();
                String tm = time.getText().toString();


                // validation
                if (TextUtils.isEmpty(pl)){
                    principal.setError("Please enter your weight");
                    principal.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(rt)){
                    rate.setError("Please enter your weight");
                    rate.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(tm)){
                    time.setError("Please enter your weight");
                    time.requestFocus();
                    return;
                }

                // Get the user values and parse them to float
                float ppl = Float.parseFloat(pl);
                float rat = calInt(Float.parseFloat(rt));
                float tim = Float.parseFloat(tm);


                float nume = calFinalNume(tim, rat);
                float deno = calFinalDeno(tim, rat);
                float dividedvalue = divideNumeandDeno(nume, deno);

                float emiValue = calculateEMI(ppl, rat, dividedvalue);

                // Rounding up and displaying in 2 dec place
                DecimalFormat df = new DecimalFormat("0.00");
                df.setRoundingMode(RoundingMode.UP);

                result.setText(String.valueOf("EMI: Rs. " + df.format(emiValue)));

            }
        });
    }

    // calculate rate
    public float calInt(float i) {
        return (float)(i / 12 / 100);
    }

    // numerator
    public float calFinalNume(float months, float Rate) {
        return (float)(Math.pow(1 + Rate, months));
    }

    // denominator
    public float calFinalDeno(float months, float Rate) {
        return (float)(Math.pow(1 + Rate, months ) - 1);
    }

    // divide
    public float divideNumeandDeno(float nume, float deno){
        return (float) (nume/deno);
    }


    // Calculate EMI
    private float calculateEMI(float principal, float rate, float dividedvalue){

        return (float) (principal * rate * dividedvalue);
    }

}
