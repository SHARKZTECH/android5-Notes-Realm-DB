package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.text.DateFormat;

import io.realm.RealmResults;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    RealmResults<Notes> noteslist;

    public MyAdapter(Context context, RealmResults<Notes> noteslist) {
        this.context = context;
        this.noteslist = noteslist;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
          Notes notes=noteslist.get(position);
        holder.titleOut.setText(notes.getTitle());
        holder.descriptionOut.setText(notes.getDescription());
        String formatedTime= DateFormat.getDateTimeInstance().format(notes.getCreatedTim());
        holder.titleOut.setText(formatedTime);
    }

    @Override
    public int getItemCount() {
        return noteslist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titleOut;
        TextView descriptionOut;
        TextView timeOut;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            timeOut=itemView.findViewById(R.id.titleOut);
            descriptionOut=itemView.findViewById(R.id.DescriptionOut);
            timeOut=itemView.findViewById(R.id.createdTimOut);

        }
    }
}
