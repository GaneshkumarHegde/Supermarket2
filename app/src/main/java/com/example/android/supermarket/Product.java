package com.example.android.supermarket;


/**
 * Created by WINDOWS on 4/5/2017.
 */

public class Product {
    //name and address string
    private String name;
    private int number,quantity;
private float price,discount;
   // private HashMap<String,Object> getName,getNumber,getQuantity,getPrice,getDiscount;
    public Product() {
    }

    public Product(String name,int number,int quantity,float discount,float price) {
        this.name = name;
        this.number = number;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }
    public String getName() {
        return name;
    }



    public int getNumber() {
        return number;
    }



    public int getQuantity(){

        return quantity;
    }

    public float getPrice(){
        return price;
    }

    public float getDiscount(){
        return discount;
    }

    public void decreaseQuantity(){
        this.quantity=this.quantity-1;
        Product product=new Product(name,number,quantity,discount,price);
    }
    }
