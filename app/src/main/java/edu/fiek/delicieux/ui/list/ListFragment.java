package edu.fiek.delicieux.ui.list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.api.CustomHttpPattern;

import java.util.ArrayList;
import java.util.List;

import edu.fiek.delicieux.R;
import edu.fiek.delicieux.adapter.ListItemsAdapter;
import edu.fiek.delicieux.models.Ingredients;
import edu.fiek.delicieux.repositories.IngredientRepository;

public class ListFragment extends Fragment {

    private IngredientRepository ingredientRepository;
    private ListViewModel mViewModel;
    public static IngredientRepository repository;
    public static final String ARG_OBJECT = "object";

    RecyclerView recyclerView;
    static ListItemsAdapter listItemsAdapter;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_fragment, container, false);

        ingredientRepository = new IngredientRepository(getContext());

        repository = new IngredientRepository(getContext());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();


        recyclerView = view.findViewById(R.id.shop_list_rec_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        listItemsAdapter = new ListItemsAdapter(getContext(), items);
//        listItemsAdapter = new ListItemsAdapter(getContext(), items, getContext().getString(R.string.remove));


//        ((TextView) view.findViewById(R.id.test12))
//                .setText(args.getString(ARG_OBJECT));
    }

    @Override
    public void onResume() {
        super.onResume();

        repository.getIngredients().observe(this, new Observer<List<Ingredients>>() {
            @Override
            public void onChanged(List<Ingredients> ingredients) {
                System.out.println("Dulem knej pak: " + ingredients.size());

                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                    }
                };

                listItemsAdapter = new ListItemsAdapter(getContext(), ingredients, R.string.remove);
                recyclerView.setAdapter(listItemsAdapter);
            }
        });

    }

    public static class CustomOnClickListener implements View.OnClickListener {
        Ingredients ingredient;

        public void setIngredient(Ingredients ingredient) {
            this.ingredient = ingredient;
        }

        @Override
        public void onClick(View v) {
            System.out.println("Text: remove");
            repository.deleteIngredient(ingredient);
            listItemsAdapter.notifyDataSetChanged();
        }
    }
}