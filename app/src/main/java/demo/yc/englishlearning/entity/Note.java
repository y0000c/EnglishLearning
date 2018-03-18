package demo.yc.englishlearning.entity;

import org.litepal.crud.DataSupport;

/**
 * @author: YC
 * @date: 2018/3/18 0018
 * @time: 15:37
 * @detail:
 */

public class Note extends DataSupport
{
    private int id;

    private Word word;

    private User user;

    private String noteText;

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

    public String getNoteText()
    {
        return noteText;
    }

    public void setNoteText(String noteText)
    {
        this.noteText = noteText;
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
