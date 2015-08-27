//package autoscrollview;
//
//import android.view.View;
//import android.widget.ImageView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import bmob.fragment.BmobListFragment;
//import cn.andthink.andthink_library.R;
//import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
//
///**
// * Created by thekey123 on 2015/8/27.
// * <p/>
// * 图片轮播的装配
// */
//public abstract class AutoScrollviewPagerFragment<T> extends BmobListFragment<T> {
//
//    /**
//     * 是否第一次初始化轮播，如果是第一次加载指示器，以后不再加载，解决每次下拉刷新指示器会多出一个的问题
//     */
//    protected boolean isFristInitLunBo = true;
//    /**
//     * 轮播图adapter
//     */
//    protected CarouselPagerAdapter pageradapter;
//    /**
//     * 轮播的view
//     */
//    protected List<View> lunbo_list;
//    protected AutoScrollViewPager viewPager;
//
//    @Override
//    protected void initVariable() {
//        /**图片轮播相关初始化**/
//        lunbo_list = new ArrayList<>();
//        for (int i = 0; i < 4; i++) {
//            ImageView iv = new ImageView(getActivity());
//            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            iv.setImageResource(R.drawable.bg);
//            lunbo_list.add(iv);
//        }
//        pageradapter = new CarouselPagerAdapter(,lunbo_list);
//    }
//
//    /**
//     * 设置相关属性
//     */
//    @Override
//    protected void setAttribute() {
//        initLunboData(isFristInitLunBo);
//        viewPager.setAdapter(pageradapter);
//        viewPager.setInterval(4000);
//        viewPager.setScrollDurationFactor(4);
//        viewPager.startAutoScroll();
//    }
//
//    //从服务器获取轮播的数据源
//    protected abstract void initLunboData(final boolean isFristInitLunBo);
//
//}
