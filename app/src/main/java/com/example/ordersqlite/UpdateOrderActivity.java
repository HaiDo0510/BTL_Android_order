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

public class UpdateOrderActivity extends AppCompatActivity {

    private EditText txtId, txtName, txtDate, txtPrice,txtAddress;
    private Button btnUpdate, btnCancel, btnDate, btnDelete;
    private SqliteOrderHelper sqliteOrderHelper;
    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_order);
        initView();
        sqliteOrderHelper = new SqliteOrderHelper(this);

        Intent intent = getIntent();
        if(intent != null) {
            order = (Order) intent.getSerializableExtra("order");
            Integer id = order.getId();
            String name = order.getName();
            String date = order.getDate();
            Double price = order.getPrice();
            String address = order.getAddress();
            txtId.setText(id.toString());
            txtName.setText(name);
            txtDate.setText(date);
            txtPrice.setText(price.toString());
            txtAddress.setText(address);
        }
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    int id=Integer.parseInt(txtId.getText().toString());
                    String n=txtName.getText().toString();
                    String date = txtDate.getText().toString();
                    Double price = Double.parseDouble(txtPrice.getText().toString());
                    String address = txtAddress.getText().toString();
                    Order s=new Order(id,n, date, price, address, "Đang giao");
                    sqliteOrderHelper.update(s);
                    finish();
                }catch(NumberFormatException e){
                    System.out.println(e);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=Integer.parseInt(txtId.getText().toString());
                sqliteOrderHelper.delete(id);
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
                DatePickerDialog dialog = new DatePickerDialog(UpdateOrderActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        txtId = findViewById(R.id.txtId);
        txtName = findViewById(R.id.txtName);
        txtDate = findViewById(R.id.txtDate);
        txtPrice = findViewById(R.id.txtPrice);
        txtAddress = findViewById(R.id.txtAddress);
        btnUpdate = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        btnDate = findViewById(R.id.btnDate);
        btnDelete = findViewById(R.id.btnDelete);
    }
}