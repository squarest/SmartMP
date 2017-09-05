package com.example.clevercafe.base.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clevercafe.R;

import java.util.ArrayList;

/**
 * Created by Chudofom on 17.10.16.
 */

public class DrawerListAdapter extends BaseExpandableListAdapter {
    private String[] groups;
    private ArrayList<String[]> childs;
    private Integer[] icons;

    public DrawerListAdapter(String[] groups, ArrayList<String[]> childs, Integer[] icons) {
        this.childs = childs;
        this.groups = groups;
        this.icons = icons;
    }

    @Override
    public int getGroupCount() {
        return groups.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (childs.get(groupPosition) != null) {
            return childs.get(groupPosition).length;
        } else return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childs.get(groupPosition)[childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.drawer_list_item, parent, false);
        TextView textView = view.findViewById(R.id.drawer_item_text_view);
        ImageView imageView = view.findViewById(R.id.drawer_item_image_view);
        textView.setText(groups[groupPosition]);
        imageView.setImageResource(icons[groupPosition]);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.drawer_list_subitem, parent, false);
        TextView textView = view.findViewById(R.id.drawer_subitem_text_view);
        textView.setText(childs.get(groupPosition)[childPosition]);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
