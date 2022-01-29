package com.example.founderscompany.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.founderscompany.R;

import java.util.List;

public class ArrayAdapterCompany extends ArrayAdapter<ListItem> {

    private List<ListItem> listItems;
    private Context context;
    private LayoutInflater inflater;

    public ArrayAdapterCompany(Context context, int resource, List<ListItem> listItems, LayoutInflater inflater) {
        super(context, resource, listItems);
        this.context = context;
        this.inflater = inflater;
        this.listItems = listItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        ListItem listItem = listItems.get(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_view_item, null, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.iv_company);
            viewHolder.name = convertView.findViewById(R.id.tv_name_company);
            viewHolder.country = convertView.findViewById(R.id.tv_country_company);
            convertView.setTag(viewHolder);
        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(listItem.nameCompany);
        viewHolder.country.setText(listItem.country);
        viewHolder.imageView.setImageResource(listItem.getImageId());

        return convertView;
    }

    private static class ViewHolder {
        TextView name;
        TextView country;
        ImageView imageView;
    }
}
