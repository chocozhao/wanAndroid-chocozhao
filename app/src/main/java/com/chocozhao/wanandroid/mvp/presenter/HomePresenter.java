package com.chocozhao.wanandroid.mvp.presenter;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.chocozhao.wanandroid.app.utils.RxUtils;
import com.chocozhao.wanandroid.mvp.contract.HomeContract;
import com.chocozhao.wanandroid.mvp.model.entity.BaseResponse;
import com.chocozhao.wanandroid.mvp.model.entity.GetArticleData;
import com.chocozhao.wanandroid.mvp.model.entity.GetBannerInfo;
import com.chocozhao.wanandroid.mvp.model.entity.GetZipData;
import com.chocozhao.wanandroid.mvp.model.entity.User;
import com.chocozhao.wanandroid.mvp.ui.adapter.ArticleAdapter;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.jess.arms.utils.PermissionUtil;
import com.jess.arms.utils.RxLifecycleUtils;

import java.util.List;
import java.util.Timer;
import java.util.logging.Logger;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import timber.log.Timber;


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
public class HomePresenter extends BasePresenter<HomeContract.Model, HomeContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    @Inject
    List<GetArticleData.DatasBean> mArticleData;
    @Inject
    ArticleAdapter mArticleAdapter;
    private int num = 1;
    private boolean isFirst = true;
    private int preEndIndex;
    private static final int PAGE_SIZE = 10;

    @Inject
    public HomePresenter(HomeContract.Model model, HomeContract.View rootView) {
        super(model, rootView);
    }

    //fragment用ON_START  activity用ON_CREATE
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onCreate() {
        //打开 App 时自动加载列表
        requestData(true, num);
    }

    public void requestData(final boolean pullToRefresh, int num) {
        //请求外部存储权限用于适配android6.0的权限管理机制
        PermissionUtil.externalStorage(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
                //request permission success, do something.
//                requestBannerFromModel();
//                //列表数据
//                requestArticle(pullToRefresh, num);
                requestZipFromModel();
            }

            @Override
            public void onRequestPermissionFailure(List<String> permissions) {
                mRootView.showMessage("Request permissions failure");
                mRootView.hideLoading();//隐藏下拉刷新的进度条
            }

            @Override
            public void onRequestPermissionFailureWithAskNeverAgain(List<String> permissions) {
                mRootView.showMessage("Need to go to the settings");
                mRootView.hideLoading();//隐藏下拉刷新的进度条
            }
        }, mRootView.getRxPermissions(), mErrorHandler);
    }

    private void requestZipFromModel() {
//        Observable.zip(mModel.getBanner(), mModel.getArticle(1, true), new BiFunction<BaseResponse<List<GetBannerInfo>>, BaseResponse<GetArticleData>, BaseResponse<GetZipData>>() {
//            @Override
//            public BaseResponse<GetZipData>apply(BaseResponse<List<GetBannerInfo>> listBaseResponse, BaseResponse<GetArticleData> getArticleDataBaseResponse) throws Exception {
//                BaseResponse<GetZipData> getZipDataBaseResponse = new BaseResponse<>();
//                GetZipData getZipData = new GetZipData(listBaseResponse,getArticleDataBaseResponse);
//                getZipDataBaseResponse.setData(getZipData);
//                return getZipDataBaseResponse;
//            }
//        }).compose(RxUtils.observableToMain())
//                .subscribe(new ErrorHandleSubscriber<BaseResponse<GetZipData>>(mErrorHandler) {
//                    @Override
//                    public void onNext(BaseResponse<GetZipData> baseResponse) {
//                        if (baseResponse.isSuccess()) {
//                            mRootView.setUpBannerData(baseResponse.getData().getGetBannerInfo().getData());
//                        } else {
//                            mRootView.showMessage(baseResponse.getErrorMsg());
//                        }
////                        LogUtils.debugInfo("getGetBannerInfo getTitle:" + baseResponse.getData().getGetBannerInfo().getData().get(0).getTitle()
////                                + "  getGetArticleData Author:" + baseResponse.getData().getGetArticleData().getData().getDatas().get(0).getAuthor());
//                    }
//                });

        mModel.getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(new Function<BaseResponse<List<GetBannerInfo>>, ObservableSource<BaseResponse<GetArticleData>>>() {
                    @Override
                    public ObservableSource<BaseResponse<GetArticleData>> apply(BaseResponse<List<GetBannerInfo>> listBaseResponse) throws Exception {
                        Timber.d("getBanner:" + listBaseResponse.getData().get(0).getTitle());
                        if (listBaseResponse.isSuccess()) {
                            mRootView.setUpBannerData(listBaseResponse.getData());
                        } else {
                            mRootView.showMessage(listBaseResponse.getErrorMsg());
                        }
                        return mModel.getArticle(1, true);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<BaseResponse<GetArticleData>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseResponse<GetArticleData> getArticleDataBaseResponse) {
                        Timber.d("getArticle:" + getArticleDataBaseResponse.getData().getDatas().get(0).getAuthor());
                    }
                });

    }

    /**
     * 轮播图
     */
    public void requestBannerFromModel() {
        mModel.getBanner()
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse<List<GetBannerInfo>>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseResponse<List<GetBannerInfo>> listBaseResponse) {
                        if (listBaseResponse.isSuccess()) {
                            mRootView.setUpBannerData(listBaseResponse.getData());
                        } else {
                            mRootView.showMessage(listBaseResponse.getErrorMsg());
                        }
                    }
                });
    }

    //请求M层数据并做逻辑处理
    public void requestArticle(boolean pullToRefresh, int num) {
        if (pullToRefresh) {
            num = 1;//下拉刷新默认只请求第一页
        }

        //关于RxCache缓存库的使用请参考 http://www.jianshu.com/p/b58ef6b0624b

        //是否驱逐缓存,为ture即不使用缓存,每次下拉刷新即需要最新数据,则不使用缓存
        boolean isEvictCache = pullToRefresh;

        //默认在第一次下拉刷新时使用缓存
        if (pullToRefresh && isFirst) {
            isFirst = false;
            isEvictCache = false;
        }

        mModel.getArticle(num, isEvictCache)
                .subscribeOn(Schedulers.io())
                //遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .retryWhen(new RetryWithDelay(3, 2))
                .doOnSubscribe(disposable -> {
                    if (pullToRefresh) {
                        mRootView.showLoading();//显示下拉刷新的进度条
                    } else {
                        mRootView.startLoadMore();//显示上拉加载更多的进度条
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    if (pullToRefresh) {
                        mRootView.hideLoading();//隐藏下拉刷新的进度条
                    } else {
                        mRootView.endLoadMore();//隐藏上拉加载更多的进度条
                    }
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(new ErrorHandleSubscriber<BaseResponse<GetArticleData>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseResponse<GetArticleData> articleData) {
                        if (articleData.isSuccess()) {
                            if (pullToRefresh) {
                                mArticleData.clear();//如果是下拉刷新则清空列表
                            }
                            //往list添加数据
                            if (pullToRefresh) {
                                mArticleAdapter.setNewData(articleData.getData().getDatas());
                            } else {
                                mArticleAdapter.addData(articleData.getData().getDatas());
                            }
                            if (articleData.getData().getDatas().size() < PAGE_SIZE) {
                                //第一页如果不够一页就不显示没有更多数据布局
                                mArticleAdapter.loadMoreEnd();
                            } else {
                                mArticleAdapter.loadMoreComplete();
                            }
                        } else {
                            articleData.getErrorMsg();
                        }

                    }
                });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
}
