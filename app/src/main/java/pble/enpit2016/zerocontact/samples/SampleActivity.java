package pble.enpit2016.zerocontact.samples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import pble.enpit2016.zerocontact.R;
import pble.enpit2016.zerocontact.fragment.FriendFragment;
import pble.enpit2016.zerocontact.samples.listview.ListViewFragment;

/**
 * Created by kyokn on 2016/11/25.
 */

public class SampleActivity extends AppCompatActivity {

    /**
     * フラグメントマネージャ
     */
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //レイアウトの設定
        //佐々木の足跡---もう一回追加--author名を変更
        setContentView(R.layout.samples_activity_sample);

        //マネージャの取得
        manager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            FragmentTransaction transaction = manager.beginTransaction();
            ListViewFragment fragment = new ListViewFragment();
            transaction.replace(R.id.sample_fragment_container, fragment, FriendFragment.class.getName());
            transaction.commit();
        }

    }


}
