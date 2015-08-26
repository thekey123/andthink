package cn.andthink.andthink_simple.activity;

import android.widget.ImageView;
import activity.BaseActivity;
import butterknife.InjectView;
import cn.andthink.andthink_simple.R;

public class MainActivity extends BaseActivity{

    @InjectView(R.id.iv_menu)
    ImageView ivMenu;
    @InjectView(R.id.iv_add)
    ImageView ivAdd;
    @InjectView(R.id.iv_search)
    ImageView ivSearch;

    @Override
    protected void initLayoutAndView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void addListener() {

    }
}

