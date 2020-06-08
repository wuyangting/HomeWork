package com.example.adapterfeng;

import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RecAdapter extends BaseMultiItemQuickAdapter<Bean.DataBean.DatasBean, BaseViewHolder> {
    public RecAdapter(@Nullable List<Bean.DataBean.DatasBean> data) {
        super(data);
        addItemType(Bean.DataBean.DatasBean.TYPE_LEFT,R.layout.item_left);
        addItemType(Bean.DataBean.DatasBean.TYPE_RIGHT,R.layout.item_right);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Bean.DataBean.DatasBean datasBean) {
        switch (baseViewHolder.getItemViewType()){
            case Bean.DataBean.DatasBean.TYPE_LEFT:
                left(baseViewHolder,datasBean);
                break;
            case Bean.DataBean.DatasBean.TYPE_RIGHT:
                right(baseViewHolder,datasBean);
                break;
        }
    }

    private void right(BaseViewHolder baseViewHolder, Bean.DataBean.DatasBean datasBean) {
        baseViewHolder.setText(R.id.right_tv,datasBean.getTitle());
        Glide.with(getContext()).load(datasBean.getEnvelopePic()).into((ImageView) baseViewHolder.getView(R.id.right_iv));
    }

    private void left(BaseViewHolder baseViewHolder, Bean.DataBean.DatasBean datasBean) {
            baseViewHolder.setText(R.id.left_tv,datasBean.getTitle());
        Glide.with(getContext()).load(datasBean.getEnvelopePic()).into((ImageView) baseViewHolder.getView(R.id.left_iv));
    }
}