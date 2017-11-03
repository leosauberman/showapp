package br.com.series.showapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADM on 02/11/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {
    private ArrayList<Serie> arrayList;
    private Context c;

    class RecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView image = (ImageView)  itemView.findViewById(R.id.imageView);
        TextView name = (TextView) itemView.findViewById(R.id.name);
        CheckBox check = (CheckBox) itemView.findViewById(R.id.checkBox);
        TextView desc = (TextView) itemView.findViewById(R.id.desc);

        RecyclerHolder(View itemView) {
            super(itemView);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

        }
    }

    public RecyclerAdapter(ArrayList<Serie> arrayList, Context context) {
        this.c = context;
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_child, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.RecyclerHolder holder, int position) {
        holder.name.setText(arrayList.get(position).getNome());
        holder.desc.setText(arrayList.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
