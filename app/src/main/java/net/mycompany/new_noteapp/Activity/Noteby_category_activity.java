package net.mycompany.new_noteapp.Activity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import net.mycompany.new_noteapp.Adapters.CategoryAdapter;
import net.mycompany.new_noteapp.Adapters.NoteAdapter;
import net.mycompany.new_noteapp.Delegate.NoteCallBack;
import net.mycompany.new_noteapp.Local_DB.Database.note_db;
import net.mycompany.new_noteapp.Local_DB.Entity.Category;
import net.mycompany.new_noteapp.Local_DB.Entity.Note;
import net.mycompany.new_noteapp.R;
import net.mycompany.new_noteapp.Utils.Appconstant;
import java.util.List;


public class Noteby_category_activity extends AppCompatActivity {
    TextView tv;
    RecyclerView rv;
    CategoryAdapter cad;
    NoteAdapter nad;
    int id=0;
    note_db db;
    LiveData<List<Note>> notelist;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noteby_category);

        tv=findViewById(R.id.tv_catname_anc);
        rv=findViewById(R.id.rv_anc);
        rv.setLayoutManager(new LinearLayoutManager(Noteby_category_activity.this));
        nad=new NoteAdapter(Noteby_category_activity.this);
        rv.setAdapter(nad);

        nad.NotecallBackListener(new NoteCallBack() {
            @Override
            public void NoteCallback(Note note) {
                Intent i=new Intent(Noteby_category_activity.this,Note_activity.class);
                Bundle bd=new Bundle();
                bd.putParcelable(Appconstant.ID_Key,note);
                i.putExtras(bd);
                startActivity(i);
            }
        });

        db=note_db.getIns(this);

        Intent i=getIntent();
        Bundle bd=i.getExtras();
        Category category=bd.getParcelable(Appconstant.ID_Key);
        id=category.getId();
        tv.setText(category.getCat_Name());

        db.getNoteDao().queryByCategory(id).observe(Noteby_category_activity.this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                nad.setNotes(notes);
            }
        });

    }
}
