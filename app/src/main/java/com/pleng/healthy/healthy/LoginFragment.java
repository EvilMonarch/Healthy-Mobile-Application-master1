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

import static android.R.attr.onClick;


/**
 * Created by LAB203_13 on 20/8/2561.
 */

public class LoginFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_login, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        Button loginButton = (Button) getView().findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TextView userTextField = (TextView) getView().findViewById(R.id.user_id);
                TextView passwordTextField = (TextView) getView().findViewById(R.id.password_id);

                String userTextFieldStr = userTextField.getText().toString();
                String passwordTextFieldStr = passwordTextField.getText().toString();

                if(userTextFieldStr.isEmpty() || passwordTextFieldStr.isEmpty()){
                    Log.i("LOGIN","USER OR PASSWORD IS EMPTY");
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new MenuFragment()).addToBackStack(null).commit();
                    Toast.makeText(getActivity(), "กรุณาระบุ Username หรือ Password",Toast.LENGTH_SHORT).show();
                }else if(userTextField.equals("admin") && passwordTextField.equals("admin")){
                    Log.i("LOGIN","INVALID USER OR PASSWORD");
                    Toast.makeText(getActivity(), "Username และ Password ไม่ถูกต้อง",Toast.LENGTH_SHORT);

                }else{
                    Log.i("LOGIN","GO BMI");
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightFragment()).addToBackStack(null).commit();
                }
            }

        });

        TextView signUpButton = (TextView) getView().findViewById(R.id.sign_up_button);
        signUpButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new RegisterFragment()).addToBackStack(null).commit();
            }
        });

    }

}

