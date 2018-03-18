package demo.yc.englishlearning.entity;

import org.litepal.crud.DataSupport;

/**
 * @author: YC
 * @date: 2018/3/18 0018
 * @time: 15:40
 * @detail:
 */

public class Test extends DataSupport
{
    private int id;

    private User user;

    private Word word;

    private int statusCount ; // 检测测数

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Word getWord()
    {
        return word;
    }

    public void setWord(Word word)
    {
        this.word = word;
    }

    public int getStatusCount()
    {
        return statusCount;
    }

    public void setStatusCount(int statusCount)
    {
        this.statusCount = statusCount;
    }
}
