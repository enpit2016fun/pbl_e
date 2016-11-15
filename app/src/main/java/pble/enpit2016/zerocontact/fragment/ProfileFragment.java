package pble.enpit2016.zerocontact.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.zip.Inflater;

import pble.enpit2016.zerocontact.R;


/**
 * 自分や友達のプロフィールを描画するフラグメント
 * Created by kyokn on 2016/10/31.
 */

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //ここで指定したR.layout.fragment_profile（res\layout\fragment_profile.xml）が描画される
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

}
