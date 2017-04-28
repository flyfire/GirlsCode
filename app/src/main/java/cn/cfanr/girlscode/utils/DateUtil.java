package cn.cfanr.girlscode.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xifan
 * @since 2017/4/24.
 */

public class DateUtil {

    public static String getYMDFormatDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(date);
    }

}
