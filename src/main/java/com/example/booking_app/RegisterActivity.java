package com.example.booking_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText mUsername,mEmail,mPassword,mConformPassword;
    Button mRegisterBtn;
    TextView mLoginBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUsername=findViewById(R.id.inputUsername);
        mEmail=findViewById(R.id.inputEmail);
        mPassword=findViewById(R.id.inputPassword);
        mConformPassword=findViewById(R.id.inputConformPassword);

        mAuth=FirebaseAuth.getInstance();


        mRegisterBtn=findViewById(R.id.btnRegister);
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCredentials();
            }
        });






        TextView btn=findViewById(R.id.alreadyHaveAccount);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }
    private  void checkCredentials(){
        String username=mUsername.getText().toString();
        String email=mEmail.getText().toString();
        String password=mPassword.getText().toString();
        String conformedPassword=mConformPassword.getText().toString();


        if(username.isEmpty()|| username.length()<7)
        {
            showError(mUsername,"your username is not valid!");
        }
        else if (email.isEmpty() || !email.contains("@"))
        {
            showError(mEmail,"your email is not valid!");
        }
        else if(password.isEmpty()|| password.length()<6)
        {
            showError(mPassword,"password must be >= 6 characters");
        }
        else if(conformedPassword.isEmpty()|| !conformedPassword.equals(password))
        {
            showError(mConformPassword,"password not match!");
        }
        else{
            Toast.makeText(this,"call Registration Method",Toast.LENGTH_SHORT).show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(RegisterActivity.this, "Successfully Registration", Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);

                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(RegisterActivity.this,task.getException().toString(), Toast.LENGTH_SHORT).show();
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