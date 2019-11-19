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
import com.anfly.summary.bean.BannerBean;
import com.anfly.summary.bean.HomeListBean;
import com.anfly.summary.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int VIEW_TYPE_ONE = 1;
    private int VIEW_TYPE_TWO = 2;
    private ArrayList<BannerBean.DataBean> banners;
    private ArrayList<HomeListBean.DataBean.DatasBean> rereults;
    private Context context;
    private final LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public HomeAdapter(ArrayList<BannerBean.DataBean> banners, ArrayList<HomeListBean.DataBean.DatasBean> rereults, Context context) {
        this.banners = banners;
        this.rereults = rereults;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ONE) {
            View view = inflater.inflate(R.layout.item_banner, null);
            return new BannerViewHolder(view);
        } else {
            View view2 = inflater.inflate(R.layout.item_list, null);
            return new ListViewHolder(view2);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = holder.getItemViewType();
        if (itemViewType == VIEW_TYPE_ONE) {
            ArrayList<String> images = new ArrayList<>();
            ArrayList<String> titiles = new ArrayList<>();
            for (int i = 0; i < banners.size(); i++) {
                images.add(banners.get(i).getImagePath());
                titiles.add(banners.get(i).getTitle());
            }

            BannerViewHolder viewHolder = (BannerViewHolder) holder;

            viewHolder.bannerItem.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                    .setBannerTitles(titiles)
                    .setImages(images)
                    .setImageLoader(new GlideImageLoader())
                    .start();
        } else {
            HomeListBean.DataBean.DatasBean datasBean = rereults.get(position - 1);
            ListViewHolder listViewHolder = (ListViewHolder) holder;
            listViewHolder.tvChapterName.setText(datasBean.getSuperChapterName() + "/" + datasBean.getChapterName());
            listViewHolder.tvTime.setText(datasBean.getNiceDate());
            listViewHolder.tvTitle.setText(datasBean.getTitle());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position -1);
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_ONE;
        } else {
            return VIEW_TYPE_TWO;
        }
    }

    @Override
    public int getItemCount() {
        return rereults.size() + 1;
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner_item)
        Banner bannerItem;

        BannerViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_logo)
        ImageView ivLogo;
        @BindView(R.id.tv_chapter_name)
        TextView tvChapterName;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv_time)
        ImageView ivTime;
        @BindView(R.id.tv_time)
        TextView tvTime;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
