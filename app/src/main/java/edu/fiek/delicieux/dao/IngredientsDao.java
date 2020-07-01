package edu.fiek.delicieux.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import edu.fiek.delicieux.models.Ingredients;

@Dao
public interface IngredientsDao {

    @Query("SELECT * FROM ingredients")
    LiveData<List<Ingredients>> getAll();

    @Insert
    void insertAll(Ingredients... users);

    @Delete
    void delete(Ingredients user);

}
