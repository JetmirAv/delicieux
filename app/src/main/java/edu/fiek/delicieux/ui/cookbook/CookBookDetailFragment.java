package edu.fiek.delicieux.ui.cookbook;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import edu.fiek.delicieux.R;

public class CookBookDetailFragment extends Fragment {

    private CookBookDetailViewModel mViewModel;

    public static CookBookDetailFragment newInstance() {
        return new CookBookDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.cook_book_detail_fragment, container, false);
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
        mViewModel = ViewModelProviders.of(this).get(CookBookDetailViewModel.class);
        // TODO: Use the ViewModel
    }

}