package bmob;

import android.content.Context;

import bmob.callback.OnHttpObjectCallBack;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;

/**
 * Created by thekey123 on 2015/8/25.
 * <p/>
 * Bmob操作核心类
 */
public class BmobHelper<T> {

    private Context context;

    public BmobHelper (Context context) {
        this.context = context;
    }

    /**
     * 初始化Bmob
     *
     * @param context
     * @param appId
     */
    public static void initBmob(Context context, String appId) {
        Bmob.initialize(context, appId);
    }

    /**
     * 根据ID查询单个对象的详细信息
     * @param callBack
     */
    public void loadDataById(String ObjectId,final OnHttpObjectCallBack<T> callBack) {
        BmobQuery<T> query = new BmobQuery<T>();
        query.getObject(context,ObjectId, new GetListener<T>() {

            @Override
            public void onSuccess(T object) {
                // TODO Auto-generated method stub
                    if (object!=null) {
                        callBack.onHttpResult(object);
                    }else {
                        callBack.onHttpResult(null);
                    }
            }

            @Override
            public void onFailure(int code, String arg0) {
                // TODO Auto-generated method stub
                callBack.onHttpResult(null);
            }

        });
    }
}
