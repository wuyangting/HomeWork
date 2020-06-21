package com.example.slideconflicttest.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slideconflicttest.R;
import com.example.slideconflicttest.RlvAdapter;

import java.util.ArrayList;
import java.util.List;

public class UpOneFragment extends Fragment implements View.OnClickListener {
    private RecyclerView mRlv;
    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;
    private Button mBtn4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_upone, container, false);
        initView(inflate);
        initRlv();
        return inflate;
    }

    private void initRlv() {
        final List<String> strings = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            strings.add(i + 1 + "-----------");
        }
        mRlv.setLayoutManager(new LinearLayoutManager(getActivity()));
        RlvAdapter adapter = new RlvAdapter(getContext(), strings);
        mRlv.setAdapter(adapter);
        adapter.setOnItemClick(new RlvAdapter.OnItemClick() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getActivity(), strings.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView(@NonNull final View itemView) {
        mRlv = (RecyclerView) itemView.findViewById(R.id.rlv);
        mBtn1 = (Button) itemView.findViewById(R.id.btn1);
        mBtn1.setOnClickListener(this);
        mBtn2 = (Button) itemView.findViewById(R.id.btn2);
        mBtn2.setOnClickListener(this);
        mBtn3 = (Button) itemView.findViewById(R.id.btn3);
        mBtn3.setOnClickListener(this);
        mBtn4 = (Button) itemView.findViewById(R.id.btn4);
        mBtn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                // TODO 20/06/21
                Toast.makeText(getActivity(), "btn1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn2:
                // TODO 20/06/21
                Toast.makeText(getActivity(), "btn2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn3:
                // TODO 20/06/21
                Toast.makeText(getActivity(), "btn3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn4:
                // TODO 20/06/21
                Toast.makeText(getActivity(), "btn4", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
