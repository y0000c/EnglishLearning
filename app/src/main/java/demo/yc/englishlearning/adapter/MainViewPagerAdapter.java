package demo.yc.englishlearning.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import demo.yc.lib.base.BaseFragment;

/**
 * 主界面中ViewPager的适配器
 *
 *@version XXX
 *@author HE_YONG_CAI
 *
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {

	private List<BaseFragment> mFragmentList; // 需要切换的Fragment集合
    private List<String> mTitleList;  // 对应Fragment的title

    /**
     * 构造函数
     * @param fm 管理Fragment切换的manager
     * @param titleList fragment对应的title
     * @param fragmentList 需要切换的Fragment
     */
	public MainViewPagerAdapter(FragmentManager fm, List<String> titleList,
			List<BaseFragment> fragmentList) {
		super(fm);
		this.mTitleList = titleList;
		this.mFragmentList = fragmentList;
	}

	@Override
	public Fragment getItem(int position) {
		return mFragmentList.get(position);
	}

	@Override
	public int getCount() {
		if (mFragmentList != null)
			return mFragmentList.size();
		return 0;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return mTitleList.get(position);
	}
}
