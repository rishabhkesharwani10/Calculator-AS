package com.example.projectcalc;

import androidx.appcompat.app.AppCompatActivity;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv;
    MaterialButton buttonAC, buttonDel, buttondivide, buttonmultiply, button7, button8, button9, buttonminus;
    MaterialButton button4, button5, button6, buttonplus, button1, button2, button3 ,buttonequal, button00, buttonpoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv=findViewById(R.id.result_tv);

        assignId(buttonAC,R.id.button_ac);
        assignId(buttonDel,R.id.button_del);
        assignId(buttondivide,R.id.button_divide);
        assignId(buttonmultiply,R.id.button_multiply);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(buttonminus,R.id.button_minus);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(buttonplus,R.id.button_plus);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(buttonequal,R.id.button_equal);
        assignId(button00,R.id.button_0);
        assignId(buttonpoint,R.id.button_point);

    }


    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
    MaterialButton button=(MaterialButton) view;
    String buttonText=button.getText().toString();
    String dtc=resultTv.getText().toString();

    if(buttonText.equals("AC")){
        resultTv.setText("0");
        return;
    }
    if(buttonText.equals("=")){
    resultTv.setText(resultTv.getText());
    return;
    }
if(buttonText.equals("DEL")){
    dtc=dtc.substring(0,dtc.length()-1);
}else {
    dtc=dtc+buttonText;
    resultTv.setText(dtc);
}
    resultTv.setText(dtc);
    String finalResult=getresult(dtc);
    if(!finalResult.equals("Error")){
        resultTv.setText(finalResult);
    }
}
String getresult(String data){
   try {
       Context context=Context.enter();
       context.setOptimizationLevel(-1);
       Scriptable scriptable=context.initStandardObjects();
      String finalResult= context.evaluateString(scriptable,data,"Javascipt",1,null).toString();
      if (finalResult.endsWith(".0")){
          finalResult=finalResult.replace(".0","");
      }
      return finalResult;
   }catch (Exception e){
       return "Error";
   }


}
}