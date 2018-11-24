package net.mycompany.new_noteapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.mycompany.new_noteapp.Local_DB.Entity.Category;
import net.mycompany.new_noteapp.R;

import java.util.List;

public class ChoiceAdapter extends ArrayAdapter<Category> {


    public ChoiceAdapter(@NonNull Context context, @NonNull List<Category> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Viewholder vh;
        if(convertView==null){
            convertView=LayoutInflater.from(getContext()).inflate(R.layout.sample_choice,parent,false);
            vh=new Viewholder(convertView);
            convertView.setTag(vh);
        }
        else vh=(Viewholder)convertView.getTag();
        Category cat=getItem(position);
        vh.tvCat.setText(cat.getCat_Name());
        return convertView;
    }

    private static final class Viewholder{
        TextView tvCat;
        public Viewholder(View v){
            tvCat=v.findViewById(R.id.tv_sv_choice);
        }
    }
}
