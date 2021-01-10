package com.chocozhao.wanandroid.di.module;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.chocozhao.wanandroid.mvp.contract.HomeContract;
import com.chocozhao.wanandroid.mvp.contract.KnowledgeContract;
import com.chocozhao.wanandroid.mvp.model.KnowledgeModel;
import com.chocozhao.wanandroid.mvp.model.entity.GetArticleData;
import com.chocozhao.wanandroid.mvp.model.entity.GetTestData;
import com.chocozhao.wanandroid.mvp.ui.adapter.ArticleAdapter;
import com.chocozhao.wanandroid.mvp.ui.adapter.TestAdapter;
import com.jess.arms.di.scope.FragmentScope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
public abstract class KnowledgeModule {

    @Binds
    abstract KnowledgeContract.Model bindKnowledgeModel(KnowledgeModel model);


//    @FragmentScope
//    @Provides
//    static RecyclerView.LayoutManager provideGridLayoutManager(HomeContract.View view) {
//        return new GridLayoutManager(view.getActivity(),4);
//    }

    @FragmentScope
    @Provides
    static List<GetTestData> provideTestList() {
        return new ArrayList<>();
    }


    @FragmentScope
    @Provides
    static Map<Integer, Boolean> provideMap() {
        return new HashMap<>();
    }

    @FragmentScope
    @Provides
    static TestAdapter provideTestAdapter(List<GetTestData> list){
        return new TestAdapter(list);
    }
}