package com.example.dhania.mycdcdc;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewUploadsActivity extends AppCompatActivity {

    ListView listView;

    //database reference to get uploads data
    DatabaseReference mDatabaseReference;

    //list to store uploads data
    List<Upload> uploadList;
    ArrayAdapter<String> adapter;
    ArrayList<String>keyList;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_uploads);
        uploadList = new ArrayList<>();
        keyList = new ArrayList<>();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);
        listView = (ListView) findViewById(R.id.listView);
        //adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_expandable_list_item_1);


        //adding a clicklistener on listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the upload
                Upload upload = uploadList.get(i);

                //Opening the upload file in browser using the upload url
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(upload.getUrl()));
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           final int position, long id) {

                AlertDialog.Builder alert = new AlertDialog.Builder(
                        ViewUploadsActivity.this);

                alert.setTitle("Delete");
                alert.setMessage("Do you want delete this item?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TOD O Auto-generated method stub

                        // main code on after clicking yes
                        /*uploadList.remove(position);
                        adapter.notifyDataSetChanged();*/
                        mDatabaseReference.child(keyList.get(position)).setValue(null);
                        //adapter.notifyDataSetChanged();
                        //adapter.notifyDataSetInvalidated();
                        flag=1;

                        Toast.makeText(ViewUploadsActivity.this,"Item has been deleted",Toast.LENGTH_LONG).show();
                        // keyList.remove(position);


                    }
                });
                alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                    }
                });
                alert.show();
                //new code below

                return true;
            }

        });



        //getting the database reference


        //retrieving upload data from firebase database
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (flag == 0) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        keyList.add(postSnapshot.getKey().toString());
                        uploadList.add(upload);
                    }
                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }

                    //displaying it to list
                    adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1, uploads);
                    listView.setAdapter(adapter);
                }
                else if(flag==1){
                    uploadList.clear();
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Upload upload = postSnapshot.getValue(Upload.class);
                        keyList.add(postSnapshot.getKey().toString());
                        uploadList.add(upload);
                    }

                    String[] uploads = new String[uploadList.size()];

                    for (int i = 0; i < uploads.length; i++) {
                        uploads[i] = uploadList.get(i).getName();
                    }

                    //displaying it to list
                    adapter = new ArrayAdapter<String>(ViewUploadsActivity.this, android.R.layout.simple_expandable_list_item_1, uploads);

                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }
}
