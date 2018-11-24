package net.mycompany.new_noteapp.Local_DB.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import net.mycompany.new_noteapp.Local_DB.Entity.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("select * from tbl_note")
    LiveData<List<Note>> getAllnote();

    @Insert
    void insertNote(Note note);

    @Update
    void updateNote(Note note);

    @Query("select * from tbl_note where date=:date")
    LiveData<List<Note>> querybydate(String date);

    @Query("select * from tbl_note where cat_id=:cat_id")
    LiveData<List<Note>> queryByCategory(int cat_id);
}
