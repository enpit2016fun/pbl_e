package pble.enpit2016.zerocontact;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import pble.enpit2016.zerocontact.communication.CommunicationService;
import pble.enpit2016.zerocontact.parts.CustomViewPager;
import pble.enpit2016.zerocontact.parts.PagerAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private int[] navigationResources = {R.id.near_friends, R.id.favorites,
            R.id.my_profile, R.id.settings, R.id.nav_share, R.id.nav_send};

    private TabLayout tabLayout;
    private CustomViewPager viewPager;
    private NavigationView navigationView;
    private CommunicationService communicationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initFloatActionButton();
        initDrawerLayout(toolbar);
        initNavigationView();
        initTabLayout();

//        communicationService = new CommunicationService(this);
//        communicationService.startReceive();
//        communicationService.startTransmit();
    }

    private void initFloatActionButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "一言コメントのポスト機能でもつけたらいいんじゃないかな", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initDrawerLayout(Toolbar toolbar) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void initNavigationView() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setTabLayout() {
        int[] textResources = {R.string.text_near_friends, R.string.text_favorite, R.string.text_my_profile, R.string.text_settings};
        int[] imageResources = {R.drawable.ic_face, R.drawable.ic_favorite, R.drawable.ic_account_circle, R.drawable.ic_settings};
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

    private void initTabLayout() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (CustomViewPager) findViewById(R.id.view_pager);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                navigationView.setCheckedItem(navigationResources[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(adapter);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabsFromPagerAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        setTabLayout();

        //0のみでは動かない・・・
        viewPager.setCurrentItem(2);
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

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

}
