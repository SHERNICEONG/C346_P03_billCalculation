package sg.edu.rp.c346.id22017424.p03_billcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;


import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    EditText etAmt;
    EditText etPax;
    EditText etDsc;
    ToggleButton tbtnSvs;
    ToggleButton tbtnGst;
    Button btnSplit;
    Button btnReset;
    RadioGroup payment;
    TextView tvBill;
    TextView tvPay;

    String stringBill = "$";
    String stringPay = "$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etAmt = findViewById(R.id.editTextAmt);
        etPax = findViewById(R.id.editTextPax);
        tbtnSvs = findViewById(R.id.toggleButtonSvsEnabled);
        tbtnGst = findViewById(R.id.toggleButtonGstEnabled);
        btnSplit = findViewById(R.id.buttonSplit);
        btnReset = findViewById(R.id.buttonReset);
        tvBill = findViewById(R.id.textViewBillDisplay);
        tvPay = findViewById(R.id.textViewPayDisplay);

        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Calculating Total
                double total = 0.0;
                if (etAmt.getText().toString().trim().length() !=0 && etPax.getText().toString().trim().length() !=0) {
                    if (tbtnGst.isChecked() && !tbtnSvs.isChecked()) {
                        total = Double.parseDouble(etAmt.getText().toString()) * 1.07;
                    } else if (tbtnGst.isChecked() && tbtnSvs.isChecked()) {
                        total = Double.parseDouble(etAmt.getText().toString()) * 1.17;
                    } else if (!tbtnGst.isChecked() && tbtnSvs.isChecked()){
                        total = Double.parseDouble(etAmt.getText().toString()) * 1.10;
                    } else if (!tbtnGst.isChecked() && !tbtnSvs.isChecked()){
                        total = Double.parseDouble(etAmt.getText().toString()) * 1;
                    }
                    stringBill += String.format("%.2f", total);
                }
                // Calculating Discount
                if (etDsc.getText().toString().length()!=0){
                    double discount = 1 - Double.parseDouble(etDsc.getText().toString()) / 100;
                    total *= discount;
                }
                tvBill.setText(stringBill);
                String stringResponse = "Total Billed: $" + String.format("%.2f",total);
                tvBill.setText(stringResponse);
                if (Integer.parseInt(etPax.getText().toString()) != 0){
                    Double newAmt = total /  Integer.parseInt(etPax.getText().toString());
                    stringPay += String.format("%.2f", newAmt);
                }
                String method = " ";
                int checkedRadioId = payment.getCheckedRadioButtonId();
                if(checkedRadioId == R.id.radioButtonCash) {
                    // When Cash selected
                    method = " in cash";
                } else {
                    // When PayNow selected
                    method = " via PayNow to 91234567";

                }
                tvPay.setText(stringPay + method);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Resetting Form
                tvPay.setText("");
                tvBill.setText("");
                etAmt.setText("");
                etPax.setText("");
                etDsc.setText("");
            }
        });

    }
}