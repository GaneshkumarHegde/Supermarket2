package com.example.android.supermarket;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.*;
import android.widget.EditText;
import android.widget.Toast;

public class Buy extends AppCompatActivity {
String m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
    }
   public void placeOrder(android.view.View view){
       //EditText e1=(EditText)findViewById(R.id.mobile);
       EditText e2=(EditText)findViewById(R.id.mail);
       //String phoneNumber=e1.getText().toString();
       String email=e2.getText().toString();
       Intent i=getIntent();
       m=i.getStringExtra("msg");

       //Toast.makeText(this,m,Toast.LENGTH_SHORT).show();
       Intent emailIntent = new Intent( android.content.Intent.ACTION_SEND);

       emailIntent.setType("plain/text");

       emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
               new String[] { email });

       emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
               "Your order placed");

       emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
               m);

       startActivity(Intent.createChooser(
               emailIntent, "Send mail..."));
       Toast.makeText(this,"Order has been placed successfully",Toast.LENGTH_SHORT).show();

   }
}
