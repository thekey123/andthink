package utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by thekey123 on 2015/8/27.
 */
public class ShowMsg {

    public static void showToast(Context context,String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
