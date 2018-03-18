package demo.yc.englishlearning.fragment;

import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import demo.yc.englishlearning.R;
import demo.yc.englishlearning.activity.BaseAppActivity;
import demo.yc.englishlearning.activity.WordDetailActivity;
import demo.yc.englishlearning.entity.User;
import demo.yc.lib.base.BaseActivity;
import demo.yc.lib.base.BaseFragment;

/**
 * 主界面中的学习界面
 * @version XXX
 * @author HE_YONG_CAI
 */

public class StudyFragment extends BaseFragment {

	@BindView(R.id.frag_study_days)
	TextView mDays;
	@BindView(R.id.study_learn_total)
	TextView mTotal;
	@BindView(R.id.study_learn_rest)
	TextView mRest;
	@BindView(R.id.study_review_count)
	TextView mCount;
	@BindView(R.id.study_learn_btn)
	Button mLearnBtn;

	User mUser;

	/**
	 * 默认构造函数
	 */
	public StudyFragment() {
	}

	/**
	 * 单利模式
	 * @return 返回fragment的实例对象
	 */
	public static StudyFragment newInstance() {
		return new StudyFragment();
	}

	@Override
	protected int getContentLayoutId() {
		return R.layout.fragment_study;
	}

	@Override
	protected void initEvents() {

		mUser = ((BaseAppActivity)getActivity()).mApp.getLoginUser();
		mDays.setText(String.valueOf(mUser.getDayNum()));
		mTotal.setText(String.valueOf(mUser.getTaskNum()));
	}


	/**
	 *
	 */
	@OnClick(R.id.study_learn_btn)
	public void onMLearnBtnClicked() {

	}
	/**
	 *
	 */
	@OnClick(R.id.study_test)
	public void onMStudyTestClicked() {
		((BaseActivity)getActivity()).toOtherActivity(WordDetailActivity.class,false);
	}

	/**
	 *
	 */
	@OnClick(R.id.study_note)
	public void onMStudyNoteClicked() {

	}
}