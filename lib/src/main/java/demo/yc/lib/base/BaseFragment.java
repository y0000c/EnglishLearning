package demo.yc.lib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * description：Fragment的基类封装
 * @version XXX
 * @author HE_YONG_CAI
 */

public abstract class BaseFragment extends Fragment {

	private Unbinder mUnbinder; // 用于ViewId的绑定

	/**
	 * 默认的构造方法
	 */
	public BaseFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (getContentLayoutId() != 0) { // 布局文件Id不能为0
			return inflater.inflate(getContentLayoutId(), null);
		}
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	/**
	 * fragment显示的布局文件
	 * @return 由子类提供布局文件id
	 */
	protected abstract int getContentLayoutId();

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mUnbinder = ButterKnife.bind(this, view);
		initEvents();
	}

	/**
	 * 处理各种事件
	 */
	protected abstract void initEvents();

	@Override
	public void onDestroy() {
		mUnbinder.unbind();
		super.onDestroy();
	}
}
