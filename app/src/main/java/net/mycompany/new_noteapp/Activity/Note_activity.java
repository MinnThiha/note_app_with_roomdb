package net.mycompany.new_noteapp.Activity;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yarolegovich.lovelydialog.LovelyChoiceDialog;

import net.mycompany.new_noteapp.Adapters.ChoiceAdapter;
import net.mycompany.new_noteapp.Local_DB.Database.note_db;
import net.mycompany.new_noteapp.Local_DB.Entity.Category;
import net.mycompany.new_noteapp.Local_DB.Entity.Note;
import net.mycompany.new_noteapp.R;
import net.mycompany.new_noteapp.Utils.Appconstant;

import java.util.Calendar;
import java.util.List;

public class Note_activity extends AppCompatActivity{
    EditText etTitle,etDesc,etCat;
    Button btn_cat,btn_add;
    note_db db;
    ChoiceAdapter cad;
    int id=0;
    Boolean isUpdate=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        etTitle=findViewById(R.id.et_title_an);
        etDesc=findViewById(R.id.et_des_an);
        etCat=findViewById(R.id.etCat_an);
        btn_add=findViewById(R.id.btn_add_an);
        btn_cat=findViewById(R.id.btn_cat_an);
        db=note_db.getIns(this);
        if(getIntent()!=null){
            Intent i=getIntent();
            Bundle bd=i.getExtras();
            if(bd!=null){
                isUpdate=true;
                Note note=bd.getParcelable(Appconstant.ID_Key);
                etTitle.setText(note.getTitle());
                etDesc.setText(note.getDesc());
                etCat.setText("");
            }
        }

        db.getcatDao().getCategory().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> categories) {
                cad=new ChoiceAdapter(Note_activity.this,categories);
            }
        });

        btn_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LovelyChoiceDialog(Note_activity.this)
                        .setTopColorRes(R.color.colorPrimary)
                        .setTitle("Choice Category")
                        .setItems(cad, new LovelyChoiceDialog.OnItemSelectedListener<Category>() {
                    @Override
                    public void onItemSelected(int position, Category item) {
                        etCat.setText(item.getCat_Name());
                        item.getId();
                        id=item.getId();
                    }
                }).show();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=etTitle.getText().toString();
                String des=etDesc.getText().toString();

                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                String date=year+"/"+month+"/"+day;

                if(isUpdate){
                    Note n=new Note(title,des,date,id);
                    db.getNoteDao().updateNote(n);
                }
                else {
                    Note n=new Note(title,des,date,id);
                    db.getNoteDao().insertNote(n);
                }
                finish();

            }
        });

    }
}
