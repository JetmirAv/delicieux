package edu.fiek.delicieux.ui.list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.fiek.delicieux.R;
import edu.fiek.delicieux.models.CookBook;
import edu.fiek.delicieux.models.ShopListItem;

public class ListFragment extends Fragment {

    private ListViewModel mViewModel;
    public static final String ARG_OBJECT = "object";

    RecyclerView recyclerView;
    ListItemsAdapter listItemsAdapter;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_fragment, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();

        ArrayList<ShopListItem> items = new ArrayList<>();
        items.add(new ShopListItem("Test", "21", false));
        items.add(new ShopListItem("Test", "21", false));
        items.add(new ShopListItem("Test", "21", false));
        items.add(new ShopListItem("Test", "21", false));
        items.add(new ShopListItem("Test", "21", false));
        items.add(new ShopListItem("Test", "21", false));
        items.add(new ShopListItem("Test", "21", false));
        items.add(new ShopListItem("Test", "21", false));
        items.add(new ShopListItem("Test", "21", false));
        items.add(new ShopListItem("Test", "21", false));
        items.add(new ShopListItem("Test", "21", false));
        items.add(new ShopListItem("Test", "21", false));
        items.add(new ShopListItem("Test", "21", false));
        items.add(new ShopListItem("Test", "21", false));
        items.add(new ShopListItem("Test", "21", false));
        items.add(new ShopListItem("Test", "21", false));
        items.add(new ShopListItem("Test", "21", false));
        items.add(new ShopListItem("Test", "21", false));
        items.add(new ShopListItem("Test", "21", false));
        items.add(new ShopListItem("Test", "21", false));
        items.add(new ShopListItem("Test", "21", false));
        items.add(new ShopListItem("Test", "21", false));
        items.add(new ShopListItem("Test", "21", false));
        items.add(new ShopListItem("Test", "21", false));
        items.add(new ShopListItem("Test", "21", false));
        items.add(new ShopListItem("Test", "21", false));
        items.add(new ShopListItem("Test", "21", false));
        items.add(new ShopListItem("Test", "21", false));


        recyclerView = view.findViewById(R.id.shop_list_rec_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listItemsAdapter = new ListItemsAdapter(getContext(), items);
        recyclerView.setAdapter(listItemsAdapter);


//        ((TextView) view.findViewById(R.id.test12))
//                .setText(args.getString(ARG_OBJECT));
    }
}