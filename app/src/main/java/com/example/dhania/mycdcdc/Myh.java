package com.example.dhania.mycdcdc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Myh extends AppCompatActivity {
    CardView cv61,cv62,cv63,cv64;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myh);

        cv61=(CardView)findViewById(R.id.cardView51);
        cv62=(CardView)findViewById(R.id.cardView52);
        cv63=(CardView)findViewById(R.id.cardView53);
        cv64=(CardView)findViewById(R.id.cardView54);

        cv61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Myh.this,My_about1.class);
                startActivity(i);
            }
        });

        cv62.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Myh.this,Mymission.class);
                startActivity(i);
            }
        });

        cv63.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Myh.this,Myvision.class);
                startActivity(i);
            }
        });

        cv64.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Myh.this,Main2Activity.class);
                startActivity(i);
            }
        });
    }
}
