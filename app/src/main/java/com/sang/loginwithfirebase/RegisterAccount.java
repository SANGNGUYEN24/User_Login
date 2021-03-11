package com.sang.loginwithfirebase;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;


public class RegisterAccount extends AppCompatActivity {

    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        //Elements
        TextInputEditText r_username = findViewById(R.id.r_username);
        TextInputEditText r_password = findViewById(R.id.r_password);
        Button register = findViewById(R.id.register);


        //Progressing
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering your account...");

        //get the shared instance of the FirebaseAuth object
        mAuth = FirebaseAuth.getInstance();

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
                else{
                    registerUser(email, pass);
                }
            }
        });

    }

    private void registerUser(String email, String  pass) {
        //Email and password are valid -> progressing and register the user
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, dismiss the dialog and start register activity

                            progressDialog.dismiss();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(RegisterAccount.this, "Registered",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterAccount.this, Hello.class);
                            startActivity(intent);

                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterAccount.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Error, dismiss progress dialog and get and show the error
                progressDialog.dismiss();
                Toast.makeText(RegisterAccount.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

}