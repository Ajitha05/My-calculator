package com.example.mycaci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView resultv,solutiontv;
    MaterialButton buttondiv,buttonmul,buttonplus,buttonminus,buttoneqal,buttonc;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonac,buttondot,buttonopen,buttonclose;

    Button btn;

    public MainActivity() {
        super();
    }

    @Override
    protected void onStart() {
        Log.d("aji","Onstartcalled" );
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.d("aji","Onrestartcalled" );
        super.onRestart();
    }

    @Override
    protected void onStop() {
        Log.d("aji","Onstartcalled" );
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("aji","Ondestriycalled" );
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.d("aji","Onpausecalled" );
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d("aji","Onresumecalled" );
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("aji","Oncreate called" );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultv = findViewById(R.id.result);
        solutiontv = findViewById(R.id.solution);

        assignId(buttonc,R.id.button_c);
        assignId(button0,R.id.button_0);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(buttonac,R.id.button_ac);
        assignId(buttonplus,R.id.button_plus);
        assignId(buttonminus,R.id.button_minus);
        assignId(buttondot,R.id.button_dout);
        assignId(buttondiv,R.id.button_div);
        assignId(buttonmul,R.id.button_mul);
        assignId(buttonclose,R.id.button_clode);
        assignId(buttonopen,R.id.button_open);
        assignId(buttoneqal,R.id.button_equal);
        btn = findViewById(R.id.switchbtn);



    }


    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String datacalculate = solutiontv.getText().toString();


        if(buttonText.equals("AC")){
            solutiontv.setText("");
            resultv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutiontv.setText(resultv.getText());
            return;
        }
        if (buttonText.equals("C")){
            datacalculate =datacalculate.substring(0,datacalculate.length()-1);
        }else {
            datacalculate = datacalculate+buttonText;

        }
        solutiontv.setText(datacalculate);
        String finalResult = getresult(datacalculate);


        if(!finalResult.equals("err")){
            resultv.setText(finalResult);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MainActivity2.class));
            }
        });


    }
    String getresult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalresult= context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalresult.endsWith(".0")){
                finalresult = finalresult.replace(".0","");
            }
            return finalresult;
        }catch (Exception e){
            return "err";
        }

    }}
