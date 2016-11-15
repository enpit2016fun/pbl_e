package pble.enpit2016.zerocontact.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pble.enpit2016.zerocontact.FriendActivity;
import pble.enpit2016.zerocontact.R;
import pble.enpit2016.zerocontact.communication.receive.Signal;
import pble.enpit2016.zerocontact.parts.Icon;

/**
 * 近い人を表示する画面のフラグメント
 * Created by kyokn on 2016/10/31.
 */

public class NearFragment extends Fragment implements View.OnClickListener {

    /**
     * 親要素の縦横サイズ
     */
    private int width, height;

    /**
     * フラグメントのビュー
     */
    private View view;

    /**
     * 初期に表示する友達のiconサイズ
     */
    private int friendSize = 400;

    /**
     * iconを保持するリスト
     */
    private HashMap<String, Icon> iconMap = new HashMap<>();

    public static NearFragment newInstance() {
        return new NearFragment();
    }

    //Iconの再描画
    @Override
    public void onStart() {
        super.onStart();
        RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.friends);
        for (Map.Entry<String, Icon> entry : iconMap.entrySet()) {
            layout.addView(createIconView(entry.getKey(), entry.getValue().getImage()),
                    createParam(friendSize, friendSize, entry.getValue().getLocation()[0], entry.getValue().getLocation()[1], 0, 0));
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_near, container, false);

        //親要素の画面サイズを取得
        setParentSize(view);
        return view;
    }

    //Iconの生成（今は仮データを生成するようになっています）
    private Icon createIcon(String id) {
        switch (id) {
            case "01":
                return new Icon("name", "hobby", R.drawable.user_sample_daiki);
            case "04":
                return new Icon("name", "hobby", R.drawable.user_sample_sasaki);
            case "06":
                return new Icon("name", "hobby", R.drawable.user_sample_osanai);
            case "07":
                return new Icon("name", "hobby", R.drawable.user_sample_simiken);
            case "02":
                return new Icon("name", "hobby", R.drawable.user_sample_maeken);
            case "05":
                return new Icon("name", "hobby", R.drawable.user_sample_inoue);
            default:
                return null;
        }
    }

    //IconのViewを生成
    private ImageView createIconView(String id, int image) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setId(Integer.parseInt(id));
        imageView.setImageResource(image);
        //iconを押したときのリスナーを設定
        imageView.setOnClickListener(NearFragment.this);
        //iconに触れたときのリスナーを設定
        return imageView;
    }

    //画面上にIconViewを追加
    private void addIcon(RelativeLayout layout, String id) {
        //画面サイズからiconの描画位置の候補を作成
        int[] location = {((int) (Math.random() * (width - 200 - 100))) + 50
                , ((int) (Math.random() * (height - 200 - 300))) + 150};

        //Iconの生成
        Icon icon = createIcon(id);
        if (icon == null) return;
        //iconが被らないようにiconの描画位置を再生成
        location = createIconLocation(location);

        //iconに位置を設定
        icon.setLocation(location);
        iconMap.put(id, icon);

        //IconのViewを生成
        ImageView imageView = createIconView(id, icon.getImage());

        //iconを追加
        layout.addView(imageView, createParam(friendSize, friendSize, location[0], location[1], 0, 0));
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(1500);
        imageView.startAnimation(animation);
    }

    //再帰関数でiconの位置が重複しないようlocationを計算（バグ有）
    private int[] createIconLocation(int[] location) {
        int padding = 400;
        for (Map.Entry<String, Icon> i : iconMap.entrySet()) {
            if (i.getValue().getLocation()[0] + padding > location[0]
                    && i.getValue().getLocation()[0] - padding < location[0]
                    && i.getValue().getLocation()[1] + padding > location[1]
                    && i.getValue().getLocation()[1] - padding < location[1]) {
                int[] innerLocation = {((int) (Math.random() * (width - 200 - 100))) + 50
                        , ((int) (Math.random() * (height - 200 - 200))) + 100};
                return createIconLocation(innerLocation);
            }
        }
        return location;
    }

    //渡されたidのIconを削除
    private void removeIcon(String id) {
        iconMap.remove(id);
        RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.friends);
        layout.removeView(view.findViewById(Integer.parseInt(id)));
    }

    //友達を全員破棄
    private void removeAllIcon() {
        iconMap.clear();
        RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.friends);
        for (Map.Entry<String, Icon> i : iconMap.entrySet()) {
            layout.removeView(view.findViewById(Integer.parseInt(i.getKey())));
        }
    }

    //レイアウト設定直後だと縦横サイズが取れないのでViewtreeObserverを使用
    private void setParentSize(final View view) {
        ViewTreeObserver observer = view.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                width = view.getWidth();
                height = view.getHeight();
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

    //iconをクリックした時に呼ばれる関数
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), FriendActivity.class);
        intent.putExtra("key", String.valueOf(v.getId()));
        startActivity(intent);
    }

    //画面上のIconのViewを更新
    public void update(Map<String, Signal> userMap) {
        if (userMap.size() == 0) return;
        RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.friends);
        //iconの追加
        for (Map.Entry<String, Signal> entry : userMap.entrySet()) {
            if (!iconMap.containsKey(entry.getKey())) addIcon(layout, entry.getKey());
        }
        //iconの削除
        ArrayList<String> delList = new ArrayList<>();
        for (Map.Entry<String, Icon> icon : iconMap.entrySet()) {
            if (!userMap.containsKey(icon.getKey())) delList.add(icon.getKey());
        }
        for (String s : delList) {
            removeIcon(s);
        }
    }
}

