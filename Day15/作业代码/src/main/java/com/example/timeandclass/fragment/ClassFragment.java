package com.example.timeandclass.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.timeandclass.R;
import com.example.timeandclass.SearchActivity;

public class ClassFragment extends Fragment implements View.OnClickListener {
    private RadioButton mButtonFenlei;
    private RadioButton mButtonSort;
    private RadioButton mButtonSelect;
    private RadioGroup mGroupClass;
    private PopupWindow popupWindow;
    private boolean isFindFenLei;
    private boolean isFindSort;
    RadioButton chuyi = null;
    RadioButton chuer = null;
    RadioButton chusan = null;
    RadioButton gaoyi = null;
    RadioButton gaoer = null;
    RadioButton china = null;
    RadioButton math = null;
    RadioButton english = null;
    RadioButton wuli = null;
    RadioButton huaxue = null;
    Button clear = null;
    Button complete = null;
    private View fenlei_inflate = null;
    private ImageView mSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.class_fragmnet, null);
        initView(inflate);
        initListener();
        popupWindow = null;
        return inflate;
    }

    private void initListener() {
        mGroupClass.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.fenlei_button:

                        //判断其他的PopUpWindow是否展示
                        ifPopShowing();

                        /**
                         * 展示PopUpWindow
                         */
                        fenlei_inflate = LayoutInflater.from(getActivity()).inflate(R.layout.fenlei_pop, null);


                        findFenLeiPop(fenlei_inflate);
                        popupWindow = new PopupWindow(fenlei_inflate, LinearLayout.LayoutParams.MATCH_PARENT, 600);
                        popupWindow.showAsDropDown(mButtonFenlei);

                        break;
                    case R.id.sort_button:
                        ifPopShowing();
                        /**
                         * 展示PopUpWindow
                         */
                        View sort_inflate = LayoutInflater.from(getActivity()).inflate(R.layout.sort, null);

                        popupWindow = new PopupWindow(sort_inflate, LinearLayout.LayoutParams.MATCH_PARENT, 600);
                        popupWindow.showAsDropDown(mButtonFenlei);

                        break;
                    case R.id.select_button:
                        ifPopShowing();
                        /**
                         * 展示PopUpWindow
                         */
                        View select_inflate = LayoutInflater.from(getActivity()).inflate(R.layout.select_pop, null);

                        popupWindow = new PopupWindow(select_inflate, LinearLayout.LayoutParams.MATCH_PARENT, 600);
                        popupWindow.showAsDropDown(mButtonFenlei);

                        break;
                }
            }
        });
    }

    /**
     * 找到分类PopUpWindow；里面的控件设置
     *
     * @param fenlei_inflate
     */
    private void findFenLeiPop(View fenlei_inflate) {


        chuyi = fenlei_inflate.findViewById(R.id.chuyi);
        chuer = fenlei_inflate.findViewById(R.id.chuer);
        chusan = fenlei_inflate.findViewById(R.id.chusan);
        gaoyi = fenlei_inflate.findViewById(R.id.gaoyi);
        gaoer = fenlei_inflate.findViewById(R.id.gaoer);
        china = fenlei_inflate.findViewById(R.id.china);
        math = fenlei_inflate.findViewById(R.id.math);
        english = fenlei_inflate.findViewById(R.id.english);
        wuli = fenlei_inflate.findViewById(R.id.wuli);
        huaxue = fenlei_inflate.findViewById(R.id.huaxue);
        clear = fenlei_inflate.findViewById(R.id.clear);
        complete = fenlei_inflate.findViewById(R.id.complete);
        isFindFenLei = true;
        final RadioButton finalGaoyi = gaoyi;
        final RadioButton finalGaoer = gaoer;
        chuyi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    finalGaoer.setChecked(false);
                }
            }
        });
        chuer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                finalGaoer.setChecked(false);
            }
        });
        chusan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                finalGaoer.setChecked(false);
            }
        });
        gaoyi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                finalGaoer.setChecked(false);
            }
        });
        final RadioButton finalChuyi = chuyi;
        final RadioButton finalChuer1 = chuer;
        final RadioButton finalChusan = chusan;
        gaoer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                finalChuyi.setChecked(false);
                finalChuer1.setChecked(false);
                finalChusan.setChecked(false);
                finalGaoyi.setChecked(false);
                gaoer.setChecked(true);
                gaoer.setChecked(isChecked);
            }
        });
        final RadioButton finalHuaxue = huaxue;
        china.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                finalHuaxue.setChecked(false);
            }
        });
        math.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                finalHuaxue.setChecked(false);
            }
        });
        english.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                finalHuaxue.setChecked(false);
            }
        });
        wuli.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                finalHuaxue.setChecked(false);
            }
        });
        final RadioButton finalChina = china;
        final RadioButton finalWuli = wuli;
        final RadioButton finalEnglish = english;
        final RadioButton finalMath = math;
        huaxue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                finalChina.setChecked(false);
                finalMath.setChecked(false);
                finalEnglish.setChecked(false);
                finalWuli.setChecked(false);
                huaxue.setChecked(true);
            }
        });

        //按钮点击事件处理
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalChina.setChecked(false);
                finalMath.setChecked(false);
                finalEnglish.setChecked(false);
                finalWuli.setChecked(false);
                finalHuaxue.setChecked(false);
                finalChuyi.setChecked(false);
                finalChuer1.setChecked(false);
                finalChusan.setChecked(false);
                finalGaoyi.setChecked(false);
                finalGaoer.setChecked(false);
            }
        });
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    private boolean ifPopShowing() {
        /**
         * 判断是否正在展示其它的PopUpWindow
         */
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            return true;
        }
        return false;
    }

    private void initView(@NonNull final View itemView) {
        mButtonFenlei = (RadioButton) itemView.findViewById(R.id.fenlei_button);
        mButtonSort = (RadioButton) itemView.findViewById(R.id.sort_button);
        mButtonSelect = (RadioButton) itemView.findViewById(R.id.select_button);
        mGroupClass = (RadioGroup) itemView.findViewById(R.id.class_group);
        mSearch = (ImageView) itemView.findViewById(R.id.search);
        mSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search:
                // TODO 20/06/17
                toSearch();
                break;
            default:
                break;
        }
    }

    private void toSearch() {
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        startActivity(intent);
    }
}
