package edu.fiek.delicieux.ui.favourite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.fiek.delicieux.R;
import edu.fiek.delicieux.models.RecipesFood;

public class FavouriteRecipesFragment extends Fragment {

    public static final String ARG_OBJECT = "object";
    RecyclerView recyclerView;
    FavouriteRecipesAdapter favouriteRecipesAdapter;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.favourite_recipe_fragment, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();

        ArrayList<RecipesFood> recipesFoods = new ArrayList<>();
//        recipesFoods.add(new RecipesFood("Test", "21", 21, "asf", "ASFasf"));
//        recipesFoods.add(new RecipesFood("Test", "21", 21, "asf", "ASFasf"));
//        recipesFoods.add(new RecipesFood("Test", "21", 21, "asf", "ASFasf"));
//        recipesFoods.add(new RecipesFood("Test", "21", 21, "asf", "ASFasf"));
//        recipesFoods.add(new RecipesFood("Test", "21", 21, "asf", "ASFasf"));
//        recipesFoods.add(new RecipesFood("Test", "21", 21, "asf", "ASFasf"));
//        recipesFoods.add(new RecipesFood("Test", "21", 21, "asf", "ASFasf"));


        recyclerView = view.findViewById(R.id.favourite_recipe_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        favouriteRecipesAdapter = new FavouriteRecipesAdapter(getContext(), recipesFoods);
        recyclerView.setAdapter(favouriteRecipesAdapter);


    }

}
