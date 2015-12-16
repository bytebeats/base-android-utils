package me.pc.mobile.helper.v14.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yixin on 15/10/12.
 */
public class RippleView extends View {
    /**
     * According to the test result in known devices, 130 is approximately OK at the max value.
     */
    private static final int MAX_VOLUME_RANGE = 130;
    /**
     * According to the test result in known devices, 25 is approximately OK at the min value.
     */
    private static final int BASE_VOLUME = 25;
    private static final int CODE_RIPPLE_OUTER = 0x1000;
    private static final int CODE_RIPPLE_INNER = 0x1001;
    private static final int WAVE_INTERVAL = 30;
    private int maxSize = 0;
    private int minSize = 0;
    private int outerRadius;
    private int innerRadius1;
    private int innerRadius2;
    private Paint outerPaint;
    private Paint innerPaint1;
    private Paint innerPaint2;
    private int mX;
    private int mY;
    private Handler mUIHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case CODE_RIPPLE_OUTER:
                    int radius = msg.arg1;
                    drawOuterRipple(radius);
                    radius = (radius + 10) % (maxSize / 2);//update outer ripple radius
                    ripplingAtDelay(radius, WAVE_INTERVAL);
                    return true;
                case CODE_RIPPLE_INNER:

                    return true;
            }
            return false;
        }
    });

    public RippleView(Context context) {
        this(context, null);
    }

    public RippleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RippleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        minSize = 0;
        outerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        outerPaint.setStyle(Paint.Style.STROKE);
        outerPaint.setStrokeWidth(2.5F);
        outerPaint.setColor(Color.parseColor("#dcdcdc"));

        innerPaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        innerPaint1.setStyle(Paint.Style.FILL);
        innerPaint1.setColor(Color.parseColor("#cdcdcd"));

        innerPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        innerPaint2.setStyle(Paint.Style.STROKE);
        innerPaint2.setStrokeWidth(4.0F);
        innerPaint2.setColor(Color.parseColor("#dcdcdc"));
    }

    public void updateVisualizer(double volume) {
        double ratio = volume / MAX_VOLUME_RANGE;
        double baseRatio = BASE_VOLUME / (double) MAX_VOLUME_RANGE;
        if (ratio <= baseRatio) {
            ratio = baseRatio + 0.05;
        }
        if (ratio >= 1.0) {
            ratio = 0.95;
        }
        this.innerRadius2 = (this.innerRadius1 = (int) (ratio * maxSize / 2.0));
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mX = getWidth() / 2;
        mY = getHeight() / 2;
        canvas.drawCircle(mX, mY, outerRadius, outerPaint);
        canvas.drawCircle(mX, mY, innerRadius1, innerPaint1);
        canvas.drawCircle(mX, mY, innerRadius2, innerPaint2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int widthWithoutPadding = width - getPaddingLeft() - getPaddingRight();
        int heightWithoutPadding = height - getPaddingTop() - getPaddingBottom();
        if (widthWithoutPadding > heightWithoutPadding) {
            maxSize = widthWithoutPadding;
        } else {
            maxSize = heightWithoutPadding;
        }
        setMeasuredDimension(widthWithoutPadding, heightWithoutPadding);
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    private void drawOuterRipple(int radius) {
        outerRadius = radius;
        if (outerRadius <= minSize / 2) {
            outerRadius = minSize / 2;
        }
        if (outerRadius >= maxSize / 2) {
            outerRadius = maxSize / 2;
        }
        invalidate();
    }

    public void startOuterRipple() {
        ripplingAtDelay(outerRadius, WAVE_INTERVAL);
    }

    private void ripplingAtDelay(int radius, long delay) {
        Message message = mUIHandler.obtainMessage();
        message.what = CODE_RIPPLE_OUTER;
        message.arg1 = radius;
        mUIHandler.sendMessageDelayed(message, delay);
    }

    public void clearRipples() {
        mUIHandler.removeMessages(CODE_RIPPLE_OUTER);
        outerRadius = 0;
        innerRadius2 = innerRadius1 = 0;
        invalidate();
    }
}
