package cn.andthink.andthink_simple.activity;

import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import activity.BaseActivity;
import butterknife.InjectView;
import cn.andthink.andthink_simple.R;
import cn.andthink.andthink_simple.fragment.HomeFragment;
import cn.andthink.andthink_simple.fragment.MessageFragment;
import cn.andthink.andthink_simple.fragment.PersonalFragment;
import cn.andthink.andthink_simple.fragment.TypeFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    @InjectView(R.id.iv_home)
    ImageView ivHome;
    @InjectView(R.id.iv_type)
    ImageView ivType;
    @InjectView(R.id.iv_message)
    ImageView ivMessage;
    @InjectView(R.id.iv_personal)
    ImageView ivPersonal;
    @InjectView(R.id.bottombar)
    LinearLayout bottombar;
    @InjectView(R.id.bottombar_divider)
    View bottombarDivider;
    @InjectView(R.id.center)
    FrameLayout center;

    private HomeFragment homeFragment;
    private TypeFragment typeFragment;
    private MessageFragment messageFragment;
    private PersonalFragment personalFragment;

    private final static int HOME = 0, TYPE = 1, MESSAGE = 2, PERSONAL = 3;

    @Override
    protected void initLayoutAndView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initVariable() {
        showFragment(HOME);
    }

    @Override
    protected void addListener() {
        ivHome.setOnClickListener(this);
        ivType.setOnClickListener(this);
        ivMessage.setOnClickListener(this);
        ivPersonal.setOnClickListener(this);
    }

    /**
     * 显示指定的fragment
     * * @param index
     */
    private void showFragment(int index) {
        hideAllFragments();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (index) {
            case HOME:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.center, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case TYPE:
                if (typeFragment == null) {
                    typeFragment = new TypeFragment();
                    transaction.add(R.id.center, typeFragment);
                } else {
                    transaction.show(typeFragment);
                }
                break;

            case MESSAGE:
                if (messageFragment == null) {
                    messageFragment = new MessageFragment();
                    transaction.add(R.id.center, messageFragment);
                } else {
                    transaction.show(messageFragment);
                }
                break;

            case PERSONAL:
                if (personalFragment == null) {
                    personalFragment = new PersonalFragment();
                    transaction.add(R.id.center, personalFragment);
                } else {
                    transaction.show(personalFragment);
                }
                break;
        }
        transaction.commit();
        changeBottomButtonStyle(index);
    }

    /**
     * 根据用户点击，改变底部button样式
     */
    private void changeBottomButtonStyle(int index) {
        ImageView[] ivs = {ivHome, ivType, ivMessage, ivPersonal};
        int[] images_off = {R.mipmap.home_press, R.mipmap.type_press, R.mipmap.message_press, R.mipmap.me_press};
        int[] images_on = {R.mipmap.home_normal, R.mipmap.type_normal, R.mipmap.message_normal, R.mipmap.me_normal};

        for (int i = 0; i <= 3; i++) {
            if (i == index) {
                ivs[i].setImageResource(images_on[i]);
            } else {
                ivs[i].setImageResource(images_off[i]);
            }
        }
    }

    /**
     * 隐藏所有Fragment
     */
    private void hideAllFragments() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (homeFragment != null)
            transaction.hide(homeFragment);
        if (typeFragment != null)
            transaction.hide(typeFragment);
        if (messageFragment != null)
            transaction.hide(messageFragment);
        if (personalFragment != null)
            transaction.hide(personalFragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home:
                showFragment(HOME);
                break;
            case R.id.iv_type:
                showFragment(TYPE);
                break;
            case R.id.iv_message:
                showFragment(MESSAGE);
                break;
            case R.id.iv_personal:
                showFragment(PERSONAL);
                break;
        }
    }

    /**
     * 按两次返回键退出应用
     *
     * @param keyCode
     * @param event
     * @return
     */
    long exitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2000)  //System.currentTimeMillis()无论何时调用，肯定大于2000
            {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

