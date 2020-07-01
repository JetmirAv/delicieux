package edu.fiek.delicieux.repositories;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;

import edu.fiek.delicieux.AppDatabase;
import edu.fiek.delicieux.models.Ingredients;

public class IngredientRepository {

    private AppDatabase database;

    public IngredientRepository(Context context) {
        database = Room.databaseBuilder(context, AppDatabase.class, Ingredients.TABLE_NAME).build();

    }

    public LiveData<List<Ingredients>> getIngredients() {
        return database.ingredientsDao().getAll();
    }

//    @SuppressLint("StaticFieldLeak")
//    public Void getIngredients() {
//        new AsyncTask<Void, Void, Void>() {
//
//            @Override
//            protected Void doInBackground(Void... liveData) {
//
//                List<Ingredients> ingredientsList = database.ingredientsDao().getAll();
//
//                return null;
//            }
//        }.execute();
//
//        return null;
//    }

    @SuppressLint("StaticFieldLeak")
    public void insertIngredient(final Ingredients ingredient) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.ingredientsDao().insertAll(ingredient);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void deleteIngredient(final Ingredients ingredient) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.ingredientsDao().delete(ingredient);
                return null;
            }
        }.execute();
    }
}
