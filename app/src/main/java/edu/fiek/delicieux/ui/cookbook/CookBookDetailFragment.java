package edu.fiek.delicieux.ui.cookbook;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import edu.fiek.delicieux.R;
import edu.fiek.delicieux.models.CookBook;

public class CookBookDetailFragment extends Fragment {

    private CookBookDetailViewModel mViewModel;

    ImageView media;
    TextView title, description;

    public static CookBookDetailFragment newInstance() {
        return new CookBookDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cook_book_detail_fragment, container, false);

        ImageView backSign = view.findViewById(R.id.back_Id);
        media = view.findViewById(R.id.cookbookImage);
        title = view.findViewById(R.id.cookbookTitle);
        description = view.findViewById(R.id.cookbookDescription);



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
        mViewModel = new ViewModelProvider(requireActivity()).get(CookBookDetailViewModel.class);
        mViewModel.getSelected().observe(getViewLifecycleOwner(), new Observer<CookBook>() {
            @Override
            public void onChanged(CookBook cookBook) {
                title.setText(cookBook.getTitle());
                description.setText(cookBook.getDescription());

                Glide.with(getContext()).load(cookBook.getMedia()).into(media);
            }
        });
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();


    }
}