package net.mycompany.new_noteapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import net.mycompany.new_noteapp.R;

public class Splash_activity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {

               Intent i=new Intent(Splash_activity.this,MainActivity.class);
               startActivity(i);
               finish();
           }
       }, 3000);
    }
}
