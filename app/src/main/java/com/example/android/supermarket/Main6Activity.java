package com.example.android.supermarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main6Activity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private TextView textView;
    private ListView listView;
    String value,name;
    private ArrayList<String> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Products");
        listView=(ListView)findViewById(R.id.l);
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Product product=dataSnapshot.getValue(Product.class);

                value="Name: "+product.getName()+"\n"+" Price: "+product.getPrice()+"\nDiscount: "+product.getDiscount()+"\n";




                list.add(value);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }


            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long val=arrayAdapter.getItemId(position);
                String s=arrayAdapter.getItem(position);
                String[] s1=s.split(" ");
                 name=s1[1];
              //  Toast.makeText(Main6Activity.this,""+name+"\t"+name.trim().length(),Toast.LENGTH_SHORT).show();

               mDatabase= FirebaseDatabase.getInstance().getReference().child("Products").child(name.trim());
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Product p=dataSnapshot.getValue(Product.class);

                        int a= p.getQuantity();
                        Toast.makeText(Main6Activity.this,""+a,Toast.LENGTH_SHORT).show();

                        dataSnapshot.getRef().child("quantity").setValue(a-1);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

              /*  Product p=new Product();
                p.decreaseQuantity();

               mDatabase.setValue(p);*/
            }
        });




    }
}
