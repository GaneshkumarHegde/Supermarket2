package com.example.android.supermarket;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.R.attr.data;
import static android.widget.Toast.LENGTH_SHORT;
import static com.example.android.supermarket.R.attr.title;
import static java.sql.Types.NULL;

public class createProduct extends AppCompatActivity {
    //SQLiteDatabase db;
    private DatabaseReference mDatabase;
RadioButton r1;
    RadioButton r2;
    ImageView imageview;
    public int pno,pprice,pdiscount,q;
    public String pname;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);
        mDatabase = FirebaseDatabase.getInstance().getReference();
      //  db = openOrCreateDatabase("ProductsDB", Context.MODE_WORLD_WRITEABLE, null);
      //  db.execSQL("CREATE TABLE IF NOT EXISTS products(pname VARCHAR,pnumber VARCHAR,quantity VARCHAR,price VARCHAR,discount VARCHAR);");
    }

    public void openCamera(View view){
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, 0);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
         imageview=(ImageView)findViewById(R.id.imageView);
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    imageview.setImageURI(selectedImage);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    imageview.setImageURI(selectedImage);
                }
                break;
        }
    }
    public void clickBack(View view){
        Intent i=new Intent(this,Main2Activity.class);
        startActivity(i);
    }
    public void create(View view){
EditText e5=(EditText)findViewById(R.id.quantity);
        EditText e1=(EditText)findViewById(R.id.ProductName);
        EditText e2=(EditText)findViewById(R.id.ProductNo);
        EditText e3=(EditText)findViewById(R.id.ProductPrice);
        EditText e4=(EditText)findViewById(R.id.ProductDiscount);
        r1=(RadioButton)findViewById(R.id.starter);
        r2=(RadioButton)findViewById(R.id.mainCourse);
        if(r1.isChecked()){
            type=r1.getText().toString();
        }
        else{
            type=r2.getText().toString();

        }
        if(TextUtils.isEmpty(e1.getText().toString())){
            e1.setError("Required");

        }
        Toast.makeText(this,""+type,LENGTH_SHORT).show();
        if(TextUtils.isEmpty(e2.getText().toString())){
            e2.setError("Required");

        }
        if(TextUtils.isEmpty(e3.getText().toString())){
            e3.setError("Required");

        }
        if(TextUtils.isEmpty(e4.getText().toString())){
            e4.setError("Required");

        }
        if(TextUtils.isEmpty(e5.getText().toString())){
            e5.setError("Required");

        }
        if(!((TextUtils.isEmpty(e1.getText().toString()))||(TextUtils.isEmpty(e2.getText().toString())) ||(TextUtils.isEmpty(e3.getText().toString())) ||(TextUtils.isEmpty(e4.getText().toString())) ||  (TextUtils.isEmpty(e5.getText().toString()))  )) {
            q=Integer.parseInt(e5.getText().toString());
            pname = e1.getText().toString();
            pno = Integer.parseInt(e2.getText().toString());
            pprice = Integer.parseInt(e3.getText().toString());
            pdiscount = Integer.parseInt(e4.getText().toString());
            Product product=new Product(pname,pno,q,pdiscount,pprice);

           // String productId = mDatabase.push().getKey();
            mDatabase.child("Products").child(pname).setValue(product).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(createProduct.this, "Product  created Successfully  ", LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(createProduct.this, "Error", LENGTH_SHORT).show();

                    }
                }
            });



    }}
    public void gallery(View view){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto , 1);
    }

}

