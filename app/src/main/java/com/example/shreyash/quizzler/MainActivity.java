package com.example.shreyash.quizzler;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

     Button button1;
    Button button2;
    EditText emailText;
    EditText passwordText;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailText=findViewById(R.id.emailEditText);
        mAuth = FirebaseAuth.getInstance();
        passwordText=findViewById(R.id.passwordEditText);
        button1 =findViewById(R.id.loginButton);
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                logInActivity();
            }
        });
        button2 = findViewById(R.id.signUpButton);
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpActivity();
            }
        });


    }
    public void logInActivity()
    {
        String email= emailText.getText().toString().trim();
        String password=passwordText.getText().toString();
        if(email.isEmpty())
        { emailText.setError("Email is required!");
            emailText.requestFocus();}
        if(password.isEmpty())
        { passwordText.setError("Password is required!");
            passwordText.requestFocus();}


        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {

                    openHomeActivity();
                }
            }
        });
    }

    public void openHomeActivity()
    {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }

    public  void openSignUpActivity()
    {
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);

    }

    //    CODE FOR PRESS BACK AGAIN TO EXIT OPERATION

    boolean twice;

    @Override
    public void onBackPressed() {

        if (twice == true) {
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory(Intent.CATEGORY_HOME);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }

        twice = true;

        Toast.makeText(MainActivity.this, "Please press BACK again to EXIT", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                twice = false;


            }
        }, 3000);
    }
}
