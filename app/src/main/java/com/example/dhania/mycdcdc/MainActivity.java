package com.example.dhania.mycdcdc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    RelativeLayout tvt;
    Animation tt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.tv);
        tt= AnimationUtils.loadAnimation(this,R.anim.myanimation);
        tv.startAnimation(tt);

        //tvt=(RelativeLayout)findViewById(R.id.tvt);
        tv.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(MainActivity.this,Main3Activity.class);
                startActivity(i);
                finish();
            }
        },3000);
    }
}
