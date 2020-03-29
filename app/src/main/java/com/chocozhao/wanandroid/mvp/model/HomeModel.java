package com.chocozhao.wanandroid.mvp.model;

import android.app.Application;

import com.chocozhao.wanandroid.mvp.contract.HomeContract;
import com.chocozhao.wanandroid.mvp.model.api.service.UserService;
import com.chocozhao.wanandroid.mvp.model.entity.BaseResponse;
import com.chocozhao.wanandroid.mvp.model.entity.GetBannerInfo;
import com.google.gson.Gson;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


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
public class HomeModel extends BaseModel implements HomeContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public HomeModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }


    @Override
    public Observable<BaseResponse<List<GetBannerInfo>>> getBanner() {
        return mRepositoryManager
                .obtainRetrofitService(UserService.class)
                .getBanner();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}