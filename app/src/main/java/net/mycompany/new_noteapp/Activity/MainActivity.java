package net.mycompany.new_noteapp.Activity;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import net.mycompany.new_noteapp.Adapters.CategoryAdapter;
import net.mycompany.new_noteapp.Adapters.NoteAdapter;
import net.mycompany.new_noteapp.Delegate.CategoryDelegate;
import net.mycompany.new_noteapp.Delegate.NoteCallBack;
import net.mycompany.new_noteapp.Local_DB.Database.note_db;
import net.mycompany.new_noteapp.Local_DB.Entity.Category;
import net.mycompany.new_noteapp.Local_DB.Entity.Note;
import net.mycompany.new_noteapp.R;
import net.mycompany.new_noteapp.Utils.Appconstant;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private RecyclerView rv_cat,rv_note;
    private ImageButton ibtn;
    private Button btn_add;
    NoteAdapter nad;
    CategoryAdapter cad;
    note_db db;
    CategoryDelegate cd;
    int id=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ibtn=findViewById(R.id.ibtn_search_am);
        btn_add=findViewById(R.id.btn_add_am);
        nad=new NoteAdapter(MainActivity.this);
        cad=new CategoryAdapter(this);
        rv_cat=findViewById(R.id.rv_cate_am);
        rv_note=findViewById(R.id.rv_note_am);
        rv_cat.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
        rv_note.setLayoutManager(new LinearLayoutManager(this));
        db=note_db.getIns(this);
        rv_cat.setAdapter(cad);
        rv_note.setAdapter(nad);

        cad.CategoryDelegatelistener(new CategoryDelegate() {
            @Override
            public void CatCallback(Category category) {
                Intent i=new Intent(MainActivity.this,Noteby_category_activity.class);
                Bundle bd=new Bundle();
                bd.putParcelable(Appconstant.ID_Key,category);
                i.putExtras(bd);
                startActivity(i);
            }
        });

        nad.NotecallBackListener(new NoteCallBack() {
            @Override
            public void NoteCallback(Note note) {
                Intent i=new Intent(MainActivity.this,Note_activity.class);
                Bundle bd=new Bundle();
                bd.putParcelable(Appconstant.ID_Key,note);
                i.putExtras(bd);
                startActivity(i);
            }
        });

        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        String date=year+"/"+month+"/"+day;
        db.getNoteDao().querybydate(date).observe(MainActivity.this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                nad.setNotes(notes);
            }
        });



//        db.getNoteDao().getAllnote().observe(MainActivity.this, new Observer<List<Note>>() {
//            @Override
//            public void onChanged(@Nullable List<Note> notes) {
//                nad.setNotes(notes);
//            }
//        });
        db.getcatDao().getCategory().observe(MainActivity.this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> categories) {
                cad.setCategories(categories);
            }
        });


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoice();
            }
        });
        ibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Search_activity.class);
                startActivity(i);
            }
        });
    }
    public void showChoice(){
        AlertDialog.Builder ab=new AlertDialog.Builder(MainActivity.this);
        View v=LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_customer_alert,null);
        ab.setView(v);
        Button btnnote=v.findViewById(R.id.btn_note_aca);
        Button btncate=v.findViewById(R.id.btn_cate_aca);
        final AlertDialog ad=ab.create();
        btncate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.dismiss();
                Intent i=new Intent(MainActivity.this,AddCategory_Activity.class);
                startActivity(i);
            }
        });
        btnnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.dismiss();
                Intent i=new Intent(MainActivity.this,Note_activity.class);
                startActivity(i);
            }
        });
        ad.show();
    }
}
