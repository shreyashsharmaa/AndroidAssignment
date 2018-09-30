package com.example.shreyash.quizzler;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final String TAG = this.getClass().getName();
    private Button button1;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       button1 =(Button)findViewById(R.id.loginButton);
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeActivity();
            }
        });
        button2 = (Button)findViewById(R.id.signUpButton);
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpActivity();
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
        Log.d(TAG, "Click");
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
                Log.d(TAG, "Twice:" + twice);

            }
        }, 3000);
    }
}
