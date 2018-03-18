package demo.yc.englishlearning;

import android.app.Application;
import android.content.SharedPreferences;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import demo.yc.englishlearning.entity.User;
import demo.yc.lib.utils.CommonUtil;

import static demo.yc.englishlearning.utils.Conf.FILE_NAME;
import static demo.yc.englishlearning.utils.Conf.LOGIN_TAG;

/**
 * @author: YC
 * @date: 2018/3/11 0011
 * @time: 13:36
 * @detail:
 */

public class MyApplication extends Application
{
    private User mLoginUser = null;

    /**
     * 设置登录的User
     * @param user
     */
    public void setLoginUser(User user)
    {
        mLoginUser = user;
        SharedPreferences sp = getSharedPreferences(FILE_NAME
                ,MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        if(user == null)
            edit.putString(LOGIN_TAG,"");
        else
            edit.putString(LOGIN_TAG,user.getUserAccount());

        edit.apply();
        //Log.e("app", "setLoginUser: "+mLoginUser.getUserAccount() );
    }

    /**
     * 获取已登录user
     * 有可能因为内存不足被回收，因此需要去sp中获取
     * @return
     */
    public User getLoginUser()
    {
        if(mLoginUser == null)
        {
            SharedPreferences sp = getSharedPreferences(FILE_NAME
                    ,MODE_PRIVATE);
            String account = sp.getString(LOGIN_TAG,"");
            if(CommonUtil.isEmpty(account))
            {
                return null;
            }
            else
            {
                User user = DataSupport
                        .where("userAccount = "+account)
                        .findFirst(User.class);
                setLoginUser(user);
                return user;
            }
        }else
            return mLoginUser;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        LitePal.initialize(this);
    }


}
