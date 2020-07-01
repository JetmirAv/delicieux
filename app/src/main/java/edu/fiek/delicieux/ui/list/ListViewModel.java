package edu.fiek.delicieux.ui.list;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import edu.fiek.delicieux.models.Ingredients;
import edu.fiek.delicieux.repositories.IngredientRepository;

public class ListViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    MutableLiveData<Ingredients> ingredients = new MutableLiveData<>();
//    IngredientRepository repository = new IngredientRepository();
//
//    public LiveData<Ingredients> getIngredients(){
//        if(ingredients.getValue() == null){
//            IngredientRepository
//        }
//    }

}