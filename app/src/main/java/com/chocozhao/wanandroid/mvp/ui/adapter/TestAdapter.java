package com.chocozhao.wanandroid.mvp.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chocozhao.wanandroid.R;
import com.chocozhao.wanandroid.mvp.model.entity.GetArticleData;
import com.chocozhao.wanandroid.mvp.model.entity.GetTestData;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import timber.log.Timber;

public class TestAdapter extends BaseQuickAdapter<GetTestData, BaseViewHolder> {

    Map<Integer, Boolean> map;

    public TestAdapter(@Nullable List<GetTestData> data) {
        super(R.layout.item_test, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GetTestData item) {
        if (!ArmsUtils.isEmpty(map)) {
            for (Integer key : map.keySet()) {
                LogUtils.debugInfo("map Value:" + map.get(key).booleanValue());
                helper.setGone(R.id.iv_select, map.get(key).booleanValue());
            }
        }
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_number, item.getNumber());
        helper.setText(R.id.tv_amt, item.getAmt());

    }

    public Map<Integer, Boolean> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Boolean> map) {
        this.map = map;
        notifyDataSetChanged();
    }
}
