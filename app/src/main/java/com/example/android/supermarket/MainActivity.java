package com.example.android.supermarket;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
   // SQLiteDatabase db;
   private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    Button admin,customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // db = openOrCreateDatabase("CustomerDB", Context.MODE_WORLD_WRITEABLE, null);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


    }
public void onClickAdmin(View view){
    adminLogin c=new adminLogin();
    FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.fragment_container,c);
    transaction.addToBackStack(null);
    transaction.commit();

}
    public  void clickCustomer(View view){
    /*     CustomerLogin c=new CustomerLogin();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,c);
        transaction.addToBackStack(null);
        transaction.commit();
*/
      Intent intent=new Intent(this,Main6Activity.class);
startActivity(intent);
    }




public  void customerLogin(View view){
    EditText e1=(EditText)findViewById(R.id.cName);
    EditText e2=(EditText)findViewById(R.id.cPassword);
    String name=e1.getText().toString();
    StringBuffer pswd=new StringBuffer();
pswd.append(e2.getText().toString());
    progressDialog.setMessage("Please Wait...");
    progressDialog.show();
    if(e1.getText().toString().trim().length()==0 ){
e1.setError("Required");                        progressDialog.dismiss();

    }
    if(e2.getText().toString().trim().length()==0 ){
        e2.setError("Required");                        progressDialog.dismiss();

    }
    if(!((e2.getText().toString().trim().length()==0)   ||(e1.getText().toString().trim().length()==0))  ){

        firebaseAuth.signInWithEmailAndPassword(name, pswd.toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //if the task is successfull
                        if(task.isSuccessful()){
                            //start the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), Main5Activity.class));
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Invalid Email or Password",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();

                }
                });



}}
    public void createNewCustomer(View view){
        Intent i=new Intent(this,CustomerSignIn.class);
        startActivity(i);

    }
public void exit(View view){
    finishAffinity();
    /*Intent intent = new Intent(getApplicationContext(), Welcome.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    intent.putExtra("EXIT", true);
    startActivity(intent);*/
}
    @Override
    public void onBackPressed(){
        finishAffinity();

    }
    public void adminLogin(View view) {
        EditText e2 = (EditText) findViewById(R.id.Password1);
        String pswd = e2.getText().toString();
        EditText e1 = (EditText) findViewById(R.id.Name1);
        String name = e1.getText().toString();
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        if (e1.getText().toString().trim().length() == 0) {
            e1.setError("Required");
            progressDialog.dismiss();

        }
        if (e2.getText().toString().trim().length() == 0) {
            e2.setError("Required");
            progressDialog.dismiss();

        }
        if (!((e2.getText().toString().trim().length() == 0) || (e1.getText().toString().trim().length() == 0))) {


            firebaseAuth.signInWithEmailAndPassword(name, pswd.toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //if the task is successfull
                            if (task.isSuccessful()) {
                                //start the profile activity
                                finish();
                                startActivity(new Intent(getApplicationContext(), Main2Activity.class));
                            } else {
                                Toast.makeText(MainActivity.this, "Invalid Email or Password", Toast.LENGTH_LONG).show();
                            }
                            progressDialog.dismiss();

                        }
                    });
        }
    }}



