package org.jfteam.mybatis.util;

import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Package : org.jfteam.mybatis.util
 * @Description : 时间工具栏(jdk1.8)  Instant时间会少八小时
 * @Author : yuanDong.lin
 * @Date : 2019-07-12 11:46
 */
public class DateUtils {

    /**
     *  yyyy:MM:dd HHmmssSSS; 默认localDateTime时间格式
     */

    /**
     * 格式化日期时间格式
     */
    private static final String DEFAULT_DATETIME_FORMATTER = "yyyy:MM:dd HH:mm:ss";

    /**
     * 格式化日期时间格式(横线连接)
     */
    private static final String DATETIME_FORMATTER_OF_LINE = "yyyy-MM-dd HH:mm:ss";

    /**
     * 格式化日期时间格式(中文)
     */
    private static final String DATETIME_FORMATTER_CN = "yyyy年MM月dd日 HH:mm:ss";

    /**
     * 格式化日期格式
     */
    private static final String DEFAULT_DATE_FORMATTER = "yyyy:MM:dd";

    /**
     * 格式化日期格式(横线连接)
     */
    private static final String DATE_FORMATTER_OF_LINE = "yyyy-MM-dd";

    /**
     * 格式化日期格式(中文)
     */
    private static final String DATE_FORMATTER_CN = "yyyy年MM月dd日";

    /**
     * 格式化时间格式
     */
    private static final String DEFAULT_IME_FORMATTER = "HH:mm:ss";

    /**
     * 格式化时间格式(中文)
     */
    private static final String TIME_FORMATTER = "HH时mm分ss秒";

    /**
     * @param
     * @return java.time.LocalDateTime
     * @throws
     * @Description: 获取当前系统日期时间:年-月-日 时:分:秒[2019-07-12T11:58:43.146]
     * @author yuanDong.lin
     * @date 2019/7/12 11:59
     */
    public static LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * @param
     * @return java.time.LocalDate
     * @throws
     * @Description: 获取当前系统日期
     * @author yuanDong.lin
     * @date 2019/7/12 14:06
     */
    public static LocalDate getLocalDate() {
        return LocalDate.now();
    }

    /**
     * @param
     * @return java.time.LocalTime
     * @throws
     * @Description: 获取当前系统时间
     * @author yuanDong.lin
     * @date 2019/7/12 14:02
     */
    public static LocalTime getLocalTime() {
        return LocalTime.now();
    }

    /**
     * @param
     * @return long 毫秒时间
     * @throws
     * @Description: 获取当前毫秒时间
     * @author yuanDong.lin
     * @date 2019/7/12 15:21
     */
    public static long getCurrentTime() {
        return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * @return java.lang.String
     * @throws
     * @Description: 获取格式化后的系统日期时间
     * @author yuanDong.lin
     * @date 2019/7/12 14:25
     */
    public static String getLocalDateTimeFormatter() {
        return getLocalDateTime().format(DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMATTER));
    }

    /**
     * @param pattern 系统日期时间格式
     * @return java.lang.String
     * @throws
     * @Description: 获取格式化后的系统日期时间
     * @author yuanDong.lin
     * @date 2019/7/12 14:25
     */
    public static String getLocalDateTimeFormatter(String pattern) {
        String formatRule = StringUtils.isEmpty(pattern) ? DATETIME_FORMATTER_OF_LINE : pattern;
        return getLocalDateTime().format(DateTimeFormatter.ofPattern(formatRule));
    }

    /**
     * @return java.lang.String
     * @throws
     * @Description: 获取格式化的系统日期
     * @author yuanDong.lin
     * @date 2019/7/12 14:27
     */
    public static String getLocalDateFormatter() {
        return getLocalDate().format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMATTER));
    }

    /**
     * @param pattern 系统日期格式
     * @return java.lang.String
     * @throws
     * @Description: 获取格式化的系统日期
     * @author yuanDong.lin
     * @date 2019/7/12 14:27
     */
    public static String getLocalDateFormatter(String pattern) {
        String formatRule = StringUtils.isEmpty(pattern) ? DATE_FORMATTER_OF_LINE : pattern;
        return getLocalDate().format(DateTimeFormatter.ofPattern(formatRule));
    }

    /**
     * @return java.lang.String
     * @throws
     * @Description: 获取格式化当前系统时间
     * @author yuanDong.lin
     * @date 2019/7/12 14:31
     */
    public static String getLocalTimeFormatter() {
        return getLocalTime().format(DateTimeFormatter.ofPattern(DEFAULT_IME_FORMATTER));
    }

    /**
     * @param pattern 系统时间格式
     * @return java.lang.String
     * @throws
     * @Description: 获取格式化当前系统时间
     * @author yuanDong.lin
     * @date 2019/7/12 14:31
     */
    public static String getLocalTimeFormatter(String pattern) {
        String formatRule = StringUtils.isEmpty(pattern) ? DEFAULT_IME_FORMATTER : pattern;
        return getLocalTime().format(DateTimeFormatter.ofPattern(formatRule));
    }

    /**
     * @param dateTime 字符串系统日期时间
     * @return java.time.LocalDateTime
     * @throws
     * @Description: 字符串转LocalDateTime
     * @author yuanDong.lin
     * @date 2019/7/12 14:59
     */
    public static LocalDateTime str2LocalDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMATTER));
    }

    /**
     * @param dateTime 字符串系统日期时间
     * @param pattern  系统日期时间格式
     * @return java.time.LocalDateTime
     * @throws
     * @Description: 字符串转LocalDateTime
     * @author yuanDong.lin
     * @date 2019/7/12 14:59
     */
    public static LocalDateTime str2LocalDateTimeFormatter(String dateTime, String pattern) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * @param date 字符串日期
     * @return java.time.LocalDate
     * @throws
     * @Description: 字符串转LocalDate
     * @author yuanDong.lin
     * @date 2019/7/12 14:57
     */
    public static LocalDate str2LocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMATTER));
    }

    /**
     * @param date 字符串日期
     * @return java.time.LocalDate
     * @throws
     * @Description: 字符串转LocalDate
     * @author yuanDong.lin
     * @date 2019/7/12 14:57
     */
    public static LocalDate str2LocalDateFormatter(String date, String pattern) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * @param time 字符串格式时间(14:42:29)
     * @return java.time.LocalTime
     * @throws
     * @Description: 字符串转LocalTime
     * @author yuanDong.lin
     * @date 2019/7/12 14:49
     */
    public static LocalTime str2LocalTime(String time) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern(DEFAULT_IME_FORMATTER));
    }


    /**
     * @param time    字符串格式时间(14时49分32秒)
     * @param pattern 转换时间格式
     * @return java.time.LocalTime
     * @throws
     * @Description: 字符串转LocalTime
     * @author yuanDong.lin
     * @date 2019/7/12 14:51
     */
    public static LocalTime str2LocalTimeFormatter(String time, String pattern) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * @param date jdk1.7(java.util.Date)日期时间
     * @return java.time.LocalDateTime
     * @throws
     * @Description: Date转LocalDateTime
     * @author yuanDong.lin
     * @date 2019/7/12 15:07
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        //时间线上的一个瞬时点
        Instant instant = date.toInstant();
        //time-zone ID 时区
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param localDateTime
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * @param date jdk1.7(java.util.Date)日期时间
     * @return java.time.LocalDate
     * @throws
     * @Description: Date转LocalDate
     * @author yuanDong.lin
     * @date 2019/7/12 15:07
     */
    public static LocalDate date2LocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }


    /**
     * @param date jdk1.7(java.util.Date)日期时间
     * @return java.time.LocalTime
     * @throws
     * @Description: Date转LocalDate
     * @author yuanDong.lin
     * @date 2019/7/12 15:09
     */
    public static LocalTime date2LocalTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalTime localTime = instant.atZone(zoneId).toLocalTime();
        return localTime;
    }


    /**
     * @param
     * @return int 年
     * @throws
     * @Description: 获取当前年
     * @author yuanDong.lin
     * @date 2019/7/12 15:16
     */
    public static int getCurrentYear() {
        return getLocalDateTime().getYear();
    }

    /**
     * @param
     * @return int 月
     * @throws
     * @Description: 获取当前月
     * @author yuanDong.lin
     * @date 2019/7/12 15:17
     */
    public static int getCurrentMonth() {
        return getLocalDateTime().getMonthValue();
    }

    /**
     * @param
     * @return int 天
     * @throws
     * @Description: 获取当前天(月范围)
     * @author yuanDong.lin
     * @date 2019/7/12 15:18
     */
    public static int getCurrentDay() {
        return getLocalDateTime().getDayOfMonth();
    }


    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(getCurrentTime());
        System.out.println(date2LocalDate(date));
        System.out.println(date2LocalDateTime(date));
        System.out.println(date2LocalTime(date));
    }
}
