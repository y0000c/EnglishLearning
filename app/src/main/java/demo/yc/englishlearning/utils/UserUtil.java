package demo.yc.englishlearning.utils;

/**
 * @author: YC
 * @date: 2018/3/11 0011
 * @time: 17:32
 * @detail:
 */

public class UserUtil
{
    public static String getValueByMode(int mode)
    {
        if(mode == 0)
            return "有序";
        else
            return "无序";
    }
}
