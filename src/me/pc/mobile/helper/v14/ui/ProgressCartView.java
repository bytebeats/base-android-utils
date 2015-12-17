package me.pc.mobile.helper.v14.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.util.AttributeSet;
import android.view.View;

import com.creditease.zhiwang.R;
import com.creditease.zhiwang.util.Util;

/**
 * Created by Pan Chen on 12/4/15 : 11:56.
 * Company : www.CreditEase.cn;
 * Email : chenpan1@creditease.cn;
 * For you: If you can take it, you can make it.
 */
public class ProgressCartView extends View {
    public static final int PROGRESS_RANGE = 100;
    public static final float ARC_START_ANGLE = 270F;
    public static final float ARC_STROKE_WIDTH_DEFAULT = 4F;
    private static final int CART_BITMAP_RES_ID_DEFAULT = R.drawable.ic_launcher;
    public float mArcStrokeWidth;
    private Bitmap mCartBitmap;
    private int mProgressColor = Color.TRANSPARENT;
    private float mProgressPercent = 0.0F;
    private int mProgress = (int) (PROGRESS_RANGE * mProgressPercent);
    private Paint mArcPaint;
    private Paint mArcBgPaint;
    private Paint mBitmapPaint;
    private int mViewWidth;
    private int mViewHeight;
    private float mOvalLeft;
    private float mOvalTop;
    private float mOvalBottom;
    private float mOvalRight;
    private float mSweepAngle = 0.0F;

    public ProgressCartView(Context context) {
        this(context, null);
    }

    public ProgressCartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressCartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(21)
    public ProgressCartView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        TypedArray a = context.obtainStyledAttributes(R.styleable.ProgressCartView);
        try {
            mProgressPercent = a.getFloat(R.styleable.ProgressCartView_progress_percent, 0.0F);
            mArcStrokeWidth = a.getDimension(R.styleable.ProgressCartView_progress_width, ARC_STROKE_WIDTH_DEFAULT);
            mSweepAngle = 360 * mProgressPercent;
            mProgress = (int) (mProgressPercent * PROGRESS_RANGE);
        } catch (Exception e) {
            a.recycle();
        }
        mProgressColor = Util.getColor(context, R.color.prog_cart_arc_color);
        mCartBitmap = BitmapFactory.decodeResource(getResources(), CART_BITMAP_RES_ID_DEFAULT);
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setStyle(Paint.Style.FILL);
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(ARC_STROKE_WIDTH_DEFAULT);
        mArcPaint.setColor(mProgressColor);
        mArcBgPaint = new Paint();
        mArcBgPaint.setAntiAlias(true);
        mArcBgPaint.setStyle(Paint.Style.STROKE);
        mArcBgPaint.setStrokeWidth(ARC_STROKE_WIDTH_DEFAULT);
        mArcBgPaint.setColor(Util.getColor(context, R.color.prog_cart_arc_bg_color));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int bmWidth = mCartBitmap.getWidth();
        int bmHeight = mCartBitmap.getHeight();
        float centerX = (mViewWidth - bmWidth) / 2;
        float centerY = (mViewHeight - bmHeight) / 2;
        float diameter;
        if (bmWidth > bmHeight) {
            diameter = bmHeight;
        } else {
            diameter = bmWidth;
        }
        mOvalLeft = centerX + mArcStrokeWidth / 2F;
        mOvalTop = centerY + mArcStrokeWidth / 2f;

        mOvalBottom = centerY + diameter - mArcStrokeWidth / 2f;
        mOvalRight = centerX + diameter - mArcStrokeWidth / 2f;
        canvas.drawBitmap(mCartBitmap, centerX, centerY, mBitmapPaint);
        if (mProgressPercent > 0.5f) {
            mArcPaint.setColor(mProgressColor);
            mArcBgPaint.setColor(Util.getColor(getContext(), R.color.prog_cart_arc_bg_color));
        } else {
            mArcPaint.setColor(Color.TRANSPARENT);
            mArcBgPaint.setColor(Color.TRANSPARENT);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawArc(mOvalLeft, mOvalTop, mOvalRight, mOvalBottom, 0F, 360, false, mArcBgPaint);
            canvas.drawArc(mOvalLeft, mOvalTop, mOvalRight, mOvalBottom, ARC_START_ANGLE, mSweepAngle, false, mArcPaint);
        } else {
            canvas.drawArc(new RectF(mOvalLeft, mOvalTop, mOvalRight, mOvalBottom), 0F, 360, false, mArcBgPaint);
            canvas.drawArc(new RectF(mOvalLeft, mOvalTop, mOvalRight, mOvalBottom), ARC_START_ANGLE, mSweepAngle, false, mArcPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = MeasureSpec.getSize(widthMeasureSpec);
        mViewHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(mViewWidth, mViewHeight);
    }

    private void generateSweepAngle(@IntRange(from = 0, to = PROGRESS_RANGE) int progress) {
        mSweepAngle = 360 * progress / PROGRESS_RANGE;
    }

    private void generateSweepAngle(@FloatRange(from = 0.0F, to = 1.0F) float percent) {
        mSweepAngle = 360 * percent;
    }

    public void setProgress(@IntRange(from = 0, to = PROGRESS_RANGE) int progress) {
        mProgress = progress;
        generateSweepAngle(mProgress);
        mProgressPercent = (float) mProgress / PROGRESS_RANGE;
        invalidate();
    }

    public void setProgressPercent(@FloatRange(from = 0.0F, to = 1.0F) float percent) {
        mProgressPercent = percent;
        generateSweepAngle(mProgressPercent);
        mProgress = (int) (mProgressPercent * PROGRESS_RANGE);
        invalidate();
    }

    public float getProgressPercent() {
        return mProgressPercent;
    }

    public int getProgress() {
        return mProgress;
    }
}
