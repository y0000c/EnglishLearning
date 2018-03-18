package demo.yc.lib.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import demo.yc.lib.R;
import demo.yc.lib.utils.ActivityUtil;

/**
 * description：对基类Activity进行生命周期划分
 * 布局加载，事件处理
 * @version XXX
 * @author HE_YONG_CAI
 */

public abstract class BaseActivity extends AppCompatActivity {

	protected String mTag; // 定义公用的TAG标识
	Unbinder mUnbinder; // 用于viewId的绑定

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityUtil.newInstance().addActivity(this);
		mTag = getClass().getSimpleName();
		initWindow();
		if (getLayoutId() != 0) { // 布局文件id不可以为0
			setContentView(getLayoutId());
		} else {
			throw new IllegalArgumentException(getResources()
					.getString(R.string.no_layout_file_id));
		}
		initEvents();
	}

	@Override
	public void setContentView(@LayoutRes int layoutResID) {
		super.setContentView(layoutResID);
		mUnbinder = ButterKnife.bind(this);
	}

	/**
	 * 每个Activity都必须加载对应的布局文件，因此设置为abstract
	 * @return 由子类提供
	 */
	protected abstract int getLayoutId();

	/**
	 * 处理每个Activity对应的事件
	 */
	protected abstract void initEvents();

	/**
	 * 初始化状态栏4.4以上,因为只需要在基类控制，因此权限为private
	 */
	private void initWindow() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { // KITKAT 是支持透明状态栏的最低版本
			WindowManager.LayoutParams windowLp = getWindow().getAttributes();
			windowLp.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | windowLp.flags);
		}
	}

	/**
	 * 提供公用的StartActivity封装方法
	 * @param clazz 目标class
	 * @param isKill 是否finish当前Activity
	 */
	public void toOtherActivity(Class<?> clazz, boolean isKill) {
		Intent intent = new Intent(this, clazz);
		startActivity(intent);
		if (isKill) {
            finish();
        }
	}

	@Override
	public void finish() {
		super.finish();
		ActivityUtil.newInstance().removeActivity(this);
	}

	@Override
	protected void onDestroy() {
		mUnbinder.unbind();
		super.onDestroy();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
