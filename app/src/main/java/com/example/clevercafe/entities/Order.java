package com.example.clevercafe.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

/**
 * Created by Chudofom on 24.07.17.
 */
@Entity(tableName = "orders")
public class Order {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public Double sum;

    public Order() {
    }

    @Ignore
    public Order(long id, ArrayList<Product> products) {
        this.id = id;
        this.products = products;
    }

    @Ignore
    public ArrayList<Product> products;
}
