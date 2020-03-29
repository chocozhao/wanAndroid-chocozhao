package com.chocozhao.wanandroid.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.chocozhao.wanandroid.di.module.NavigationModule;
import com.chocozhao.wanandroid.mvp.contract.NavigationContract;

import com.jess.arms.di.scope.FragmentScope;
import com.chocozhao.wanandroid.mvp.ui.fragment.NavigationFragment;


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
@Component(modules = NavigationModule.class, dependencies = AppComponent.class)
public interface NavigationComponent {
    void inject(NavigationFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        NavigationComponent.Builder view(NavigationContract.View view);

        NavigationComponent.Builder appComponent(AppComponent appComponent);

        NavigationComponent build();
    }
}