package edu.fiek.delicieux.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.fiek.delicieux.R;
import edu.fiek.delicieux.model.RecipesFood;


public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder> {

    Context context;
    List<RecipesFood> recipesFoodList;



    public RecipesAdapter(Context context, List<RecipesFood> recipesFoodList) {
        this.context = context;
        this.recipesFoodList = recipesFoodList;
    }

    @NonNull
    @Override
    public RecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recipes_food_row_item, parent, false);
        return new RecipesViewHolder(view);
    }

    @Override
    public void onBindViewHolder( RecipesViewHolder holder, int position) {

        holder.foodImage.setImageResource(recipesFoodList.get(position).getImageUrl());
        holder.name.setText(recipesFoodList.get(position).getName());
        holder.price.setText(recipesFoodList.get(position).getPrice());
        holder.rating.setText(recipesFoodList.get(position).getRating());
        holder.restorantName.setText(recipesFoodList.get(position).getRestorantname());

    }

    @Override
    public int getItemCount() {
        return recipesFoodList.size();
    }


    public static final class RecipesViewHolder extends RecyclerView.ViewHolder{


        ImageView foodImage;
        TextView price, name, rating, restorantName;

        public RecipesViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.food_image);
            price = itemView.findViewById(R.id.price);
            name = itemView.findViewById(R.id.name);
            rating = itemView.findViewById(R.id.rating);
            restorantName = itemView.findViewById(R.id.restorant_name);



        }
    }

}