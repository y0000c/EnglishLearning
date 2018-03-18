package demo.yc.englishlearning.entity;

import org.litepal.crud.DataSupport;

/**
 * @author: YC
 * @date: 2018/3/18 0018
 * @time: 15:27
 * @detail:
 */

public class Collection extends DataSupport
{
    private int id;

    private User user;

    private Word word;

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

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
