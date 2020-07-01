package edu.fiek.delicieux.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "ingredients")
public class Ingredients {

    public static String TABLE_NAME = "ingredients";

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "quantity")
    String quantity;

    public Ingredients(Integer id, String name, String quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    @Ignore
    public Ingredients() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
