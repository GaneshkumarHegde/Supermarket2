package com.example.android.supermarket;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
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
private static final int CAMERA_REQUEST=123;
    public int pno,pprice,pdiscount;
    public String pname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);
    }

    public void openCamera(View view){
        Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,CAMERA_REQUEST);

    }
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode==CAMERA_REQUEST && resultCode== Activity.RESULT_OK){
            Bitmap photo=(Bitmap)data.getExtras().get("data");
            ImageView i=(ImageView) findViewById(R.id.imageView);
            i.setImageBitmap(photo);
        }
    }
    public void clickBack(View view){
        Intent i=new Intent(this,Main2Activity.class);
        startActivity(i);
    }
    public void create(View view){
try{
        EditText e1=(EditText)findViewById(R.id.ProductName);
        EditText e2=(EditText)findViewById(R.id.ProductNo);
        EditText e3=(EditText)findViewById(R.id.ProductPrice);
        EditText e4=(EditText)findViewById(R.id.ProductDiscount);

         pname=e1.getText().toString();
         pno=Integer.parseInt(e2.getText().toString());
         pprice=Integer.parseInt(e3.getText().toString());
         pdiscount=Integer.parseInt(e4.getText().toString());
    TextView textView=(TextView)findViewById(R.id.textView);
    textView.setText("Product Name:"+pname+"\nProduct No:"+pno+"\nProduct Price:"+pprice);

        //Toast.makeText(this,"Product created Successfully with product number "+pno,LENGTH_SHORT).show();


    }
    catch(Exception e){
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
    }

}

