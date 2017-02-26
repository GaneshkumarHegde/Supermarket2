package com.example.android.supermarket;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CustomerSignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customersignin);
    }
    public void signup(View view){
        EditText e1=(EditText)findViewById(R.id.name);
        EditText e2=(EditText)findViewById(R.id.email);
        EditText e3=(EditText)findViewById(R.id.pswd);
        EditText e4=(EditText)findViewById(R.id.cpswd);
        String Name= e1.getText().toString();
        String email= e2.getText().toString();
        String pswd= e3.getText().toString();
        String cpswd= e4.getText().toString();
String a=pswd;String b=cpswd;
if( a == b )     Toast.makeText(this,"Successful",Toast.LENGTH_SHORT).show();

else{

    AlertDialog.Builder builder=new AlertDialog.Builder(this);
    builder.setCancelable(false);
    builder.setTitle("Note") ;
    builder.setMessage("Passwords don't match");
    builder.setPositiveButton("Ok",new DialogInterface.OnClickListener()
    {
        @Override
        public void onClick(DialogInterface dialog,int which){
            //  finish();
        }
    });


   // builder.create().show();
  //  Toast.makeText(this,pswd+"\t"+cpswd,Toast.LENGTH_SHORT).show();
    Toast.makeText(this,"Successful",Toast.LENGTH_SHORT).show();
}
    }
    public void back(View view){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
