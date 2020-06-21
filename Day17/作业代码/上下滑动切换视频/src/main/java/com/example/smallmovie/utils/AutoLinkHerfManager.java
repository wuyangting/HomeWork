package com.example.smallmovie.utils;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.example.smallmovie.utils.autolinktextview.AutoLinkMode;
import com.example.smallmovie.utils.autolinktextview.AutoLinkOnClickListener;
import com.example.smallmovie.utils.autolinktextview.AutoLinkTextView;


/**
 * create by libo
 * create on 2020-05-21
 * description
 */
public class AutoLinkHerfManager {
    /**
     * 设置正文内容
     *
     * @param content
     */
    public static void setContent(String content, AutoLinkTextView autoLinkTextView) {

        if (TextUtils.isEmpty(content)) return;

        autoLinkTextView.setVisibility(View.VISIBLE);
        autoLinkTextView.addAutoLinkMode(AutoLinkMode.MODE_HASHTAG, AutoLinkMode.MODE_MENTION, AutoLinkMode.MODE_URL);  //设置需要富文本的模式
        autoLinkTextView.setText(content);
        autoLinkTextView.setAutoLinkOnClickListener(new AutoLinkOnClickListener() {
            @Override
            public void onAutoLinkTextClick(AutoLinkMode autoLinkMode, String matchedText) {

                switch (autoLinkMode) {
                    case MODE_HASHTAG:
                        Log.i("minfo", "话题 " + matchedText);
                        break;
                    case MODE_MENTION:
                        Log.i("minfo", "at " + matchedText);
                        break;
                }
            }
        });
    }

}
