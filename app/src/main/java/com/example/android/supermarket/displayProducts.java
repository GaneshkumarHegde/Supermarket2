package com.example.android.supermarket;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewManager;
import android.widget.ImageButton;

public class displayProducts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_products);
ImageButton i=(ImageButton)findViewById(R.id.i1);
        registerForContextMenu(i);
    }
public void clickBack(View view){
    Intent i=new Intent(this,Main2Activity.class);
    startActivity(i);
}
    @Override
    public  void onCreateContextMenu(ContextMenu menu,View v,ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        menu.setHeaderTitle("Select the action");
        menu.add(0,v.getId(),0,"Modify");
        menu.add(0,v.getId(),0,"View");
        menu.add(0,v.getId(),0,"Delete");


    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()=="Modify"){
            Intent i=new Intent(this, Modify.class);
            startActivity(i);
        }
        if(item.getTitle()=="View"){
            Intent i=new Intent(this, com.example.android.supermarket.View.class);
            startActivity(i);

        }
        if(item.getTitle()=="Delete"){
            final AlertDialog.Builder a=new AlertDialog.Builder(this);
            a.setMessage("Are you sure you want to delete this?");
            a.setNegativeButton("No",new DialogInterface.OnClickListener()
            {
                @Override
               public void onClick(DialogInterface dialog,int which){

                }

            });
            a.setPositiveButton("Yes",new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog,int which){
                    ImageButton i=(ImageButton)findViewById(R.id.i1);
                    ((ViewManager)i.getParent()).removeView(i);
                }

            });
            AlertDialog alertDialog=a.create();
            alertDialog.show();
             }
        return true;
    }
}
