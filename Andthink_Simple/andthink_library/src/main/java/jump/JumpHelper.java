package jump;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by thekey123 on 2015/8/21.
 *
 * 处理跳转的核心类
 *
 */
public class JumpHelper {

    /**
     * 不带参数的跳转
     * @param ondactivity
     * @param c
     */
    public static void jumpToActivity(Activity ondactivity,Class c) {
        Intent intent = new Intent(ondactivity,c);
        ondactivity.startActivity(intent);
    }

    /**
     * 以bunlde作为参数来往于各个activity之间
     * @param ondactivity
     * @param c
     * @param bundle
     */
    public static void jumpToActivity(Activity ondactivity,Class c,Bundle bundle) {
        Intent intent = new Intent(ondactivity,c.getClass());
        try {
            intent.putExtras(bundle);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("andthink", "未序列化");
        }
        ondactivity.startActivity(intent);
    }
}

