package com.example.piotrek.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class AdvanceActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String COS_TEXT = "cos";
    private static final String SIN_TEXT = "sin";
    private static final String TG_TEXT = "tg";
    private static final String CTG_TEXT = "ctg";
    private static final String POW_TEXT = "x^2";
    private static final String POWX_TEXT = "x^y";
    private static final String EXP_TEXT = "exp";
    private static final String SQRT_TEXT = "sqrt";
    private static final String ACTUAL_EQUATION = "equation";
    private static final String ACTUAL_CLEAR = "toClear";
    private TextView textView;
    private boolean toClear = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance);
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
        String action = (String) ((Button) findViewById(v.getId())).getText();
        switch (action) {
            case COS_TEXT:
                textView.append(getString(R.string.cos_eq));
                break;
            case SIN_TEXT:
                textView.append(getString(R.string.sin_eq));
                break;
            case TG_TEXT:
                textView.append(getString(R.string.tg_eq));
                break;
            case CTG_TEXT:
                textView.append(getString(R.string.ctg_eq));
                break;
            case POW_TEXT:
                textView.append(getString(R.string.pow_eq));
                break;
            case POWX_TEXT:
                textView.append(getString(R.string.powx_eq));
                break;
            case EXP_TEXT:
                textView.append(getString(R.string.exp_eq));
                break;
            case SQRT_TEXT:
                textView.append(getString(R.string.sqrt_eq));
                break;
            default:
                textView.append(((Button) findViewById(v.getId())).getText().toString());
        }
    }


    public void onEqualsClick(@SuppressWarnings("unused") View v) {

        try {
            String evaluation = textView.getText().toString();
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
