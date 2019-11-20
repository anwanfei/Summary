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
import com.anfly.summary.bean.FoodDbBean;
import com.bumptech.glide.Glide;

import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<FoodDbBean> list;
    private Context context;
    private final LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public CollectionAdapter(List<FoodDbBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_multi_one, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FoodDbBean dataBean = list.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        Glide.with(context).load(dataBean.getUrl()).into(viewHolder.iv_item_one);
        viewHolder.tv_one_title.setText(dataBean.getTitle());
        viewHolder.tv_two_title.setText(dataBean.getDes());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (onItemLongClickListener != null) {
                    onItemLongClickListener.onItemLongClick(position);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
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

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }
}
