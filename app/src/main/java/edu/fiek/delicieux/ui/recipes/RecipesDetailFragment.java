package edu.fiek.delicieux.ui.recipes;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import edu.fiek.delicieux.R;
import edu.fiek.delicieux.adapter.ListItemsAdapter;
import edu.fiek.delicieux.models.Ingredients;
import edu.fiek.delicieux.models.RecipesFood;
import edu.fiek.delicieux.repositories.IngredientRepository;

public class RecipesDetailFragment extends Fragment {

    static IngredientRepository repository;
    ImageView recipesImage;
    TextView recipesTitle;
    TextView recipesDescription;
    static Context context;
    static RecyclerView ing_rec_view;

    private RecipesDetailViewModel mViewModel;

    public static RecipesDetailFragment newInstance() {
        return new RecipesDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipes_detail_fragment, container, false);

        repository = new IngredientRepository(getContext());
        context = getContext();

        recipesImage = view.findViewById(R.id.recipesImage);
        recipesTitle = view.findViewById(R.id.recipesTitle);
        recipesDescription = view.findViewById(R.id.recipesDescription);
        ing_rec_view = view.findViewById(R.id.ing_rec_view);
        ing_rec_view.setLayoutManager(new LinearLayoutManager(getContext()));
        ImageView backSign = view.findViewById(R.id.back_Id);

        backSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigateUp();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(RecipesDetailViewModel.class);

        // TODO: Use the ViewModel
        mViewModel.getSelected().observe(getViewLifecycleOwner(), new Observer<RecipesFood>() {
            @Override
            public void onChanged(RecipesFood recipesFood) {
                recipesTitle.setText(recipesFood.getTitle());
                recipesDescription.setText(recipesFood.getDescription());
                Glide.with(getContext()).load(recipesFood.getMedia()).into(recipesImage);

                ing_rec_view.setAdapter(new ListItemsAdapter(getContext(), recipesFood.getIngridients(), R.string.add_to_cart));

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
            System.out.println("Text: insert");
            repository.insertIngredient(ingredient);

            Toast.makeText(context, "Ingridient added to cart successfully", Toast.LENGTH_SHORT).show();
        }
    }

}