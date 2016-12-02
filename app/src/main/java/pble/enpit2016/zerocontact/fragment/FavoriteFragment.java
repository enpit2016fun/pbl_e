package pble.enpit2016.zerocontact.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;

import pble.enpit2016.zerocontact.R;

/**
 * 一回あった人（ファボした人）を表示する画面のフラグメント
 * Created by kyokn on 2016/10/31.
 */

public class FavoriteFragment extends Fragment {
    private Button b ;
    public FavoriteFragment() {
    }

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_favorite, container, false);
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

    Button button = (Button)getActivity().findViewById(R.id.text_seach);
    button.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {

                final String[] items1 = {"趣味", "出会った場所","出身"};
                final String[] items2 = {"スポーツ", "読書", "映画鑑賞", "音楽", "アニメ", "プログラミング"};
                final String[] items3 = {"学会", "授業", "スポーツ観戦", "合コン"};
                final String[] items4 = {"北海道", "東北", "関東", "関西","中国・四国","九州","沖縄"};
                final ArrayList<Integer> checkedItems = new ArrayList<Integer>();
                //AlertDialog.Builder alert = new AlertDialog.Builder(getActivity())
            int paddingLeftRight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics());
            int paddingTopBottom = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());


                final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                // タイトル部分のTextView
                TextView textView = new TextView(getActivity());
                // タイトルの背景色
                textView.setBackgroundColor(Color.parseColor("#4784BF"));
                // タイトルの文字色
                textView.setTextColor(Color.WHITE);
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                textView.setPadding(paddingLeftRight, paddingTopBottom, paddingLeftRight, paddingTopBottom);
                // テキスト
                textView.setText("詳細検索");
                // テキストサイズ
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

                // タイトル部分のTextView
                final TextView textView0 = new TextView(getActivity());
                // タイトルの背景色
                textView0.setBackgroundColor(Color.parseColor("#4784BF"));
                // タイトルの文字色
                textView0.setTextColor(Color.WHITE);
                textView0.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                textView0.setPadding(paddingLeftRight, paddingTopBottom, paddingLeftRight, paddingTopBottom);
                // テキスト
                textView0.setText("趣味");
                // テキストサイズ
                textView0.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

                // タイトル部分のTextView
                final TextView textView1 = new TextView(getActivity());
                // タイトルの背景色
                textView1.setBackgroundColor(Color.parseColor("#4784BF"));
                // タイトルの文字色
                textView1.setTextColor(Color.WHITE);
                textView1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                textView1.setPadding(paddingLeftRight, paddingTopBottom, paddingLeftRight, paddingTopBottom);
                // テキスト
                textView1.setText("出会った場所");
                // テキストサイズ
                textView1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

                // タイトル部分のTextView
                final TextView textView2 = new TextView(getActivity());
                // タイトルの背景色
                textView2.setBackgroundColor(Color.parseColor("#4784BF"));
                // タイトルの文字色
                textView2.setTextColor(Color.WHITE);
                textView2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                textView2.setPadding(paddingLeftRight, paddingTopBottom, paddingLeftRight, paddingTopBottom);
                // テキスト
                textView2.setText("出身");
                // テキストサイズ
                textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);



                alert.setCustomTitle(textView);
                //.setMessage("message")
                alert.setItems(items1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // item_which pressed
                        if (items1[which] == items1[0]) {
                            new AlertDialog.Builder(getActivity())
                                    .setCustomTitle(textView0)
                                    .setMultiChoiceItems(items2, null, new DialogInterface.OnMultiChoiceClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                            if (isChecked) checkedItems.add(which);
                                            else checkedItems.remove((Integer) which);
                                        }
                                    })

                                    .setPositiveButton("検索", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            for (Integer i : checkedItems) {
                                                // item_i checked
                                            }
                                        }
                                    })
                                    .setNegativeButton("キャンセル", null)
                                    .show();
                        }
                        if (items1[which] == items1[1]) {
                            new AlertDialog.Builder(getActivity())
                                    //.setView(userView)
                                    //.show();
                                .setCustomTitle(textView1)
                                .setMultiChoiceItems(items3, null, new DialogInterface.OnMultiChoiceClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                            if (isChecked) checkedItems.add(which);
                                            else checkedItems.remove((Integer) which);
                                        }
                                    })

                                    .setPositiveButton("検索", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            for (Integer i : checkedItems) {
                                                // item_i checked
                                            }
                                        }
                                    })
                                    .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    })
                                    .show();
                        }
                        if (items1[which] == items1[2]) {
                            new AlertDialog.Builder(getActivity())
                                    //.setView(userView)
                                    //.show();
                                    .setCustomTitle(textView2)
                                    .setMultiChoiceItems(items4, null, new DialogInterface.OnMultiChoiceClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                            if (isChecked) checkedItems.add(which);
                                            else checkedItems.remove((Integer) which);
                                        }
                                    })

                                    .setPositiveButton("検索", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            for (Integer i : checkedItems) {
                                                // item_i checked
                                            }
                                        }
                                    })
                                    .setNegativeButton("キャンセル", null)
                                    .show();
                        }
                    }
                });
//                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            // OK button pressed
//
//                        }
//                    })
                alert.setNegativeButton("キャンセル", null);
                alert.show();


            }

    });

}



}
