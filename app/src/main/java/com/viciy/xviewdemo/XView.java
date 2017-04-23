package com.viciy.xviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Baiqiang on 2017/4/21.
 */

public class XView extends View {

    private int mWidth;
    private int mHeight;
    private int mPos;
    private int mCMWidth = 6;//对勾宽度
    private int mTextSize = 20;//文字的大小
    private int mArcHighet = 100;//弓形的高度


    private Paint mPaint;
    private Paint mArcPaint;

    public XView(Context context) {
        this(context, null);
    }

    public XView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //画矩形
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        ////画弓形
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setStyle(Paint.Style.FILL);
    }

    public void setPos(int pos) {
        this.mPos = pos;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int dx = mWidth / 5;
        int pgRectStartX = mHeight / 3;
        int pgRectHeight = 5;
        int mSRadius = 2 * pgRectHeight;
        int mMRadius = 2 * mSRadius;
        int mLRadius = mMRadius + 5;
        //画渐变矩形
        mPaint.setColor(Color.rgb(Color.RED, Color.GREEN, Color.BLUE));
        for (int i = 0; i < mWidth; i++) {
            mPaint.setAlpha(255 - i * 220 / mWidth);
            canvas.drawLine(i, 0, i, mHeight, mPaint);
        }
        //画弓行
        mArcPaint.setColor(Color.YELLOW);
        for (int i = 0; i < mWidth; i++) {
            double sin = Math.sin(((float) i / mWidth) * Math.PI);
            float f = (float) (mHeight - (mArcHighet * sin));
            canvas.drawLine(i, f, i, mHeight, mArcPaint);
        }
        //画下层圆圈
        for (int i = 1; i < 5; i++) {
            canvas.drawCircle(i * dx, pgRectStartX + pgRectHeight / 2, mSRadius, mArcPaint);
        }
        //画下层矩形
        canvas.drawRect(dx, pgRectStartX, 4 * dx, pgRectStartX + pgRectHeight, mArcPaint);
        //画上层矩形
        mArcPaint.setColor(Color.GREEN);
        canvas.drawRect(dx, pgRectStartX, mPos * dx, pgRectStartX + pgRectHeight, mArcPaint);
        //画上层圆圈
        mArcPaint.setColor(Color.WHITE);
        for (int i = 1; i < mPos; i++) {
            canvas.drawCircle(i * dx, pgRectStartX + pgRectHeight / 2, mSRadius, mArcPaint);
        }
        //画外层大圆圈
        mArcPaint.setColor(Color.GREEN);
        canvas.drawCircle(mPos * dx, pgRectStartX + pgRectHeight / 2, mLRadius, mArcPaint);
        //画内层大圆圈
        mArcPaint.setColor(Color.RED);
        canvas.drawCircle(mPos * dx, pgRectStartX + pgRectHeight / 2, mMRadius, mArcPaint);
        //画对勾
        mArcPaint.setColor(Color.WHITE);
        for (int j = mPos * dx - 10; j <= mPos * dx + 13; j++) {
            if (j < mPos * dx) {
                canvas.drawLine(j, pgRectStartX + pgRectHeight + j - mPos * dx, j, pgRectStartX + pgRectHeight + j - mPos * dx +
                        mCMWidth, mArcPaint);
            } else {
                canvas.drawLine(j, pgRectStartX + pgRectHeight - j + mPos * dx, j, pgRectStartX + pgRectHeight - j + mPos * dx +
                        mCMWidth, mArcPaint);
            }
        }
        //画文字
        mArcPaint.setTextSize(mTextSize);
        List<String> s = new ArrayList<>();
        s.add("登记账户");
        s.add("数字证书");
        s.add("电子合同");
        s.add("开通成功");
        for (int i = 0; i < s.size(); i++) {
            canvas.drawText(s.get(i), (i + 1) * dx - 2 * mTextSize, pgRectStartX + pgRectHeight + 3 * mTextSize, mArcPaint);
        }
    }
}
