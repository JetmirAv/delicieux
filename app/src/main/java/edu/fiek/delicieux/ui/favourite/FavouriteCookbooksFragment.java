package edu.fiek.delicieux.ui.favourite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.fiek.delicieux.R;
import edu.fiek.delicieux.adapter.CookBookAdapter;
import edu.fiek.delicieux.models.CookBook;
import edu.fiek.delicieux.models.RecipesFood;
import edu.fiek.delicieux.ui.cookbook.CookBookDetailViewModel;
import edu.fiek.delicieux.ui.recipes.RecipesDetailViewModel;

public class FavouriteCookbooksFragment extends Fragment {
    public static final String ARG_OBJECT = "object";

    RecyclerView recyclerView;
    CookBookDetailViewModel viewModel;
    CookBookAdapter favouriteCookbooksAdapter;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.favourite_cookbook_fragment, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();

        viewModel = new ViewModelProvider(requireActivity()).get(CookBookDetailViewModel.class);


        ArrayList<CookBook> cookBooks = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        list.add("-MB5SVmNvcJd6O1N4BGl");
        list.add("-MB5SVmNvcJd6O1N4BGl");
        list.add("-MB5SVmNvcJd6O1N4BGl");


        for (int i = 0; i < 5; i++) {
            CookBook cookBook = new CookBook("Test", "21", "Test", list, "-MB5TjKFjCjTi18AoRlD");
            cookBook.setMedia("https://firebasestorage.googleapis.com/v0/b/delicieux-5b867.appspot.com/o/media%2Fasiafood1.png?alt=media&token=50c585b6-1d9c-413b-a4ed-4306bc2c2e82");
            cookBooks.add(cookBook);
        }

        recyclerView = view.findViewById(R.id.favourite_cookbook_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        favouriteCookbooksAdapter = new CookBookAdapter(getContext(), cookBooks, viewModel);
        recyclerView.setAdapter(favouriteCookbooksAdapter);


//        ((TextView) view.findViewById(R.id.test12))
//                .setText(args.getString(ARG_OBJECT));
    }
}
