package cn.cfanr.girlscode.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by MaiBenBen on 2017/4/21.
 *
 */

public class RemoteControlView extends View{

    Paint mDeafultPaint;

    Path[] paths = new Path[5];
    Path center_path;
    Region[] regions = new Region[5];
    Region center_region;

    Matrix mMapMatrix = null;

    int CENTER = 0;
    int[] flag = new int[5];
    int touchFlag = -1;
    int currentFlag = -1;

    int mViewWidth;
    int mViewHeight;

    MenuListener mListener = null;

    int mDefauColor = 0xFF4E5268;
    int mTouchedColor = 0xFFDF9C81;


    public RemoteControlView(Context context) {
        this(context, null);
    }

    public RemoteControlView(Context context, AttributeSet attrs) {
        super(context, attrs);
        for (int i = 0; i < 5; i++) {
            paths[i] = new Path();
            regions[i] = new Region();
            flag[i] = i + 1;
        }
        center_path = new Path();
        center_region = new Region();

        mDeafultPaint = new Paint();
        mDeafultPaint.setColor(mDefauColor);
        mDeafultPaint.setAntiAlias(true);

        mMapMatrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mViewWidth = w;
        mViewHeight = h;
        mMapMatrix.reset();

        // 注意这个区域的大小 画一个全屏的区域
        Region globalRegion = new Region(-w, -h, w, h);
        int minWidth = w > h ? h : w;
        minWidth *= 0.8;

        int br = minWidth / 2;
        RectF bigCircleRect = new RectF(-br, -br, br, br);

        int sr = minWidth / 4;
        RectF smallCircleRect = new RectF(-sr, -sr, sr, sr);

        float bigSweepAngle = 84;//大圆扫描的角度范围
        float smallSweepAngle = -80;//小圆

        // 根据视图大小，初始化 Path 和 Region
        center_path.addCircle(0, 0, 0.2f * minWidth, Path.Direction.CW);
        /*往一个Region中添加一个Path只有这种方法，参数clip代表这个整个Region的区域，在在里面裁剪出path范围的区域*/
        //用指定的Path和裁剪范围构建一个区域
        center_region.setPath(center_path, globalRegion);

        float startAngle = -40;
        float startAngle2 = 40;
        for (int i = 0; i < paths.length; i++) {
            Path path = paths[i];
            Region region = regions[i];
            //添加大圆圆弧
            path.addArc(bigCircleRect, startAngle, bigSweepAngle);
            //连接大圆结束点 并从起点开始画小圆弧形  结束时终点与大圆弧形起点相连形成回路  画出右边扇形区域
            path.arcTo(smallCircleRect, startAngle2, smallSweepAngle);
            path.close();
            region.setPath(path, globalRegion);
            startAngle = startAngle + 90;
            startAngle2 = startAngle2 + 90;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float[] pts = new float[2];
        pts[0] = event.getRawX();
        pts[1] = event.getRawY();
        mMapMatrix.mapPoints(pts);

        int x = (int) pts[0];
        int y = (int) pts[1];

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                touchFlag = getTouchedPath(x, y);
                currentFlag = touchFlag;
                break;
            case MotionEvent.ACTION_MOVE:
                currentFlag = getTouchedPath(x, y);
                break;
            case MotionEvent.ACTION_UP:
                currentFlag = getTouchedPath(x, y);
                // 如果手指按下区域和抬起区域相同且不为空，则判断点击事件
                if (currentFlag == touchFlag && currentFlag != -1 && mListener != null) {
                    if (currentFlag == CENTER) {
                        mListener.onCenterCliched();
                    } else {
                        for (int i : flag) {
                            if(currentFlag == i){
                                mListener.onMenuCliched(i);
                            }
                        }
                    }
                }
                touchFlag = currentFlag = -1;
                break;
            case MotionEvent.ACTION_CANCEL:
                touchFlag = currentFlag = -1;
                break;
        }

        invalidate();
        return true;
    }

    // 获取当前触摸点在哪个区域
    int getTouchedPath(int x, int y) {
        if (center_region.contains(x, y)) {
            return CENTER;
        } else {
            for (int i = 0; i < regions.length; i++) {
                Region region = regions[i];
                if(region.contains(x, y)){
                    return flag[i];
                }
            }
        }
        return -1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("", "canvas======" + canvas.getHeight() + "===" + canvas.getWidth());
        Log.i("", "mViewWidth======" + mViewHeight + "===" + mViewWidth);
        canvas.translate(mViewWidth / 2, mViewHeight / 2);

        // 获取测量矩阵(逆矩阵)
        if (mMapMatrix.isIdentity()) {
            canvas.getMatrix().invert(mMapMatrix);
        }

        // 绘制默认颜色
        canvas.drawPath(center_path, mDeafultPaint);
        for (Path path : paths) {
            canvas.drawPath(path, mDeafultPaint);
        }

        // 绘制触摸区域颜色
        mDeafultPaint.setColor(mTouchedColor);
        if (currentFlag == CENTER) {
            canvas.drawPath(center_path, mDeafultPaint);
        } else {
            for (int i = 0; i < flag.length; i++) {
                if(currentFlag == flag[i]){
                    canvas.drawPath(paths[i], mDeafultPaint);
                }
            }
        }
        mDeafultPaint.setColor(mDefauColor);
    }

    public void setListener(MenuListener listener) {
        mListener = listener;
    }

    // 点击事件监听器
    public interface MenuListener {
        void onCenterCliched();
        void onMenuCliched(int curFlag);
    }

}
