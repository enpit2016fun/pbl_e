package pble.enpit2016.zerocontact.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;

import pble.enpit2016.zerocontact.R;
import pble.enpit2016.zerocontact.parts.SearchDialog;

/**
 * 一回あった人（ファボした人）を表示する画面のフラグメント
 * Created by kyokn on 2016/10/31.
 */

public class FavoriteFragment extends Fragment {
    private Button b;

    public FavoriteFragment() {
    }

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        //b=(Button)v.findViewById(R.id.text_seach);

        return view;
    }


    //   // Button button = (Button)getActivity().findViewById(R.id.text_seach);
//    //button.setOnClickListener(new View.OnClickListener() {
//
//        @Override
//        public void onClick(View v) {
//
//          //  if (!iconDataMap.containsKey(String.valueOf(v.getId()))) return;
//            Button button = (Button)getActivity().findViewById(R.id.text_seach);
//           // UserDetailView userView = new UserDetailView(getActivity(), button.getName(), button.getHobby(),
//             //       icon.getComment(), icon.getImage());
//            //userView.setCardElevation(20);
//            new AlertDialog.Builder(getActivity())
//                    //.setView(userView)
//                    //.show();
//                    .setTitle("タイトル")
//                    .setMessage("メッセージ")
//                    .create();
//
//        }
//   // });
    @Override
    public void onStart() {
        super.onStart();

        Button button = (Button) getActivity().findViewById(R.id.text_seach);
        button.setOnClickListener(new View.OnClickListener() {
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            };


            @Override
            public void onClick(View v) {
                SearchDialog searchDialog = new SearchDialog(getActivity());
                searchDialog.showDialog(listener);
            }

        });

    }


}
