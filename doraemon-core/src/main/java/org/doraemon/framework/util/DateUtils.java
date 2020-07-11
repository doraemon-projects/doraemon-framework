package org.doraemon.framework.util;

import org.doraemon.framework.exception.ApplicationRuntimeException;
import org.doraemon.framework.response.ResultCode;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * Description: 时间工具类(yyyy-MM-dd HH:mm:ss)
 *
 * @author: yuanDong.lin
 * Date:     2019/12/17 22:22
 */
public abstract class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private DateUtils() {

    }

    /**
     * 格式化日期时间格式
     */
    public static final String DEFAULT_DATETIME_FORMATTER = "yyyy:MM:dd HH:mm:ss";

    /**
     * 格式化日期时间格式(横线连接)
     */
    public static final String LOCAL_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 格式化日期时间格式(中文)
     */
    public static final String LOCAL_DATE_TIME_PATTERN_CN = "yyyy年MM月dd日 HH:mm:ss";

    /**
     * 格式化日期格式
     */
    public static final String DEFAULT_DATE_FORMATTER = "yyyy:MM:dd";

    /**
     * 格式化日期格式(横线连接)
     */
    public static final String LOCAL_DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 格式化日期格式(中文)
     */
    public static final String DATE_FORMATTER_CN = "yyyy年MM月dd日";

    /**
     * 格式化时间格式
     */
    public static final String DEFAULT_IME_FORMATTER = "HH:mm:ss";

    /**
     * 格式化时间格式(中文)
     */
    public static final String TIME_FORMATTER = "HH时mm分ss秒";

    /**
     * 获取当前时间毫秒数
     *
     * @return java.lang 毫秒数
     */
    public static Long getCurrentTimestamp() {
        return Instant.now().toEpochMilli();
    }

    /**
     * 默认日期格式化
     *
     * @param date 日期
     * @return 格式化后字符串时间
     */
    public static String defaultFormatDate(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return localDateTime.format(DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_PATTERN));
    }

    /**
     * 指定格式日期格式化
     *
     * @param date    日期
     * @param pattern 格式
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        assert Objects.nonNull(pattern) : "时间格式不能为空";
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * date转LocalDateTime
     *
     * @param date 转换前时间
     * @return LocalDateTime 转换后时间
     */
    private static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static Date parseByDayPattern(String date) {
        return parseLocalDateTime2Date(date, LOCAL_DATE_PATTERN);
    }

    /**
     * 默认格式字符时间转日期
     *
     * @param date 字符时间
     * @return
     */
    public static Date defaultParseLocalDateTime2Date(String date) {
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(date);
            return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        } catch (Exception e) {
            throw new ApplicationRuntimeException(ResultCode.CUSTOM_ERROR.getCode(), e);
        }
    }

    /**
     * 指定格式字符时间转日期
     *
     * @param date 字符时间
     * @return
     */
    public static Date parseLocalDateTime2Date(String date, String pattern) {
        AssertUtils.assertTrue(Objects.nonNull(pattern), "格式不能为空");
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern));
            return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        } catch (Exception e) {
            throw new ApplicationRuntimeException(ResultCode.CUSTOM_ERROR.getCode(), e);
        }
    }

    /**
     * 比较两个时间大小：true->第一个时间小于第二个时间,false->第二个时间小于第一个时间
     *
     * @param date  第一个时间
     * @param date1 第二个时间
     * @return boolean
     */
    public static boolean compareDate(Date date, Date date1) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime localDateTime1 = dateToLocalDateTime(date1);
        return localDateTime.isBefore(localDateTime1);
    }

    /**
     * 比较两个时间大小：true->第一个时间小于第二个时间,false->第二个时间小于第一个时间
     *
     * @param localDateTime  第一个时间
     * @param localDateTime1 第二个时间
     * @return boolean
     */
    public static boolean compareDate(LocalDateTime localDateTime, LocalDateTime localDateTime1) {
        return localDateTime.isBefore(localDateTime1);
    }

    /**
     * 计算两个时间相差年份
     *
     * @param date  第一个时间
     * @param date1 第二个时间
     * @return java.lang.Integer 年份
     */
    public static Integer compareDateWithYear(Date date, Date date1) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime localDateTime1 = dateToLocalDateTime(date1);
        return Math.abs(localDateTime.getYear() - localDateTime1.getYear());
    }

    /**
     * 计算两个时间相差年份
     *
     * @param localDateTime  第一个时间
     * @param localDateTime1 第二个时间
     * @return java.lang.Integer 年份
     */
    public static Integer compareDateWithYear(LocalDateTime localDateTime, LocalDateTime localDateTime1) {
        return Math.abs(localDateTime.getYear() - localDateTime1.getYear());
    }

    /**
     * 计算两个时间相差月份
     *
     * @param date  第一个时间
     * @param date1 第二个时间
     * @return java.lang.Integer 月份
     */
    public static Integer compareDateWithMonth(Date date, Date date1) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime localDateTime1 = dateToLocalDateTime(date1);
        return Math.abs(localDateTime.getMonthValue() - localDateTime1.getMonthValue());
    }

    /**
     * 计算两个时间相差月份
     *
     * @param localDateTime  第一个时间
     * @param localDateTime1 第二个时间
     * @return java.lang.Integer 月份
     */
    public static Integer compareDateWithMonth(LocalDateTime localDateTime, LocalDateTime localDateTime1) {
        return Math.abs(localDateTime.getMonthValue() - localDateTime1.getMonthValue());
    }

    /**
     * 计算两个时间相差天数
     *
     * @param date  第一个时间
     * @param date1 第二个时间
     * @return java.lang 天数
     */
    public static Long compareDateWithDay(Date date, Date date1) {
        Duration duration = calculateDuration(date, date1);
        return duration.toDays();
    }

    /**
     * 计算两个时间相差天数
     *
     * @param localDateTime  第一个时间
     * @param localDateTime1 第二个时间
     * @return java.lang 天数
     */
    public static Long compareDateWithDay(LocalDateTime localDateTime, LocalDateTime localDateTime1) {
        Duration duration = calculateDuration(localDateTime, localDateTime1);
        return duration.toDays();
    }

    /**
     * 计算两个时间相差小时
     *
     * @param date  第一个时间
     * @param date1 第二个时间
     * @return java.lang 小时
     */
    public static Long compareDateWithHours(Date date, Date date1) {
        Duration duration = calculateDuration(date, date1);
        return duration.toHours();
    }

    /**
     * 计算两个时间相差小时
     *
     * @param localDateTime  第一个时间
     * @param localDateTime1 第二个时间
     * @return java.lang 小时
     */
    public static Long compareDateWithHours(LocalDateTime localDateTime, LocalDateTime localDateTime1) {
        Duration duration = calculateDuration(localDateTime, localDateTime1);
        return duration.toHours();
    }


    /**
     * 给指定日期加上一个数
     *
     * @param date         日期
     * @param num          数量
     * @param temporalUnit 日期时间单位,默认传入子类ChronoUnit
     * @return
     */
    public static String plus(Date date, Long num, TemporalUnit temporalUnit) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return localDateTime.plus(num, temporalUnit).toString();
    }

    /**
     * 给指定日期加上一个数
     *
     * @param localDateTime 日期
     * @param num           数量
     * @param temporalUnit  日期时间单位,默认传入子类ChronoUnit
     * @return
     */
    public static String plus(LocalDateTime localDateTime, Long num, TemporalUnit temporalUnit) {
        return localDateTime.plus(num, temporalUnit).toString();
    }

    /**
     * 给指定日期减去一个数
     *
     * @param date         日期
     * @param num          数量
     * @param temporalUnit 日期时间单位,默认传入子类ChronoUnit
     * @return
     */
    public static String minus(Date date, Long num, TemporalUnit temporalUnit) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return localDateTime.minus(num, temporalUnit).toString();
    }

    /**
     * 给指定日期减去一个数
     *
     * @param localDateTime 日期
     * @param num           数量
     * @param temporalUnit  日期时间单位,默认传入子类ChronoUnit
     * @return
     */
    public static String minus(LocalDateTime localDateTime, Long num, TemporalUnit temporalUnit) {
        return localDateTime.plus(num, temporalUnit).toString();
    }

    /**
     * 获取当天的开始时间(2019-12-21T00:00)
     *
     * @return
     */
    public static LocalDateTime getStartOfDay() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
    }

    /**
     * 获取当天的结束时间(2019-12-21T23:59:59.999999999)
     *
     * @return
     */
    public static LocalDateTime getEndOfDay() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
    }

    /**
     * 获取本月第一天
     *
     * @param pattern 格式化时间格式
     * @return
     */
    public static String getFirstDayOfTheMonth(String pattern) {
        LocalDateTime firstDayOfTheMonth = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth());
        pattern = StringUtils.isEmpty(pattern) ? LOCAL_DATE_PATTERN : pattern;
        return DateTimeFormatter.ofPattern(pattern).format(firstDayOfTheMonth);
    }

    /**
     * 获取本月最后一天
     *
     * @param pattern 格式化时间格式
     * @return
     */
    public static String getLastDayOfTheMonth(String pattern) {
        LocalDateTime firstDayOfTheMonth = LocalDateTime.now().with(TemporalAdjusters.lastDayOfMonth());
        pattern = StringUtils.isEmpty(pattern) ? LOCAL_DATE_PATTERN : pattern;
        return DateTimeFormatter.ofPattern(pattern).format(firstDayOfTheMonth);
    }

    private static Duration calculateDuration(Date date, Date date1) {
        Duration duration;
        if (compareDate(date, date1)) {
            duration = Duration.between(dateToLocalDateTime(date), dateToLocalDateTime(date1));
        } else {
            duration = Duration.between(dateToLocalDateTime(date1), dateToLocalDateTime(date));
        }
        return duration;
    }

    private static Duration calculateDuration(LocalDateTime localDateTime, LocalDateTime localDateTime1) {
        Duration duration;
        if (compareDate(localDateTime, localDateTime1)) {
            duration = Duration.between(localDateTime, localDateTime1);
        } else {
            duration = Duration.between(localDateTime1, localDateTime);
        }
        return duration;
    }
}
