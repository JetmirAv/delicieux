package edu.fiek.delicieux.ui.recipes;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import edu.fiek.delicieux.R;

public class RecipesDetailFragment extends Fragment {

    private RecipesDetailViewModel mViewModel;

    public static RecipesDetailFragment newInstance() {
        return new RecipesDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.recipes_detail_fragment, container, false);

//        Button showMoreBtn = view.findViewById(R.id.show_more);
////        showMoreBtn.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Toast.makeText(getContext(), "TEstingggg", Toast.LENGTH_LONG).show();
////                System.out.println("asfasFASFASASFasfasfasaf");
////                Navigation.findNavController(v).navigate(R.id.recipesDetailFragment);
////            }
////        });
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
        mViewModel = ViewModelProviders.of(this).get(RecipesDetailViewModel.class);
        // TODO: Use the ViewModel
    }

}