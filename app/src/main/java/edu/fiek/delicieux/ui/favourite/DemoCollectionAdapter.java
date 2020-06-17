package edu.fiek.delicieux.ui.favourite;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class DemoCollectionAdapter extends FragmentStateAdapter {
    public DemoCollectionAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return a NEW fragment instance in createFragment(int)
        Fragment fragment;
        Bundle args = new Bundle();

        if(position == 0){
            fragment = new FavouriteReceiptFragment();
            args.putString(DemoObjectFragment.ARG_OBJECT, "Receipt");
        } else {
            fragment = new FavouriteCookbookFragment();
            args.putString(DemoObjectFragment.ARG_OBJECT, "Cookbook");
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