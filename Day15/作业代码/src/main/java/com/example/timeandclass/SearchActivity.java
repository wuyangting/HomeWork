package com.example.timeandclass;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timeandclass.bean.SearchBean;
import com.example.timeandclass.greendaodemo.db.SearchBeanDao;
import com.example.timeandclass.widget.FlowLayout;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mBackSearch;
    private TextView mSearchTv;
    private EditText mSearchEt;
    private ImageView mClean;
    private FlowLayout mFlow;
    private ConstraintLayout mConFlow;
    private RecyclerView mRecSearch;
    private ConstraintLayout mConRec;
    private SearchBeanDao searchBeanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initListener();
    }

    private void initListener() {
        //输入框监听
        mSearchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //文字改变监听
                if (TextUtils.isEmpty(s)) {
                    mConFlow.setVisibility(View.VISIBLE);
                    mConRec.setVisibility(View.GONE);
                } else {
                    mConFlow.setVisibility(View.GONE);
                    mConRec.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initView() {
        mBackSearch = (ImageView) findViewById(R.id.search_back);
        mBackSearch.setOnClickListener(this);
        mSearchTv = (TextView) findViewById(R.id.tv_search);
        mSearchTv.setOnClickListener(this);
        mSearchEt = (EditText) findViewById(R.id.et_search);
        mClean = (ImageView) findViewById(R.id.clean);
        mClean.setOnClickListener(this);
        mFlow = (FlowLayout) findViewById(R.id.flow);
        mConFlow = (ConstraintLayout) findViewById(R.id.flow_con);
        mRecSearch = (RecyclerView) findViewById(R.id.search_rec);
        mConRec = (ConstraintLayout) findViewById(R.id.rec_con);
        searchBeanDao = BaseApplication.getsInstance().getDaoSession().getSearchBeanDao();
        updateDataBase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_back:
                finish();
                // TODO 20/06/17
                break;
            case R.id.tv_search:
                addDataBase();
                // TODO 20/06/17
                break;
            case R.id.clean:
                // TODO 20/06/17
                searchBeanDao.deleteAll();
                updateDataBase();
                break;
            default:
                break;
        }
    }

    private void addDataBase() {
        String trim = mSearchEt.getText().toString().trim();
        if(TextUtils.isEmpty(trim)){
            Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
        }else {
            SearchBean searchBean = new SearchBean();
            searchBean.setKeyWord(trim);
            searchBean.setTime(System.currentTimeMillis());
            searchBeanDao.insertOrReplace(searchBean);
            updateDataBase();
        }
    }

    private void updateDataBase() {
        List<SearchBean> searchBeans = searchBeanDao.loadAll();
        //排序
        mFlow.removeAllViews();
        if (searchBeans != null && searchBeans.size() > 0) {
            Collections.sort(searchBeans, new Comparator<SearchBean>() {
                @Override
                public int compare(SearchBean o1, SearchBean o2) {
                    //升序 时间小的在前面 返回大于零的数 这里是升序
                    //降序 返回小于零的数
                    return (int) (o2.getTime() - o1.getTime());
                }
            });
            //界面展示
            for (int i = 0; i < searchBeans.size(); i++) {
                TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.item_label, null);
                textView.setText(searchBeans.get(i).getKeyWord());
                mFlow.addView(textView);
            }
        }

    }
}