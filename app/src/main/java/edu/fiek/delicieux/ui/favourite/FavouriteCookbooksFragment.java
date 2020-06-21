package edu.fiek.delicieux.ui.favourite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.fiek.delicieux.R;
import edu.fiek.delicieux.model.CookBook;

public class FavouriteCookbooksFragment extends Fragment {
    public static final String ARG_OBJECT = "object";

    RecyclerView recyclerView;
    FavouriteCookbooksAdapter favouriteCookbooksAdapter;
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

        ArrayList<CookBook> cookBooks = new ArrayList<>();
        cookBooks.add(new CookBook("Test", 21));
        cookBooks.add(new CookBook("Test", 21));
        cookBooks.add(new CookBook("Test", 21));
        cookBooks.add(new CookBook("Test", 21));
        cookBooks.add(new CookBook("Test", 21));
        cookBooks.add(new CookBook("Test", 21));
        cookBooks.add(new CookBook("Test", 21));


        recyclerView = view.findViewById(R.id.favourite_cookbook_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        favouriteCookbooksAdapter = new FavouriteCookbooksAdapter(getContext(), cookBooks);
        recyclerView.setAdapter(favouriteCookbooksAdapter);


//        ((TextView) view.findViewById(R.id.test12))
//                .setText(args.getString(ARG_OBJECT));
    }
}
