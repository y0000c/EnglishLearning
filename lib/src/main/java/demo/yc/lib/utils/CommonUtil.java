package demo.yc.lib.utils;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description： 通用简单工具类
 *
 *@version XXX
 *@author HE_YONG_CAI
 *
 */
public class CommonUtil {
    /**
     * 判断字符串是否为“空”
     * @param msg 需要判断的字符串
     * @return 返回结果 true 表示空
     */
	public static boolean isEmpty(String msg) {
		if (TextUtils.isEmpty(msg)) // 匹配null 和 “”
			return true;
		else if (msg.matches("\\s")) // 匹配所有的空格
			return true;
		return false;
	}

	/**
	 * 返回 年月日 格式时间
	 * @return
	 */
	public static String dateFormat()
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
}
