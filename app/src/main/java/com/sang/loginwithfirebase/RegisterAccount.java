package com.sang.loginwithfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import java.util.Objects;


public class RegisterAccount extends AppCompatActivity {

    //ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        //Elements
        TextInputEditText r_username = findViewById(R.id.r_username);
        TextInputEditText r_password = findViewById(R.id.r_password);
        Button register = findViewById(R.id.register);


        //Progressing
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Registering your account...");

        //get the shared instance of the FirebaseAuth object


        //Click Create an account -> Register account
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //input mail, pass
                String email = Objects.requireNonNull(r_username.getText()).toString().trim();
                String pass = Objects.requireNonNull(r_password.getText()).toString().trim();

                //Validate them
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    //Set error
                    r_username.setError("Invalid Email");
                    r_username.setFocusable(true);

                }
                else if (r_password.length() < 6){
                    //set error
                    r_password.setError("Password length at least 6 characters");
                    r_password.setFocusable(true);

                }
//                else{
////                    //registerUser(email, pass);
////                }
            }
        });

    }
}