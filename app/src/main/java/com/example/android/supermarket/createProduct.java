package com.example.android.supermarket;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.data;
import static android.widget.Toast.LENGTH_SHORT;
import static java.sql.Types.NULL;

public class createProduct extends AppCompatActivity {
    SQLiteDatabase db;
    ImageView imageview;
    public int pno,pprice,pdiscount,q;
    public String pname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);
        db = openOrCreateDatabase("ProductsDB", Context.MODE_WORLD_WRITEABLE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS products(pname VARCHAR,pnumber VARCHAR,quantity VARCHAR,price VARCHAR,discount VARCHAR);");
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
        if(e1.getText().toString().trim().length()==0 ||e2.getText().toString().trim().length()==0||e3.getText().toString().trim().length()==0||e4.getText().toString().trim().length()==0)
        {
            // Toast.makeText(this,"Enter all fields",LENGTH_SHORT).show();
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
        else {
            q=Integer.parseInt(e5.getText().toString());
            pname = e1.getText().toString();
            pno = Integer.parseInt(e2.getText().toString());
            pprice = Integer.parseInt(e3.getText().toString());
            pdiscount = Integer.parseInt(e4.getText().toString());
            db.execSQL("INSERT INTO products VALUES('"+pname+"','"+pno+"','"+pprice+"','"+q+"','"+pdiscount+"');");

            Toast.makeText(this, "Product with product number " + pno + "  created Successfully  ", LENGTH_SHORT).show();


        }

    }
    public void gallery(View view){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto , 1);
    }

}

