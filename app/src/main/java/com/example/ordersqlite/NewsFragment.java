package com.example.ordersqlite;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class NewsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_news,container,false);
        // Khởi tạo RecyclerView.
        final RecyclerView rvUsers = (RecyclerView) view.findViewById(R.id.rv_users);
        rvUsers.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Khởi tạo OkHttpClient để lấy dữ liệu.
        OkHttpClient client = new OkHttpClient();

        // Khởi tạo Moshi adapter để biến đổi json sang model java (ở đây là User)
        Moshi moshi = new Moshi.Builder().build();
        Type usersType = Types.newParameterizedType(List.class, User.class);
        final JsonAdapter<List<User>> jsonAdapter = moshi.adapter(usersType);

        // Tạo request lên server.
        Request request = new Request.Builder()
                .url("https://api.github.com/users")
                .build();

        // Thực thi request.
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                // Lấy thông tin JSON trả về. Bạn có thể log lại biến json này để xem nó như thế nào.
                String json = response.body().string();
                final List<User> users = jsonAdapter.fromJson(json);
                Random rand = new Random();
                for (User i: users){
                    i.id += 941 + rand.nextInt(900)*100000;
                    System.out.println(i.login);
                }
                // Cho hiển thị lên RecyclerView.
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rvUsers.setAdapter(new UserAdapter(users, getActivity()));
                    }
                });
            }
        });

        return view;
    }
}