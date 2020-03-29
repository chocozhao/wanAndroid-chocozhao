package com.chocozhao.wanandroid.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.chocozhao.wanandroid.mvp.contract.NoPubliceContract;
import com.chocozhao.wanandroid.mvp.model.NoPubliceModel;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 11/06/2019 00:23
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class NoPubliceModule {

    @Binds
    abstract NoPubliceContract.Model bindNoPubliceModel(NoPubliceModel model);
}