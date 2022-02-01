package com.example.booking_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {

   TextView btn,forgotPassword;
   EditText inputEmail,inputPassword;
   Button btnLogin;
   private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btn=findViewById(R.id.textViewSignUp);

        inputEmail=findViewById(R.id.inputEmail);
        inputPassword=findViewById(R.id.inputPassword);
        btnLogin=findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });
        mAuth=FirebaseAuth.getInstance();

        btn=findViewById(R.id.textViewSignUp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ForgotPassword.class));
            }
        });
    }

    private  void checkCredentials(){

        String email=inputEmail.getText().toString();
        String password=inputPassword.getText().toString();


        if (email.isEmpty() || !email.contains("@"))
        {
            showError(inputEmail,"your email is not valid!");
        }
        else if(password.isEmpty()|| password.length()<6)
        {
            showError(inputPassword,"password must be >= 6 characters");
        }
        else
        {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(LoginActivity.this, "Successfully Login", Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);

                        startActivity(intent);

                    }
                }
            });
        }

    }
    private void showError(EditText input, String s)
    {
        input.setError(s);
        input.requestFocus();
    }

}