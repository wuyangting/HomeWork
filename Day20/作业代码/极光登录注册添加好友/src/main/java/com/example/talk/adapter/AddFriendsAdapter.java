package com.example.talk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.talk.R;

import java.util.ArrayList;

public class AddFriendsAdapter extends RecyclerView.Adapter {
    private ArrayList<String> name=new ArrayList<>();
    private Context context;

    public AddFriendsAdapter(Context context) {
        this.context = context;
    }
public void addList(ArrayList<String> data){
        this.name.addAll(data);
        notifyDataSetChanged();
}
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.add_rec, parent, false);
        return new Holder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Holder holder1= (Holder) holder;
        holder1.name.setText(name.get(position));
        holder1.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.isAdd(position,name.get(position),true);
            }
        });
        holder1.noAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.isAdd(position,name.get(position),false);
            }
        });
    }
public void delete(int posi){
        name.remove(posi);
        notifyDataSetChanged();
}
    @Override
    public int getItemCount() {
        return name.size();
    }
    public class Holder extends RecyclerView.ViewHolder {
        TextView name;
        Button add;
        Button noAdd;
        public Holder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            add=itemView.findViewById(R.id.agreeadd);
            noAdd=itemView.findViewById(R.id.noagreeadd);
        }
    }
    onClick onClick;

    public void setOnClick(AddFriendsAdapter.onClick onClick) {
        this.onClick = onClick;
    }

    public interface onClick{
        void isAdd(int posi,String name,boolean isAddOrNo);
    }
}
