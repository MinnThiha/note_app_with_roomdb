package net.mycompany.new_noteapp.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.mycompany.new_noteapp.Local_DB.Database.note_db;
import net.mycompany.new_noteapp.Local_DB.Entity.Category;
import net.mycompany.new_noteapp.R;

public class AddCategory_Activity extends AppCompatActivity{
    EditText etcat;
    Button btnadd;
    note_db db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_add);
        etcat=findViewById(R.id.et_cat_ac);
        btnadd=findViewById(R.id.btn_add_ac);

        db=note_db.getIns(this);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String et=etcat.getText().toString();
                db.getcatDao().insertCategory(new Category(et));
                finish();
            }
        });
    }
}
