package com.example.bonasagala.atmaauto.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bonasagala.atmaauto.R;

public class SplashAct extends AppCompatActivity {

    Animation app_splash, splash_text_animation;

    ImageView app_logo;
    TextView app_tagline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //load animation
        app_splash = AnimationUtils.loadAnimation(this, R.anim.app_splash);
        splash_text_animation = AnimationUtils.loadAnimation(this, R.anim.bottomtotop);

        app_logo = findViewById(R.id.app_logo);
        app_tagline = findViewById(R.id.app_tagline);

        app_logo.startAnimation(app_splash);
        app_tagline.startAnimation(splash_text_animation);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //merubah activity ke activity lain
                Intent gogetstarted = new Intent(SplashAct.this, GetStartedAct.class);
                startActivity(gogetstarted);
                finish();
            }
        }, 2000);
    }
}
