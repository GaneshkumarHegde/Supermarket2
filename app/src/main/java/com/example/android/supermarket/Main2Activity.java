package com.example.android.supermarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void clickBack(View view){
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);

    }
    public  void createProduct(View view){
        Intent i=new Intent(this,createProduct.class);
        startActivity(i);
    }
    public void modify(View view){
        Intent i=new Intent(this,Modify.class);
        startActivity(i);
    }
    public void viewProduct(View view){
        Intent i=new Intent(this,ViewProductMenu.class);
        startActivity(i);
    }
    public void offers(View view){
        Intent intent=new Intent(this,Offers.class);
        startActivity(intent);
    }
}
