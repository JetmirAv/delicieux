package edu.fiek.delicieux.ui.favourite;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FavouriteCollectionAdapter extends FragmentStateAdapter {
    public FavouriteCollectionAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return a NEW fragment instance in createFragment(int)
        Fragment fragment;
        Bundle args = new Bundle();

        if(position == 0){
            fragment = new FavouriteRecipesFragment();
            args.putString(FavouriteObjectFragment.ARG_OBJECT, "Receipt");
        } else {
            fragment = new FavouriteCookbooksFragment();
            args.putString(FavouriteObjectFragment.ARG_OBJECT, "Cookbook");
        }

        // Our object is just an integer :-P

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}