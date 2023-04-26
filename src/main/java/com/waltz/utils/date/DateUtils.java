package com.waltz.utils.date;

import com.waltz.constant.CommonConstant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @version 1.0.0
 * @author: wei-zhe-wu
 * @description: 日期工具类
 * @createDate: 2023/4/26 17:39
 **/
public class DateUtils {
    /**
     * LocalDate转Date
     * @param localDate LocalDate日期格式
     * @return Date日期格式
     */
    public static Date localDateToDate(LocalDate localDate){
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 时间字符串转指定日期格式：yyyy-MM-dd
     * @param dateTimeStr 时间字符串
     * @return yyyy-MM-dd日期格式
     */
    public static Date strToYmd(String dateTimeStr){
        SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.Date.YMD);
        try {
            return dateFormat.parse(dateTimeStr);
        } catch (ParseException e){
            return null;
        }
    }

}
