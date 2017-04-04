package com.example.android.supermarket;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewProductMenu extends AppCompatActivity {
SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product_menu);
        db = openOrCreateDatabase("ProductsDB", Context.MODE_WORLD_WRITEABLE, null);
        Cursor c=db.rawQuery("SELECT * FROM products", null);
        if(c.getCount()==0)
        { Toast.makeText(this,"No records found", Toast.LENGTH_LONG).show();
            return;
        }             StringBuffer buffer=new StringBuffer();String i;
        while(c.moveToNext())
        {
            buffer.append("Products name: "+c.getString(0)+"\n");
            buffer.append("Product Number: "+c.getString(1)+"\n");
            buffer.append("Price: "+c.getString(2)+"\n");
            buffer.append("Quantity: "+c.getString(3)+"\n");
            buffer.append("Discount: "+c.getString(4)+"\n\n");


        }
        TextView textView=(TextView)findViewById(R.id.printProducts);
        textView.setText(buffer);

    }
}
