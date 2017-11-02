package br.com.series.showapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ADM on 02/11/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {
    private Context c;


    class RecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView image = (ImageView)  itemView.findViewById(R.id.imageView);
        TextView name = (TextView) itemView.findViewById(R.id.name);

        RecyclerHolder(View itemView) {
            super(itemView);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public RecyclerAdapter(Context c) {
        this.c = c;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_child, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.RecyclerHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
