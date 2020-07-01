package edu.fiek.delicieux;

import androidx.room.RoomDatabase;
import androidx.room.Database;

import edu.fiek.delicieux.dao.IngredientsDao;
import edu.fiek.delicieux.models.Ingredients;

@Database(entities = {Ingredients.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract IngredientsDao ingredientsDao();
}
