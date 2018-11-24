package net.mycompany.new_noteapp.Delegate;

import net.mycompany.new_noteapp.Local_DB.Entity.Note;

public interface NoteCallBack {
    void NoteCallback(Note note);
}
