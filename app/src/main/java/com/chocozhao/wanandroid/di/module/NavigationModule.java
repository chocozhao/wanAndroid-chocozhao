package com.chocozhao.wanandroid.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.chocozhao.wanandroid.mvp.contract.NavigationContract;
import com.chocozhao.wanandroid.mvp.model.NavigationModel;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 11/06/2019 00:24
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class NavigationModule {

    @Binds
    abstract NavigationContract.Model bindNavigationModel(NavigationModel model);
}