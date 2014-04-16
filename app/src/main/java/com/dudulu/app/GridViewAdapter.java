package com.dudulu.app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vincent on 4/12/14.
 */
public class GridViewAdapter extends ArrayAdapter<Item> {
    private Context context;
    private int layoutResourceId;
    ArrayList<Item> data = new ArrayList<Item>();

    public GridViewAdapter(Context context, int resource, ArrayList<Item> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.data = objects;
    }

    @Override
    public Item getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewItem = convertView;

        if(viewItem==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            viewItem = inflater.inflate(R.layout.gridview_row,parent,false);
        }else{
            viewItem = convertView;
        }

        ImageView thumb = (ImageView)viewItem.findViewById(R.id.item_thumb);
        TextView title = (TextView)viewItem.findViewById(R.id.item_title);

        Item item = data.get(position);

        thumb.setImageResource(R.drawable.ic_launcher);
        title.setText(item.getTitle());

        return viewItem;
    }
}
