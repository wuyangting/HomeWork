package com.example.timeandclass.fragment.yueke_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.timeandclass.R;

public class DaiShnagKeFragment extends Fragment {
    private TextView mYukeText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.daishangke, null);
        initView(inflate);
        return inflate;
    }

    private void initView(@NonNull final View itemView) {
        mYukeText = (TextView) itemView.findViewById(R.id.text_yuke);
        Bundle arguments = getArguments();
        if(arguments!=null){
            int id = arguments.getInt("Id");
            switch (id){
                case 0:
                    mYukeText.setText("待上课");
                    break;
                case 1:
                    mYukeText.setText("已上课");
                    break;
                case 2:
                    mYukeText.setText("已取消");
                    break;
            }
        }

    }
}
