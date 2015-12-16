package me.pc.mobile.helper.v14.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by Pan Chen on 12/16/15 : 11:39.
 * Company : www.CreditEase.cn;
 * Email : chenpan1@creditease.cn;
 * Motto : If you can take it, you can make it.
 * </p>
 * Triggers a event when scrolling reaches bottom.
 * <p/>
 * Usage:
 * <p/>
 * listView.setOnBottomReachedListener(
 * new ScrollListView.OnBottomReachedListener() {
 *
 * @Override public void onBottomReached() {
 * // do something
 * }
 * }
 * );
 * Use your own {#OnScrollListener}:
 * <p/>
 * listView.setOnScrollListener(
 * new AbsListView.OnScrollListener() {
 * @Override public void onScrollStateChanged(AbsListView view, int scrollState) {
 * // Stub
 * }
 * @Override public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
 * int totalItemCount) {
 * // Stub
 * }
 * }
 * );
 */
public class ExtListView extends ListView implements AbsListView.OnScrollListener {

    private OnBottomReachedListener mBottomReachedListener;
    private OnScrollListener mScrollListener;
    /**
     * Scroll position offset value to trigger earlier bottom reached events.
     * By default, it's 0;
     */
    private int mOffset = 0;

    public ExtListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        defineScrolling();
    }

    public ExtListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        defineScrolling();
    }

    public ExtListView(Context context) {
        super(context);
        defineScrolling();
    }

    /**
     * Defines scrolling behaviour by subscribing a scroll listener.
     */
    private void defineScrolling() {
        super.setOnScrollListener(this);
    }

    /**
     * Removes internal scroll listener.
     */
    public void reset() {
        super.setOnScrollListener(null);
    }

    @Override
    public void setOnScrollListener(OnScrollListener l) {
        mScrollListener = l;
    }

    // Listeners
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (mScrollListener != null) {
            mScrollListener.onScrollStateChanged(view, scrollState);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                         int totalItemCount) {
        if (mScrollListener != null) {
            mScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
        int position = firstVisibleItem + visibleItemCount;
        int limit = totalItemCount - mOffset;

//         Check if bottom has been reached
        if (position >= limit && totalItemCount > 0 && getChildAt(getChildCount() - 1).getBottom() <= getHeight()) {
            if (mBottomReachedListener != null) {
                mBottomReachedListener.onBottomReached();
            }
        }
    }

    // Getters & Setters
    public OnBottomReachedListener getOnBottomReachedListener() {
        return mBottomReachedListener;
    }

    public void setOnBottomReachedListener(OnBottomReachedListener listener) {
        this.mBottomReachedListener = listener;
    }

    public int getOffset() {
        return mOffset;
    }

    public void setOffset(int offset) {
        mOffset = offset;
    }

    /**
     * Event listener.
     */
    public interface OnBottomReachedListener {
        void onBottomReached();
    }
}
