package com.example.kalkulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity  extends AppCompatActivity {
    private Calculator mCalculator;
    private TextView mInputValue1TextView;
    private TextView mInputValue2TextView;
    private TextView mOperatorTextView;
    private TextView mFinalResultTextView;
    private TextView mCompleteOperation;
    private double number_one;
    private double number_two;
    private String operation_string;
    private static int MAX_CHARACTERS = 10;

    private enum operator {
        ADD, SUB, MUL, DIV, MOD, ROOT, POW, NULL
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCalculator = new Calculator();
        mInputValue1TextView = findViewById(R.id.input_value_1);
        mInputValue2TextView = findViewById(R.id.input_value_2);
        mOperatorTextView = findViewById(R.id.input_operation);
        mFinalResultTextView = findViewById(R.id.textView_result);
        mCompleteOperation = findViewById(R.id.complete_operation);
        operation_string = operator.NULL.name();

//        Implementation of saved instance state
        if (savedInstanceState != null) {
            mInputValue1TextView.setText(savedInstanceState.getString("First_number", ""));
            mInputValue2TextView.setText(savedInstanceState.getString("Second_number", ""));
            mOperatorTextView.setText(savedInstanceState.getString("Operation", ""));
            mFinalResultTextView.setText(savedInstanceState.getString("Final_result", ""));
            mCompleteOperation.setText(savedInstanceState.getString("Complete_operation", ""));
            operation_string = savedInstanceState.getString("Operation_string", operator.NULL.name());
            number_one = savedInstanceState.getDouble("Number_one", 0);
            number_two = savedInstanceState.getDouble("Number_two", 0);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("First_number", mInputValue1TextView.getText().toString());
        outState.putString("Operation", mOperatorTextView.getText().toString());
        outState.putString("Second_number", mInputValue2TextView.getText().toString());
        outState.putString("Final_result", mFinalResultTextView.getText().toString());
        outState.putString("Complete_operation", mCompleteOperation.getText().toString());
        outState.putString("Operation_string", operation_string);
        outState.putDouble("Number_one", number_one);
        outState.putDouble("Number_two", number_two);
    }


    private void selectTextViewToAppend(String number) {
        if (operation_string.equals(operator.NULL.name())) {
            if (mInputValue1TextView.getText().toString().contains(".")) {
                MAX_CHARACTERS++;
            }
            if (mInputValue1TextView.getText().length() < MAX_CHARACTERS) {
                mInputValue1TextView.append(number);
                MAX_CHARACTERS = 10;
            } else {
                MAX_CHARACTERS = 10;
                Toast.makeText(this, "Не может содержать более 10 чисел", Toast.LENGTH_LONG).show();
            }
        } else {
            if (mInputValue1TextView.getText().toString().contains(".")) {
                MAX_CHARACTERS++;
            }
            if (mInputValue2TextView.getText().length() < MAX_CHARACTERS) {
                mInputValue2TextView.append(number);
                MAX_CHARACTERS = 10;
            } else {
                MAX_CHARACTERS = 10;
                Toast.makeText(this, "Не может содержать более 10 чисел", Toast.LENGTH_LONG).show();
            }

        }
    }


    public void onNumClick(View view) {
        if (!mFinalResultTextView.getText().toString().equals("")) {
            onClearClick(view);
        }
        switch (view.getId()) {
            case R.id.button_one:
                selectTextViewToAppend("1");
                break;
            case R.id.button_two:
                selectTextViewToAppend("2");
                break;
            case R.id.button_three:
                selectTextViewToAppend("3");
                break;
            case R.id.button_four:
                selectTextViewToAppend("4");
                break;
            case R.id.button_five:
                selectTextViewToAppend("5");
                break;
            case R.id.button_six:
                selectTextViewToAppend("6");
                break;
            case R.id.button_seven:
                selectTextViewToAppend("7");
                break;
            case R.id.button_eight:
                selectTextViewToAppend("8");
                break;
            case R.id.button_nine:
                selectTextViewToAppend("9");
                break;
            case R.id.button_zero:
                selectTextViewToAppend("0");
                break;
            case R.id.button_dot:
                if (operation_string.equals(operator.NULL.name())) {
                    if (mInputValue1TextView.getText().toString().contains(".")) {
                        Toast.makeText(this, "Число не может содержать более одной точки", Toast.LENGTH_LONG).show();
                    } else {
                        mInputValue1TextView.append(".");
                    }
                } else {
                    if (mInputValue2TextView.getText().toString().contains(".")) {
                        Toast.makeText(this, "Число не может содержать более одной точки", Toast.LENGTH_LONG).show();
                    } else {
                        mInputValue2TextView.append(".");
                    }
                }
                break;
            default:
                break;
        }
    }


    public void onOperatorClick(View view) {
        if (!mInputValue1TextView.getText().toString().equals("")) {
            switch (view.getId()) {
                case R.id.button_plus:
                    operation_string = operator.ADD.name();
                    mOperatorTextView.setText("+");
                    break;
                case R.id.button_minus:
                    operation_string = operator.SUB.name();
                    mOperatorTextView.setText("-");
                    break;
                case R.id.button_multiply:
                    operation_string = operator.MUL.name();
                    mOperatorTextView.setText("x");
                    break;
                case R.id.button_divide:
                    operation_string = operator.DIV.name();
                    mOperatorTextView.setText("/");
                    break;
                case R.id.button_mod:
                    operation_string = operator.MOD.name();
                    mOperatorTextView.setText("%");
                    break;
                case R.id.button_pow:
                    operation_string = operator.POW.name();
                    mOperatorTextView.setText("^");
                    break;
                case R.id.button_root:
                    operation_string = operator.ROOT.name();
                    mOperatorTextView.setText("√");
                    break;
                default:
                    operation_string = operator.NULL.name();
                    break;
            }

        }
        else if (view.getId() == R.id.button_root) {
            mInputValue1TextView.setText("1");
            operation_string = operator.ROOT.name();
            mOperatorTextView.setText("√");
        }
        else {
            Toast.makeText(this, "Введите цифры и первый номер операционного центра перед выполнением операции", Toast.LENGTH_LONG).show();
        }
    }

    public void onEqualsClick(View view) {
//
        if (mInputValue1TextView.getText().toString().equals("") || mOperatorTextView.getText().toString().equals("") || mInputValue2TextView.getText().toString().equals("")) {
            Toast.makeText(this, "Введите цифры и операцию", Toast.LENGTH_LONG).show();
        } else {
            number_one = Double.parseDouble(mInputValue1TextView.getText().toString());
            number_two = Double.parseDouble(mInputValue2TextView.getText().toString());
            String operation;

            switch (operator.valueOf(operation_string)) {
                case ADD:
                    mFinalResultTextView.setText(String.valueOf(mCalculator.addition(number_one, number_two)));
                    operation = mInputValue1TextView.getText().toString() + getString(R.string.button_plus) + mInputValue2TextView.getText().toString();
                    break;
                case SUB:
                    mFinalResultTextView.setText(String.valueOf(mCalculator.subtraction(number_one, number_two)));
                    operation = mInputValue1TextView.getText().toString() + getString(R.string.button_minus) + mInputValue2TextView.getText().toString();
                    break;
                case MUL:
                    mFinalResultTextView.setText(String.valueOf(mCalculator.multiplication(number_one, number_two)));
                    operation = mInputValue1TextView.getText().toString() + getString(R.string.button_mul) + mInputValue2TextView.getText().toString();
                    break;
                case DIV:
                    try {
                        mFinalResultTextView.setText(String.valueOf(mCalculator.division(number_one, number_two)));
                        operation = mInputValue1TextView.getText().toString() + getString(R.string.button_div) + mInputValue2TextView.getText().toString();
                    } catch (IllegalArgumentException e) {
                        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG).show();
                        operation = "";
                        clearAll();
                    }
                    break;
                case MOD:
                    mFinalResultTextView.setText(String.valueOf(mCalculator.modulus(number_one, number_two)));
                    operation = mInputValue1TextView.getText().toString() + getString(R.string.button_mod) + mInputValue2TextView.getText().toString();
                    break;
                case ROOT:
                    mFinalResultTextView.setText(String.valueOf(mCalculator.squareRoot(number_one, number_two)));
                    operation = mInputValue1TextView.getText().toString() + getString(R.string.button_root) + mInputValue2TextView.getText().toString();
                    break;
                case POW:
                    mFinalResultTextView.setText(String.valueOf(mCalculator.power(number_one, number_two)));
                    operation = mInputValue1TextView.getText().toString() + getString(R.string.button_pow) + mInputValue2TextView.getText().toString();
                    break;
                case NULL:
                    mFinalResultTextView.setText(getString(R.string.error));
                    operation = "";
                    break;
                default:
                    operation = "";
                    break;
            }
            mCompleteOperation.setText(operation);
            clearAll();
        }
    }


    public void onClearClick(View view) {
        clearAll();
        mFinalResultTextView.setText("");
        mCompleteOperation.setText("");
    }


    public void clearAll() {
        mInputValue1TextView.setText("");
        mOperatorTextView.setText("");
        mInputValue2TextView.setText("");
        number_one = 0;
        number_two = 0;
        operation_string = operator.NULL.name();
    }


    public void onBackspaceClick(View view) {
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                clearAll();
                return false;
            }
        });
        if (!mInputValue2TextView.getText().toString().equals("")) {
            backspaceImplementation(mInputValue2TextView);
        } else {
            if (!mOperatorTextView.getText().toString().equals("")) {
                backspaceImplementation(mOperatorTextView);
            } else {
                if (!mInputValue1TextView.getText().toString().equals("")) {
                    backspaceImplementation(mInputValue1TextView);
                }
            }
        }
    }

    private void backspaceImplementation(TextView view) {
        String backspace = view.getText().toString();
        backspace = backspace.substring(0, backspace.length() - 1);
        view.setText(backspace);
    }

}