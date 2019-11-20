package com.anfly.summary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.anfly.summary.R;
import com.anfly.summary.bean.ExpandbleListviewBean;

import java.util.ArrayList;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private ArrayList<ExpandbleListviewBean.DataBean> list;
    private Context context;
    private final LayoutInflater inflater;

    public ExpandableListViewAdapter(ArrayList<ExpandbleListviewBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return list.get(i).getChildren().size();
    }

    @Override
    public Object getGroup(int i) {
        return list.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return list.get(i).getChildren().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.item_expandablelistview_group, null);
            TextView tv_group = view.findViewById(R.id.tv_group);
            TextView tv_order = view.findViewById(R.id.tv_order);
            ExpandbleListviewBean.DataBean dataBean = list.get(i);
            tv_group.setText(dataBean.getName());
            tv_order.setText((i + 1) + " / " + list.size());
        }
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.item_expandablelistview_child, null);
            TextView tv_name = view.findViewById(R.id.tv_name);
            TextView tv_course_id = view.findViewById(R.id.tv_course_id);
            ExpandbleListviewBean.DataBean.ChildrenBean childrenBean = list.get(i).getChildren().get(i1);
            tv_name.setText(childrenBean.getName());
            tv_course_id.setText("CourseId:" + childrenBean.getCourseId());
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
