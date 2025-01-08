package com.example.emi_calculator;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    EditText etPrincipal, etTenure, etInterestRate;
    Button btnCalculate;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etPrincipal = findViewById(R.id.etPrincipal);
        etTenure = findViewById(R.id.etTenure);
        etInterestRate = findViewById(R.id.etInterestRate);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateEMI();
            }
        });
    }

    private void calculateEMI() {

        String principalStr = etPrincipal.getText().toString();
        String tenureStr = etTenure.getText().toString();
        String interestRateStr = etInterestRate.getText().toString();


        if (principalStr.isEmpty() || tenureStr.isEmpty() || interestRateStr.isEmpty()) {
            tvResult.setText("Please enter all values!");
            return;
        }

        double principal = Double.parseDouble(principalStr);
        int tenure = Integer.parseInt(tenureStr);
        double annualInterestRate = Double.parseDouble(interestRateStr);

        // Calculate Monthly Interest Rate
        double monthlyInterestRate = annualInterestRate / 12 / 100;

        // Calculate EMI using the formula
        double emi = (principal * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, tenure)) /
                (Math.pow(1 + monthlyInterestRate, tenure) - 1);


        tvResult.setText(String.format("Your EMI is: ₹%.2f", emi));
    }
}
