package pble.enpit2016.zerocontact.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pble.enpit2016.zerocontact.R;

/**
 * Created by kyokn on 2016/10/31.
 */

public class ProfileEditFragment extends Fragment {
    public static ProfileEditFragment newInstance() {
        return new ProfileEditFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false);
    }
}
