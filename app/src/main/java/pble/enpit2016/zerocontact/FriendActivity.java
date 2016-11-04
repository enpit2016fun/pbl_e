package pble.enpit2016.zerocontact;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import pble.enpit2016.zerocontact.fragment.ProfileFragment;

public class FriendActivity extends AppCompatActivity {

    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //レイアウトの設定
        //佐々木の足跡
        setContentView(R.layout.activity_friend);

        manager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragment_container, new ProfileFragment(), ProfileFragment.class.getName());
            transaction.commit();
        }

    }

}
