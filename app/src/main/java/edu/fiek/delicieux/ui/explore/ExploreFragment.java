package edu.fiek.delicieux.ui.explore;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import edu.fiek.delicieux.R;
import edu.fiek.delicieux.adapter.CookBookAdapter;
import edu.fiek.delicieux.adapter.RecipesAdapter;
import edu.fiek.delicieux.model.CookBook;
import edu.fiek.delicieux.model.RecipesFood;

public class ExploreFragment extends Fragment {

    private ExploreViewModel mViewModel;

    RecyclerView cookRecycler;
    RecyclerView recipesRecycler;
    Context context;
    CookBookAdapter cookBookAdapter;
    RecipesAdapter recipesAdapter;

    public static ExploreFragment newInstance() {
        return new ExploreFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.explore_fragment, container, false);

        cookRecycler = view.findViewById(R.id.cook_recycler);
        context = getContext();

        recipesRecycler = view.findViewById(R.id.recipes_recycler);

        List<CookBook> cookBookList = new ArrayList<>();

        cookBookList.add(new CookBook("Float Cake Vietnam", R.drawable.appertizers));
        cookBookList.add(new CookBook("Float Cake Vietnam", R.drawable.appertizers));
        cookBookList.add(new CookBook("Float Cake Vietnam", R.drawable.appertizers));
        cookBookList.add(new CookBook("Float Cake Vietnam", R.drawable.appertizers));
        cookBookList.add(new CookBook("Float Cake Vietnam", R.drawable.appertizers));
        cookBookList.add(new CookBook("Float Cake Vietnam", R.drawable.appertizers));
        cookBookList.add(new CookBook("Float Cake Vietnam", R.drawable.appertizers));
        cookBookList.add(new CookBook("Float Cake Vietnam", R.drawable.appertizers));
        cookBookList.add(new CookBook("Float Cake Vietnam", R.drawable.appertizers));
        cookBookList.add(new CookBook("Float Cake Vietnam", R.drawable.appertizers));



        setCookRecycler(cookBookList);

        List<RecipesFood> recipesFoodList = new ArrayList<>();
        recipesFoodList.add(new RecipesFood("Chicago Pizza", "$20", R.drawable.asiafood1, "4.5", "Briand Restaurant"));
        recipesFoodList.add(new RecipesFood("Straberry Cake", "$25", R.drawable.asiafood2, "4.2", "Friends Restaurant"));
        recipesFoodList.add(new RecipesFood("Chicago Pizza", "$20", R.drawable.asiafood1, "4.5", "Briand Restaurant"));
        recipesFoodList.add(new RecipesFood("Straberry Cake", "$25", R.drawable.asiafood2, "4.2", "Friends Restaurant"));
        recipesFoodList.add(new RecipesFood("Chicago Pizza", "$20", R.drawable.asiafood1, "4.5", "Briand Restaurant"));
        recipesFoodList.add(new RecipesFood("Straberry Cake", "$25", R.drawable.asiafood2, "4.2", "Friends Restaurant"));
        recipesFoodList.add(new RecipesFood("Straberry Cake", "$25", R.drawable.asiafood2, "4.2", "Friends Restaurant"));
        recipesFoodList.add(new RecipesFood("Straberry Cake", "$25", R.drawable.asiafood2, "4.2", "Friends Restaurant"));
        recipesFoodList.add(new RecipesFood("Straberry Cake", "$25", R.drawable.asiafood2, "4.2", "Friends Restaurant"));
        recipesFoodList.add(new RecipesFood("Straberry Cake", "$25", R.drawable.asiafood2, "4.2", "Friends Restaurant"));
        recipesFoodList.add(new RecipesFood("Straberry Cake", "$25", R.drawable.asiafood2, "4.2", "Friends Restaurant"));
        recipesFoodList.add(new RecipesFood("Straberry Cake", "$25", R.drawable.asiafood2, "4.2", "Friends Restaurant"));
        recipesFoodList.add(new RecipesFood("Straberry Cake", "$25", R.drawable.asiafood2, "4.2", "Friends Restaurant"));
        recipesFoodList.add(new RecipesFood("Straberry Cake", "$25", R.drawable.asiafood2, "4.2", "Friends Restaurant"));
        recipesFoodList.add(new RecipesFood("Straberry Cake", "$25", R.drawable.asiafood2, "4.2", "Friends Restaurant"));

        setRecipesRecycler(recipesFoodList);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ExploreViewModel.class);
        // TODO: Use the ViewModel
    }

    private void setCookRecycler(List<CookBook> cookBookList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        cookRecycler.setLayoutManager(layoutManager);
        cookBookAdapter = new CookBookAdapter(context, cookBookList);
        cookRecycler.setAdapter(cookBookAdapter);

    }
    private void setRecipesRecycler(List<RecipesFood> recipesFoodList) {


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        recipesRecycler.setLayoutManager(layoutManager);
        recipesAdapter = new RecipesAdapter(context, recipesFoodList);
        recipesRecycler.setAdapter(recipesAdapter);

    }

}