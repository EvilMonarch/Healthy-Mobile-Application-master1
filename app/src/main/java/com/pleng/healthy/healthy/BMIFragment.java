package com.pleng.healthy.healthy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by LAB203_13 on 20/8/2561.
 */

public class BMIFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bmi, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        Button calculatorButton = (Button)getView().findViewById(R.id.calculateBmiButton);
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView weightBMI = (TextView)getView().findViewById(R.id.weightBmi);
                TextView heightBMI = (TextView)getView().findViewById(R.id.heightBmi);
                String weightBmiStr = weightBMI.getText().toString();
                String heightBmiStr = heightBMI.getText().toString();



                TextView resultCalBmi = (TextView)getView().findViewById(R.id.result_text_bmi);
                if(weightBmiStr.isEmpty() || heightBmiStr.isEmpty()){
                    Toast.makeText(getActivity(),"กรุณาระบุข้อมูลให้ครบถ้วน",Toast.LENGTH_SHORT).show();
                    Log.i("BMI","Feild is empty");
                }
                else {
                    Float weightBmiInt = Float.parseFloat(weightBMI.getText().toString());
                    Float heightBmiInt = Float.parseFloat(heightBMI.getText().toString())/100;
                    float result = weightBmiInt / (heightBmiInt * heightBmiInt);
                    resultCalBmi.setText("" + result);
                    Log.i("BMI","BMI is value");
                }
            }
        });

    }
}
