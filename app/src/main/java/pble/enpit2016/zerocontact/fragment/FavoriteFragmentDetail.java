package pble.enpit2016.zerocontact.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;


import pble.enpit2016.zerocontact.FavoriteActivity;
import pble.enpit2016.zerocontact.R;

/**
 * 一回あった人（ファボした人）を表示する画面のフラグメント
 * Created by kyokn on 2016/10/31.
 */

public class FavoriteFragmentDetail extends Fragment {
    private Button b ;
    public FavoriteFragmentDetail() {
    }

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.fragment_favorite_detail, container, false);
        //b=(Button)v.findViewById(R.id.text_seach);

        return v;
    }



}
