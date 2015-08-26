package bmob.activity;

import java.util.Comparator;

import activity.BaseActivity;
import bmob.listener.OnHttpBmobListener;
import handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by thekey123 on 2015/8/25.
 *
 *  用到bmob框架列表界面的封装
 *
 */
public abstract class BmobListActivity<T> extends BaseActivity implements Comparator<T>, PullToRefreshBase.OnRefreshListener2, OnHttpBmobListener<T> {







    /**
     * 从Bmob平台获取数据
     */
    protected void loadData() {

    }
}
