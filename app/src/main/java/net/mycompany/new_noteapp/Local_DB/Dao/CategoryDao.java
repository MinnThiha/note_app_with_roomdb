package net.mycompany.new_noteapp.Local_DB.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import net.mycompany.new_noteapp.Local_DB.Entity.Category;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("select * from tbl_category")
    LiveData<List<Category>> getCategory();

    @Insert
    void insertCategory(Category category);
}