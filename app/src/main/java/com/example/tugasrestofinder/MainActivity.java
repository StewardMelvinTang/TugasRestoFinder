package com.example.tugasrestofinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.LinkAddress;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RestoAdapter adapter;
    ImageButton imgbtn_profile;
    ArrayList<RestoModel> restolist;
    TextView txt_discover;
    RecyclerView recyclerView;
    EditText et_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txt_discover = findViewById(R.id.txt_discoveryour);
        imgbtn_profile = findViewById(R.id.imgbtn_profile);
        recyclerView = findViewById(R.id.rv_list);
        et_search = findViewById(R.id.tb_search);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        restolist = new ArrayList<>();

        adapter = new RestoAdapter(restolist, this);
        recyclerView.setAdapter(adapter);

        getData();

        imgbtn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nextactivity=new Intent(getBaseContext(), FavoriteList.class);
                startActivity(nextactivity);

            }
        });


        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });



// method
    }

    private void filter(String text) {
            ArrayList<RestoModel> filteredlist = new ArrayList<>();

            for (RestoModel item : restolist) {
                if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                    filteredlist.add(item);
                }
            }

            adapter.filterList(filteredlist);

    }



    private void getData() {
        AndroidNetworking.get("https://restaurant-api.dicoding.dev/list")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray result = response.getJSONArray("restaurants");
                            for (int i = 0; i < result.length(); i++) {
                                JSONObject resultObj = result.getJSONObject(i);

                                String id = resultObj.getString("id");
                                String name = resultObj.getString("name");
                                String description = resultObj.getString("description");
                                String pictureId = "https://restaurant-api.dicoding.dev/images/medium/".concat(resultObj.getString("pictureId"));
                                String city = resultObj.getString("city");

                                restolist.add(new RestoModel(id, name, description, pictureId, city));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                    }
                });
    }


    // end
}