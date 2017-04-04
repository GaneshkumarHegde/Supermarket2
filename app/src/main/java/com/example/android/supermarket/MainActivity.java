package com.example.android.supermarket;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
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

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;

    private FirebaseAuth firebaseAuth;

    Button admin,customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = openOrCreateDatabase("CustomerDB", Context.MODE_WORLD_WRITEABLE, null);
        firebaseAuth=FirebaseAuth.getInstance();


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
    EditText e1=(EditText)findViewById(R.id.cName);
    EditText e2=(EditText)findViewById(R.id.cPassword);
    String name=e1.getText().toString();
    StringBuffer pswd=new StringBuffer();
pswd.append(e2.getText().toString());

    if(e1.getText().toString().trim().length()==0 || e2.getText().toString().trim().length()==0 ){

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
    }
    else{

    Cursor c=db.rawQuery("SELECT pswd FROM customer WHERE name='"+name+"' ", null);

    if(c.getCount()==0)
    { Toast.makeText(this,"Username does not exist", Toast.LENGTH_LONG).show();
        return;
    }
StringBuffer buffer=new StringBuffer();

    while(c.moveToNext()){
        buffer.append(c.getString(0));


    }
if( buffer.toString().equals(pswd.toString()) ) {

    Intent myIntent = new Intent(this, Main5Activity.class);
    startActivity(myIntent);
}
else{
    Toast.makeText(this,"Invalid Password\t"+buffer+"\t"+pswd,Toast.LENGTH_SHORT).show();
}



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
    public void adminLogin(View view){
        EditText e2=(EditText)findViewById(R.id.Password1);
        String pswd=e2.getText().toString();
        EditText e1=(EditText)findViewById(R.id.Name1);
        String name=e1.getText().toString();
       // Toast.makeText(this,name+"\t"+pswd,Toast.LENGTH_SHORT).show();

        if(name.equals("Ganesh") && pswd.equals("Ganesh")) {
            Intent myIntent = new Intent(this, Main2Activity.class);
            startActivity(myIntent);
        }
        else{
             Toast.makeText(this,"Invalid Name or Password",Toast.LENGTH_SHORT).show();

        }
    }
}


