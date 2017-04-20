package com.example.android.supermarket;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Cart extends AppCompatActivity {
    public String msg;String s[];
    String itemToBeRemoved;
    private DatabaseReference mDatabase;

    private ArrayList<String> cartList=new ArrayList<>();
StringBuffer sb=new StringBuffer();
    StringBuffer sb1=new StringBuffer();
TextView t;
    TextView t1;
TextView t3;
    StringBuffer names=new StringBuffer();
    StringBuffer quantities=new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Intent i=getIntent();
        float cartValue=i.getFloatExtra("CartValue",0);
        String cartItems=i.getStringExtra("CartItems");
        Toast.makeText(this,""+cartValue+"\t"+cartItems,Toast.LENGTH_LONG).show();
        t=(TextView)findViewById(R.id.print1);

        t3=(TextView)findViewById(R.id.printTotal);
        t1=(TextView)findViewById(R.id.t1);
        t1.setText("Your Bill");
        t.setText(cartItems);
        t3.setText("Total (Including offers)=\t"+cartValue);
 msg="Your Order \n"+cartItems+"\nTotal="+cartValue+" has been placed";





    }
    public  void buy(android.view.View view){
Intent i=new Intent(this,Buy.class);
        i.putExtra("msg",msg);
        startActivity(i);
    }
   }
