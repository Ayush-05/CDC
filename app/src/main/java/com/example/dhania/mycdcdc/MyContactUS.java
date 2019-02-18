package com.example.dhania.mycdcdc;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MyContactUS extends AppCompatActivity {

    private static final int REQUENT_CAll=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contact_us);
        ImageView b=(ImageView) findViewById(R.id.bt3);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });
    }
    private void makePhoneCall()
    {
        if(ContextCompat.checkSelfPermission(MyContactUS.this,
                Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MyContactUS.this,
                    new String[]{Manifest.permission.CALL_PHONE},REQUENT_CAll);
        }
        else{
            String dial="tel:9413605825";
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==REQUENT_CAll)
        {
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                makePhoneCall();
            }


        }
    }
}
