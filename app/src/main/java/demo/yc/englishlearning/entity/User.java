package demo.yc.englishlearning.entity;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * @author: YC
 * @date: 2018/3/11 0011
 * @time: 13:57
 * @detail:
 */

public class User extends DataSupport
{
    private int id;

    private String userAccount;     // 登录账号

    private String userPassword;    // 登录密码

    private String userNickName;   // 用户昵称

    private int dayNum ;  // 学习天数

    private int reviewNum; // 每次单词复习次数

    private int taskNum; // 每日单词学习数量

    private String date ; // 开始学习日期

    private String iconPath; // 头像地址

    private List<AllLearn> allLearnList;  // 所有已经学习的单词

    private List<Collection> collectionList;    // 所有收藏的单词

    private List<Note> noteList;    // 所有笔记单词

    private List<TodayLearn> todayLearnList;    // 今日需要学习的单词

    private List<Test> testList ;   // 所有经过测试的单词

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUserAccount()
    {
        return userAccount;
    }

    public void setUserAccount(String userAccount)
    {
        this.userAccount = userAccount;
    }

    public String getUserPassword()
    {
        return userPassword;
    }

    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }

    public String getUserNickName()
    {
        return userNickName;
    }

    public void setUserNickName(String userNickName)
    {
        this.userNickName = userNickName;
    }

    public int getDayNum()
    {
        return dayNum;
    }

    public void setDayNum(int dayNum)
    {
        this.dayNum = dayNum;
    }

    public int getReviewNum()
    {
        return this.reviewNum;
    }

    public void setReviewNum(int num)
    {
        this.reviewNum = num;
    }

    public int getTaskNum()
    {
        return taskNum;
    }

    public void setTaskNum(int taskNum)
    {
        this.taskNum = taskNum;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getIconPath()
    {
        return iconPath;
    }

    public void setIconPath(String iconPath)
    {
        this.iconPath = iconPath;
    }

    public List<AllLearn> getAllLearnList()
    {
        return DataSupport.where("user_id = ?", String.valueOf(id))
                .find(AllLearn.class);
    }

    public void setAllLearnList(List<AllLearn> allLearnList)
    {
        this.allLearnList = allLearnList;
    }

    public List<Collection> getCollectionList()
    {
        return DataSupport.where("user_id = ?", String.valueOf(id))
                .find(Collection.class);
    }

    public void setCollectionList(List<Collection> collectionList)
    {
        this.collectionList = collectionList;
    }

    public List<Note> getNoteList()
    {
        return DataSupport.where("user_id = ?", String.valueOf(id))
                .find(Note.class);
    }

    public void setNoteList(List<Note> noteList)
    {
        this.noteList = noteList;
    }

    public List<TodayLearn> getTodayLearnList()
    {
        return DataSupport.where("user_id = ?", String.valueOf(id))
                .find(TodayLearn.class);
    }

    public void setTodayLearnList(List<TodayLearn> todayLearnList)
    {
        this.todayLearnList = todayLearnList;
    }

    public List<Test> getTestList()
    {
        return DataSupport.where("user_id = ?", String.valueOf(id))
                .find(Test.class);
    }

    public void setTestList(List<Test> testList)
    {
        this.testList = testList;
    }

    @Override
    public String toString()
    {
        return getIconPath()+"--"+getUserNickName();
    }
}
