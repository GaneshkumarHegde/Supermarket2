package com.example.android.supermarket;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
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
    private ProgressDialog progressDialog;

    int quantitySelected;
    private DatabaseReference mDatabase;
    private TextView textView;
    private ListView listView;

    String value,name;
    StringBuffer sb=new StringBuffer();
    ArrayAdapter<String> arrayAdapter;

    int p1;
    float cart=0;
ArrayList<String> cartList=new ArrayList<>();
    private ArrayList<String> list=new ArrayList<>();

    public  AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Products");
        listView=(ListView)findViewById(R.id.l);


        list.clear();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Starter");
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

            /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.BLACK);

                return view;
            }
        };
        listView.setAdapter(arrayAdapter);
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Product product=dataSnapshot.getValue(Product.class);

                value="Name: "+product.getName()+" \n"+"Price(Rs.): "+product.getPrice()+"\nQuantity: "+product.getQuantity()+"\nProduct Number: "+product.getNumber()+"\nDiscount: "+product.getDiscount()+"\n";




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
                p1 = position;
                final NumberPicker numberPicker = new NumberPicker(Main6Activity.this);
                numberPicker.setMaxValue(10);
                numberPicker.setMinValue(1);
                long val = arrayAdapter.getItemId(p1);
                String s = arrayAdapter.getItem(p1);
                String[] s1 = s.split(" ");
                name = s1[1];


                builder = new AlertDialog.Builder(Main6Activity.this);
                //  builder.setCancelable(true);
                builder.setTitle("Buy");
                builder.setMessage("Select Quantity:");
                builder.setView(numberPicker);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        quantitySelected=numberPicker.getValue();
                        //     Toast.makeText(Main6Activity.this,""+quantitySelected,Toast.LENGTH_SHORT).show();

                        mDatabase = FirebaseDatabase.getInstance().getReference().child("Starter").child(name.trim());
                        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Product p = dataSnapshot.getValue(Product.class);
                                int a = p.getQuantity();
                                if (quantitySelected > a && quantitySelected != 1) {
                                    Toast.makeText(Main6Activity.this, "Choose a lesser quantity", Toast.LENGTH_SHORT).show();
                                } else if (quantitySelected == 1 && a == 0) {
                                    Toast.makeText(Main6Activity.this, "Sorry,This item is not available now", Toast.LENGTH_SHORT).show();

                                } else {
                                    cart += p.getPrice() * quantitySelected - ((p.getPrice() * p.getDiscount()) / 100);
                                    sb.append(p.getName() + "\t x \t " + quantitySelected + "\t\t="+(p.getPrice()*quantitySelected)+"\n");
                                    dataSnapshot.getRef().child("quantity").setValue(a - quantitySelected);
                                    Toast.makeText(Main6Activity.this, "Item added", Toast.LENGTH_LONG).show();

                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                    }
                }); builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create().show();

                // finish();
            }
        });





    }

    public void checkCart(View view){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        if (settings.getBoolean("mypreference_checkbox1",false )){

            cart-=cart*0.1;
        }
        else if(cart>500){
            if (settings.getBoolean("mypreference_checkbox2",false )) {
                cart-=cart*0.1;

            }
        }
      //  Toast.makeText(this,""+o1,Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,Cart.class);
        i.putExtra("CartValue",cart);
        i.putExtra("CartItems",sb.toString());
        startActivity(i);


    }

    public void cStarters(View view){

        listView=(ListView)findViewById(R.id.l);


        list.clear();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Starter");
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

            /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.BLACK);

                return view;
            }
        };
        listView.setAdapter(arrayAdapter);
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Product product=dataSnapshot.getValue(Product.class);

                value="Name: "+product.getName()+" \n"+"Price(Rs.): "+product.getPrice()+"\nQuantity: "+product.getQuantity()+"\nProduct Number: "+product.getNumber()+"\nDiscount: "+product.getDiscount()+"\n";




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
                p1 = position;
                final NumberPicker numberPicker = new NumberPicker(Main6Activity.this);
                numberPicker.setMaxValue(10);
                numberPicker.setMinValue(1);
                long val = arrayAdapter.getItemId(p1);
                String s = arrayAdapter.getItem(p1);
                String[] s1 = s.split(" ");
                name = s1[1];


                builder = new AlertDialog.Builder(Main6Activity.this);
                //  builder.setCancelable(true);
                builder.setTitle("Buy");
                builder.setMessage("Select Quantity:");
                builder.setView(numberPicker);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        quantitySelected=numberPicker.getValue();
                        //     Toast.makeText(Main6Activity.this,""+quantitySelected,Toast.LENGTH_SHORT).show();

                                               mDatabase = FirebaseDatabase.getInstance().getReference().child("Starter").child(name.trim());
                        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Product p = dataSnapshot.getValue(Product.class);
                                int a = p.getQuantity();
                                if (quantitySelected > a && quantitySelected != 1) {
                                    Toast.makeText(Main6Activity.this, "Choose a lesser quantity", Toast.LENGTH_SHORT).show();
                                } else if (quantitySelected == 1 && a == 0) {
                                    Toast.makeText(Main6Activity.this, "Sorry,This item is not available now", Toast.LENGTH_SHORT).show();

                                } else {
                                    cart += p.getPrice() * quantitySelected - ((p.getPrice() * p.getDiscount()) / 100);
                                    sb.append(p.getName() + "\t x \t " + quantitySelected + "\t\t="+(p.getPrice()*quantitySelected)+"\n");
                                    dataSnapshot.getRef().child("quantity").setValue(a - quantitySelected);
                                    Toast.makeText(Main6Activity.this, "Item added", Toast.LENGTH_LONG).show();

                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                    }
                }); builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create().show();

                // finish();
            }
        });


    }
    public void cMain(View view){
        list.clear();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Main Course");
        final ArrayAdapter<String> a1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

            /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.BLACK);

                return view;
            }
        };

        listView.setAdapter(a1);
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Product product=dataSnapshot.getValue(Product.class);

                value="Name: "+product.getName()+"\n"+" Price: "+product.getPrice()+"\nQuantity: "+product.getQuantity()+"\nProduct Number: "+product.getNumber()+"\nDiscount: "+product.getDiscount()+"\n";




                list.add(value);

                a1.notifyDataSetChanged();
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
                p1 = position;
                final NumberPicker numberPicker = new NumberPicker(Main6Activity.this);
                numberPicker.setMaxValue(10);
                numberPicker.setMinValue(1);
                long val = a1.getItemId(p1);
                String s = a1.getItem(p1);
                String[] s1 = s.split(" ");
                name = s1[1];


                builder = new AlertDialog.Builder(Main6Activity.this);
                builder.setTitle("Buy");
                builder.setMessage("Select Quantity:");
                builder.setView(numberPicker);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        quantitySelected=numberPicker.getValue();
                        //     Toast.makeText(Main6Activity.this,""+quantitySelected,Toast.LENGTH_SHORT).show();

                        mDatabase = FirebaseDatabase.getInstance().getReference().child("Main Course").child(name.trim());
                        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Product p = dataSnapshot.getValue(Product.class);
                                int a = p.getQuantity();
                                if (quantitySelected > a && quantitySelected != 1) {
                                    Toast.makeText(Main6Activity.this, "Choose a lesser quantity", Toast.LENGTH_SHORT).show();
                                } else if (quantitySelected == 1 && a == 0) {
                                    Toast.makeText(Main6Activity.this, "Sorry,This item is not available now", Toast.LENGTH_SHORT).show();

                                } else {
                                    cart += p.getPrice() * quantitySelected - ((p.getPrice() * p.getDiscount()) / 100);
                                    sb.append(p.getName() + "\t x \t " + quantitySelected + "\t\t="+(p.getPrice()*quantitySelected)+"\n");
                                    dataSnapshot.getRef().child("quantity").setValue(a - quantitySelected);
                                    Toast.makeText(Main6Activity.this, "Item added", Toast.LENGTH_LONG).show();

                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                    }
                }); builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create().show();

                // finish();
            }
        });


    }
    public void clearCart(View view){
        sb.replace(0,sb.toString().length(),"");
        cart=0;
    }
    }

