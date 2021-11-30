package com.example.tugasrestofinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class FavoriteList extends AppCompatActivity {

    ImageButton btn_back;
    RecyclerView rv_list;

    FavoriteAdapter adapter;
    List<RestoModel> restaurantlist;
    Realm realm;
    RealmHelper realmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);
        btn_back = findViewById(R.id.imgbtn_back);
        // recycler view
        rv_list = findViewById(R.id.rv_list);

        rv_list.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        restaurantlist = new ArrayList<>();

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        realm = Realm.getInstance(configuration);

        realmHelper = new RealmHelper(realm);
        restaurantlist = new ArrayList<>();

        restaurantlist = realmHelper.getAllRestaurant();

        for (int i = 0; i <restaurantlist.size() ; i++) {
            Log.d("gambarFav", "onCreate: "+restaurantlist.get(i).getPictureId());
        }

        adapter = new FavoriteAdapter(restaurantlist, getApplicationContext());
        rv_list.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    finish();
            }
        });
    }
}