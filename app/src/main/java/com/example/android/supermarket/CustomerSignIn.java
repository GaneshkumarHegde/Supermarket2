package com.example.android.supermarket;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CustomerSignIn extends AppCompatActivity {
//SQLiteDatabase db;
private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customersignin);
        progressDialog = new ProgressDialog(this);
firebaseAuth=FirebaseAuth.getInstance();

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
        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();
        if(e1.getText().toString().trim().length()==0 ||e2.getText().toString().trim().length()==0||e3.getText().toString().trim().length()==0||e4.getText().toString().trim().length()==0) {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle("Error") ;
            builder.setMessage("Enter all the fields");
            builder.setPositiveButton("Ok",new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog,int which){
                    //  finish();
                }
            });


            builder.create().show();
            progressDialog.dismiss();

        }
       else if(!pswd.equals(cpswd)){
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


            builder.create().show();
            progressDialog.dismiss();

        }

        else{

            firebaseAuth.createUserWithEmailAndPassword(email, pswd)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //checking if success
                            if(task.isSuccessful()){
                                //display some message here
                                Toast.makeText(CustomerSignIn.this,"Successfully registered",Toast.LENGTH_LONG).show();
                            }else{
                                //display some message here
                                Toast.makeText(CustomerSignIn.this,"Registration Error",Toast.LENGTH_LONG).show();
                            }                        progressDialog.dismiss();
                        }
                    });
        }
    }
    public void back(View view){


        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
