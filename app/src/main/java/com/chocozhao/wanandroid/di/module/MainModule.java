package com.chocozhao.wanandroid.di.module;

import dagger.Binds;
import dagger.Module;

import com.chocozhao.wanandroid.mvp.contract.MainContract;
import com.chocozhao.wanandroid.mvp.model.MainModel;


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
@Module
public abstract class MainModule {

    @Binds
    abstract MainContract.Model bindMainModel(MainModel model);
}