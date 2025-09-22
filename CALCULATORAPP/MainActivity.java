package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button buttonzero, buttonone, buttontwo, buttonthree, buttonfour, buttonfive, buttonsix, buttonseven, buttoneight, buttonnine, buttonpoint, buttonplus, buttonminus, buttonmultiplication, buttondivision, buttonequals, buttonclear;
    EditText edit;
    float mvalueone, mvaluetwo;

    boolean plus, minus, multiplication, division;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons and editText
        buttonzero = findViewById(R.id.zero);
        buttonone = findViewById(R.id.one);
        buttontwo = findViewById(R.id.two);
        buttonthree = findViewById(R.id.three);
        buttonfour = findViewById(R.id.four);
        buttonfive = findViewById(R.id.five);
        buttonsix = findViewById(R.id.six);
        buttonseven = findViewById(R.id.seven);
        buttoneight = findViewById(R.id.eight);
        buttonnine = findViewById(R.id.nine);
        buttonclear = findViewById(R.id.clear);
        buttonpoint = findViewById(R.id.point);
        buttonplus = findViewById(R.id.plus);
        buttonminus = findViewById(R.id.minus);
        buttonmultiplication = findViewById(R.id.multiplication);
        buttondivision = findViewById(R.id.division);
        buttonequals = findViewById(R.id.equals);
        edit = findViewById(R.id.edit);

        // Number buttons
        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button)v;
                edit.append(b.getText());
            }
        };

        buttonzero.setOnClickListener(numberListener);
        buttonone.setOnClickListener(numberListener);
        buttontwo.setOnClickListener(numberListener);
        buttonthree.setOnClickListener(numberListener);
        buttonfour.setOnClickListener(numberListener);
        buttonfive.setOnClickListener(numberListener);
        buttonsix.setOnClickListener(numberListener);
        buttonseven.setOnClickListener(numberListener);
        buttoneight.setOnClickListener(numberListener);
        buttonnine.setOnClickListener(numberListener);
        buttonpoint.setOnClickListener(numberListener);

        // Clear button
        buttonclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText("");
                resetOperationFlags();
            }
        });

        // Operator buttons
        buttonplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEditTextEmpty()) {
                    showToast("Enter a number first");
                } else {
                    mvalueone = parseInput();
                    plus = true;
                    resetOtherOperations("plus");
                    edit.setText("");
                }
            }
        });

        buttonminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEditTextEmpty()) {
                    showToast("Enter a number first");
                } else {
                    mvalueone = parseInput();
                    minus = true;
                    resetOtherOperations("minus");
                    edit.setText("");
                }
            }
        });

        buttonmultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEditTextEmpty()) {
                    showToast("Enter a number first");
                } else {
                    mvalueone = parseInput();
                    multiplication = true;
                    resetOtherOperations("multiplication");
                    edit.setText("");
                }
            }
        });

        buttondivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEditTextEmpty()) {
                    showToast("Enter a number first");
                } else {
                    mvalueone = parseInput();
                    division = true;
                    resetOtherOperations("division");
                    edit.setText("");
                }
            }
        });

        // Equals button
        buttonequals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEditTextEmpty()) {
                    showToast("Enter a number first");
                    return;
                }
                mvaluetwo = parseInput();
                float result = 0;

                try {
                    if (plus) {
                        result = mvalueone + mvaluetwo;
                    } else if (minus) {
                        result = mvalueone - mvaluetwo;
                    } else if (multiplication) {
                        result = mvalueone * mvaluetwo;
                    } else if (division) {
                        if (mvaluetwo == 0) {
                            showToast("Cannot divide by zero");
                            return;
                        }
                        result = mvalueone / mvaluetwo;
                    } else {
                        showToast("Select operation");
                        return;
                    }
                    edit.setText(String.valueOf(result));
                } catch (NumberFormatException e) {
                    showToast("Invalid input");
                }

                resetOperationFlags();
            }
        });
    }

    private boolean isEditTextEmpty() {
        return edit.getText().toString().trim().isEmpty();
    }

    private float parseInput() {
        String input = edit.getText().toString().trim();
        try {
            return Float.parseFloat(input);
        } catch (NumberFormatException e) {
            showToast("Invalid number");
            return 0;
        }
    }

    private void resetOperationFlags() {
        plus = false;
        minus = false;
        multiplication = false;
        division = false;
    }

    private void resetOtherOperations(String current) {
        plus = current.equals("plus");
        minus = current.equals("minus");
        multiplication = current.equals("multiplication");
        division = current.equals("division");
    }

    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
