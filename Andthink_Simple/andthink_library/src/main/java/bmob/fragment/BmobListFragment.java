package bmob.fragment;

import java.util.ArrayList;
import java.util.List;

import bmob.listener.OnHttpBmobListener;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import fragment.BaseFragment;
import handmark.pulltorefresh.library.PullToRefreshBase;
import utils.ShowMsg;

/**
 * Created by thekey123 on 2015/8/27.
 */
public abstract class BmobListFragment<T> extends BaseFragment implements PullToRefreshBase.OnRefreshListener2, OnHttpBmobListener<T> {

    public static final int STATE_REFRESH = 0;// 下拉刷新
    public static final int STATE_MORE = 1;// 加载更多

    private int limit = 10;        // 每页的数据是10条
    private int curPage = 0;        // 当前页的编号，从0开始

    protected List<T> data = new ArrayList<>();

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        loadData(0, STATE_REFRESH);
        onPullDownRefresh();
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
        query.findObjects(getActivity(), new FindListener<T>() {
            @Override
            public void onSuccess(List<T> list) {
                //关闭下拉刷新的状态
                setStateRefresh();
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
                    ShowMsg.showToast(getActivity(), "没有更多的数据了");
                } else if (actionType == STATE_REFRESH) {
                    ShowMsg.showToast(getActivity(), "没有数据");
                }
            }

            @Override
            public void onError(int code, String msg) {

            }
        });
    }

    /**
     * 如果界面存在轮播的图片，下拉的时候也可以调用此方法来刷新轮播的图片
     */
    protected void onPullDownRefresh() {
    }

    /**
     * 结束下拉刷新的状态
     */
    protected void setStateRefresh() {
    }

}
