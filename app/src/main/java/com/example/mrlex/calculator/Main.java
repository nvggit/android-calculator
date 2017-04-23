package com.example.mrlex.calculator;

import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;


public class Main extends AppCompatActivity {
    public static final String SAVE_TEMP = "SAVE_TEMP";
    public static final String SAVE_NUMBER = "SAVE_NUMBER";
    public static final String SAVE_OPERATOR = "SAVE_OPERATOR";
    TextView display;
    private String temp = "";
    private String number = "";
    private char operator = ' ';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVE_TEMP, temp);
        outState.putString(SAVE_NUMBER, number);
        outState.putChar(SAVE_OPERATOR, operator);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        temp = savedInstanceState.getString(SAVE_TEMP);
        number = savedInstanceState.getString(SAVE_NUMBER);
        operator = savedInstanceState.getChar(String.valueOf(SAVE_OPERATOR));

        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!number.equals("")) {
            sendToDisplay(this.number);
        } else if (!temp.equals("")) {
            sendToDisplay(this.temp);
        } else {
            sendToDisplay("0");
        }
    }

    void initViews() {

        GridLayout container = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < container.getChildCount(); i++) {
            container.getChildAt(i).setOnClickListener(clickListener);
        }
        display = (TextView) findViewById(R.id.display);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getTag().toString()) {
                case "btn0":
                    addNumber("0");
                    break;
                case "btn1":
                    addNumber("1");
                    break;
                case "btn2":
                    addNumber("2");
                    break;
                case "btn3":
                    addNumber("3");
                    break;
                case "btn4":
                    addNumber("4");
                    break;
                case "btn5":
                    addNumber("5");
                    break;
                case "btn6":
                    addNumber("6");
                    break;
                case "btn7":
                    addNumber("7");
                    break;
                case "btn8":
                    addNumber("8");
                    break;
                case "btn9":
                    addNumber("9");
                    break;
                case "btnRes":
                    temp = "";
                    number = "";
                    operator = ' ';
                    sendToDisplay("0");
                    break;
                case "btnDevis":
                    setOperator('/');
                    break;
                case "btnMulti":
                    setOperator('*');
                    break;
                case "btnPlus":
                    setOperator('+');
                    break;
                case "btnMinus":
                    setOperator('-');
                    break;
                case "btnEqual":
                    if (!number.equals("")) {
                        calculate(Double.parseDouble(number), operator);
                    }
                    operator = ' ';
                    break;
                case "btnDot":
                    addNumber(".");
                    break;
            }
        }
    };

    private void calculate(Double num, char op) {
        switch (op) {
            case ('/'):
                if (Double.parseDouble(number) != 0.0) {
                    this.temp = Double.toString(Double.parseDouble(this.temp) / num);
                } else {
                    display.setText(R.string.noZero);
                }
                break;
            case ('-'):
                this.temp = Double.toString(Double.parseDouble(this.temp) - num);
                break;
            case ('+'):
                this.temp = Double.toString(Double.parseDouble(this.temp) + num);
                break;
            case ('*'):
                this.temp = Double.toString(Double.parseDouble(this.temp) * num);
                break;
        }
        this.number = "";
        sendToDisplay(this.temp);
    }

    private void addNumber(String num) {
        if (num.equals(".")) {
            if (this.number.equals("")) {
                this.number = "0" + num;
            } else if (!this.number.contains(".")) {
                this.number += num;
            }
        } else {

            this.number += num;
        }
        display.setText(this.number);
    }

    private void setOperator(char op) {
        if (this.operator != ' ') {
            if (!this.number.equals("")) {
                calculate(Double.parseDouble(this.number), this.operator);
            }
            this.operator = op;
        } else {
            this.operator = op;
            if (!this.number.equals("")) {
                this.temp = this.number;
            }
            this.number = "";
        }

    }

    public void sendToDisplay(String s) {
        if ((Double.parseDouble(s) % 1) == 0) {
            s = s.split("\\.", 2)[0];
        }
        display.setText(s);
    }


}
