package com.chocozhao.wanandroid.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.chocozhao.wanandroid.di.module.KnowledgeModule;
import com.jess.arms.di.component.AppComponent;

import com.chocozhao.wanandroid.mvp.contract.KnowledgeContract;

import com.jess.arms.di.scope.FragmentScope;
import com.chocozhao.wanandroid.mvp.ui.fragment.KnowledgeFragment;


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
@FragmentScope
@Component(modules = KnowledgeModule.class, dependencies = AppComponent.class)
public interface knowledgeComponent {
    void inject(KnowledgeFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        knowledgeComponent.Builder view(KnowledgeContract.View view);

        knowledgeComponent.Builder appComponent(AppComponent appComponent);

        knowledgeComponent build();
    }
}