package bmob.listener;

import android.widget.Adapter;

/**
 * Created by thekey123 on 2015/8/25.
 * bmob查询所需要的参数
 */
public interface OnHttpBmobListener<T> {

    //获取adapter实例
    Adapter getAdapter();

    //获取数据的实体类
    Class getClazz();

}
