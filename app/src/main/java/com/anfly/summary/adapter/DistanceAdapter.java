package com.anfly.summary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anfly.summary.R;
import com.anfly.summary.bean.FoodBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DistanceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<FoodBean.DataBean> list;
    private Context context;
    private int VIEW_TYPE_ONE = 1;
    private int VIEW_TYPE_TWO = 2;
    private final LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public DistanceAdapter(ArrayList<FoodBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ONE) {
            View view = inflater.inflate(R.layout.item_multi_one, null);
            return new ViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_multi_two, null);
            return new ViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = holder.getItemViewType();
        FoodBean.DataBean dataBean = list.get(position);
        if (itemViewType == VIEW_TYPE_ONE) {
            ViewHolder viewHolder = (ViewHolder) holder;
            Glide.with(context).load(dataBean.getPic()).into(viewHolder.iv_item_one);
            viewHolder.tv_one_title.setText(dataBean.getTitle());
            viewHolder.tv_two_title.setText(dataBean.getFood_str());
        } else {
            ViewHolder2 viewHolder2 = (ViewHolder2) holder;
            Glide.with(context).load(dataBean.getPic()).into(viewHolder2.iv_item_one);
            viewHolder2.tv_one_title.setText(dataBean.getTitle());
            viewHolder2.tv_two_title.setText(dataBean.getFood_str());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return VIEW_TYPE_ONE;
        } else {
            return VIEW_TYPE_TWO;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_item_one;
        public TextView tv_one_title;
        public TextView tv_two_title;

        public ViewHolder(View rootView) {
            super(rootView);
            this.iv_item_one = (ImageView) rootView.findViewById(R.id.iv_item_one);
            this.tv_one_title = (TextView) rootView.findViewById(R.id.tv_one_title);
            this.tv_two_title = (TextView) rootView.findViewById(R.id.tv_two_title);
        }

    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        public ImageView iv_item_one;
        public TextView tv_one_title;
        public TextView tv_two_title;

        public ViewHolder2(View rootView) {
            super(rootView);
            this.iv_item_one = (ImageView) rootView.findViewById(R.id.iv_item_one);
            this.tv_one_title = (TextView) rootView.findViewById(R.id.tv_one_title);
            this.tv_two_title = (TextView) rootView.findViewById(R.id.tv_two_title);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}
