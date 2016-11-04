package pble.enpit2016.zerocontact.parts;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 画面のスクロールをきんしするために作成した独自のViewPager
 * Created by kyokn on 2016/11/01.
 */

public class CustomViewPager extends ViewPager {
    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //常にfalseを返すことでスクロールを禁止する
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return false;
    }

}
