package demo.yc.englishlearning.entity;

import org.litepal.crud.DataSupport;

/**
 * @author: YC
 * @date: 2018/3/18 0018
 * @time: 15:38
 * @detail:
 */

public class TodayLearn extends DataSupport
{
    private int id;

    private Word word;

    private int status; // 是否学习 1 已经学了  0  未学

    private User user;

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

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }
}
