package com.example.tugasrestofinder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.realm.Realm;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>{
    public List<RestoModel> restaurantModels;
    Context context;

    public FavoriteAdapter(List<RestoModel> restaurantModels, Context context) {
        this.restaurantModels = restaurantModels;
        this.context = context;
    }

    @NonNull
    @Override
    public FavoriteAdapter.FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new FavoriteAdapter.FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.FavoriteViewHolder holder, int position) {
        holder.tvName.setText(restaurantModels.get(position).getName());
        holder.tvCity.setText(restaurantModels.get(position).getPictureId());
        Glide.with(context)
                .load(restaurantModels.get(position).getCity())
                .into(holder.ivPicture);
        holder.linearLayout.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("id", restaurantModels.get(position).getId());
            intent.putExtra("description", restaurantModels.get(position).getDescription());
            intent.putExtra("name", restaurantModels.get(position).getName());
            intent.putExtra("city", restaurantModels.get(position).getPictureId());
            intent.putExtra("pictureId", restaurantModels.get(position).getCity());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return (restaurantModels != null) ? restaurantModels.size() : 0;
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvCity;
        ImageView ivPicture;
        LinearLayout linearLayout;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.list_title);
            linearLayout = itemView.findViewById(R.id.linearlayout);
            ivPicture = itemView.findViewById(R.id.list_image);
            tvCity = itemView.findViewById(R.id.list_description);
        }
    }
}
