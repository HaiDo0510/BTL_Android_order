package com.example.ordersqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ConfirmActivity extends AppCompatActivity {
    private SqliteOrderHelper sqliteOrderHelper;
    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        finish();
    }
}