package pble.enpit2016.zerocontact.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pble.enpit2016.zerocontact.R;
import pble.enpit2016.zerocontact.communication.receive.Signal;
import pble.enpit2016.zerocontact.data.IconMap;
import pble.enpit2016.zerocontact.parts.Icon;
import pble.enpit2016.zerocontact.parts.UserDetailView;
import pble.enpit2016.zerocontact.parts.UserView;

import static android.R.attr.animation;

/**
 * 近い人を表示する画面のフラグメント
 * Created by kyokn on 2016/10/31.
 */

public class NearFragment extends Fragment implements View.OnClickListener {

    private int width, height;

    private View view;

    private int friendSize = 300;

    private HashMap<String, Icon> iconMap = new HashMap<>();

    private RelativeLayout childLayout;

    private IconMap iconDataMap = new IconMap();

    private Integer spinnerPosition = 0;

    private float nowRatio = 1f;

    public static NearFragment newInstance() {
        return new NearFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(1500);
        addAllIcon();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_near, container, false);
        getActivity().setTitle(R.string.text_near_friends);

        initSpinner();
        childLayout = (RelativeLayout) view.findViewById(R.id.friends);
        getParentSize(view);
        return view;
    }

    private void initSpinner() {
        final Spinner spinner = (Spinner) (getActivity().findViewById(R.id.spinner));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ScaleAnimation animation;
                int differ = position - spinnerPosition;
                if (differ == -2) {
                    animation = new ScaleAnimation(nowRatio, nowRatio + 0.5f, nowRatio, nowRatio + 0.5f);
                    nowRatio += 0.5f;
                } else if (differ == -1) {
                    animation = new ScaleAnimation(nowRatio, nowRatio + 0.25f, nowRatio, nowRatio + 0.25f);
                    nowRatio += 0.25f;
                } else if (differ == 1) {
                    animation = new ScaleAnimation(nowRatio, nowRatio - 0.25f, nowRatio, nowRatio - 0.25f);
                    nowRatio -= 0.25f;
                } else if (differ == 2) {
                    animation = new ScaleAnimation(nowRatio, nowRatio - 0.5f, nowRatio, nowRatio - 0.5f);
                    nowRatio -= 0.5f;
                } else return;

                animation.setFillAfter(true);
                animation.setDuration(1500);
                animate(animation);
                spinnerPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void addAllIcon() {
        ScaleAnimation animation = null;
        if (spinnerPosition != null) {
            nowRatio = 1f;
            if (spinnerPosition == 0) {
                animation = new ScaleAnimation(nowRatio, nowRatio, nowRatio, nowRatio);
                nowRatio = 1f;
            } else if (spinnerPosition == 1) {
                animation = new ScaleAnimation(nowRatio, nowRatio - 0.25f, nowRatio, nowRatio - 0.25f);
                nowRatio = 0.75f;
            } else if (spinnerPosition == 2) {
                animation = new ScaleAnimation(nowRatio, nowRatio - 0.5f, nowRatio, nowRatio - 0.5f);
                nowRatio = 0.5f;
            } else return;
            animation.setFillAfter(true);
        }
        if (animation == null) return;
        for (Map.Entry<String, Icon> entry : iconMap.entrySet()) {
            UserView userView = createUserView(entry.getKey(), entry.getValue().getName(),
                    entry.getValue().getName(), entry.getValue().getImage(), friendSize);
            childLayout.addView(userView, createParam(entry.getValue().getLocation()[0],
                    entry.getValue().getLocation()[1], 0, 0));

            userView.startAnimation(animation);
        }
    }


    private UserView createUserView(String id, String name, String hobby, int image, int size) {
        UserView userView = new UserView(getActivity(), name, hobby, image, size);
        userView.setId(Integer.parseInt(id));
        userView.setOnClickListener(NearFragment.this);
        userView.setCardElevation(20);

        return userView;
    }

    //画面上にIconViewを追加
    private void addIcon(String id) {
        int[] location = {((int) (Math.random() * (width - 200 - 100))) + 50
                , ((int) (Math.random() * (height - 200 - 300))) + 150};
        if (!iconDataMap.containsKey(id)) return;
        Icon icon = iconDataMap.get(id);
        if (icon == null) return;
        location = createIconLocation(location);

        icon.setLocation(location);
        iconMap.put(id, icon);

        UserView userView = createUserView(id, icon.getName(), icon.getHobby(), icon.getImage(), friendSize);

        childLayout.addView(userView, createParam(location[0], location[1], 0, 0));
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(1500);
        userView.startAnimation(animation);
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

    private void animate(Animation animation) {
        for (Map.Entry<String, Icon> i : iconMap.entrySet()) {
            view.findViewById(Integer.parseInt(i.getKey())).startAnimation(animation);
        }
    }

    //渡されたidのIconを削除
    private void removeIcon(String id) {
        iconMap.remove(id);
        childLayout.removeView(view.findViewById(Integer.parseInt(id)));
    }

    //友達を全員破棄
    private void removeAllIcon() {
        for (Map.Entry<String, Icon> i : iconMap.entrySet()) {
            childLayout.removeView(view.findViewById(Integer.parseInt(i.getKey())));
        }
    }

    //レイアウト設定直後だと縦横サイズが取れないのでViewtreeObserverを使用
    private void getParentSize(final View view) {
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

    private RelativeLayout.LayoutParams createParam(int l, int t, int r, int b) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(l, t, r, b);
        return params;
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
        //クリックしたビューの位置からでっかくしたユーザビュー作って表示する
        //適当な箇所クリックしたら戻る
        if (!iconDataMap.containsKey(String.valueOf(v.getId()))) return;
        Icon icon = iconDataMap.get(String.valueOf(v.getId()));
        UserDetailView userView = new UserDetailView(getActivity(), icon.getName(), icon.getHobby(),
                icon.getComment(), icon.getImage());
        userView.setCardElevation(20);

        new AlertDialog.Builder(getActivity())
                .setView(userView)
                .show();
    }

    //画面上のIconのViewを更新
    public void update(Map<String, Signal> userMap) {
        if (userMap.size() == 0) return;
        //iconの追加
        for (Map.Entry<String, Signal> entry : userMap.entrySet()) {
            if (!iconMap.containsKey(entry.getKey())) addIcon(entry.getKey());
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

