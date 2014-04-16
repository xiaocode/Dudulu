package com.dudulu.app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

/**
 * Created by Vincent on 4/14/14.
 */
public class ListViewAdapter extends ArrayAdapter<RowItem> {
    private ImageLoader imageLoader;
    private Context context;
    private int layoutResourceId;
    private View current_view;
    private Item current_item;
    ArrayList<RowItem> data = new ArrayList<RowItem>();

    public ListViewAdapter(Context context, int resource, ArrayList<RowItem> objects, ImageLoader imageLoader) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.data = objects;
        this.imageLoader = imageLoader;
    }

    @Override
    public RowItem getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewItem = convertView;

        if(viewItem==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            viewItem = inflater.inflate(R.layout.listview_item,parent,false);
        }else{
            viewItem = convertView;
        }

        RowItem row = data.get(position);
        Item item0 = row.getItem0();
        Item item1 = row.getItem1();
        Item item2 = row.getItem2();

        LinearLayout column0 = (LinearLayout)viewItem.findViewById(R.id.layout_column0);
        NetworkImageView thumb0 = (NetworkImageView)viewItem.findViewById(R.id.imageView0);
        TextView title0 = (TextView)viewItem.findViewById(R.id.textView0);

        LinearLayout column1 = (LinearLayout)viewItem.findViewById(R.id.layout_column1);
        NetworkImageView thumb1 = (NetworkImageView)viewItem.findViewById(R.id.imageView1);
        TextView title1 = (TextView)viewItem.findViewById(R.id.textView1);

        LinearLayout column2 = (LinearLayout)viewItem.findViewById(R.id.layout_column2);
        NetworkImageView thumb2 = (NetworkImageView)viewItem.findViewById(R.id.imageView2);
        TextView title2 = (TextView)viewItem.findViewById(R.id.textView2);


        if (item0.getThumb() != null) {
            thumb0.setImageUrl(item0.getThumb(), imageLoader);
        } else {
            thumb0.setImageResource(R.drawable.ic_launcher);
        }
        title0.setText(item0.getTitle());

        if (item1.getThumb() != null) {
            thumb1.setImageUrl(item1.getThumb(),imageLoader);
        } else {
            thumb1.setImageResource(R.drawable.ic_launcher);
        }
        title1.setText(item1.getTitle());

        if (item2.getThumb() != null) {
            thumb2.setImageUrl(item2.getThumb(),imageLoader);
        } else {
            thumb2.setImageResource(R.drawable.ic_launcher);
        }
        title2.setText(item2.getTitle());

        column0.setOnClickListener(new imageViewClickListener(viewItem, position, item0));
        column1.setOnClickListener(new imageViewClickListener(viewItem, position, item1));
        column2.setOnClickListener(new imageViewClickListener(viewItem, position, item2));

        return viewItem;
    }

    class imageViewClickListener implements View.OnClickListener {
        View convertView;
        int position;
        Item column;

        imageViewClickListener(View convertView, int position, Item column) {
            this.convertView = convertView;
            this.position = position;
            this.column = column;
        }

        @Override
        public void onClick(View view) {
            if (current_item == column) {
                current_view.findViewById(R.id.expand_layout).setVisibility(View.GONE);
                current_item = null;
                return;
            }

            if (current_item != null) {
                current_view.findViewById(R.id.expand_layout).setVisibility(View.GONE);
            }

            current_item = column;
            current_view = convertView;
            RelativeLayout layout = (RelativeLayout) convertView.findViewById(R.id.expand_layout);
            layout.setVisibility(View.VISIBLE);

            TextView title = (TextView)layout.findViewById(R.id.textView_title);
            TextView subtitle = (TextView)layout.findViewById(R.id.textView_subtitle);
            TextView intro = (TextView)layout.findViewById(R.id.textView_intro);
            Button download = (Button)layout.findViewById(R.id.download_button);
            Button enter = (Button)layout.findViewById(R.id.enter_button);

            title.setText(column.getTitle());
            subtitle.setText(column.getSubtitle());
            intro.setText(column.getTitle());

        }
    }
}
