package com.example.shreyash.quizzler;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    final String TAG = this.getClass().getName();
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle ;
    LinearLayout start,quiz;
    TextView ques,cat,diff,top;
    Button b1 ;
    int i  ;
    int score  ;
    ProgressBar pb;
//    NavigationView navigationView1 = findViewById(R.id.navigationView1);






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawerLayout = findViewById(R.id.navigationDrawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout ,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getData();
        b1 = findViewById(R.id.startButton);
        start=findViewById(R.id.startLayout);
        quiz=findViewById(R.id.quizLayout);
        ques=findViewById(R.id.ques);
        cat=findViewById(R.id.category);
        diff=findViewById(R.id.difficulty);
        top=findViewById(R.id.topTextView);
        pb=findViewById(R.id.progressbar);
    }


    // function for menu

      @Override
        public boolean onCreateOptionsMenu (Menu menu)

{
    getMenuInflater().inflate(R.menu.main,menu);
    return  true;

}


     @Override
             public boolean onOptionsItemSelected (MenuItem item)
     {

         if (actionBarDrawerToggle.onOptionsItemSelected(item))
         {
             return  true;
         }
         else{
             int id = item.getItemId();
             if(id==R.id.SignOut)
             {
                 FirebaseAuth.getInstance().signOut();
                 finish();
                 Toast.makeText(HomeActivity.this, "Successfully Signed Out", Toast.LENGTH_LONG).show();
                 startActivity(new Intent(this,MainActivity.class));
                 return true;
             }
             else if(id==R.id.instructions){

                 startActivity(new Intent(this,MainActivity.class));
                 return true;

             }
             return true;
         }

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

        Toast.makeText(HomeActivity.this, "Please press BACK again to EXIT", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                twice = false;
                Log.d(TAG, "Twice:" + twice);

            }
        }, 3000);
    }





    //API CALL
    Results results[]=new Results[10];

    public void  getData()
    {
        final Call<QuestionBank>  getResults = ApiResponse.getService().getQuestionList();
        getResults.enqueue(new Callback<QuestionBank>() {
            @Override
            public void onResponse(Call<QuestionBank> call, Response<QuestionBank> response) {

                QuestionBank questionBank = response.body();
                pb.setVisibility(View.GONE);
                start.setVisibility(View.VISIBLE);
                results=questionBank.getResults();





            }

            @Override
            public void onFailure(Call<QuestionBank> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Fail to retrive Data", Toast.LENGTH_SHORT).show();


            }
        });

    }


    void questionSetter(int  j ){
        if(j<10)

        {
            results[j].getQuestion().replace("&quot;", "'");
            ques.setText(results[j].getQuestion());
            cat.setText(results[j].getCategory());
            diff.setText(results[j].getDifficulty());

        }

    }

public void startQuiz(View view){
        start.setVisibility(View.GONE);
        quiz.setVisibility(View.VISIBLE);
        i=0;
        score=0;
        questionSetter(i);


}
public void tappedButton(View view)
{
    String str= view.getTag().toString();
    if(results[i].getCorrect_answer().equals(str)) {
        Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        score+=10;

        i++;
        questionSetter(i);
    }

    else {
        Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();


        i++;
        questionSetter(i);
    }

    if(i==10)
    {
        quiz.setVisibility(View.GONE);
        start.setVisibility(View.VISIBLE);
        top.setText("LAST GAME SCORE\n"+Integer.toString(score));
        b1.setText("RESTART QUIZ");
    }
}

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
   int id = item.getItemId();
   if( id == R.id.instructions)
   {
       startActivity(new Intent(this,MainActivity.class));
   }

    return false;
    }
}











