package pble.enpit2016.zerocontact;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import pble.enpit2016.zerocontact.fragment.ProfileFragment;

/**
 * 友達の詳細情報を表示するアクティビティ（fragmentを使う画面遷移はここを参考にすると分かりやすいです）
 * Created by kyokn on 2016/10/31.
 */
public class FriendActivity extends AppCompatActivity {

    /**
     * フラグメントマネージャ
     */
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //レイアウトの設定
        //佐々木の足跡
        setContentView(R.layout.activity_friend);

        //マネージャの取得
        manager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            //フラグメントトランザクションの取得
            FragmentTransaction transaction = manager.beginTransaction();
            //fragment_containerレイアウトをProfileFragmentにリプレイス（置換）
            transaction.replace(R.id.fragment_container, new ProfileFragment(), ProfileFragment.class.getName());
            //コミットするとreplaceで設定したページに飛ぶ
            transaction.commit();
        }

    }

}
