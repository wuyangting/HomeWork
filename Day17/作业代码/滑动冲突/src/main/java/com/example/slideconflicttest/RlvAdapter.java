package com.example.slideconflicttest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RlvAdapter extends RecyclerView.Adapter<RlvAdapter.ViewHolder> {
    public Context mContext;
    public List<String> mList;

    public RlvAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.rlv_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.mTv.setText(mList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClick != null){
                    mOnItemClick.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.tv);
        }
    }
    OnItemClick mOnItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        mOnItemClick = onItemClick;
    }
    public interface OnItemClick{
        void onClick(int position);
    }
}
