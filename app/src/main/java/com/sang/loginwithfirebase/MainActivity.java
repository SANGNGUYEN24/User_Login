package com.sang.loginwithfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextInputEditText username = findViewById(R.id.username);

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    username.setError("Username must not empty");
                }
                else{
                    username.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        TextInputEditText password = findViewById(R.id.password);

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    password.setError("Password must not empty");
                }
                else{
                    password.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        AppCompatButton button = findViewById(R.id.login) ;
        TextView textView = findViewById(R.id.create_acc);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoRegister = new Intent(MainActivity.this, RegisterAccount.class);
                startActivity(gotoRegister);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent gotoHello = new Intent(MainActivity.this, Hello.class);
                    startActivity(gotoHello);
            }
        });


    }
}