package demo.yc.englishlearning.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import demo.yc.englishlearning.MyApplication;
import demo.yc.englishlearning.R;
import demo.yc.englishlearning.adapter.MainViewPagerAdapter;
import demo.yc.englishlearning.entity.User;
import demo.yc.englishlearning.fragment.MineFragment;
import demo.yc.englishlearning.fragment.StudyFragment;
import demo.yc.lib.base.BaseFragment;
import demo.yc.lib.utils.ActivityUtil;

/**
 * description : 主界面
 * @version XXX
 * @author HE_YONG_CAI
 */
public class MainActivity extends BaseAppActivity {

	private long currentTime = System.currentTimeMillis();

	@BindView(R.id.act_main_viewpager)
	ViewPager mViewpager; // 主界面滑动
	@BindView(R.id.image_main_indicator)
	TabLayout mIndicator; // 底部选择栏

	private final List<BaseFragment> mFragmentList = new ArrayList<>(); // 所有Fragment的集合
	private final List<String> mTitleList = new ArrayList<>(); // 所有Fragment对应的title
	private MainViewPagerAdapter mAdapter; // ViewPager所需的适配器

	private MyApplication mApp ;
	private User mUser;
	@Override
	protected int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	protected void initEvents() {
		mApp = (MyApplication) getApplication();
		mUser = mApp.getLoginUser();
		setTitle(getResources().getString(R.string.main_title));
		mTitleList.add(getResources().getString(R.string.main_array_study));
		mTitleList.add(getResources().getString(R.string.main_array_mine));
		mFragmentList.add(StudyFragment.newInstance());
		mFragmentList.add(MineFragment.newInstance());
		mAdapter = new MainViewPagerAdapter(getSupportFragmentManager(),
				mTitleList,
				mFragmentList);
		mViewpager.setAdapter(mAdapter);
		mIndicator.setupWithViewPager(mViewpager);
		mIndicator.setTabMode(TabLayout.GRAVITY_CENTER);
		mIndicator.addOnTabSelectedListener(new TabLayout
				.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				mViewpager.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {

			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {

			}
		});

		Log.w(mTag, "initEvents: " + mUser.getUserAccount()+"--"
				+mUser.getUserPassword());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_toolbar_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.tool_menu_collection) { // 点击了收藏图标
			toOtherActivity(CollectionActivity.class, false);
		} else if (item.getItemId() == R.id.tool_menu_search) { // 点击了搜索图标
			toOtherActivity(SearchActivity.class, false);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		if(System.currentTimeMillis() - currentTime > 3000)
		{
			currentTime = System.currentTimeMillis();
			Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
		}else
		{
			ActivityUtil.newInstance().clearActivity();
		}

	}
}
