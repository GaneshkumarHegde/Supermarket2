package com.example.android.supermarket;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.*;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Modify extends AppCompatActivity {
EditText e1;
    String name;EditText e2;RadioButton r1;
    RadioButton r2;
String type;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_modify);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Products");

    }
    public void  onSubmit(View view){
        e1=(EditText)findViewById(R.id.modifyItem);
        name=e1.getText().toString();
        Toast.makeText(this,"Modify the Item details now",Toast.LENGTH_SHORT).show();
        r1=(RadioButton)findViewById(R.id.r1);
        r2=(RadioButton)findViewById(R.id.r2);
        if(r1.isChecked()){
            type=r1.getText().toString();
        }
        else{
            type=r2.getText().toString();

        }
    }
    public void abc(android.view.View v){
        Intent intent=new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
    public  void changePrice(View view){
        if(TextUtils.isEmpty(e1.getText().toString())){
            e1.setError("Required");

        }
        else{
            mDatabase = FirebaseDatabase.getInstance().getReference().child(type).child(name);

            e2=new EditText(Modify.this);
            e2.setHint("Enter new value");
            final AlertDialog.Builder alertDialog=new AlertDialog.Builder(Modify.this);
            alertDialog.setTitle("Modification");
            alertDialog.setMessage("Set new Value");
            alertDialog.setView(e2);
alertDialog.setCancelable(true);
            alertDialog.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //Product p = dataSnapshot.getValue(Product.class);
                            dataSnapshot.getRef().child("price").setValue(Float.parseFloat(e2.getText().toString()));
Toast.makeText(Modify.this,"Modified Successfully",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
            });                    alertDialog.create().show();


        }
    }
    public  void changeDiscount(View view){
        if(TextUtils.isEmpty(e1.getText().toString())){
            e1.setError("Required");

        }else{
            mDatabase = FirebaseDatabase.getInstance().getReference().child("Products").child(name);
            e2=new EditText(Modify.this);
            e2.setHint("Enter new value");
            final AlertDialog.Builder alertDialog=new AlertDialog.Builder(Modify.this);
            alertDialog.setTitle("Modification");
            alertDialog.setMessage("Set new Value");
            alertDialog.setView(e2);
            alertDialog.setCancelable(true);
            alertDialog.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //Product p = dataSnapshot.getValue(Product.class);
                            dataSnapshot.getRef().child("discount").setValue(Float.parseFloat(e2.getText().toString()));
                            Toast.makeText(Modify.this,"Modified Successfully",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
            });                    alertDialog.create().show();


        }
    }
    public  void delete(View view){
        if(TextUtils.isEmpty(e1.getText().toString())){
            e1.setError("Required");

        }else{
            mDatabase = FirebaseDatabase.getInstance().getReference().child("Products").child(name);
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    dataSnapshot.getRef().setValue(null);
                    Toast.makeText(Modify.this,"Deleted Successfully",Toast.LENGTH_SHORT).show();


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            }


        }
    }

