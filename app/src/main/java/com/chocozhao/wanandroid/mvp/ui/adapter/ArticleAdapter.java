package com.chocozhao.wanandroid.mvp.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chocozhao.wanandroid.R;
import com.chocozhao.wanandroid.mvp.model.entity.GetArticleData;

import java.util.List;

public class ArticleAdapter extends BaseQuickAdapter<GetArticleData.DatasBean, BaseViewHolder> {


    public ArticleAdapter(@Nullable List<GetArticleData.DatasBean> data) {
        super(R.layout.recycle_item_article, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GetArticleData.DatasBean item) {
        helper.setText(R.id.tv_author_name, TextUtils.isEmpty(item.getTitle())?item.getShareUser():item.getAuthor())
                .setText(R.id.tv_article_title, Html.fromHtml(item.getTitle()))
                .setText(R.id.tv_super_chapterName,item.getSuperChapterName()+" | "+item.getChapterName())
                .setText(R.id.tv_article_date,item.getNiceDate());

    }
}
