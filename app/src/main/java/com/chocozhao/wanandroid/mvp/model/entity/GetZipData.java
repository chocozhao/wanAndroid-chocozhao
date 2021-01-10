package com.chocozhao.wanandroid.mvp.model.entity;

import java.util.List;

/**
 * ClaseName：GetArticleData
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/12/17 16:12
 * Modified By：
 * Fixtime：2019/12/17 16:12
 * FixDescription：
 **/

public class GetZipData {

    private BaseResponse<GetArticleData> getArticleData;
    private BaseResponse<List<GetBannerInfo>> getBannerInfo;

    public GetZipData(BaseResponse<List<GetBannerInfo>> getBannerInfo, BaseResponse<GetArticleData> getArticleData) {
        this.getArticleData = getArticleData;
        this.getBannerInfo = getBannerInfo;
    }

    public BaseResponse<GetArticleData> getGetArticleData() {
        return getArticleData;
    }

    public void setGetArticleData(BaseResponse<GetArticleData> getArticleData) {
        this.getArticleData = getArticleData;
    }

    public BaseResponse<List<GetBannerInfo>> getGetBannerInfo() {
        return getBannerInfo;
    }

    public void setGetBannerInfo(BaseResponse<List<GetBannerInfo>> getBannerInfo) {
        this.getBannerInfo = getBannerInfo;
    }
}