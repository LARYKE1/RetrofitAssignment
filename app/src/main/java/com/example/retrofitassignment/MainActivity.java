package com.example.retrofitassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Button btn;
    RecyclerView rv;
    recyclerAdapter adapter;
    String url="https://jsonplaceholder.typicode.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=findViewById(R.id.btnPress);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api api = retrofit.create(com.example.retrofitassignment.api.class);
        Call<List<model>> call = api.getProducts();

       btn.setOnClickListener(view -> {
           call.enqueue(new Callback<List<model>>() {
               @Override
               public void onResponse(Call<List<model>> call, Response<List<model>> response) {
                   getData(response.body());

               }

               @Override
               public void onFailure(Call<List<model>> call, Throwable t) {

               }
           });
       });
    }

    private void getData(List<model>data){
        rv=findViewById(R.id.recyclerView1);
        adapter=new recyclerAdapter(rv,data);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(MainActivity.this);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }
}