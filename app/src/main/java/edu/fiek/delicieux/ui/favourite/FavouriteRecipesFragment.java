package edu.fiek.delicieux.ui.favourite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.fiek.delicieux.R;
import edu.fiek.delicieux.adapter.RecipesAdapter;
import edu.fiek.delicieux.models.Ingredients;
import edu.fiek.delicieux.models.RecipesFood;
import edu.fiek.delicieux.ui.recipes.RecipesDetailViewModel;

public class FavouriteRecipesFragment extends Fragment {

    public static final String ARG_OBJECT = "object";

    RecipesDetailViewModel viewModel;
    RecyclerView recyclerView;
    RecipesAdapter favouriteRecipesAdapter;
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

        viewModel = new ViewModelProvider(requireActivity()).get(RecipesDetailViewModel.class);

        ArrayList<RecipesFood> recipesFoods = new ArrayList<>();
        ArrayList<Ingredients> ingredients  = new ArrayList<>();

        ingredients.add(new Ingredients(1, "Salt", "12gr"));
        ingredients.add(new Ingredients(2, "Eggs", "14"));

        for(int i = 0; i<9; i++){
            RecipesFood recipesFood = new RecipesFood("Test", "21", "21", ingredients);
            recipesFood.setMedia("https://firebasestorage.googleapis.com/v0/b/delicieux-5b867.appspot.com/o/media%2Fasiafood1.png?alt=media&token=50c585b6-1d9c-413b-a4ed-4306bc2c2e82");
            recipesFoods.add(recipesFood);
        }

        recyclerView = view.findViewById(R.id.favourite_recipe_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        favouriteRecipesAdapter = new RecipesAdapter(getContext(), recipesFoods, viewModel);
        recyclerView.setAdapter(favouriteRecipesAdapter);


    }

}
