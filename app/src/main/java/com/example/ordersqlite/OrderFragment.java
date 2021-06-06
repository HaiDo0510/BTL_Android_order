package com.example.ordersqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class OrderFragment extends Fragment {


    private FloatingActionButton btnShowAddForm;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private SqliteOrderHelper sqliteOrderHelper;
    private SearchView searchView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_order,container,false);
        btnShowAddForm = view.findViewById(R.id.fab);
        recyclerView = view.findViewById(R.id.recyclerView);
        searchView = view.findViewById(R.id.searchView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerViewAdapter();
        sqliteOrderHelper = new SqliteOrderHelper(getActivity());

        List<Order> list = sqliteOrderHelper.getAll();
        adapter.setOrders(list);
        recyclerView.setAdapter(adapter);

        btnShowAddForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddOrderActivity.class);
                startActivity(intent);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Order> list=sqliteOrderHelper.getByName(newText);
                adapter.setOrders(list);
                recyclerView.setAdapter(adapter);
                return true;
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        List<Order> list = sqliteOrderHelper.getAll();
        adapter.setOrders(list);
        recyclerView.setAdapter(adapter);
        super.onResume();
    }
}