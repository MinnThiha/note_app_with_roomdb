package net.mycompany.new_noteapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.mycompany.new_noteapp.Delegate.NoteCallBack;
import net.mycompany.new_noteapp.Local_DB.Entity.Note;
import net.mycompany.new_noteapp.R;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    Context ctxt;
    List<Note> notes=new ArrayList<>();
    NoteCallBack ncb;

    public NoteAdapter(Context ctxt) {
        this.ctxt = ctxt;
    }
    public void setNotes(List<Note> notes){
        this.notes=notes;
        notifyDataSetChanged();
    }

    public void NotecallBackListener(NoteCallBack ncb) {
        this.ncb = ncb;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(ctxt).inflate(R.layout.sv_note,parent,false);
        return new NoteHolder(v);    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note note=notes.get(position);
        holder.tvTitle.setText(note.getTitle());
        holder.tvDate.setText(note.getDate());
        holder.tvDesc.setText(note.getDesc());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class NoteHolder extends RecyclerView.ViewHolder {
        TextView tvDate,tvTitle,tvDesc;
        public NoteHolder(View itemView) {
            super(itemView);
            tvDate=itemView.findViewById(R.id.tv_date_svn);
            tvDesc=itemView.findViewById(R.id.tv_desc_svn);
            tvTitle=itemView.findViewById(R.id.tv_title_svn);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ncb.NoteCallback(notes.get(getAdapterPosition()));
                }
            });
        }
    }
}
