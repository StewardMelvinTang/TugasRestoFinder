package com.example.tugasrestofinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.concurrent.atomic.AtomicReference;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;

public class DetailActivity extends AppCompatActivity {

    String id, name, description, pictureId, city;
    TextView txt_title, txt_description, txt_location;
    ImageView img_thumb;
    ImageButton imgbtn_back;
    Button btn_favorite;
    Bundle bundle;

    Realm realm;
    RealmModel model;
    RealmHelper realmHelper;
    private String API_URL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        txt_title = findViewById(R.id.txt_title);
        txt_description = findViewById(R.id.txt_description);
        txt_location = findViewById(R.id.txt_location);
        img_thumb = findViewById(R.id.img_restoimage);
        imgbtn_back = findViewById(R.id.imgbtn_back);
        btn_favorite = findViewById(R.id.btn_favorite);

        API_URL = "https://restaurant-api.dicoding.dev/detail/"+id;

        // Set up
        Realm.init(this);
        RealmConfiguration configuration1 = new RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        Realm realm1 = Realm.getInstance(configuration1);
        realmHelper = new RealmHelper(realm1);



        bundle = getIntent().getExtras();
        if (bundle != null){
            id = bundle.getString("id");
            name = bundle.getString("name");
            description = bundle.getString("description");
            pictureId = bundle.getString("pictureId");
            city = bundle.getString("city");

            txt_title.setText(name);
            txt_description.setText(description);
            txt_location.setText(city);
            Glide.with(getApplicationContext())
                    .load(pictureId)
                    .into(img_thumb);
        }

        realm = Realm.getDefaultInstance();
        AtomicReference<RestoModel> model = new AtomicReference<>(realm.where(RestoModel.class).equalTo("id", id).findFirst());
        Log.d("TAG", String.valueOf(model));
        if(model.get() == null){
            btn_favorite.setText("ADD TO FAVORITES");
        }else {
            btn_favorite.setText("REMOVE FROM FAV");
        }

        imgbtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestoModel restaurantModel = new RestoModel(id, name, description, city, pictureId);
                model.set(realm.where(RestoModel.class).equalTo("id", id).findFirst());

                if(model.get() == null){
                    realmHelper.save(restaurantModel);
                    Toast.makeText(getApplicationContext(), "Added to your favorite", Toast.LENGTH_SHORT).show();
                    btn_favorite.setText("REMOVE FROM FAV");
                } else {
                    realmHelper.delete(id);
                    Toast.makeText(getApplicationContext(), "Removed from your favorite", Toast.LENGTH_SHORT).show();
                    btn_favorite.setText("ADD TO FAVORITES");
                }
            }
        });


        //method
    }


    //end
}