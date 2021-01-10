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

public class GetTestData {
    private String title;
    private String number;
    private String amt;
    private Boolean isSelect = false;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public Boolean isSelect() {
        return isSelect;
    }

    public void setSelect(Boolean select) {
        isSelect = select;
    }
}