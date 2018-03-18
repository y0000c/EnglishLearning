package demo.yc.englishlearning.activity;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;

import demo.yc.englishlearning.MyApplication;
import demo.yc.englishlearning.R;
import demo.yc.lib.base.BaseActivity;

/**
 * description
 * @version XXX
 * @author HE_YONG_CAI
 */
public abstract class BaseAppActivity extends BaseActivity {

	protected Toolbar mToolbar; // 通用的toolbar
	public MyApplication mApp;

	@Override
	public void setContentView(@LayoutRes int layoutResID) {
		super.setContentView(layoutResID);
		mToolbar = (Toolbar) findViewById(R.id.common_toolbar);
		if (null != mToolbar) {
			setSupportActionBar(mToolbar); // 一次绑定，避免在多个activity中绑定
		}
		mApp = (MyApplication) getApplication();
	}


}
