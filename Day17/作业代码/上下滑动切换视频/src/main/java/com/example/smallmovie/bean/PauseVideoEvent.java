package com.example.smallmovie.bean;

/**
 * create by libo
 * create on 2020-05-21
 * description
 */
public class PauseVideoEvent {
    private boolean playOrPause;

    public PauseVideoEvent(boolean playOrPause) {
        this.playOrPause = playOrPause;
    }

    public boolean isPlayOrPause() {
        return playOrPause;
    }
}
