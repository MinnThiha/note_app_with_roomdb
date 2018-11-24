package net.mycompany.new_noteapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import net.mycompany.new_noteapp.Delegate.CategoryDelegate;
import net.mycompany.new_noteapp.Delegate.NoteCallBack;
import net.mycompany.new_noteapp.Local_DB.Entity.Category;
import net.mycompany.new_noteapp.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder>  {
    List<Category> categories=new ArrayList<>();
    Context ctxt;
    CategoryDelegate ccb;

    public CategoryAdapter(Context ctxt) {
        this.ctxt = ctxt;
    }
    public void setCategories(List<Category> categories){
        this.categories=categories;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(ctxt).inflate(R.layout.sv_category,parent,false);
        return new CategoryHolder(v);
    }

    public void CategoryDelegatelistener(CategoryDelegate ccb) {
        this.ccb = ccb;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        Category category=categories.get(position);
        holder.tvcategory.setText(category.getCat_Name());

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder {
        TextView tvcategory;
        public CategoryHolder(View itemView) {
            super(itemView);
            tvcategory=itemView.findViewById(R.id.tv_category_svcate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ccb.CatCallback(categories.get(getAdapterPosition()));
                }
            });
        }
    }
}
