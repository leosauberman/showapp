package br.com.series.showapp;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

/**
 * Created by leonardo.moraes on 27/10/2017.
 */

public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        private Activity mActivity;

        public ImageAdapter(Context c, Activity activity) {
            mContext = c;
            mActivity = activity;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public static class ViewHolder
        {
            public ImageView imgView;
            public TextView txtView;
        }

        // create a new ImageView for each item referenced by the Adapter
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder view;
            LayoutInflater inflater = mActivity.getLayoutInflater();

            if(convertView==null)
            {
                view = new ViewHolder();
                convertView = inflater.inflate(R.layout.gridview_row, null);

                view.txtView = (TextView) convertView.findViewById(R.id.textView1);
                view.imgView = (ImageView) convertView.findViewById(R.id.imageView1);

                convertView.setTag(view);
            }
            else
            {
                view = (ViewHolder) convertView.getTag();
            }

            view.txtView.setText(mNames[position]);
            view.imgView.setImageResource(mThumbIds[position]);

            return convertView;
        }

        // references to our images
        private Integer[] mThumbIds = {
                R.drawable.acao, R.drawable.comedia,
                R.drawable.cult, R.drawable.drama,
                R.drawable.heroi, R.drawable.romance,
                R.drawable.terror
        };
        private Integer[] mNames = {
                R.string.acao, R.string.comedia,
                R.string.cult, R.string.drama,
                R.string.heroi, R.string.romance,
                R.string.terror
        };
}