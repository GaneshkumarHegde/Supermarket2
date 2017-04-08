package com.example.android.supermarket;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewProductMenu extends AppCompatActivity {
    private DatabaseReference mDatabase;
TextView textView;
    String s;
    String URL_PRODUCTS="https://console.firebase.google.com/project/supermarket2-80016/database/data/Products";
String userId="KhCm8DZpg72BBXCISCC";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product_menu);
        textView=(TextView)findViewById(R.id.printProducts);
        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Products");
        mDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Product product = dataSnapshot.getValue(Product.class);
s="Product Name: "+product.getName();
textView.setText(s);            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }}
