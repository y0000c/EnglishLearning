package demo.yc.englishlearning.activity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import demo.yc.englishlearning.R;

/**
 * description：收藏界面
 * @version XXX
 * @author HE_YONG_CAI
 */

public class CollectionActivity extends BaseAppActivity {

	@BindView(R.id.coll_listView)
	ListView mListView; // 显示所有单词的列表
	@BindView(R.id.coll_select_num)
	TextView mSelectNum; // 显示删除数量
	@BindView(R.id.coll_delete_btn)
	Button mDeleteBtn; // 删除按钮
	@BindView(R.id.collection_layout_menu)
	LinearLayout mLayoutMenu; // 显示删除的布局

	private boolean mIsShowMenuLayout; // 是否显示menu布局 ,默认是关闭的

	@Override
	protected int getLayoutId() {
		return R.layout.activity_collection;
	}

	@Override
	protected void initEvents() {
		setTitle(getResources().getString(R.string.toolbar_collection));
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);


		Log.w("collection", "initEvents: 收藏个数：=----》"+
				mApp.getLoginUser().getCollectionList().size());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.coll_toolbar_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.coll_toolbar_select) { // 对当前menuLayout的状态进行取反
			controlMenuLayout(!mIsShowMenuLayout);
		} else if (item.getItemId() == android.R.id.home) { // 点击toolbar返回按钮前需要判断menuLayout状态
			if (mIsShowMenuLayout) { // 如果已经打开，需要先关闭
				controlMenuLayout(false);
				return true;
			}
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 删除按钮对应的事件
	 */
	@OnClick(R.id.coll_delete_btn)
	public void onViewClicked() {
		Toast.makeText(this, "点击delete", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onBackPressed() {
		if (mIsShowMenuLayout) {
			controlMenuLayout(false); // 如果布局已经打开，需要关闭后才能返回
		} else {
			super.onBackPressed();
		}
	}

	/**
	 * 控制menuLayout的显示和关闭
	 * @param isShow
	 */
	private void controlMenuLayout(boolean isShow) {
		if (isShow) {
			mLayoutMenu.setVisibility(View.VISIBLE);// 显示布局
		} else {
			mLayoutMenu.setVisibility(View.GONE);// 关闭布局
		}
		mIsShowMenuLayout = isShow; // 记录当前的布局的状态
	}
}
