package com.pleng.healthy.healthy;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * Created by LAB203_13 on 20/8/2561.
 */

public class RegisterFragment extends Fragment {
    private FirebaseAuth fbAuth;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fbAuth = FirebaseAuth.getInstance();
        initRegisterBtn();
    }
    void initRegisterBtn(){
        Button signUpButton = (Button) getView().findViewById(R.id.sign_up_button_sign_up_page);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView userId = (TextView) getView().findViewById(R.id.user_id_sign_up_page);
                TextView passwordId = (TextView) getView().findViewById(R.id.password_id_sign_up_page);
                TextView passwordId2 = (TextView) getView().findViewById(R.id.password_id_sign_up_page2);

                String userIdStr = userId.getText().toString();
                String passwordIdStr = passwordId.getText().toString();
                String passwordIdStr2 = passwordId2.getText().toString();


                if (userIdStr.isEmpty() || passwordIdStr.isEmpty() || passwordIdStr2.isEmpty()) {
                    Toast.makeText(getActivity(), "กรุณาระบุข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();
                    Log.i("REGISTER", "Field Name is Empyty");
                } else if (userIdStr.equals("admin")) {
                    Toast.makeText(getActivity(), "user นี้มีอยู่ในระบบแล้ว", Toast.LENGTH_SHORT).show();
                    Log.i("REGISTER", "USER ALREADY EXITS");

                } else if (passwordIdStr.length() < 6 || passwordIdStr2.length() < 6) {
                    Toast.makeText(getActivity(), "password ไม่ครบ", Toast.LENGTH_SHORT).show();
                    Log.i("REGISTER", "USER ALREADY EXITS");
                } else if (passwordIdStr.compareTo(passwordIdStr2) != 0) {
                    Toast.makeText(getActivity(), "Password ไม่ตรงกัน", Toast.LENGTH_SHORT).show();
                    Log.i("REGISTER", "Password incorrect");
                } else {
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    mAuth.createUserWithEmailAndPassword("email","password").addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(getActivity(),"สมัครสมาชิกเรียบร้อย",Toast.LENGTH_SHORT).show();
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.main_view, new LoginFragment()).addToBackStack(null).commit();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(),"Error = " + e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

    }
    void regiterNewUser(){
        EditText _email = (EditText) getView().findViewById(R.id.user_id_sign_up_page);
        EditText _password = (EditText) getView().findViewById(R.id.password_id_sign_up_page);
        EditText _password2= (EditText) getView().findViewById(R.id.password_id_sign_up_page2);
        String _emailStr = _email.getText().toString();
        String _passwordStr = _password.getText().toString();
        String _password2Str = _password2.getText().toString();
        if(_passwordStr.equals(_password2Str)){
            fbAuth.createUserWithEmailAndPassword(_emailStr,_passwordStr).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    sendVerifiedEmail(authResult.getUser());
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(),"ERROR"+e.getMessage(),Toast.LENGTH_SHORT);
                }
            });
        }
    }
    private void sendVerifiedEmail(FirebaseUser _user){
        _user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getActivity(),"สมัครสมาชิกเรียบร้อย",Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_view, new LoginFragment()).addToBackStack(null).commit();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(),"Error = " + e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
