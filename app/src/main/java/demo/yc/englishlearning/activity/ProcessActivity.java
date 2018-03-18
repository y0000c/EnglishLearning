package demo.yc.englishlearning.activity;

import android.widget.TextView;

import org.litepal.crud.DataSupport;

import butterknife.BindView;
import demo.yc.englishlearning.R;
import demo.yc.englishlearning.entity.Word;

public class ProcessActivity extends BaseAppActivity
{


    @BindView(R.id.process_date)
    TextView mDate;
    @BindView(R.id.process_word_total)
    TextView mTotal;
    @BindView(R.id.process_learned)
    TextView mLearned;
    @BindView(R.id.process_collect)
    TextView mCollect;
    @BindView(R.id.process_test)
    TextView mTest;

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_process;
    }

    @Override
    protected void initEvents()
    {
        setTitle("学习进度");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTotal.setText(String.valueOf(DataSupport.findAll(Word.class).size()));

        mDate.setText(mApp.getLoginUser().getDate());

    }

}
