package net.mycompany.new_noteapp.Local_DB.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import net.mycompany.new_noteapp.Local_DB.Dao.CategoryDao;
import net.mycompany.new_noteapp.Local_DB.Dao.NoteDao;
import net.mycompany.new_noteapp.Local_DB.Entity.Category;
import net.mycompany.new_noteapp.Local_DB.Entity.Note;

@Database(version = 1,entities = {Note.class,Category.class},exportSchema = false)
public abstract class note_db  extends RoomDatabase {
    public static note_db Ins;
    public abstract NoteDao getNoteDao();
    public abstract CategoryDao getcatDao();
    public static note_db getIns(Context ctxt) {
        if (Ins==null){
            Ins=Room.databaseBuilder(ctxt,note_db.class,"note_db").allowMainThreadQueries().build();
        }
        return Ins;
    }
}
