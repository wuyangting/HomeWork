package com.example.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

public class MyButton extends androidx.appcompat.widget.AppCompatButton {
    int mLastX=0;
    int mLastY=0;
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                mLastX= (int) event.getX();
                mLastY= (int) event.getY();
                break;

            case MotionEvent.ACTION_MOVE:
               int mNowX= (int) event.getX();
               int mNowY= (int) event.getY();

                int parentX = (int) getX();
                int parenty = (int) getY();

                int finalX=mNowX-mLastX;
                int finalY=mNowY-mLastY;


                setTranslationX(parentX+finalX);
                setTranslationY(parenty+finalY);
                break;

            case MotionEvent.ACTION_UP:

                break;

        }

        return true;
    }

  public  float getLastX(){
        return getX();
  } public  float getLastY(){
        return getY();
  }

}
