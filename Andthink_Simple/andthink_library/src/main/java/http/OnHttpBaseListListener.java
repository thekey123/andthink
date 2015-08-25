package http;

import android.widget.Adapter;

import java.util.HashMap;

/**
 * Created by thekey123 on 2015/8/24.
 *
 * 访问接口的必要方法
 *
 */
public interface OnHttpBaseListListener<T> {

    //获取请求地址
    String getUrlForList();

    //获取网络请求参数
    HashMap<String, Object> getRequestParams();

    //获取adapter实例
    Adapter getAdapter();

    //获取数据的实体类
    Class getClazz();

}
