package com.example.dhania.mycdcdc;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyChatting extends AppCompatActivity {

    private static int SIGN_IN_REQUEST_CODE=1;
    //private FirebaseListAdapter<ChatMessage> adapter;
    RelativeLayout activity_main;
    FloatingActionButton fab;
    private ChildEventListener childEventListener;
    FirebaseDatabase mref;
    DatabaseReference db;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_sign_out)
        {
            AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Snackbar.make(activity_main,"You have been signed out",Snackbar.LENGTH_SHORT).show();
                    finish();
                }
            });
        }

        return true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SIGN_IN_REQUEST_CODE)
        {
            if(resultCode==RESULT_OK)
            {
                Snackbar.make(activity_main,"Successfully signed in.Welcome",Snackbar.LENGTH_SHORT).show();
                //displayChatMessage();
            }
            else {
                Snackbar.make(activity_main,"We couln't sign in",Snackbar.LENGTH_SHORT).show();
                finish();
            }
        }
    }
    DatabaseReference mRoot;
    EditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_chatting);
        activity_main = (RelativeLayout) findViewById(R.id.activity_main);

        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1);
        listView=(ListView)findViewById(R.id.list_of_messages);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        mRoot=FirebaseDatabase.getInstance().getReference();
        input = (EditText) findViewById(R.id.input);
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(), SIGN_IN_REQUEST_CODE);

        } else {
            Snackbar.make(activity_main, "Welcome" + FirebaseAuth.getInstance().getCurrentUser().getEmail(), Snackbar.LENGTH_SHORT).show();
            displayChatMessage();

        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference db=mRoot.child("Messages").push();
                String dbnot=db.getKey();
                HashMap<String,String> myhash=new HashMap<>();
                myhash.put("messageText",input.getText().toString());
                myhash.put("messageUser",FirebaseAuth.getInstance().getCurrentUser().getEmail().toString());
                Map mp=new HashMap();
                mp.put("Messages/"+dbnot,myhash);
                input.setText("");
                mRoot.updateChildren(mp, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if(databaseError != null)
                        {

                            Toast.makeText(MyChatting.this,"Error",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }

        });
        }


        public void displayChatMessage() {


            DatabaseReference ret=FirebaseDatabase.getInstance().getReference().child("Messages");
            ret.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists())
                    {

                        for(DataSnapshot pp:dataSnapshot.getChildren())
                        {
                            ChatMessage cm=pp.getValue(ChatMessage.class);
                           // arrayList.add("Email- "+cm.getMessageUser()+"\n--"+cm.getMessageText());
                           String s=""+cm.getMessageText()+"\n--("+cm.getMessageUser()+")";
                            //Toast.makeText(MyChatting.this,"TYY",Toast.LENGTH_SHORT).show();
                            //arrayAdapter.add("Email- "+cm.getMessageUser()+"\n--"+cm.getMessageText());
                           arrayAdapter.insert(s,0);
                        }
                        //arrayAdapter.addAll(s);

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
           listView.setAdapter(arrayAdapter);




        }

}
