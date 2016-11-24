package pble.enpit2016.zerocontact;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import pble.enpit2016.zerocontact.communication.CommunicationService;
import pble.enpit2016.zerocontact.fragment.NearFragment;
import pble.enpit2016.zerocontact.parts.CustomViewPager;
import pble.enpit2016.zerocontact.parts.PagerAdapter;

import static android.R.id.toggle;

/**
 * 主な機能のページ遷移を管理するアクティビティ
 * Created by kyokn on 2016/10/31.
 */

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        CustomViewPager.OnPageChangeListener {

    /**
     * ナビゲーションバーで用いる画像のリソース
     */
    private int[] navigationResources = {R.id.near_friends, R.id.favorites,
            R.id.my_profile, R.id.settings, R.id.nav_share, R.id.nav_send};

    /**
     * タブレイアウトで用いるテキストのリソース
     */
    private int[] textResources = {R.string.text_near_friends, R.string.text_favorite, R.string.text_my_profile
            , R.string.text_settings};

    /**
     * タブレイアウトで用いる画像のリソース
     */
    private int[] imageResources = {R.drawable.ic_face, R.drawable.ic_favorite,
            R.drawable.ic_account_circle, R.drawable.ic_settings};

    /**
     * 画面下のタブを管理する変数
     */
    private TabLayout tabLayout;

    /**
     * 画面下のタブと画面遷移を関連付ける変数
     */
    private CustomViewPager viewPager;

    private PagerAdapter adapter;

    /**
     * ナビゲーションバー（左からでてくるやつ）を管理する変数
     */
    private NavigationView navigationView;

    private ScheduledExecutorService schedule;

    /**
     * 近距離無線通信（BLE関係）の変数
     */
    private CommunicationService communicationService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //各レイアウトを初期化
        initDrawerLayout(toolbar);
        initNavigationView();
        initSpinner();
        initTabLayout();

        communicationService = new CommunicationService(this);
        //エミュだと動かない可能性があるのでBLE周りはコメントアウト
        communicationService.startReceive();
//        communicationService.startTransmit();

        //タイマー
        schedule = Executors.newSingleThreadScheduledExecutor();
        //Updaterクラスを5000ms後に3000ms周期で実行する
        schedule.scheduleAtFixedRate(new Updater(), 5000, 3000, TimeUnit.MILLISECONDS);
    }

    private void initSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.distance_array, R.layout.spinner_item_layout);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void initDrawerLayout(Toolbar toolbar) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    //ナビゲーションッビューを初期化するメソッド
    private void initNavigationView() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //タブレイアウトを設定するメソッド
    private void setTabLayout() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            View tabView = inflater.inflate(R.layout.layout_tab, null);
            TextView textView = (TextView) tabView.findViewById(R.id.tab_text);
            textView.setText(textResources[i]);
            ImageView imageView = (ImageView) tabView.findViewById(R.id.tab_icon);
            imageView.setImageResource(imageResources[i]);
            tab.setCustomView(tabView);
        }
    }

    //タブレイアウトを初期化するメソッド
    private void initTabLayout() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (CustomViewPager) findViewById(R.id.view_pager);
        viewPager.setOnPageChangeListener(this);
        adapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(adapter);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabsFromPagerAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        setTabLayout();

        //初期タブの設定（0設定だけだとうまくいかない・・・）
        viewPager.setCurrentItem(2);
        viewPager.setCurrentItem(0);
    }

    //バックボタンを押したときの処理
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //ナビゲーションバーのアイテムが選択されたとき呼ばれるメソッド
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        int count = 0;
        for (int i : navigationResources) {
            if (id == i) viewPager.setCurrentItem(count);
            count++;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //タブ押したときの挙動
    @Override
    public void onPageSelected(int position) {
        navigationView.setCheckedItem(navigationResources[position]);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class Updater implements Runnable {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //NearFragmentを取得
                    NearFragment fragment = (NearFragment) adapter.findFragmentByPosition(viewPager, 0);
                    //NearFragmentのupdate変数を実行
                    fragment.update(communicationService.getUserMap());
                }
            });
        }
    }

}