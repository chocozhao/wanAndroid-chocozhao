package com.chocozhao.wanandroid.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.chocozhao.wanandroid.di.module.NoPubliceModule;
import com.chocozhao.wanandroid.mvp.contract.NoPubliceContract;

import com.jess.arms.di.scope.FragmentScope;
import com.chocozhao.wanandroid.mvp.ui.fragment.NoPubliceFragment;


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
@FragmentScope
@Component(modules = NoPubliceModule.class, dependencies = AppComponent.class)
public interface NoPubliceComponent {
    void inject(NoPubliceFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        NoPubliceComponent.Builder view(NoPubliceContract.View view);

        NoPubliceComponent.Builder appComponent(AppComponent appComponent);

        NoPubliceComponent build();
    }
}