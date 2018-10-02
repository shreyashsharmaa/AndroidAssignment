package com.example.shreyash.quizzler;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    Button button1;
    EditText emailText;
    EditText passwordText;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        emailText=findViewById(R.id.emailEditText);
        mAuth = FirebaseAuth.getInstance();

          progressBar=findViewById(R.id.progress);
          passwordText=findViewById(R.id.passwordEditText);
        button1 =findViewById(R.id.signUpFinalButton);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signUp();
                }
            });


        }

        public void signUp()
        {     String email= emailText.getText().toString().trim();
              String password=passwordText.getText().toString();
            if(email.isEmpty())
            { emailText.setError("Email is required!");
            emailText.requestFocus();}
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            { emailText.setError("Please enter a valid Email!");
                emailText.requestFocus();

            }
                if(password.isEmpty())
            { passwordText.setError("Password is required!");
                passwordText.requestFocus();}
                if(password.length()<6)
                {
                    passwordText.setError("Try a stronger password");
                    passwordText.requestFocus();
                }
                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(),"Succesfull",Toast.LENGTH_SHORT).show();

                            openHome();
                        }
                    }
                });
        }

       void openHome(){
           Intent intent = new Intent(this,HomeActivity.class);
           startActivity(intent);

       }

    }



