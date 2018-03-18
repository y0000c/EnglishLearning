package demo.yc.englishlearning.activity;

import android.widget.ListView;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import demo.yc.englishlearning.R;
import demo.yc.englishlearning.adapter.WordSrcListAdapter;
import demo.yc.englishlearning.entity.Word;

public class WordSrcActivity extends BaseAppActivity
{

    @BindView(R.id.word_src_total)
    TextView mSrcTotal;
    @BindView(R.id.word_src_list)
    ListView mSrcList;


    List<Word> mWordList;
    WordSrcListAdapter adapter;

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_word_src;
    }

    @Override
    protected void initEvents()
    {
        setTitle("单词来源");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mWordList = DataSupport.findAll(Word.class);
        mSrcTotal.setText(String.valueOf(mWordList.size()));
        adapter = new WordSrcListAdapter(this,mWordList);
        mSrcList.setAdapter(adapter);
    }


}
