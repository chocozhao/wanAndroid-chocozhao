package com.chocozhao.wanandroid.di.module;

import dagger.Binds;
import dagger.Module;

import com.chocozhao.wanandroid.mvp.contract.KnowledgeContract;
import com.chocozhao.wanandroid.mvp.model.KnowledgeModel;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 11/06/2019 00:21
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class knowledgeModule {

    @Binds
    abstract KnowledgeContract.Model bindknowledgeModel(KnowledgeModel model);
}