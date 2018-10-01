package com.example.shreyash.quizzler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpActivity extends AppCompatActivity {
    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        button1 =(Button)findViewById(R.id.loginButton);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openHomeActivity();
                }
            });


        }

        public void openHomeActivity()
        {
            Intent intent = new Intent(this,HomeActivity.class);
            startActivity(intent);
        }



    }



