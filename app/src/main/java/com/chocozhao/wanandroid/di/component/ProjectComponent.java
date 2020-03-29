package com.chocozhao.wanandroid.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.chocozhao.wanandroid.di.module.ProjectModule;
import com.chocozhao.wanandroid.mvp.contract.ProjectContract;

import com.jess.arms.di.scope.FragmentScope;
import com.chocozhao.wanandroid.mvp.ui.fragment.ProjectFragment;


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
@FragmentScope
@Component(modules = ProjectModule.class, dependencies = AppComponent.class)
public interface ProjectComponent {
    void inject(ProjectFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        ProjectComponent.Builder view(ProjectContract.View view);

        ProjectComponent.Builder appComponent(AppComponent appComponent);

        ProjectComponent build();
    }
}