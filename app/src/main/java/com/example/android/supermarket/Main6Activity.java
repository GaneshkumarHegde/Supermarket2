package com.example.android.supermarket;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
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
    StringBuffer sb=new StringBuffer();
    int p1;
    float cart=0;
ArrayList<String> cartList=new ArrayList<>();
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
                Product p = dataSnapshot.getValue(Product.class);
                int q = p.getQuantity();
               // Toast.makeText(Main6Activity.this, "" + q, Toast.LENGTH_SHORT).show();
                if (q == 0) {
                    Intent in = new Intent(Main6Activity.this, createProduct.class);
                    PendingIntent pending = PendingIntent.getActivity(Main6Activity.this, 0, in, 0);
                    Notification noti = new Notification.Builder(Main6Activity.this).setContentTitle("Out of Stock").setContentText(p.getName() + " is out of stock\n").setSmallIcon(R.mipmap.ic_launcher_icon).setContentIntent(pending).build();
                    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    noti.flags |= Notification.FLAG_AUTO_CANCEL;
                    manager.notify(0, noti);
                }
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
                p1 = position;

                AlertDialog.Builder builder = new AlertDialog.Builder(Main6Activity.this);
                builder.setCancelable(true);
                builder.setTitle("Buy");
                builder.setMessage("Do u want to buy this item?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        long val = arrayAdapter.getItemId(p1);
                        String s = arrayAdapter.getItem(p1);
                        String[] s1 = s.split(" ");
                        name = s1[1];
                        //  Toast.makeText(Main6Activity.this,""+name+"\t"+name.trim().length(),Toast.LENGTH_SHORT).show();

                        mDatabase = FirebaseDatabase.getInstance().getReference().child("Products").child(name.trim());
                        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Product p = dataSnapshot.getValue(Product.class);
                                int a = p.getQuantity();
                              //  Toast.makeText(Main6Activity.this, "" + a, Toast.LENGTH_SHORT).show();
                                cart+=p.getPrice()-((p.getPrice()*p.getDiscount())/100);
                                sb.append(p.getName()+" ");
                                dataSnapshot.getRef().child("quantity").setValue(a - 1);
                                Toast.makeText(Main6Activity.this,"Item added to your cart",Toast.LENGTH_LONG).show();

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
                builder.create().show();

                // finish();
            }
                });






    }

    public void checkCart(View view){


       Intent i=new Intent(this,Cart.class);
        Toast.makeText(Main6Activity.this,""+sb,Toast.LENGTH_LONG).show();
        i.putExtra("CartValue",cart);
        i.putExtra("CartItems",sb.toString());
        startActivity(i);
    }
}
