package com.lkw.myapplication.tools;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

/**
 * ViewSwitcher仅仅包含子类型TextView。TextSwitcher被用来使屏幕上的label产生动画效果。
 * 每当setText(CharSequence)被调用时，TextSwitcher使用动画方式将当前的文字内容消失并显示新的文字内容。
 * （译者注：改变文字时增加一些动画效果）
 * Created by aaa on 15-5-1.
 */
public class AutoTextView extends TextSwitcher implements ViewSwitcher.ViewFactory {
    private Context mContext;
    //mInUp,mOutUp分离构成向上翻页的进出动画
    private Rotate3dAnimation mInUp;
    private Rotate3dAnimation mOutUp;

    public AutoTextView(Context context) {
        this(context, null);
        // TODO Auto-generated constructor stub
    }

    public AutoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        mContext = context;
        init();
    }

    private void init() {
        // TODO Auto-generated method stub
        setFactory(this);
        mInUp = createAnim(-90, 0, true, true);
        mOutUp = createAnim(0, 90, false, true);
        //TextSwitcher重要用于文件切换，比如 从文字A 切换到 文字 B，
        //setInAnimation()后，A将执行inAnimation，
        //setOutAnimation()后，B将执行OutAnimation
        setInAnimation(mInUp);
        setOutAnimation(mOutUp);
    }

    private Rotate3dAnimation createAnim(float start, float end, boolean turnIn, boolean turnUp) {
        final Rotate3dAnimation rotation = new Rotate3dAnimation(start, end, turnIn, turnUp);
        rotation.setDuration(500);
        rotation.setFillAfter(false);//动画播放完，是否停留在最后界面
        rotation.setInterpolator(new AccelerateInterpolator());//AccelerateInterpolator动画从开始到结束是加速过程
        return rotation;
    }

    //这里返回的TextView，就是我们看到的View
    @Override
    public View makeView() {
        // TODO Auto-generated method stub
        TextView t = new TextView(mContext);
        t.setGravity(Gravity.CENTER);
        t.setTextSize(15);
        t.setSingleLine(true);
        return t;
    }

    //定义动作，向上滚动翻页
    //在main里new 对象的时候会自动调用构造构造函数，这个方法需要用对象调用下
    public void next() {
        if (getInAnimation() != mInUp) {
            setInAnimation(mInUp);
        }
        if (getOutAnimation() != mOutUp) {
            setOutAnimation(mOutUp);
        }
    }

    /**
     * Rotate旋转
     */
    class Rotate3dAnimation extends Animation {
        private final float mFromDegrees;
        private final float mToDegrees;
        private float mCenterX;
        private float mCenterY;
        private final boolean mTurnIn;
        private final boolean mTurnUp;
        private Camera mCamera;

        public Rotate3dAnimation(float fromDegrees, float toDegrees, boolean turnIn, boolean turnUp) {
            mFromDegrees = fromDegrees;
            mToDegrees = toDegrees;
            mTurnIn = turnIn;
            mTurnUp = turnUp;
        }

        /**
         * initialize:初始化
         * @param width
         * @param height
         * @param parentWidth
         * @param parentHeight
         */
        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            mCamera = new Camera();
            mCenterY = getHeight() / 2;
            mCenterX = getWidth() / 2;
        }

        /**
         * 通过重写Animation的 applyTransformation (float interpolatedTime, Transformation t)函数来实现自定义动画效果，
         * 另外一般也会实现 initialize (int width, int height, int parentWidth, int parentHeight)函数，
         * 这是一个回调函数告诉Animation目标View的大小参数，在这里可以初始化一些相关的参数，例如设置动画持续时间、
         * 设置Interpolator、设置动画的参考点等。
         * OPhone在绘制动画的过程中会反复的调用applyTransformation 函数，每次调用参数interpolatedTime值都会变化，
         * 该参数从0渐变为1，当该参数为1时表明动画结束。
         * 通过参数Transformation 来获取变换的矩阵（matrix），通过改变矩阵就可以实现各种复杂的效果
         * @param interpolatedTime
         * @param t
         */
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            final float fromDegrees = mFromDegrees;
            float degrees = fromDegrees + ((mToDegrees - fromDegrees) * interpolatedTime);
            final float centerX = mCenterX;
            final float centerY = mCenterY;
            final Camera camera = mCamera;
            final int derection = mTurnUp ? 1 : -1;
            final Matrix matrix = t.getMatrix();
            camera.save();
            if (mTurnIn) {
                camera.translate(0.0f, derection * mCenterY * (interpolatedTime - 1.0f), 0.0f);
            } else {
                camera.translate(0.0f, derection * mCenterY * (interpolatedTime), 0.0f);
            }
            camera.rotateX(degrees);
            camera.getMatrix(matrix);
            camera.restore();
            matrix.preTranslate(-centerX, -centerY);
            matrix.postTranslate(centerX, centerY);
        }
    }
}
