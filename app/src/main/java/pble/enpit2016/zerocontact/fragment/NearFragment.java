package pble.enpit2016.zerocontact.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import java.util.ArrayList;

import pble.enpit2016.zerocontact.FriendActivity;
import pble.enpit2016.zerocontact.R;

/**
 * 近い人を表示する画面のフラグメント
 * Created by kyokn on 2016/10/31.
 */

public class NearFragment extends Fragment implements View.OnClickListener,
        View.OnTouchListener {

    /**
     * iconドラッグ用の変数
     */
    private int preDx, preDy, newDx, newDy;

    /**
     * 親要素の縦横サイズ
     */
    private int width, height;

    /**
     * iconのドラッグ＆クリックが混同しないためのカウント
     */
    private int moveCount = 0;

    /**
     * フラグメントのビュー
     */
    private View view;

    /**
     * 初期に表示する友達の人数
     */
    private int friendNum = 10;

    /**
     * 初期に表示する友達のiconサイズ
     */
    private int friendSize = 200;

    /**
     * iconの位置を保持するリスト
     */
    private ArrayList<int[]> locationList = new ArrayList<>();


    public static NearFragment newInstance() {
        return new NearFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_near, container, false);

        //シークバー（下のバー）
        SeekBar seekBar = (SeekBar) view.findViewById(R.id.seek_bar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            //下のバーを離したときの値からiconの表示を再描画するリスナーを設定
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                destroyFriends();
                if (seekBar.getProgress() < 30) {
                    friendNum = 15;
                    friendSize = 100;
                } else if (seekBar.getProgress() < 50) {
                    friendNum = 10;
                    friendSize = 150;
                } else if (seekBar.getProgress() < 80) {
                    friendNum = 7;
                    friendSize = 200;
                } else if (seekBar.getProgress() < 100) {
                    friendNum = 5;
                    friendSize = 250;
                }
                createImageViews();
            }
        });

        //初期化
        preDx = preDy = newDx = newDy = 0;

        //iconを設定
        setFriends(view);
        return view;
    }

    //ImageViewを生成しRelativeLayout上に描画する
    private void createImageViews() {
        RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.friends);

        for (int i = 0; i < friendNum; i++) {
            //ImageViewの作成
            ImageView iView = new ImageView(getActivity());
            iView.setId(i);
            iView.setImageResource(R.drawable.ic_account_circle_black_24dp);
            //iconを押したときのリスナーを設定
            iView.setOnClickListener(NearFragment.this);
            //iconに触れたときのリスナーを設定
            iView.setOnTouchListener(NearFragment.this);

            //画面サイズからiconの描画位置の候補を作成
            int[] location = {((int) (Math.random() * (width - 200 - 100))) + 50
                    , ((int) (Math.random() * (height - 200 - 300))) + 150};

            //iconが被らないようにiconの描画位置を再生成
            location = createLocation(location, locationList);

            //最終的なiconの位置をリストで保持
            locationList.add(location);

            //relativelayoutにiconを追加
            layout.addView(iView, createParam(friendSize, friendSize, location[0], location[1], 0, 0));
        }
    }

    //再帰関数でiconの位置が重複しないようlocationを計算（バグりがち）
    private int[] createLocation(int[] location, ArrayList<int[]> locationList) {
        int padding = 0;
        for (int[] l : locationList) {
            if (l[0] + padding > location[0] && l[0] - padding < location[0]
                    && l[1] + padding > location[1] && l[1] - padding < location[1]) {
                int[] innerLocation = {((int) (Math.random() * (width - 200 - 100))) + 50
                        , ((int) (Math.random() * (height - 200 - 200))) + 100};
                return createLocation(innerLocation, locationList);
            }
        }
        return location;
    }

    //友達を全員破棄
    private void destroyFriends() {
        locationList.clear();
        RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.friends);
        for (int i = 0; i < friendNum; i++) {
            layout.removeView(view.findViewById(i));
        }
    }

    //レイアウト設定直後だと縦横サイズが取れないのでViewtreeObserverを使っています
    private void setFriends(final View view) {
        ViewTreeObserver observer = view.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                width = view.getWidth();
                height = view.getHeight();
                createImageViews();
                ViewTreeObserver obs = view.getViewTreeObserver();
                obs.removeOnGlobalLayoutListener(this);
            }
        });
    }

    //追加するレイアウトのパラムを作成[width,height,left,top,right,bottom]
    private RelativeLayout.LayoutParams createParam(int w, int h, int l, int t, int r, int b) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(w, h);
        params.setMargins(l, t, r, b);
        return params;
    }

    //iconに触れた時に呼ばれる関数
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        newDx = (int) event.getRawX();
        newDy = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                moveCount = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = v.getLeft() + (newDx - preDx);
                int dy = v.getTop() + (newDy - preDy);
                v.layout(dx, dy, dx + v.getWidth(), dy + v.getHeight());
                moveCount++;
                break;
        }
        preDx = newDx;
        preDy = newDy;
        return false;
    }

    //iconをクリックした時に呼ばれる関数
    @Override
    public void onClick(View v) {
        if (moveCount < 6) {
            Intent intent = new Intent(getActivity(), FriendActivity.class);
            startActivity(intent);
        }
    }

}

