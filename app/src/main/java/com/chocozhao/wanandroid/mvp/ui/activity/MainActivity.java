package com.chocozhao.wanandroid.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.chocozhao.wanandroid.R;
import com.chocozhao.wanandroid.di.component.DaggerMainComponent;
import com.chocozhao.wanandroid.mvp.contract.MainContract;
import com.chocozhao.wanandroid.mvp.presenter.MainPresenter;
import com.chocozhao.wanandroid.mvp.ui.adapter.SectionsPagerAdapter;
import com.chocozhao.wanandroid.mvp.ui.fragment.HomeFragment;
import com.chocozhao.wanandroid.mvp.ui.fragment.KnowledgeFragment;
import com.chocozhao.wanandroid.mvp.ui.fragment.NavigationFragment;
import com.chocozhao.wanandroid.mvp.ui.fragment.NoPubliceFragment;
import com.chocozhao.wanandroid.mvp.ui.fragment.ProjectFragment;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 10/15/2019 23:29
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private String[] mTitles;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
//      初始化底部导航栏
        initBottomNavigationBar();
        initViewPager();

    }


    private void initViewPager() {
        fragmentList.clear();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new KnowledgeFragment());
        fragmentList.add(new NoPubliceFragment());
        fragmentList.add(new NavigationFragment());
        fragmentList.add(new ProjectFragment());
        mViewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager(), fragmentList));
        mViewPager.setOffscreenPageLimit(mTitles.length);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomNavigationBar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public void initBottomNavigationBar() {
         mTitles = new String[]{"首页", "知识体系", "公众号", "导航", "项目"};
        mBottomNavigationBar
                .setActiveColor(R.color.bg_white)
                .setInActiveColor(R.color.text_gray)
                .setBarBackgroundColor(R.color.text_theme);

        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        mBottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);

        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_press_24dp, mTitles[0])
                        .setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.ic_home_normal_24dp)))
                .addItem(new BottomNavigationItem(R.drawable.ic_knowledge_press, mTitles[1])
                        .setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.ic_knowledge_normal)))
                .addItem(new BottomNavigationItem(R.drawable.ic_comments_solid_press, mTitles[2])
                        .setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.ic_comments_solid_normal)))
                .addItem(new BottomNavigationItem(R.drawable.ic_navigation_press_24dp, mTitles[3])
                        .setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.ic_navigation_normal_24dp))).
                addItem(new BottomNavigationItem(R.drawable.ic_android_project_press_24dp, mTitles[4])
                        .setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.ic_android_project_normal_24dp)))
                .setFirstSelectedPosition(0)
                .initialise();//所有的设置需在调用该方法前完成```

        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {//这里也可以使用SimpleOnTabSelectedListener
            @Override
            public void onTabSelected(int position) {//未选中 -> 选中
                mViewPager.setCurrentItem(position);

            }

            @Override
            public void onTabUnselected(int position) {//选中 -> 未选中


            }

            @Override
            public void onTabReselected(int position) {//选中 -> 选中

            }
        });
    }



    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Stop polling service
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
