package edu.fiek.delicieux.ui.explore;

import android.widget.Toast;

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

public class ExploreViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    MutableLiveData<List<CookBook>> cookBooksLiveData = new MutableLiveData<>();


    public LiveData<List<CookBook>> getCookBooks() {
        if (cookBooksLiveData.getValue() == null) {
            FirebaseDatabase.getInstance().getReference("Cookbooks").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        List<CookBook> data = new ArrayList<>();

                        for (DataSnapshot cookbook : snapshot.getChildren()) {
                            data.add(cookbook.getValue(CookBook.class));
                        }

                        cookBooksLiveData.postValue(data);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        return cookBooksLiveData;
    }


    MutableLiveData<List<RecipesFood>> recipesLiveData = new MutableLiveData<>();


    public LiveData<List<RecipesFood>> getRecipes() {
        if (recipesLiveData.getValue() == null) {
            FirebaseDatabase.getInstance().getReference("Recipes").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        List<RecipesFood> data = new ArrayList<>();

                        for (DataSnapshot recipe : snapshot.getChildren()) {
                            data.add(recipe.getValue(RecipesFood.class));
                        }

                        recipesLiveData.postValue(data);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        return recipesLiveData;
    }


}