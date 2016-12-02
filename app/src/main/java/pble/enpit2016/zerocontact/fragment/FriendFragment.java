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
 * 渡された情報をもとに描画内容を変更するフラグメント
 * Created by kyokn on 2016/11/07.
 */

public class FriendFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        Bundle bundle = getArguments();
        TextView text = (TextView) view.findViewById(R.id.page_text);
        if (bundle.getString("key") != null) {
            String str = bundle.getString("key");
            text.setText("識別番号" + str);
        }

        return view;
    }




}
