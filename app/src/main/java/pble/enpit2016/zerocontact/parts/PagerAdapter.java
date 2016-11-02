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
 * Created by kyokn on 2016/10/30.
 */

public class PagerAdapter extends FragmentPagerAdapter implements CustomViewPager.OnPageChangeListener {


    public PagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(final int position) {
        switch (position) {
            case 0:
                return NearFragment.newInstance();
            case 1:
                return FavoriteFragment.newInstance();
            case 2:
                return ProfileFragment.newInstance();
            case 3:
                return ProfileEditFragment.newInstance();
            default:
                return TestFragment.newInstance(position);
        }
    }

    @Override
    public int getCount() {
        return 4;
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