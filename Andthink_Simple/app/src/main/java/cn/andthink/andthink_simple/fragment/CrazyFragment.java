package cn.andthink.andthink_simple.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import autoscrollview.CarouselPagerAdapter;
import bmob.fragment.BmobListFragment;
import butterknife.InjectView;
import cn.andthink.andthink_simple.MyApplication;
import cn.andthink.andthink_simple.R;
import cn.andthink.andthink_simple.adapter.CrazyAdapter;
import cn.andthink.andthink_simple.modle.Article;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import handmark.pulltorefresh.library.PullToRefreshScrollView;
import me.relex.circleindicator.CircleIndicator;
import view.NoScrollListView;

/**
 * Created by thekey123 on 2015/8/25.
 */
public class CrazyFragment extends BmobListFragment<Article> {

    @InjectView(R.id.view_pager)
    AutoScrollViewPager viewPager;
    @InjectView(R.id.indicator)
    CircleIndicator indicator;
    @InjectView(R.id.noscrollview)
    NoScrollListView noscrollview;
    @InjectView(R.id.scrollView)
    PullToRefreshScrollView scrollView;

    private CrazyAdapter adapter;

    //是否第一次初始化轮播，如果是第一次加载指示器，以后不再加载，解决每次下拉刷新指示器会多出一个的问题
    protected boolean isFristInitLunBo = true;
    protected CarouselPagerAdapter pageradapter;
    protected List<View> lunbo_list;
    
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    
    @Override
    protected void addListener() {
            scrollView.setOnRefreshListener(this);
    }

    @Override
    protected void setAttribute() {
        initLunboData(isFristInitLunBo);
        viewPager.setAdapter(pageradapter);
        viewPager.setInterval(4000);
        viewPager.setScrollDurationFactor(4);
        viewPager.startAutoScroll();
    }

    /**
     * 获取轮播的数据
     * @param isFristInitLunBo
     */
    private void initLunboData(final boolean isFristInitLunBo) {
        BmobQuery<Article> query = new BmobQuery<Article>();
        query.addWhereEqualTo("isNice",true);
        query.addWhereEqualTo("isPass", true);
        query.findObjects(getActivity(), new FindListener<Article>() {
            @Override
            public void onSuccess(List<Article> results) {
                for (int i = 0; i < results.size(); i++) {
                    ImageLoader.getInstance().displayImage(results.get(i).getImage(), (ImageView) lunbo_list.get(i), MyApplication.DISPLAY_IMAGE_OPTIONS());
                }
                if (isFristInitLunBo) {
                    indicator.setViewPager(viewPager);
                }
                pageradapter.notifyDataSetChanged();
            }
            @Override
            public void onError(int code, String msg) {

            }
        });
    }

    @Override
    protected void initVariable() {
        /**图片轮播相关初始化**/
        lunbo_list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ImageView iv = new ImageView(getActivity());
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setImageResource(cn.andthink.andthink_library.R.drawable.bg);
            lunbo_list.add(iv);
        }
        pageradapter = new CarouselPagerAdapter(getActivity(),lunbo_list);
        adapter = new CrazyAdapter(getActivity(),data);
        noscrollview.setAdapter(adapter);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_crazy, container, false);
    }

    @Override
    public Adapter getAdapter() {
        return adapter;
    }

    @Override
    public Class getClazz() {
        return Article.class;
    }

    /**
     * 刷新轮播的操作
     */
    @Override
    protected void onPullDownRefresh() {
        initLunboData(false);
    }

    @Override
    protected void setStateRefresh() {
        if (scrollView!=null)
        scrollView.onRefreshComplete();
    }
}
