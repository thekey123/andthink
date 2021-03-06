package bmob.activity;

import java.util.ArrayList;
import java.util.List;

import activity.BaseActivity;
import bmob.listener.OnHttpBmobListener;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import handmark.pulltorefresh.library.PullToRefreshBase;
import utils.ShowMsg;

/**
 * Created by thekey123 on 2015/8/25.
 * <p/>
 * 用到bmob框架列表界面的封装
 */
public abstract class BmobListActivity<T> extends BaseActivity implements PullToRefreshBase.OnRefreshListener2, OnHttpBmobListener<T> {

    private static final int STATE_REFRESH = 0;// 下拉刷新
    private static final int STATE_MORE = 1;// 加载更多

    private int limit = 10;        // 每页的数据是10条
    private int curPage = 0;        // 当前页的编号，从0开始

    private List<T> data = new ArrayList<>();

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        loadData(0, STATE_REFRESH);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        loadData(curPage, STATE_MORE);
    }

    /**
     * 查询Bmob的数据
     */
    public void loadData(final int page, final int actionType) {
        BmobQuery<T> query = new BmobQuery<T>();
        query.setLimit(limit);            // 设置每页多少条数据
        query.setSkip(page * limit);        // 从第几条数据开始，
        query.findObjects(this, new FindListener<T>() {
            @Override
            public void onSuccess(List<T> list) {
                if (list.size() > 0) {
                    if (actionType == STATE_REFRESH) {
                        // 当是下拉刷新操作时，将当前页的编号重置为0，并把bankCards清空，重新添加
                        curPage = 0;
                    }
                    data.removeAll(list);
                    data.addAll(list);
                    // 这里在每次加载完数据后，将当前页码+1，这样在上拉刷新的onPullUpToRefresh方法中就不需要操作curPage了
                    curPage++;
                } else if (actionType == STATE_MORE) {
                    ShowMsg.showToast(BmobListActivity.this, "没有更多的数据了");
                } else if (actionType == STATE_REFRESH) {
                    ShowMsg.showToast(BmobListActivity.this, "没有数据");
                }
            }

            @Override
            public void onError(int code, String msg) {

            }
        });
    }
}
