package com.example.smallmovie;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smallmovie.adapter.VideoAdapter;
import com.example.smallmovie.bean.CurUserBean;
import com.example.smallmovie.bean.DataCreate;
import com.example.smallmovie.bean.MainPageChangeEvent;
import com.example.smallmovie.bean.PauseVideoEvent;
import com.example.smallmovie.manager.OnViewPagerListener;
import com.example.smallmovie.manager.ViewPagerLayoutManager;
import com.example.smallmovie.utils.OnVideoControllerListener;
import com.example.smallmovie.utils.RxBus;
import com.example.smallmovie.view.ControllerView;
import com.example.smallmovie.view.FullScreenVideoView;
import com.example.smallmovie.view.LikeView;

import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRec;
    private ViewPagerLayoutManager viewPagerLayoutManager;
    private VideoAdapter adapter;
    private FullScreenVideoView videoView;
    private ImageView ivCurCover;
    private int curPlayPos=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRec = (RecyclerView) findViewById(R.id.rec);

        adapter = new VideoAdapter(this, DataCreate.datas);
        mRec.setAdapter(adapter);
        videoView = new FullScreenVideoView(this);

        setViewPagerLayoutManager();

//        setRefreshEvent();

        //监听播放或暂停事件
        RxBus.getDefault().toObservable(PauseVideoEvent.class)
                .subscribe((Action1<PauseVideoEvent>) event -> {
                    if (event.isPlayOrPause()) {
                        videoView.start();
                    } else {
                        videoView.pause();
                    }
                });
    }
    private void setViewPagerLayoutManager() {
        viewPagerLayoutManager = new ViewPagerLayoutManager(this);
        mRec.setLayoutManager(viewPagerLayoutManager);
        mRec.scrollToPosition(0);
        viewPagerLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {
                playCurVideo(0);
            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                if (ivCurCover != null) {
                    ivCurCover.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position, boolean isBottom) {
                playCurVideo(position);
            }
        });
        playCurVideo(0);
    }

    private void setRefreshEvent() {
//        refreshLayout.setColorSchemeResources(R.color.color_link);
//        refreshLayout.setOnRefreshListener(() -> new CountDownTimer(1000, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//
//            }
//
//            @Override
//            public void onFinish() {
//                refreshLayout.setRefreshing(false);
//            }
//        }.start());
//    }
    }

    private void playCurVideo(int position) {
        if (position == curPlayPos) {
            return;
        }

        View itemView = viewPagerLayoutManager.findViewByPosition(position);
        if (itemView == null) {
            return;
        }

        ViewGroup rootView = itemView.findViewById(R.id.rl_container);
        LikeView likeView = rootView.findViewById(R.id.likeview);
        ControllerView controllerView = rootView.findViewById(R.id.controller);
        ImageView ivPlay = rootView.findViewById(R.id.iv_play);
        ImageView ivCover = rootView.findViewById(R.id.iv_cover);
        ivPlay.setAlpha(0.4f);

        //播放暂停事件
        likeView.setOnPlayPauseListener(() -> {
            if (videoView.isPlaying()) {
                videoView.pause();
                ivPlay.setVisibility(View.VISIBLE);
            } else {
                videoView.start();
                ivPlay.setVisibility(View.GONE);
            }
        });

        //评论点赞事件
        likeShareEvent(controllerView);

        //切换播放视频的作者主页数据
        RxBus.getDefault().post(new CurUserBean(DataCreate.datas.get(position).getUserBean()));

        curPlayPos = position;

        //切换播放器位置
        dettachParentView(rootView);

        autoPlayVideo(curPlayPos, ivCover);
    }

    /**
     * 移除videoview父view
     */
    private void dettachParentView(ViewGroup rootView) {
        //1.添加videoview到当前需要播放的item中,添加进item之前，保证ijkVideoView没有父view
        ViewGroup parent = (ViewGroup) videoView.getParent();
        if (parent != null) {
            parent.removeView(videoView);
        }
        rootView.addView(videoView, 0);
    }

    /**
     * 自动播放视频
     */
    private void autoPlayVideo(int position, ImageView ivCover) {
        String bgVideoPath = "android.resource://" + this.getPackageName() + "/" + DataCreate.datas.get(position).getVideoRes();
        videoView.setVideoPath(bgVideoPath);
        videoView.start();
        videoView.setOnPreparedListener(mp -> {
            mp.setLooping(true);

            //延迟取消封面，避免加载视频黑屏
            new CountDownTimer(200, 200) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    ivCover.setVisibility(View.GONE);
                    ivCurCover = ivCover;
                }
            }.start();
        });
    }
    /**
     * 用户操作事件
     */
    private void likeShareEvent(ControllerView controllerView) {
        controllerView.setListener(new OnVideoControllerListener() {
            @Override
            public void onHeadClick() {
                RxBus.getDefault().post(new MainPageChangeEvent(1));
            }

            @Override
            public void onLikeClick() {

            }

            @Override
            public void onCommentClick() {
                CommentDialog commentDialog = new CommentDialog();
                commentDialog.show(getSupportFragmentManager(), "");
            }

            @Override
            public void onShareClick() {
                Toast.makeText(MainActivity.this, "23123", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
