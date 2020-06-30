package edu.fiek.delicieux.ui.cookbook;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import edu.fiek.delicieux.models.CookBook;

public class CookBookDetailViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<CookBook> selected = new MutableLiveData<>();

    public MutableLiveData<CookBook> getSelected() {
        return selected;
    }

    public void setSelected(CookBook selected) {
        this.selected.postValue(selected);
    }
}