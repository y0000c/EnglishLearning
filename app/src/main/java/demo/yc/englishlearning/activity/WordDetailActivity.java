package demo.yc.englishlearning.activity;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.OnClick;
import demo.yc.englishlearning.R;

/**
 * description：单词详细显示界面
 * @version XXX
 * @author HE_YONG_CAI
 */

public class WordDetailActivity extends BaseAppActivity {

	@BindView(R.id.detail_known_btn)
	Button mKnownBtn; // 认识按钮
	@BindView(R.id.detail_unknown_btn)
	Button mUnknownBtn; // 不认识按钮
	@BindView(R.id.detail_next_btn)
	Button mNextBtn; // 下一个按钮
	@BindView(R.id.word_detail_layout)
	LinearLayout mDetailLayout; // 单词详情界面
	@BindView(R.id.word_known_layout)
	LinearLayout mKnownLayout; // 单词认知界面

    private boolean mIsShowDetailLayout ;// 是否显示详情界面
	@Override
	protected int getLayoutId() {
		return R.layout.activity_word_detail;
	}

	@Override
	protected void initEvents() {

	}

	/**
	 * 单词认知界面中认识的按钮对应点击事件
	 */
	@OnClick(R.id.detail_known_btn)
	public void onMDetailKnownBtnClicked() {
	}

	/**
	 * 单词认知界面中不认识按钮对应点击事件
	 */
	@OnClick(R.id.detail_unknown_btn)
	public void onMDetailUnknownBtnClicked() {
	}

	/**
	 * 单词详情界面中下一个按钮对应点击事件
	 */
	@OnClick(R.id.detail_next_btn)
	public void onMDetailNextBtnClicked() {
	}

    /**
     *  转换认知界面和详情界面
     */
	private void convertMainLayout()
    {
        // TODO: 2018/3/9 需要传入参数，单词实体对象
        if(mIsShowDetailLayout){
            mDetailLayout.setVisibility(View.GONE);
            mKnownLayout.setVisibility(View.VISIBLE);
        }else
        {
            mKnownLayout.setVisibility(View.GONE);
            mDetailLayout.setVisibility(View.VISIBLE);
        }
        mIsShowDetailLayout = !mIsShowDetailLayout;
    }
}
