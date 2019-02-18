package com.example.dhania.mycdcdc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Myfeedback extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    Button bb;
    EditText tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfeedback);
        bb=(Button)findViewById(R.id.button);
        tb=(EditText)findViewById(R.id.editText2);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("Feedback");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //DataSnapshot snapshot=dataSnapshot.child("roots");
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot1:dataSnapshot.getChildren()){
                        Feeedback employee=snapshot1.getValue(Feeedback.class);

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Feeedback employee=new Feeedback();
                employee.setTb(tb.getText().toString());
                String eid=databaseReference.push().getKey();
                databaseReference.child(eid).setValue(employee);
                Toast.makeText(Myfeedback.this,"Feedback given,Thank You",Toast.LENGTH_LONG).show();
            }
        });
    }
    }

