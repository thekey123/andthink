package activity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import handmark.pulltorefresh.library.PullToRefreshBase;
import http.HttpEngine;
import http.OnHttpBaseListListener;
import http.OnHttpListResultListener;

/**
 * Created by thekey123 on 2015/8/24.
 * <p/>
 * 所有带有列表界面的Actvivity
 */
public abstract class BaseListActivity<T> extends BaseActivity implements Comparator<T>, PullToRefreshBase.OnRefreshListener2, OnHttpBaseListListener<T> {

    private List<T> data = new ArrayList<>();
    /**
     * 获取数据的起始值
     */
    private int start = 0;

    protected void initDataFromServer() {
        //初始化数据
        HashMap<String, Object> params = getRequestParams();
        params.put("start", 0);
        loadData(params);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        HashMap<String, Object> params = getRequestParams();
        params.put("start", 0);
        loadData(params);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        HashMap<String, Object> params = getRequestParams();
        params.put("start", start);
        loadData(params);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataFromServer();
    }

    protected void loadData(HashMap<String, Object> requestParams) {
        final HttpEngine<T> httpEngine = new HttpEngine<>();
        httpEngine.setUrl(getUrlForList());
        httpEngine.setRequestParams(requestParams);
        httpEngine.doPostForListResult(getClazz(), new OnHttpListResultListener<T>() {
            @Override
            public void onHttpResult(List<T> results) {
                //获取更新前本地的数据条数
                int pre_num = data.size();
                //将本地上重复的数据去掉
                data.removeAll(results);
                //将服务器上的数据添加到本地的数据
                data.addAll(results);
                //排序
                Collections.sort(data, BaseListActivity.this);
                //新增的数据条数
                int update_num = data.size() - pre_num;
                start += update_num;
            }
        });
    }
}
