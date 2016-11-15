package pble.enpit2016.zerocontact;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import pble.enpit2016.zerocontact.fragment.FriendFragment;
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
        setContentView(R.layout.activity_friend);

        //マネージャの取得
        manager = getSupportFragmentManager();

        Intent intent = getIntent();
        String key = intent.getStringExtra("key");

        if (savedInstanceState == null) {
            //フラグメントトランザクションの取得
            FragmentTransaction transaction = manager.beginTransaction();
            //フラグメントをインスタンス化
            FriendFragment fragment = new FriendFragment();
            //フラグメントに値を渡したいときの記述方法
            Bundle bundle = new Bundle();
            //String型のkey変数をid"key"で設定
            bundle.putString("key", key);
            //fragmentにbundleを設定
            fragment.setArguments(bundle);
            //fragment_containerレイアウトをProfileFragmentにリプレイス
            transaction.replace(R.id.fragment_container, fragment, FriendFragment.class.getName());
            //コミットするとreplaceで設定したページに飛ぶ
            transaction.commit();
        }

    }

}
