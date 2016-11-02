package pble.enpit2016.zerocontact.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pble.enpit2016.zerocontact.R;

/**
 * テスト用のフラグメント（そのうち消す）
 * Created by kyokn on 2016/10/30.
 */

public class TestFragment extends Fragment {
    public TestFragment() {

    }

    public static TestFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt("page", page);
        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int page = getArguments().getInt("page", 0);
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        ((TextView) view.findViewById(R.id.page_text)).setText("Page " + page);
        return view;
    }
}
