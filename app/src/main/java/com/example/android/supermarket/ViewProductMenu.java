package com.example.android.supermarket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewProductMenu extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private  TextView textView;
    private ListView listView;
    private ArrayList<String> list=new ArrayList<>();
String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product_menu);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Products");
        listView=(ListView)findViewById(R.id.list_item);
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
listView.setAdapter(arrayAdapter);
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Product product=dataSnapshot.getValue(Product.class);

                     value="Name: "+product.getName()+"\n"+"Price: "+product.getPrice()+"\nQuantity: "+product.getQuantity()+"\nProduct Number: "+product.getNumber()+"\nDiscount: "+product.getDiscount()+"\n";




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
    }
}
