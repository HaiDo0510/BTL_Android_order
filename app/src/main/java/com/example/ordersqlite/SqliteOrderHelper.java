package com.example.ordersqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SqliteOrderHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="OrdersDB.db";
    private static final int DATABSE_VERSION=1;

    public SqliteOrderHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE `orders`(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "date TEXT, " +
                "price REAL," +
                "address TEXT," +
                "status TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //add
    public void addOrder(Order order){
        String sql="INSERT INTO `orders`(name, date, price, address, status) VALUES(?,?,?,?,?)";
        String[] args={order.getName(),
                order.getDate(),
                Double.toString(order.getPrice()),
                order.getAddress(),
                order.getStatus()
                };
        SQLiteDatabase statement=getWritableDatabase();
        statement.execSQL(sql,args);
    }
    // get all
    public List<Order> getAll(){
        List<Order> list=new ArrayList<>();
        SQLiteDatabase statement=getReadableDatabase();
        Cursor rs=statement.query("`orders`",null,
                null,null,null,
                null,null);
        while((rs!=null && rs.moveToNext())){
            int id=rs.getInt(0);
            String name=rs.getString(1);
            String date = rs.getString(2);
            double price = rs.getDouble(3);
            String address = rs.getString(4);
            String status = rs.getString(5);
            list.add(new Order(id,name,date, price, address, status));
        }
        return list;
    }

    public List<Order> getByName(String name) {
        String sql = "name like ?";
        String[] args = { "%" + name + "%" };
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("`orders`", null, sql, args, null, null, null);
        List<Order> orders = new ArrayList<>();
        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String itemName = cursor.getString(1);
            String date = cursor.getString(2);
            double price = cursor.getDouble(3);
            String address = cursor.getString(4);
            String status = cursor.getString(5);
            Order order = new Order(id, itemName, date, price, address, status);
            orders.add(order);
        }
        return orders;
    }

    //getBy id
    public Order getStudentById(int id){
        String whereClause="id =?";
        String[] whereArgs={String.valueOf(id)};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query("`orders`",null,whereClause,
                whereArgs,null,null,null);
        if(rs.moveToNext()){
            String itemName = rs.getString(1);
            String date = rs.getString(2);
            double price = rs.getDouble(3);
            String address = rs.getString(4);
            String status = rs.getString(5);
            return new Order(id, itemName, date, price, address, status);
        }
        return null;
    }

    //update
    public int update(Order order){
        ContentValues v=new ContentValues();
        v.put("name",order.getName());
        v.put("date",order.getDate());
        v.put("price",order.getPrice());
        v.put("address",order.getAddress());
        v.put("status",order.getStatus());

        String whereClause="id=?";
        String[] whereArgs={String.valueOf(order.getId())};
        SQLiteDatabase st=getWritableDatabase();
        getAll();
        return st.update("`orders`",v,whereClause,whereArgs);
    }
    //delete
    public int delete(int id){
        String whereClause="id=?";
        String[] whereArgs={String.valueOf(id)};
        SQLiteDatabase st=getWritableDatabase();
        return st.delete("`orders`",whereClause,whereArgs);
    }
}
