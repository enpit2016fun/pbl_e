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
 * This class is created by Yuta Sasaki
 * Created by kyokn on 2016/10/31.
 */

public class NearFragment extends Fragment implements View.OnClickListener,
        View.OnTouchListener {

    //    private ImageView imageView;
    private int preDx, preDy, newDx, newDy;
    private int width, height;
    private int moveCount = 0;
    private View view;
    private int friendNum = 10;
    private int friendSize = 200;
    private ArrayList<int[]> locationList = new ArrayList<>();


    public static NearFragment newInstance() {
        return new NearFragment();
    }

    private void createImageViews() {
        RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.friends);

        for (int i = 0; i < friendNum; i++) {
            ImageView iView = new ImageView(getActivity());
            iView.setImageResource(R.drawable.ic_account_circle_black_24dp);
            iView.setOnClickListener(NearFragment.this);
            iView.setOnTouchListener(NearFragment.this);
            iView.setId(i);

            int[] location = {((int) (Math.random() * (width - 200 - 100))) + 50
                    , ((int) (Math.random() * (height - 200 - 300))) + 150};

            location = createLocation(location, locationList);
            locationList.add(location);
            layout.addView(iView, createParam(friendSize, friendSize, location[0], location[1], 0, 0));
        }
    }

    private void destroyFriends() {
        locationList.clear();
        RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.friends);
        for (int i = 0; i < friendNum; i++) {
            layout.removeView(view.findViewById(i));
        }
    }

    private int[] createLocation(int[] location, ArrayList<int[]> locationList) {
        int padding = (friendSize / 2) + 50;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_near, container, false);
        SeekBar seekBar = (SeekBar) view.findViewById(R.id.seek_bar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                destroyFriends();
                if (seekBar.getProgress() < 30) {
                    friendNum = 15;
                    friendSize = 100;
//                    friendSize = 30;
                } else if (seekBar.getProgress() < 50) {
                    friendNum = 10;
                    friendSize = 200;
//                    friendSize = 50;
                } else if (seekBar.getProgress() < 80) {
                    friendNum = 7;
                    friendSize = 250;
//                    friendSize = 80;
                } else if (seekBar.getProgress() < 100) {
                    friendNum = 5;
                    friendSize = 300;
//                    friendSize = 100;
                }
                createImageViews();
            }
        });


        preDx = preDy = newDx = newDy = 0;
        setFriends(view);
        return view;
    }

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

    private RelativeLayout.LayoutParams createParam(int w, int h, int l, int t, int r, int b) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(w, h);
        params.setMargins(l, t, r, b);
        return params;
    }

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

    @Override
    public void onClick(View v) {
        if (moveCount < 6) {
            Intent intent = new Intent(getActivity(), FriendActivity.class);
            startActivity(intent);
        }
    }

}

