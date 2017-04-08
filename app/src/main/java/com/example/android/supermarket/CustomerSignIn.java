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
import android.text.TextUtils;
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

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();
        if(TextUtils.isEmpty(e1.getText().toString())){
            e1.setError("Required");
            progressDialog.dismiss();

        }
        if(TextUtils.isEmpty(e2.getText().toString())){
            e2.setError("Required");
            progressDialog.dismiss();

        }
        if(TextUtils.isEmpty(e3.getText().toString())){
            e3.setError("Required");
            progressDialog.dismiss();

        }
        if(TextUtils.isEmpty(e4.getText().toString())){
            e4.setError("Required");
            progressDialog.dismiss();

        }
        String Name= e1.getText().toString();
        String email= e2.getText().toString();
        String pswd= e3.getText().toString();
        String cpswd= e4.getText().toString();
       if(!((TextUtils.isEmpty(e4.getText().toString()))||(TextUtils.isEmpty(e3.getText().toString())))) {
           if (!pswd.equals(cpswd)) {
               AlertDialog.Builder builder = new AlertDialog.Builder(this);
               builder.setCancelable(false);
               builder.setTitle("Note");
               builder.setMessage("Passwords don't match");
               builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       //  finish();
                   }
               });


               builder.create().show();
               progressDialog.dismiss();

           }
       }
        if(!((TextUtils.isEmpty(e1.getText().toString()))||(TextUtils.isEmpty(e2.getText().toString())) ||(TextUtils.isEmpty(e3.getText().toString())) ||(TextUtils.isEmpty(e4.getText().toString()))   )) {

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
