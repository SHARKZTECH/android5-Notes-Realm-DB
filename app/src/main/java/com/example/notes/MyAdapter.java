package com.example.notes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.text.DateFormat;

import io.realm.Realm;
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
        Log.d("Notes",notes.toString());

            holder.titleOut.setText(notes.getTitle().toString());
            holder.descriptionOut.setText(notes.getDescription().toString());
            String formatedTime = DateFormat.getDateTimeInstance().format(notes.getCreatedTim());
            holder.timeOut.setText(formatedTime);

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    PopupMenu menu=new PopupMenu(context,view);
                    menu.getMenu().add("DELETE");
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            if(menuItem.getTitle().equals("DELETE")){
                                Realm realm= Realm.getDefaultInstance();
                                realm.beginTransaction();
                                notes.deleteFromRealm();
                                realm.commitTransaction();
                                Toast.makeText(context, "Note Deleted", Toast.LENGTH_SHORT).show();
                            }
                            return true;
                        }
                    });
                    menu.show();
                    return true;
                }
            });
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
            titleOut=itemView.findViewById(R.id.titleOut);
            descriptionOut=itemView.findViewById(R.id.DescriptionOut);
            timeOut=itemView.findViewById(R.id.createdTimOut);

        }
    }
}
