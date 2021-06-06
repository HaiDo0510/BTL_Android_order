package com.example.ordersqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;

import java.util.Calendar;

public class AddOrderActivity extends AppCompatActivity {

    private EditText txtName, txtDate, txtPrice, txtAddress;
    private Button btnAdd, btnCancel, btnDate;
    private SqliteOrderHelper sqliteOrderHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);
        initView();
        sqliteOrderHelper = new SqliteOrderHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order s = new Order();
                String name=txtName.getText().toString();
                String date = txtDate.getText().toString();
                double price = Double.parseDouble(txtPrice.getText().toString());
                String address = txtAddress.getText().toString();
                s.setName(name);
                s.setDate(date);
                s.setPrice(price);
                s.setAddress(address);
                s.setStatus("Đang giao ....");
                sqliteOrderHelper.addOrder(s);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(AddOrderActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                dialog.show();
            }
        });
    }

    private void initView() {
        txtName = findViewById(R.id.txtName);
        txtDate = findViewById(R.id.txtDate);
        txtPrice = findViewById(R.id.txtPrice);
        txtAddress = findViewById(R.id.txtAddress);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        btnDate = findViewById(R.id.btnDate);
    }
}