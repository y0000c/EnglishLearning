package demo.yc.englishlearning.activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import demo.yc.englishlearning.R;
import demo.yc.englishlearning.entity.Word;
import demo.yc.lib.utils.CommonUtil;

import static demo.yc.englishlearning.utils.Conf.*;


/**
 * description
 *
 * @version XXX @author HE_YONG_CAI
 */
public class SplashActivity extends BaseAppActivity
{

    // handler使用的各种标识
    private static final int FIRST_USE = 0x001; // 第一次使用软件，需要加载数据
    private static final int TO_LOGIN = 0x002; // 跳转到登录界面
    private static final int TO_MAIN = 0x003; // 跳转到主界面
    private static final int SPLASH_TIME = 3000; // 欢迎界面停顿3秒

    private static final String IMPORT_DATA_NAME = "四级单词.txt"; // 存放单词数据
    private NoLeakHandler mHandler; // 非泄漏的Handler
    private boolean mIsFirst; // 记录是否第一次使用
    private String mLoginName; // 记录上次登录账号

    private ProgressDialog dialog;

    @Override
    protected int getLayoutId()
    {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_splash;
    }

    @Override
    protected void initEvents()
    {
        dialog = new ProgressDialog(this);
        mHandler = new NoLeakHandler(this);
        SharedPreferences sp = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        mIsFirst = sp.getBoolean(ENTER_TAG, true);
        mLoginName = sp.getString(LOGIN_TAG, "");

        mHandler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                if (mIsFirst) // 第一次使用，初始化数据
                    mHandler.sendEmptyMessage(FIRST_USE);
                else if (CommonUtil.isEmpty(mLoginName)) // 不是第一次使用，但是没有登录
                    mHandler.sendEmptyMessage(TO_LOGIN);
                else
                    mHandler.sendEmptyMessage(TO_MAIN); // 已经登录过了，进入主界面
            }
        }, SPLASH_TIME);
    }

    /**
     * 初次使用，初始化数据
     */
    private void initData()
    {
        // TODO: 2018/3/8 进行数据初始化

        Log.w(mTag, "initData: 第一次登录，进行数据初始化");
        importData();
    }

    /**
     * 从assets中导入单词数据
     */
    private void importData()
    {
        LoadDataAsyncTask task = new LoadDataAsyncTask(this);
        task.execute();
    }

    /**
     * 导入数据后的结果处理
     */
    private void importDataResult(boolean isOK)
    {
        if(isOK) {
            SharedPreferences sp = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean(ENTER_TAG, false);
            editor.apply();
            mHandler.sendEmptyMessage(TO_LOGIN);
        }else{
            Toast.makeText(this, "数据异常", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    /**
     * 跳转到登录界面
     */
    private void toLogin()
    {
        Log.e(mTag, "toLogin: 跳转到登录界面");
        toOtherActivity(LoginActivity.class, true);
    }

    /**
     * 跳转到主界面
     */
    private void toMain()
    {
        Log.e(mTag, "toMain: 跳转到主界面");
        toOtherActivity(MainActivity.class, true);
    }

    /**
     * 静态内部类，使用软引用，避免内存泄漏
     *
     * @version XXX @author HE_YONG_CAI
     */
    private static class NoLeakHandler extends Handler
    {
        private final WeakReference<SplashActivity> mActivity;

        private NoLeakHandler(SplashActivity a)
        {
            mActivity = new WeakReference<>(a);
        }

        @Override
        public void handleMessage(Message msg)
        {

            switch (msg.what)
            {
                case FIRST_USE:
                    mActivity.get().initData();
                    break;
                case TO_LOGIN:
                    mActivity.get().toLogin();
                    break;
                case TO_MAIN:
                    mActivity.get().toMain();
                    break;
            }
        }
    }

    /**
     *  导入数据的线程类
     */
    private static class LoadDataAsyncTask extends AsyncTask<Void,Void,Boolean>
    {
        private WeakReference<SplashActivity> splashWR;
        private static final String TAG = "import";
        public LoadDataAsyncTask(SplashActivity a)
        {
            splashWR = new WeakReference<>(a);
        }
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            splashWR.get().dialog.setMessage("正在初始化数据...");
            splashWR.get().dialog.show();
        }

        @Override
        protected Boolean doInBackground(Void...voids)
        {
            InputStream is = null;
            BufferedReader br = null;
            try
            {
                is = splashWR.get().getResources().getAssets().open(IMPORT_DATA_NAME);
                br = new BufferedReader(new InputStreamReader(is));
                String line = null;
                String[] items = null;
                List<Word> wordList = new ArrayList<>();
                while (!CommonUtil.isEmpty(line = br.readLine()))
                {
                    items = line.split("\\s{1}");
                    Word word = new Word();
                    word.setNameEng(items[0]);
                    word.setNameChn(items[1]);
                    word.setNetContent("");
                    word.setSoundPath("");
                    word.setSoundText("");
                    wordList.add(word);
                    Log.w(TAG, "importData: " + items[0] +"----" +items[1]);
                }
                Log.e(TAG, "importData: 数据导入成功" );
                DataSupport.saveAll(wordList);
                return true;
            } catch (IOException e)
            {
                Log.e(TAG, "importData: 数据导入异常" );
                e.printStackTrace();
                return false;
            } finally
            {
                try
                {
                    is.close();
                    br.close();
                } catch (Exception e)
                {
                }
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean)
        {
            super.onPostExecute(aBoolean);
            splashWR.get().dialog.dismiss();
            splashWR.get().importDataResult(aBoolean);
        }
    }
}
