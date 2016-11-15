package pble.enpit2016.zerocontact.parts;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;

import pble.enpit2016.zerocontact.R;
import pble.enpit2016.zerocontact.fragment.FavoriteFragment;
import pble.enpit2016.zerocontact.fragment.NearFragment;
import pble.enpit2016.zerocontact.fragment.ProfileEditFragment;
import pble.enpit2016.zerocontact.fragment.ProfileFragment;
import pble.enpit2016.zerocontact.fragment.TestFragment;

/**
 * 画面遷移を管理するクラス
 * Created by kyokn on 2016/10/30.
 */

public class PagerAdapter extends FragmentPagerAdapter implements CustomViewPager.OnPageChangeListener {


    public PagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    //各タブを押したときに飛ぶフラグメントを返すことで画面が遷移します。
    @Override
    public Fragment getItem(final int position) {
        switch (position) {
            case 0:
                return NearFragment.newInstance();
            case 1:
                return FavoriteFragment.newInstance();
            case 2:
                return ProfileFragment.newInstance();
            default:
                return TestFragment.newInstance(position);
        }
    }

    public Fragment findFragmentByPosition(ViewPager viewPager, int position) {
        return (Fragment) instantiateItem(viewPager, position);
    }

    //タブの数をここで変更できる
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }


    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}