package edu.fiek.delicieux.ui.cookbook;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CookBookDetailViewModel.class);
        // TODO: Use the ViewModel
    }

}