package com.example.android.supermarket;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button admin,customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
public void onClickAdmin(View view){
    adminLogin c=new adminLogin();
    FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.fragment_container,c);
    transaction.addToBackStack(null);
    transaction.commit();

}
    public  void clickCustomer(View view){
        CustomerLogin c=new CustomerLogin();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,c);
        transaction.addToBackStack(null);
        transaction.commit();

    }




public  void customerLogin(View view){
    Intent myIntent=new Intent(this,Main3Activity.class);
    startActivity(myIntent);
}
    public void createNewCustomer(View view){
        Intent i=new Intent(this,CustomerSignIn.class);
        startActivity(i);

    }

}


