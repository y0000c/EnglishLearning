package demo.yc.englishlearning.fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;
import demo.yc.englishlearning.MyApplication;
import demo.yc.englishlearning.R;
import demo.yc.englishlearning.activity.BaseAppActivity;
import demo.yc.englishlearning.activity.EditInfoActivity;
import demo.yc.englishlearning.activity.LoginActivity;
import demo.yc.englishlearning.activity.ProcessActivity;
import demo.yc.englishlearning.activity.WordSrcActivity;
import demo.yc.englishlearning.entity.User;
import demo.yc.lib.base.BaseActivity;
import demo.yc.lib.base.BaseFragment;

/**
 * 主界面中的个人界面
 *
 * @author HE_YONG_CAI
 * @version XXX
 */

public class MineFragment extends BaseFragment
{

    @BindView(R.id.frag_mine_icon)
    ImageView mIcon;
    @BindView(R.id.frag_mine_nickName)
    TextView mNickName;
    @BindView(R.id.frag_mine_account)
    TextView mAccount;
    @BindView(R.id.frag_mine_days)
    TextView mDays;
    @BindView(R.id.frag_mine_num)
    TextView mTaskNum;
    @BindView(R.id.frag_mine_review_num)
    TextView mReviewNum;


    private User mUser;

    /**
     * 默认构造函数
     */
    public MineFragment()
    {}

    /**
     * 单利模式
     *
     * @return 返回fragment的实例对象
     */
    public static MineFragment newInstance()
    {
        return new MineFragment();
    }

    @Override
    protected int getContentLayoutId()
    {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initEvents()
    {
        mUser = ((MyApplication)getActivity().getApplication()).getLoginUser();
        mAccount.setText(mUser.getUserAccount());
        mDays.setText(String.valueOf(mUser.getDayNum()));
        mNickName.setText(mUser.getUserNickName());
        mTaskNum.setText(String.valueOf(mUser.getTaskNum()));
        mReviewNum.setText(String.valueOf(mUser.getReviewNum()));
        Log.w("初始获取path", "initEvents: "+ mUser.toString());
        Glide.with(getContext()).load(mUser.getIconPath())
                .placeholder(R.mipmap.ic_launcher).thumbnail(0.2f).into(mIcon);

    }

    /**
     * 个人信息点击事件
     */
    @OnClick(R.id.frag_mine_info_btn)
    public void onFragMineInfoBtnClicked()
    {
        Intent intent = new Intent(getActivity(), EditInfoActivity.class);
        startActivityForResult(intent,1);
    }


    /**
     * 单词来源点击事件
     */
    @OnClick(R.id.frag_mine_book_btn)
    public void onFragMineBookBtnClicked()
    {
        startActivity(new Intent(getActivity(), WordSrcActivity.class));
    }

    /**
     * 学习进度点击事件
     */
    @OnClick(R.id.frag_mine_process_btn)
    public void onFragMineProcessBtnClicked()
    {
        startActivity(new Intent(getActivity(), ProcessActivity.class));
    }

    /**
     * 每天任务点击事件
     */
    @OnClick(R.id.frag_mine_task_btn)
    public void onFragMineTaskBtnClicked()
    {
    }


    /**
     * 复习次数点击事件
     */
    @OnClick(R.id.frag_mine_review_btn)
    public void onFragMineModeBtnClicked()
    {

    }

    @OnClick(R.id.frag_mine_sign_out_btn)
    public void onFragMineSignOutBtnClicked()
    {
        showDialog();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == 1)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                mUser = ((BaseAppActivity)getActivity()).mApp.getLoginUser();
                mNickName.setText(mUser.getUserNickName());
                Glide.with(getContext()).load(mUser.getIconPath())
                        .placeholder(R.mipmap.ic_launcher).into(mIcon);
                Log.w("result返回获取path", "onActivityResult: "+ mUser.toString());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 显示注销对话框
     */
    private Dialog dialog;
    private void showDialog()
    {
        if(dialog == null)
        {
            dialog = new AlertDialog.Builder(getContext())
                    .setTitle("注销'"+mUser.getUserAccount()+"'账号?")
                    .setPositiveButton("注销", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            ((BaseAppActivity)getActivity()).mApp.setLoginUser(null);
                            ((BaseActivity)getActivity()).toOtherActivity(LoginActivity.class,true);
                        }
                    }).create();
        }
        dialog.show();
    }
}
