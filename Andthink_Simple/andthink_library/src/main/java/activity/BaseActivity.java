package activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by thekey123 on 2015/8/24.
 *
 * 所有Activity的父类
 *
 */

public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initLayoutAndView();
        injectView();
        getBundle();
        initVariable();
        setAttribute();
        addListener();
    }

    /**
     * 获取从其他界面传过来的参数
     */
    protected void getBundle() {

    }

    /**
     * 初始化用到的相关常量api
     */
    protected void initVariable() {

    }

    /**
     * 设置相关属性方法
     */
    protected void setAttribute() {

    }

    /**
     * 注解注入控件
     */
    private void injectView() {
        ButterKnife.inject(this);
    }

    /**
     * 给activity添加布局
     */
    protected abstract void initLayoutAndView();

    /**
     * 添加事件监听器
     */
    protected abstract void addListener();

    /**
     * 界面统一的返回事件
     *
     * @param view
     */
    public void back(View view) {
        finish();
    }

    /**
     * 释放相关用到的api资源
     */
    @Override
    protected void onDestroy() {
        ButterKnife.reset(this);
        super.onDestroy();
    }

}
