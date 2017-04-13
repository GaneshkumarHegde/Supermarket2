package com.example.android.supermarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Cart extends AppCompatActivity {
ListView listView;
    private ArrayList<String> cartList=new ArrayList<>();
StringBuffer sb=new StringBuffer();
    StringBuffer sb1=new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        listView=(ListView)findViewById(R.id.l2);

        Intent i=getIntent();
        float cartValue=i.getFloatExtra("CartValue",0);
        String cartItems=i.getStringExtra("CartItems");
        String s[]=cartItems.split(" ");
        s = new HashSet<String>(Arrays.asList(s)).toArray(new String[0]);
        for(int j=0;j<s.length;j++){

          sb.append(s[j]+" ");
        }


        List asList = Arrays.asList(cartItems);
        Set<String> mySet = new HashSet<String>(asList);
        for(String a: mySet){

            sb1.append(a+ " " + Collections.frequency(asList,a)+"\n");

        }

        Toast.makeText(this,""+cartValue+"\t"+sb1,Toast.LENGTH_LONG).show();

    }
}
