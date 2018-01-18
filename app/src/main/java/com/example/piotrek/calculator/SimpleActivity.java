package com.example.piotrek.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class SimpleActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;
    boolean toClear = false;
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
        Context rhino = Context.enter();
// turn off optimization to work with android
        rhino.setOptimizationLevel(-1);

        String evaluation = textView.getText().toString();

        try {
            Scriptable scope = rhino.initStandardObjects();
            String result = rhino.evaluateString(scope, evaluation, "JavaScript", 1, null).toString();
            textView.setText(result);

        }
        catch (Exception e){
            Log.d("solve exception: ", e.getMessage());
            textView.setText(R.string.wrong_equation);
            toClear = true;
        }
        finally {

            Context.exit();
        }

    }
}
