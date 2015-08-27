
package cn.andthink.andthink_simple;
import android.app.Application;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

import cn.andthink.andthink_simple.utils.DiskCacheConfig;

/**
 * Created by Mr.zheng on 2015/7/20.
 *
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化ImageLoader
        initImageLoader();
    }

    /**
     * 初始化ImageLoader配置文件
     */
    private void initImageLoader() {

        File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), DiskCacheConfig.IMAGE_CACHE_DIR);
        if (!cacheDir.exists())
            cacheDir.mkdirs();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPoolSize(3)
                .memoryCache(new WeakMemoryCache())
                .memoryCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(300)
                .denyCacheImageMultipleSizesInMemory()
                .diskCache(new UnlimitedDiskCache(cacheDir))
                .defaultDisplayImageOptions(DISPLAY_IMAGE_OPTIONS())
                .diskCacheExtraOptions(480, 800, null)
                .build();
        ImageLoader.getInstance().init(config);
    }

    /**
     * 初始化ImaegLoader显示属性
     */
    public synchronized static DisplayImageOptions DISPLAY_IMAGE_OPTIONS() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
//                .showImageOnFail(R.mipmap.default_loading)
//                .showImageOnLoading(R.mipmap.default_loading)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .delayBeforeLoading(0)
                .build();
        return options;
    }
}
