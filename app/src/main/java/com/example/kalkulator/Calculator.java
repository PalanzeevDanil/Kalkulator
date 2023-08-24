package com.example.kalkulator;

import android.support.v7.app.AppCompatActivity;

class Calculator extends AppCompatActivity {

    public double addition(double num1, double num2){
        return num1 + num2;
    }

    public double subtraction(double num1, double num2){
        return num1 - num2;
    }

    public double multiplication(double num1, double num2){
        return num1 * num2;
    }

    public double division(double num1, double num2){
        return num1 / num2;
    }

    public double modulus(double num1, double num2){
        return num1 % num2;
    }

    public double squareRoot(double num1, double num2){
        return (num1 * Math.sqrt(num2));
    }
    
    public double power(double num1, double num2){
        return Math.pow(num1, num2);
    }
}
