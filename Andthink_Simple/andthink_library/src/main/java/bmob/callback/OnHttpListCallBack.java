package bmob.callback;

import java.util.List;

/**
 * Created by thekey123 on 2015/8/25.
 *
 * 处理Bmob的回调
 *
 */
public interface OnHttpListCallBack<T> {
    void onHttpResult(List<T> results);
}
