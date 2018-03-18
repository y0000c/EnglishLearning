package demo.yc.englishlearning.activity;

import android.app.Dialog;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import demo.yc.englishlearning.MyApplication;
import demo.yc.englishlearning.R;
import demo.yc.englishlearning.entity.User;
import demo.yc.lib.utils.CommonUtil;

/**
 * description:登录界面
 *
 * @author HE_YONG_CAI
 * @version XXX
 */

public class LoginActivity extends BaseAppActivity
{

    @BindView(R.id.login_progress)
    ProgressBar mProgress; // 登录的进度条
    @BindView(R.id.login_name)
    AutoCompleteTextView mNameTv; // 输入用户的账号
    @BindView(R.id.login_password)
    EditText mPasswordTv; // 输入用户的密码
    @BindView(R.id.login_sign_in_button)
    Button mSignInBtn; // 登录按钮
    @BindView(R.id.login_icon)
    ImageView mIcon; // 用户头像
    @BindView(R.id.login_register_box)
    CheckBox mBox; // 是否注册

    private Dialog mDialog;
    private List<String> accountList = new ArrayList<>();
    private User mUser;

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_login;
    }

    @Override
    protected void initEvents()
    {
        Glide.with(this).load("")
                .placeholder(R.mipmap.ic_launcher).into(mIcon);

        setTitle(getResources().getString(R.string.login_text));
        Cursor bySQL = DataSupport.findBySQL("select useraccount from user;");
        if(bySQL.moveToFirst())
        {
            do{
                accountList.add(bySQL.getString
                        (bySQL.getColumnIndexOrThrow("useraccount")));
            }while(bySQL.moveToNext());
        }

        mNameTv.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,accountList));
        mNameTv.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                mUser = DataSupport.where("useraccount = ?",String.valueOf(s))
                        .findFirst(User.class);
                if(mUser != null)
                    Glide.with(LoginActivity.this).load(mUser.getIconPath())
                            .placeholder(R.mipmap.ic_launcher).into(mIcon);
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
    }



    /**
     * 登录按钮点击事件
     */
    @OnClick(R.id.login_sign_in_button)
    public void onLoginSignInButtonClicked()
    {
       String account = mNameTv.getText().toString();
       String password = mPasswordTv.getText().toString();
       if(CommonUtil.isEmpty(account) || CommonUtil.isEmpty(password)){
           Toast.makeText(this,"账号或密码不能为空",
                   Toast.LENGTH_SHORT).show();
       }else{
           if(mBox.isChecked()){
               registerNewUser(account,password);
           }else{
               verifyInfo(account,password);
           }
       }
    }

    /**
     * 验证登录信息
     * @param account  登录账号
     * @param password  登录密码
     */
    private void verifyInfo(String account, String password)
    {

        User user = DataSupport.where("useraccount = ?",account )
                .where("userpassword = ?",password)
                .findFirst(User.class);
        if(user != null){
            toMain(user);
        }else
        {
            Toast.makeText(this,"账号或密码不正确",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 注册新用户
     * @param account 注册账号
     * @param password  注册密码
     */
    private void registerNewUser(String account, String password)
    {
        User user = DataSupport.where("useraccount = ?",account )
                .findFirst(User.class);
        if(user != null)
        {
            Toast.makeText(this,"账号已存在",Toast.LENGTH_SHORT).show();
            return;
        }

        user = new User();
        user.setUserAccount(account);
        user.setUserPassword(password);
        user.setDayNum(0);
        user.setReviewNum(3);
        user.setTaskNum(100);
        user.setDate(CommonUtil.dateFormat());
        user.setUserNickName(account);
        user.setIconPath("");
        user.save();
        toMain(user);
    }

    private void toMain(User user)
    {
        ((MyApplication)getApplication()).setLoginUser(user);
        toOtherActivity(MainActivity.class,true);
    }

    /**
     * 帮助文本点击事件
     */
    @OnClick(R.id.login_help_text)
    public void onLoginHelpTextClicked()
    {
        if(mDialog == null){
            initDialog();
        }
        mDialog.show();
    }

    /**
     * 初始化帮助文本对话框
     */
    private void initDialog()
    {
         mDialog = new AlertDialog.Builder(this)
                 .setMessage("提示")
                 .setMessage("选择注册\r\n输入账号密码后，设为注册新用户")
                 .create();
    }
}
