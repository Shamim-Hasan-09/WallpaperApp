package com.example.wallpaper_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ImageModel> modelClassList;
    private RecyclerView recyclerView;
    Adapter adapter;
    CardView nature,bus,car,train,trending;
    EditText editText;
    ImageButton search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        recyclerView=findViewById(R.id.recyclerview);
        nature=findViewById(R.id.nature);
        car=findViewById(R.id.car);
        bus=findViewById(R.id.bus);
        train=findViewById(R.id.train);
        trending=findViewById(R.id.trending);
        editText=findViewById(R.id.edittext);
        search=findViewById(R.id.search);

        modelClassList=new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);
        adapter=new Adapter(getApplicationContext(),modelClassList);
        recyclerView.setAdapter(adapter);
        findphotos();


        nature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="nature";
                getsearchimage(query);
            }
        });

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="car";
                getsearchimage(query);
            }
        });
       train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="train";
                getsearchimage(query);
            }
        });
        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="bus";
                getsearchimage(query);
            }
        });

        trending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findphotos();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String query=editText.getText().toString().trim().toLowerCase();
               if (query.isEmpty())
               {
                   Toast.makeText(getApplicationContext(),"Enter Something",Toast.LENGTH_SHORT).show();

               }
               else
               {
                   getsearchimage(query);
               }
            }
        });











    }

    private void getsearchimage(String query) {


       ApiUtilities.getApiInterface().getSearchImage(query,1,88).enqueue(new Callback<SearchModel>() {
           @Override
           public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {

               modelClassList.clear();
               if (response.isSuccessful())
               {
                   modelClassList.addAll(response.body().getPhotos());
                   adapter.notifyDataSetChanged();
               }
               else
               {
                   Toast.makeText(getApplicationContext(),"Not able to get",Toast.LENGTH_SHORT).show();

               }

           }

           @Override
           public void onFailure(Call<SearchModel> call, Throwable t) {

           }
       });


    }

    private void findphotos() {

        ApiUtilities.getApiInterface().getImage(1,88).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {

                if (response.isSuccessful())
                {
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Not able to get",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });
    }
}