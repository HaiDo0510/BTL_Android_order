package com.example.ordersqlite;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.OrderViewHolder> {

    private List<Order> list;
    private Context context;
    private SqliteOrderHelper sqliteOrderHelper;

    public RecyclerViewAdapter() { list = new ArrayList<>(); }

    public void setOrders(List<Order> list){ this.list=list; }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.order_item,parent,false);
        return new OrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order s=list.get(position);

        holder.txtIdName.setText("Name: "+s.getName());
        holder.txtDate.setText("Date: " + s.getDate());
        holder.txtPrice.setText("Price: " + s.getPrice());
        holder.txtAddress.setText("Address: " + s.getAddress());
        holder.txtStatus.setText("Status: " + s.getStatus());
        if(holder.txtStatus.getText().equals("Đã giao")){
            holder.btnConfirm.setVisibility(View.GONE);
        }

        holder.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = v.getContext();
                sqliteOrderHelper = new SqliteOrderHelper(context);
                Order order = new Order(s.getId(),s.getName(),s.getDate(),s.getPrice(),s.getAddress(),"Đã giao");
                sqliteOrderHelper.update(order);
                Intent intent = new Intent(context , ConfirmActivity.class);
                context.startActivity(intent);
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = v.getContext();
                Intent intent = new Intent(context , UpdateOrderActivity.class);
                intent.putExtra("order", s);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list!=null)
            return list.size();
        else
            return 0;
    }

    class OrderViewHolder extends RecyclerView.ViewHolder{
        private TextView txtIdName, txtDate, txtPrice, txtAddress, txtStatus;
        private CardView cardView;
        private Button btnConfirm;
        public OrderViewHolder(@NonNull View v) {
            super(v);
            txtIdName = v.findViewById(R.id.idName);
            txtPrice=v.findViewById(R.id.price);
            txtDate=v.findViewById(R.id.date);
            txtAddress = v.findViewById(R.id.address);
            txtStatus = v.findViewById(R.id.status);
            cardView = v.findViewById(R.id.cardView);
            btnConfirm = v.findViewById(R.id.btnConfirm);
        }
    }
}
