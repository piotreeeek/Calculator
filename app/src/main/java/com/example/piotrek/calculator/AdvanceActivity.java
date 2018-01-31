package com.example.piotrek.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import org.apache.commons.lang3.StringUtils;

public class AdvanceActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;
    private boolean toClear = false;
    private static final String COS_TEXT = "cos";
    private static final String SIN_TEXT = "sin";
    private static final String TG_TEXT = "tg";
    private static final String CTG_TEXT = "ctg";
    private static final String POW_TEXT = "x^2";
    private static final String POWX_TEXT = "x^y";
    private static final String EXP_TEXT = "exp";
    private static final String SQRT_TEXT = "sqrt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance);
        textView = findViewById(R.id.result);
    }

    @Override
    public void onClick(View v) {
        if (toClear){
            textView.setText(null);
            toClear = false;
        }
        String action = (String)((Button) findViewById(v.getId())).getText();
        switch (action){
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

        if (calculateBrackets(textView.getText())){
            findViewById(R.id.btn_eq).setEnabled(true);
        }else {
            findViewById(R.id.btn_eq).setEnabled(false);
        }
    }

    private boolean calculateBrackets(CharSequence text) {
        int leftBracket = StringUtils.countMatches(text, getString(R.string.bracket_1));
        int rightBracket = StringUtils.countMatches(text, getString(R.string.bracket_2));
        return leftBracket == rightBracket;
    }

    public void onEqualsClick(View v) {

        try {
            String evaluation = textView.getText().toString();
            Expression exp = new ExpressionBuilder(evaluation).build();
            double result = exp.evaluate();
            textView.setText(String.valueOf(result));
        }
        catch (Exception e){
            textView.setText(R.string.wrong_equation);
            toClear = true;
            v.setEnabled(false);
        }
    }


    public void onDeleteClick(@SuppressWarnings("unused") View view) {
        textView.setText("");
        findViewById(R.id.btn_eq).setEnabled(true);
    }
}
