package com.example.piotrek.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class SimpleActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String ACTUAL_EQUATION = "equation";
    private static final String ACTUAL_CLEAR = "toClear";
    private TextView textView;
    private boolean toClear = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        textView = findViewById(R.id.result);
        if (savedInstanceState != null) {
            textView.setText(savedInstanceState.getCharSequence(ACTUAL_EQUATION));
            toClear = savedInstanceState.getBoolean(ACTUAL_CLEAR);
        }
    }

    @Override
    public void onClick(View v) {
        if (toClear) {
            textView.setText(null);
            toClear = false;
        }
        textView.append(((Button) findViewById(v.getId())).getText().toString());
    }

    public void onEqualsClick(@SuppressWarnings("unused") View v) {
        String evaluation = textView.getText().toString();

        try {

            Expression exp = new ExpressionBuilder(evaluation).build();
            double result = exp.evaluate();
            textView.setText(String.valueOf(result));

        } catch (Exception e) {
            textView.setText(R.string.wrong_equation);
            toClear = true;
        }

    }

    public void onDeleteClick(@SuppressWarnings("unused") View view) {
        textView.setText("");
    }

    protected void onSaveInstanceState(Bundle equation) {
        super.onSaveInstanceState(equation);
        equation.putCharSequence(ACTUAL_EQUATION, textView.getText());
        equation.putBoolean(ACTUAL_CLEAR, toClear);
    }
}
