package cn.jbolt.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p> TODO</p >
 *
 * @author gong
 * @version V1.8.0
 * @date 2024/11/11 15:35
 */
public class DateUtils {
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }
}
