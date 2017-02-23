package com.example.android.supermarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class displayProducts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_products);
    }
public void clickBack(View view){
    Intent i=new Intent(this,Main2Activity.class);
    startActivity(i);
}
}
