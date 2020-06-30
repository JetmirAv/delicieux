package edu.fiek.delicieux.ui.explore;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import edu.fiek.delicieux.R;
import edu.fiek.delicieux.adapter.CookBookAdapter;
import edu.fiek.delicieux.adapter.RecipesAdapter;
import edu.fiek.delicieux.models.CookBook;
import edu.fiek.delicieux.models.RecipesFood;
import edu.fiek.delicieux.ui.cookbook.CookBookDetailViewModel;
import edu.fiek.delicieux.ui.recipes.RecipesDetailViewModel;

public class ExploreFragment extends Fragment {

    private ExploreViewModel mViewModel;
    private CookBookDetailViewModel detailViewModel;
    private RecipesDetailViewModel recipesDetailViewModel;

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
        context = getContext();

        mViewModel = new ViewModelProvider(this).get(ExploreViewModel.class);
        detailViewModel = new ViewModelProvider(requireActivity()).get(CookBookDetailViewModel.class);

        recipesDetailViewModel = new ViewModelProvider(requireActivity()).get(RecipesDetailViewModel.class);


//        mViewModel.getCookBooks();
//        mViewModel.getRecipes();

        cookRecycler = view.findViewById(R.id.cook_recycler);
        recipesRecycler = view.findViewById(R.id.recipes_recycler);



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void setCookRecycler(List<CookBook> cookBookList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        cookRecycler.setLayoutManager(layoutManager);
        cookBookAdapter = new CookBookAdapter(context, cookBookList, detailViewModel);
        cookRecycler.setAdapter(cookBookAdapter);
    }

    private void setRecipesRecycler(List<RecipesFood> recipesFoodList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        recipesRecycler.setLayoutManager(layoutManager);
        recipesAdapter = new RecipesAdapter(context, recipesFoodList,recipesDetailViewModel);
        recipesRecycler.setAdapter(recipesAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        mViewModel.getCookBooks().observe(this, new Observer<List<CookBook>>() {
            @Override
            public void onChanged(List<CookBook> cookBooks) {
                setCookRecycler(mViewModel.cookBooksLiveData.getValue());
            }
        });

        mViewModel.getRecipes().observe(this, new Observer<List<RecipesFood>>() {
            @Override
            public void onChanged(List<RecipesFood> recipesFoods) {
                setRecipesRecycler(recipesFoods);
            }
        });


    }
}