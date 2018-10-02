package com.example.shreyash.quizzler;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    final String TAG = this.getClass().getName();
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawerLayout = findViewById(R.id.navigationDrawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout ,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



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




}











