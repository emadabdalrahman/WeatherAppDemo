package com.example.emad.weatherappdemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class ToolbarShadowView extends View  {

    private float mLineDividerThickness = 0.25f;
    private float mYScrollDuration = 100.0f;
    private RecyclerView mRecyclerView;
    private boolean mDividerLine;

    public ToolbarShadowView(Context context) {
        super(context);
    }

    public ToolbarShadowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        super.setBackgroundResource(R.drawable.shadow_bottom);
        setPivotY(0);
        if (mRecyclerView != null) updateStartPosition(mRecyclerView.computeVerticalScrollOffset());
    }

    private void updateStartPosition(int position) {
        if (position == 0) {
            if (getScaleY() != 0)
                if (mDividerLine) setScaleY(mLineDividerThickness); else setScaleY(0);
        } else if (position > mYScrollDuration) {
            if (getScaleY() != 1)
                setScaleY(1);
        } else  {
            if (mDividerLine && (position/mYScrollDuration) <= mLineDividerThickness) setScaleY(mLineDividerThickness);
            else setScaleY(position/ mYScrollDuration);
        }
    }

    public void setRecyclerView(RecyclerView view) {
        mRecyclerView = view;
      //  updateStartPosition(view.computeVerticalScrollOffset());
        view.addOnScrollListener(listener);
    }

    public void setScrollDuration(float scrollDuration) {
        this.mYScrollDuration = scrollDuration;
    }

    public void setLineDivider(boolean lineDivider) {
        this.mDividerLine = lineDivider;
    }

    public void setLineDividerThickness(float thickness) {
        this.mLineDividerThickness = thickness;
    }

    @Override
    public void setBackgroundResource(int resid) {
       throw new UnsupportedOperationException("can't change shadow drawable");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mRecyclerView.removeOnScrollListener(listener);
        mRecyclerView = null;
    }

    private RecyclerView.OnScrollListener listener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            int yTotal = recyclerView.computeVerticalScrollOffset();
            if (yTotal == 0) {
                if (getScaleY() != 0)
                    if (mDividerLine) setScaleY(mLineDividerThickness); else setScaleY(0);
            } else if (yTotal > mYScrollDuration) {
                if (getScaleY() != 1)
                    setScaleY(1);
            } else  {
                if (mDividerLine && (yTotal/mYScrollDuration) <= mLineDividerThickness) setScaleY(mLineDividerThickness);
                else setScaleY(yTotal/ mYScrollDuration);
            }
        }
    };

}
