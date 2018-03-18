package demo.yc.englishlearning.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import demo.yc.englishlearning.R;
import demo.yc.englishlearning.entity.Word;

/**
 * @author: YC
 * @date: 2018/3/18 0018
 * @time: 13:48
 * @detail:
 */

public class WordSrcListAdapter extends BaseAdapter
{
    List<Word> mWordList = new ArrayList<>();
    LayoutInflater mInflater;
    public WordSrcListAdapter(Context context,List<Word> mData)
    {
        mWordList = mData;
        mInflater = LayoutInflater.from(context);

    }
    @Override
    public int getCount()
    {
        if(mWordList != null)
            return mWordList.size();
        return 0;
    }

    @Override
    public Object getItem(int position)
    {
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;
        ViewHolder holder = null;
        if(view == null)
        {
            view = mInflater.inflate(R.layout.word_src_list_item,
                    parent,false);
            holder = new ViewHolder();
            holder.chnTv = view.findViewById(R.id.word_src_list_chnTv);
            holder.engTv = view.findViewById(R.id.word_src_list_engTv);
            view.setTag(holder);
        }else
            holder = (ViewHolder) view.getTag();
        holder.engTv.setText(mWordList.get(position).getNameEng());
        holder.chnTv.setText(mWordList.get(position).getNameChn());
        return view;
    }

    static class ViewHolder
    {
        TextView engTv;
        TextView chnTv;
    }
}
