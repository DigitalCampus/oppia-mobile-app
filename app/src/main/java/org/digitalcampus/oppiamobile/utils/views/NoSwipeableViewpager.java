package org.digitalcampus.oppiamobile.utils.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class NoSwipeableViewpager extends ViewPager {

    private boolean canScroll;

    public NoSwipeableViewpager(Context context) {
        super(context);
        canScroll = false;
    }
    public NoSwipeableViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
        canScroll = false;
    }
    public void setCanScroll(boolean canScroll) {
        this.canScroll = canScroll;
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return canScroll && super.onTouchEvent(ev);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return canScroll && super.onInterceptTouchEvent(ev);
    }

}