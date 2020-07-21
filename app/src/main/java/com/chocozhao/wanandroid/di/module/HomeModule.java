package com.chocozhao.wanandroid.di.module;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chocozhao.wanandroid.R;
import com.chocozhao.wanandroid.mvp.contract.HomeContract;
import com.chocozhao.wanandroid.mvp.model.HomeModel;
import com.chocozhao.wanandroid.mvp.model.entity.GetArticleData;
import com.chocozhao.wanandroid.mvp.model.entity.GetBannerInfo;
import com.chocozhao.wanandroid.mvp.ui.adapter.ArticleAdapter;
import com.jess.arms.di.scope.FragmentScope;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 11/06/2019 00:18
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class HomeModule {

    @Binds
    abstract HomeContract.Model bindHomeModel(HomeModel model);


    @FragmentScope
    @Provides
    static RxPermissions provideRxPermissions(HomeContract.View view) {
        return new RxPermissions(view.getFragment());
    }

    @FragmentScope
    @Provides
    static RecyclerView.LayoutManager provideLayoutManager(HomeContract.View view) {
        return new LinearLayoutManager(view.getActivity());
    }

    @FragmentScope
    @Provides
    static List<GetArticleData.DatasBean> provideArticleList() {
        return new ArrayList<>();
    }

    @FragmentScope
    @Provides
    static ArticleAdapter provideArticleAdapter(List<GetArticleData.DatasBean> list){
        return new ArticleAdapter(list);
    }
}