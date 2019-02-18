package com.example.dhania.mycdcdc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Main3Activity extends AppCompatActivity {
    CardView cv15,cv16,cv17,cv18,cv19,cv20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        cv15 = (CardView) findViewById(R.id.cardView15);
        cv16 = (CardView) findViewById(R.id.cardView16);
        cv17 = (CardView) findViewById(R.id.cardView17);
        cv18 = (CardView) findViewById(R.id.cardView18);
        cv19 = (CardView) findViewById(R.id.cardView19);
        cv20 = (CardView) findViewById(R.id.cardView20);
        cv15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main3Activity.this, Myh.class);
                startActivity(i);
            }
        });

        cv16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main3Activity.this, MyContactUS.class);
                startActivity(i);
            }
        });

        cv17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main3Activity.this, MyCourses.class);
                startActivity(i);
            }
        });

        cv18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main3Activity.this, Mynotice.class);
                startActivity(i);
            }
        });

        cv19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main3Activity.this, MyChatting.class);
                startActivity(i);
            }
        });

        cv20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main3Activity.this, Myfeedback.class);
                startActivity(i);
            }
        });
    }

}
