package com.example.piotrek.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class SimpleActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;
    private boolean toClear = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        textView = findViewById(R.id.result);
    }

    @Override
    public void onClick(View v) {
        if (toClear){
            textView.setText(null);
            toClear = false;
        }
        textView.append(((Button) findViewById(v.getId())).getText().toString());
    }

    public void onEqualsClick(View v){
        String evaluation = textView.getText().toString();

        try {

            Expression exp = new ExpressionBuilder(evaluation).build();
            double result = exp.evaluate();
            textView.setText(String.valueOf(result));

        }
        catch (Exception e){
            Log.d("solve exception: ", e.getMessage());
            textView.setText(R.string.wrong_equation);
            toClear = true;
        }

    }
}
