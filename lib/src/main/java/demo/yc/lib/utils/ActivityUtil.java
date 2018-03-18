package demo.yc.lib.utils;

import java.util.ArrayList;
import java.util.List;

import demo.yc.lib.base.BaseActivity;

/**
 * description：double check 单例模式
 *              管理所有的Activity
 *
 *@version XXX
 *@author HE_YONG_CAI
 *
 */

public class ActivityUtil {
	private static ActivityUtil mInstance = null;  // 本类对象

	private final List<BaseActivity> mList = new ArrayList<>(); // 管理所有Activity的list集合

    /**
     * 双层判断单例模式
     * @return  返回工具类的实例对象
     */
	public static ActivityUtil newInstance() {
		if (mInstance == null) {
			synchronized (ActivityUtil.class) {
				if (mInstance == null)
					mInstance = new ActivityUtil();
			}
		}
		return mInstance;
	}

	/**
	 * 添加
	 * @param a 需要管理的Activity实例对象
	 */
	public synchronized void addActivity(BaseActivity a) {
		mList.add(a);
	}

	/**
	 * 移除
	 * @param a 需要移除的Activity实例对象
	 */
	public synchronized void removeActivity(BaseActivity a) {
		if (mList.contains(a))
			mList.remove(a);
	}

	/**
	 * 完全退出整个app
	 */
	public synchronized void clearActivity() {
		for (BaseActivity a : mList) {
			if (!a.isFinishing())
				a.finish();
		}
		mList.clear();

	}

}
