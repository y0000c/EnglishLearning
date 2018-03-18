package demo.yc.englishlearning.entity;

import org.litepal.crud.DataSupport;

/**
 * @author: YC
 * @date: 2018/3/11 0011
 * @time: 13:57
 * @detail:
 */

public class Word extends DataSupport
{
    private int id;

    private String nameEng; // 英语拼写

    private String nameChn; // 中文翻译

    private String netContent ; // 网络翻译

    private String soundPath ; // 发音文件地址

    private String soundText ; // 音标

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNameEng()
    {
        return nameEng;
    }

    public void setNameEng(String nameEng)
    {
        this.nameEng = nameEng;
    }

    public String getNameChn()
    {
        return nameChn;
    }

    public void setNameChn(String nameChn)
    {
        this.nameChn = nameChn;
    }

    public String getNetContent()
    {
        return netContent;
    }

    public void setNetContent(String netContent)
    {
        this.netContent = netContent;
    }

    public String getSoundPath()
    {
        return soundPath;
    }

    public void setSoundPath(String soundPath)
    {
        this.soundPath = soundPath;
    }

    public String getSoundText()
    {
        return soundText;
    }

    public void setSoundText(String soundText)
    {
        this.soundText = soundText;
    }
}
