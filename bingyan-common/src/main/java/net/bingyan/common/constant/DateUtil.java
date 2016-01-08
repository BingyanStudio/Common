package net.bingyan.common.constant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 2bab on 15/5/15.
 * 日期、时间相关的辅助类
 */
public class DateUtil {

    private static SimpleDateFormat standardFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

    public static String getStandardTime() {
        return standardFormatter.format(new Date());
    }
}
