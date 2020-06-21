package edu.fiek.delicieux.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
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

        Button showMoreBtn = view.findViewById(R.id.show_more);
        showMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("asfasFASFASASFasfasfasaf");
                Navigation.findNavController(v).navigate(R.id.recipesDetailFragment);
            }
        });

        return new RecipesViewHolder(view);
    }

    @Override
    public void onBindViewHolder( RecipesViewHolder holder, int position) {

//        holder.foodImage.setImageResource(recipesFoodList.get(position).getImageUrl());
//        holder.name.setText(recipesFoodList.get(position).getName());
//        holder.rating.setText(recipesFoodList.get(position).getRating());
//        holder.restorantName.setText(recipesFoodList.get(position).getRestorantname());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Navigation.findNavController(view).navigate(R.id.recipesDetailFragment);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return recipesFoodList.size();
    }


    public static final class RecipesViewHolder extends RecyclerView.ViewHolder{


        ImageView foodImage;
        TextView  name;
//        , rating, restorantName;

        public RecipesViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.food_image);
            name = itemView.findViewById(R.id.name);
//            rating = itemView.findViewById(R.id.rating);
//            restorantName = itemView.findViewById(R.id.restorant_name);



        }
    }

}