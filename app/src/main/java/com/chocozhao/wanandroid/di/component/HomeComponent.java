package com.chocozhao.wanandroid.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.chocozhao.wanandroid.di.module.HomeModule;
import com.chocozhao.wanandroid.mvp.contract.HomeContract;

import com.jess.arms.di.scope.FragmentScope;
import com.chocozhao.wanandroid.mvp.ui.fragment.HomeFragment;


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
@FragmentScope
@Component(modules = HomeModule.class, dependencies = AppComponent.class)
public interface HomeComponent {
    void inject(HomeFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        HomeComponent.Builder view(HomeContract.View view);

        HomeComponent.Builder appComponent(AppComponent appComponent);

        HomeComponent build();
    }
}