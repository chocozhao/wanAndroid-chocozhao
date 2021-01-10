package com.chocozhao.wanandroid.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chocozhao.wanandroid.R;
import com.chocozhao.wanandroid.di.component.DaggerknowledgeComponent;
import com.chocozhao.wanandroid.mvp.contract.KnowledgeContract;
import com.chocozhao.wanandroid.mvp.model.entity.GetTestData;
import com.chocozhao.wanandroid.mvp.presenter.KnowledgePresenter;
import com.chocozhao.wanandroid.mvp.ui.adapter.TestAdapter;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import timber.log.Timber;

import static com.jess.arms.utils.Preconditions.checkNotNull;


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
public class KnowledgeFragment extends BaseFragment<KnowledgePresenter> implements KnowledgeContract.View {

    @BindView(R.id.rv_test)
    RecyclerView mRvTest;
    //    @Inject
//    GridLayoutManager mLayoutManager;
    @Inject
    TestAdapter mTestAdapter;
    @Inject
    List<GetTestData> mTestDataList;
    @BindView(R.id.tv_template)
    TextView mTvTemplate;
    @Inject
    Map<Integer, Boolean> map;


    public static KnowledgeFragment newInstance() {
        KnowledgeFragment fragment = new KnowledgeFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerknowledgeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_knowledge, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        //初始化适配器
        initRecyclerView();
        CheckBox checkBox = getView().findViewById(R.id.cb_select);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    for (int i = 0; i < mTestAdapter.getData().size(); i++) {
                        //选中
                        map.put(i, true);
                    }
                } else {
                    for (int i = 0; i < mTestAdapter.getData().size(); i++) {
                        //取消选中
                        map.put(i, false);
                    }
                }
                mTestAdapter.setMap(map);
            }
        });
    }


    /**
     * 通过此方法可以使 Fragment 能够与外界做一些交互和通信, 比如说外部的 Activity 想让自己持有的某个 Fragment 对象执行一些方法,
     * 建议在有多个需要与外界交互的方法时, 统一传 {@link Message}, 通过 what 字段来区分不同的方法, 在 {@link #setData(Object)}
     * 方法中就可以 {@code switch} 做不同的操作, 这样就可以用统一的入口方法做多个不同的操作, 可以起到分发的作用
     * <p>
     * 调用此方法时请注意调用时 Fragment 的生命周期, 如果调用 {@link #setData(Object)} 方法时 {@link Fragment#onCreate(Bundle)} 还没执行
     * 但在 {@link #setData(Object)} 里却调用了 Presenter 的方法, 是会报空的, 因为 Dagger 注入是在 {@link Fragment#onCreate(Bundle)} 方法中执行的
     * 然后才创建的 Presenter, 如果要做一些初始化操作,可以不必让外部调用 {@link #setData(Object)}, 在 {@link #initData(Bundle)} 中初始化就可以了
     * <p>
     * Example usage:
     * <pre>
     * public void setData(@Nullable Object data) {
     *     if (data != null && data instanceof Message) {
     *         switch (((Message) data).what) {
     *             case 0:
     *                 loadData(((Message) data).arg1);
     *                 break;
     *             case 1:
     *                 refreshUI();
     *                 break;
     *             default:
     *                 //do something
     *                 break;
     *         }
     *     }
     * }
     *
     * // call setData(Object):
     * Message data = new Message();
     * data.what = 0;
     * data.arg1 = 1;
     * fragment.setData(data);
     * </pre>
     *
     * @param data 当不需要参数时 {@code data} 可以为 {@code null}
     */
    @Override
    public void setData(@Nullable Object data) {

    }

    //初始化适配器
    private void initRecyclerView() {
        ArmsUtils.configRecyclerView(mRvTest, new GridLayoutManager(mContext, 4));

        for (int i = 0; i < 10; i++) {
            GetTestData data = new GetTestData();
            data.setAmt("￥" + i + ".00/瓶/瓶");
            data.setNumber(i + "ss");
            data.setTitle(i + "--燕塘草莓味酸奶 250ml");
            mTestDataList.add(data);
        }
        //初始化adatper数据
        mRvTest.setAdapter(mTestAdapter);
        mTestAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ImageView imageView = view.findViewById(R.id.iv_select);
//                for (Integer key : mTestAdapter.getMap().keySet()) {
//                    LogUtils.debugInfo("booleanValue:"+mTestAdapter.getMap().get(key).booleanValue());
//                    imageView.setVisibility(mTestAdapter.getMap().get(key).booleanValue()?View.GONE:View.VISIBLE);
//                }
                if (imageView.getVisibility() == View.GONE) {
                    //选中
                    imageView.setVisibility(View.VISIBLE);
                    mTestAdapter.getMap().put(position, true);
                } else {
                    //取消选中
                    imageView.setVisibility(View.GONE);
                    mTestAdapter.getMap().put(position, false);
                }
            }
        });
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {

    }


    @OnClick({R.id.tv_template})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_template:

                break;

        }
    }
}
