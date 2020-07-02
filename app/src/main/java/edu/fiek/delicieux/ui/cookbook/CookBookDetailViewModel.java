package edu.fiek.delicieux.ui.cookbook;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import edu.fiek.delicieux.models.CookBook;
import edu.fiek.delicieux.models.RecipesFood;
import edu.fiek.delicieux.models.Restaurants;

public class CookBookDetailViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<CookBook> selected = new MutableLiveData<>();
    MutableLiveData<List<RecipesFood>> recipesLiveData = new MutableLiveData<>();
    MutableLiveData<List<Restaurants>> restaurantLiveData = new MutableLiveData<>();


    public void getRecipeById(String id) {
        FirebaseDatabase.getInstance().getReference("Recipes").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (recipesLiveData.getValue() == null) {
                    Log.e("Error", "Null");
                    recipesLiveData.setValue(new ArrayList<RecipesFood>());
                }
                List<RecipesFood> list = recipesLiveData.getValue();

                if (snapshot.exists()) {
                    RecipesFood food = snapshot.getValue(RecipesFood.class);
                    Log.e("TEst:", food.getDescription());
                    Log.e("TEst:", food.getMedia());
                    Log.e("TEst:", food.getTitle());
                    Log.e("TEst:", food.getIngridients().toString());

                    list.add(food);

                }
                recipesLiveData.postValue(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public LiveData<List<RecipesFood>> getCookbookRecipes() {
        if (recipesLiveData.getValue() == null) {
            List<String> recipeIds = selected.getValue().getRecipes();

            for (String id : recipeIds) {
                getRecipeById(id);
            }
        }

        return recipesLiveData;
    }

    public MutableLiveData<CookBook> getSelected() {
        return selected;
    }

    public void setSelected(CookBook selected) {
        this.selected.postValue(selected);
    }

    public LiveData<List<Restaurants>> getRestaurants() {
        if (restaurantLiveData.getValue() == null) {

            FirebaseDatabase.getInstance().getReference("Restaurants").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<Restaurants> list = new ArrayList<>();
                    for (DataSnapshot restaurant : snapshot.getChildren()) {
                        Restaurants res = restaurant.getValue(Restaurants.class);
                        res.setId(restaurant.getKey());
                        list.add(res);
                    }
                    restaurantLiveData.postValue(list);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

        return restaurantLiveData;
    }

}