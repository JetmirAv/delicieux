package edu.fiek.delicieux.ui.recipes;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import edu.fiek.delicieux.models.RecipesFood;

public class RecipesDetailViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    MutableLiveData<RecipesFood> selected = new MutableLiveData<>();

    public void setSelected(RecipesFood selected) {
        this.selected.postValue(selected);

    }

    public MutableLiveData<RecipesFood> getSelected() {
        return selected;
    }
}