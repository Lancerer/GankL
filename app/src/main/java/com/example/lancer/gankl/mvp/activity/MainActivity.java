package com.example.lancer.gankl.mvp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lancer.gankl.R;
import com.example.lancer.gankl.base.BaseActivity;
import com.example.lancer.gankl.base.BasePresenter;
import com.example.lancer.gankl.mvp.fragment.AllFragment;
import com.example.lancer.gankl.mvp.fragment.AndroidFragment;
import com.example.lancer.gankl.mvp.fragment.BeforeFragment;
import com.example.lancer.gankl.mvp.fragment.GirlFragment;
import com.example.lancer.gankl.mvp.fragment.IosFragment;
import com.example.lancer.gankl.mvp.fragment.ResFragment;
import com.example.lancer.gankl.mvp.fragment.ZhihuFragment;
import com.example.lancer.gankl.util.ThemeUtil;
import com.example.lancer.gankl.weight.MyViewPager;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;
    private ImageView iv_splash;
    private android.support.design.widget.TabLayout tab;
    private MyViewPager vp;
    private String[] tabtitle = {"all", "android", "before", "girl", "ios", "res", "知乎"};
    private List<Fragment> mFragments = new ArrayList<>();
    private FragmentManager mFragmentManager;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private android.support.design.widget.FloatingActionButton fab;
    private NavigationView navView;
    private Fragment mFragment;
    private GirlFragment mGirlFragment;

    @Override
    protected void initData() {
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
        mFragmentManager = getSupportFragmentManager();
        mGirlFragment = new GirlFragment();
        loadPic();
        initTab();
        initFragment();

    }

    private void initTab() {
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab.setupWithViewPager(vp);
        for (String title : tabtitle) {
            tab.addTab(tab.newTab().setText(title));
        }
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initFragment() {
        mFragments.add(new AllFragment());
        mFragments.add(new AndroidFragment());
        mFragments.add(new BeforeFragment());
        mFragments.add(new GirlFragment());
        mFragments.add(new IosFragment());
        mFragments.add(new ResFragment());
        mFragments.add(new ZhihuFragment());
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabtitle[position];
            }
        });
    }

    private void loadPic() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://guolin.tech/api/bing_pic")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("GankL", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(() -> Glide.with(MainActivity.this).load(string).into(iv_splash));
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    /**
     * 物理返回键点击事件，点击关闭抽屉
     */
    @Override
    public void onBackPressed() {
        mDrawer = findViewById(R.id.drawer_layout);
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * 右上角setting点击事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this,SearchActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 左侧抽屉布局点击事件
     *
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_all) {
            vp.setCurrentItem(0);
        } else if (id == R.id.nav_android) {
            vp.setCurrentItem(1);
        } else if (id == R.id.nav_before) {
            vp.setCurrentItem(2);
        } else if (id == R.id.nav_gril) {
            vp.setCurrentItem(3);
        } else if (id == R.id.nav_ios) {
            vp.setCurrentItem(4);
        } else if (id == R.id.nav_res) {
            vp.setCurrentItem(5);
        } else if (id == R.id.nav_zhihu) {
            vp.setCurrentItem(6);
        } else if (id == R.id.nav_zhuti) {
            showThemeDialog();
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(this, AboutActivity.class));
        }
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showThemeDialog() {
        final String[] theme = ThemeUtil.getInstance().getTheme();
        new AlertDialog.Builder(this)
                .setTitle("选择主题")
                .setItems(theme, (dialog, which) -> ThemeUtil.getInstance().setTheme(MainActivity.this, theme[which])).create().show();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mNavigationView = findViewById(R.id.nav_view);
        mDrawer = findViewById(R.id.drawer_layout);

        View headerView = mNavigationView.inflateHeaderView(R.layout.nav_header_main);
        iv_splash = headerView.findViewById(R.id.iv_splash);
        tab = findViewById(R.id.tab);
        vp = findViewById(R.id.vp);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        navView = findViewById(R.id.nav_view);
    }

    private long endTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - endTime) > 2000) {
                Toast.makeText(this, "在按一次退出程序", Toast.LENGTH_SHORT).show();
                endTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
