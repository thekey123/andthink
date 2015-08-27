package autoscrollview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by thekey123 on 2015/8/27.
 *
 * 轮播图片需要用到的adapter
 *
 */
public class CarouselPagerAdapter extends PagerAdapter {

    private List<View> mList;

    Context mContext;

    public CarouselPagerAdapter(Context context,List list) {
        mList = list;
        mContext=context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)   {
        container.removeView(mList.get(position));//删除页卡
    }
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {  //这个方法用来实例化页卡

        View view = mList.get(position);
        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view);
        return mList.get(position);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0==arg1;//官方提示这样写
    }
}
