package edu.fiek.delicieux.ui.cookbook;

import androidx.core.app.ActivityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;

import org.w3c.dom.Text;

import java.util.List;

import edu.fiek.delicieux.R;
import edu.fiek.delicieux.adapter.RecipesAdapter;
import edu.fiek.delicieux.helpers.ClusterLatLng;
import edu.fiek.delicieux.models.CookBook;
import edu.fiek.delicieux.models.RecipesFood;
import edu.fiek.delicieux.models.Restaurants;
import edu.fiek.delicieux.ui.recipes.RecipesDetailViewModel;

public class CookBookDetailFragment extends Fragment implements OnMapReadyCallback {

    private CookBookDetailViewModel mViewModel;
    private RecipesDetailViewModel recipesDetailViewModel;
    private ClusterManager<ClusterLatLng> clusterManager;

    ImageView media;
    TextView title, description;
    RecyclerView recipesRecycler;
    RecipesAdapter recipesAdapter;
    FrameLayout mapContainer;
    NestedScrollView recipeDetailScrollView;


    private MapView mapView;
    private GoogleMap googleMap;

    public static CookBookDetailFragment newInstance() {
        return new CookBookDetailFragment();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cook_book_detail_fragment, container, false);

        ImageView backSign = view.findViewById(R.id.back_Id);
        media = view.findViewById(R.id.cookbookImage);
        title = view.findViewById(R.id.cookbookTitle);
        description = view.findViewById(R.id.cookbookDescription);
        recipesRecycler = view.findViewById(R.id.cookbook_recipes_recycler);
        mapContainer = view.findViewById(R.id.mapContainer);
        recipeDetailScrollView = view.findViewById(R.id.recipeDetailScrollView);
        mapView = (MapView) view.findViewById(R.id.mapView);

        backSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigateUp();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        clusterManager = new ClusterManager<ClusterLatLng>(getContext(), googleMap);

//        mViewModel.restaurantLiveData.getValue(this, getRestaurantsObserver);

        List<Restaurants> list = mViewModel.restaurantLiveData.getValue();
        if(list != null){
            for(Restaurants restaurant : list){
                clusterManager.addItem(new ClusterLatLng(restaurant.getLocation(), restaurant.getName()));
                if (restaurant.getId().equals(mViewModel.getSelected().getValue().getRestaurantId())) {
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(restaurant.getLocation(), 15));
                }
            }
        }


        googleMap.setOnCameraIdleListener(clusterManager);
        googleMap.setOnMarkerClickListener(clusterManager);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(CookBookDetailViewModel.class);
        recipesDetailViewModel = new ViewModelProvider(requireActivity()).get(RecipesDetailViewModel.class);

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

    private void setRecipesRecycler(List<RecipesFood> recipesFoodList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recipesRecycler.setLayoutManager(layoutManager);
        recipesAdapter = new RecipesAdapter(getContext(), recipesFoodList, recipesDetailViewModel);
        recipesRecycler.setAdapter(recipesAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        mViewModel.getCookbookRecipes().observe(this, new Observer<List<RecipesFood>>() {
            @Override
            public void onChanged(List<RecipesFood> recipesFoods) {
                setRecipesRecycler(recipesFoods);
            }
        });

        mViewModel.getRestaurants().observe(this, getRestaurantsObserver);


    }


    Observer<List<Restaurants>> getRestaurantsObserver = new Observer<List<Restaurants>>() {
        @Override
        public void onChanged(List<Restaurants> restaurants) {



            if (clusterManager != null)
                for (Restaurants restaurant : restaurants) {
                    if (restaurant.getLocation() != null) {
                        Log.e("Error", "ASfASFASFASFSAFASF");
                        clusterManager.addItem(new ClusterLatLng(restaurant.getLocation(), restaurant.getName()));
                        if (restaurant.getId().equals(mViewModel.getSelected().getValue().getRestaurantId())) {
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(restaurant.getLocation(), 15));
                        }
                    }
                }
        }
    };
}