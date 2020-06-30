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

import com.bumptech.glide.Glide;

import java.util.List;

import edu.fiek.delicieux.R;
import edu.fiek.delicieux.models.RecipesFood;
import edu.fiek.delicieux.ui.recipes.RecipesDetailViewModel;


public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder> {
    Context context;
    List<RecipesFood> recipesFoodList;
    RecipesDetailViewModel recipesDetailViewModel;

    public RecipesAdapter(Context context, List<RecipesFood> recipesFoodList, RecipesDetailViewModel recipesDetailViewModel) {
        this.context = context;
        this.recipesFoodList = recipesFoodList;
        this.recipesDetailViewModel = recipesDetailViewModel;
    }

    @NonNull
    @Override
    public RecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recipes_food_row_item, parent, false);

        Button showMoreBtn = view.findViewById(R.id.show_more);


        return new RecipesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipesViewHolder holder, final int position) {
        holder.name.setText(recipesFoodList.get(position).getTitle());
        Glide.with(holder.foodImage.getContext()).load(recipesFoodList.get(position).getMedia()).into(holder.foodImage);
        holder.showMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipesDetailViewModel.setSelected(recipesFoodList.get(position));
                Navigation.findNavController(v).navigate(R.id.recipesDetailFragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipesFoodList.size();
    }


    public static final class RecipesViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImage;
        TextView name;
        Button showMoreBtn;

        public RecipesViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.food_image);
            name = itemView.findViewById(R.id.name);
            showMoreBtn = itemView.findViewById(R.id.show_more);
        }
    }
}