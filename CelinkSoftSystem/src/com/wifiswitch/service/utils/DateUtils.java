package com.wifiswitch.service.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: lifaqiu
 * Date: 14-3-6
 * Time: 下午3:39
 * To change this template use File | Settings | File Templates.
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    // public static final String PROP_NAME_CURRENT_YEAR = "current.year";
    private static Map<String, SimpleDateFormat> formatPool = new HashMap<String, SimpleDateFormat>();
    private static Log logger = LogFactory.getLog(DateUtils.class);
    /**
     * "JAN" to represent January
     */
    public static final String MTH_JAN = "Jan";

    /**
     * "FEB" to represent February
     */
    public static final String MTH_FEB = "Feb";

    /**
     * "MAR" to represent March
     */
    public static final String MTH_MAR = "Mar";

    /**
     * "APR" to represent April
     */
    public static final String MTH_APR = "Apr";

    /**
     * "MAY" to represent May
     */
    public static final String MTH_MAY = "May";

    /**
     * "JUNE" to represent June
     */
    public static final String MTH_JUN = "Jun";

    /**
     * "JUL" to represent July
     */
    public static final String MTH_JUL = "Jul";

    /**
     * "AUG" to represent August
     */
    public static final String MTH_AUG = "Aug";

    /**
     * "SEP" to represent September
     */
    public static final String MTH_SEP = "Sep";

    /**
     * "OCT" to represent October
     */
    public static final String MTH_OCT = "Oct";

    /**
     * "NOV" to represent November
     */
    public static final String MTH_NOV = "Nov";

    /**
     * "DEC" to represent December
     */
    public static final String MTH_DEC = "Dec";
    private static SimpleDateFormat inputDateFormatForWhat = new SimpleDateFormat(
            "yyyy/MM/dd");// ("dd/MM/yyyy");
    private static SimpleDateFormat inputDateFormat = new SimpleDateFormat(
            "yyyy年MM月dd日 HH:mm");// ("dd/MM/yyyy");
    private static SimpleDateFormat inputDateFormatWOTime = new SimpleDateFormat(
            "yyyy年MM月dd日");
    private static SimpleDateFormat inputDateFormatForReport = new SimpleDateFormat(
            "MM/dd/yyyy");
    private static SimpleDateFormat timeFormatSimple = new SimpleDateFormat(
            "yyyyMMdd-HHmmss");
    private static SimpleDateFormat dtFormatSimpleFull = new SimpleDateFormat(
            "yyyyMMddHHmmss");
    private static SimpleDateFormat timeFormatIncludeSeconds = new SimpleDateFormat(
            "yyyy年MM月dd日 HH:mm:ss");
    private static SimpleDateFormat timeFormatAMPM = new SimpleDateFormat(
            "yyyy年MM月dd日 h:mm a");
    private static SimpleDateFormat timeFormatAMPMIncludeSeconds = new SimpleDateFormat(
            "dd/MM/yyyy h:mm:ss a");
    private static SimpleDateFormat timeFormatNoSeconds = new SimpleDateFormat(
            "yyyy年MM月dd日 HH:mm");
    private static SimpleDateFormat timeFormatMonth = new SimpleDateFormat(
            "yyyy年MM月");

    private static String DTyyyyMMdd = "yyyyMMdd";
    private static String TMSyyyyMMddHHmmss = "yyyyMMddHHmmss";
    private static String timezone = "China/BeiJing";

    public static String dateToStr(Date date, String format) {
        if (date == null)
            return "";
        return getDateFormat(format).format(date);
    }

    public static String dateToStr(Timestamp date, String format) {
        if (date == null)
            return "";
        return getDateFormat(format).format(date);
    }

    public static String dateToStr(Date date) {
        return dateToStr(date, DTyyyyMMdd);
    }

    public static String dateToStr(Timestamp date) {
        return dateToStr(date, TMSyyyyMMddHHmmss);
    }

    public static int getYear(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return c.get(Calendar.YEAR);
    }

    public static int getMonth(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return c.get(Calendar.MONTH) + 1;
    }

    public static int getDay(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static int getTheDayInWeek(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    public static int getTheMonthDays(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, 1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static int getTheYear(int x) {
        Calendar c = Calendar.getInstance();
        c.setTime(getDate());
        c.add(Calendar.YEAR, x);
        return c.get(Calendar.YEAR);
    }

    public static int getTheMonth(int x) {
        Calendar c = Calendar.getInstance();
        c.setTime(getDate());
        c.add(Calendar.MONTH, x);
        return c.get(Calendar.MONTH) + 1;
    }

    public static void main(String[] args) {
        Date date = getDateWithOutTime();
        System.out.println(date);

//        Calendar cal1 = Calendar.getInstance();
//        cal1.set(Calendar.DAY_OF_MONTH,7);
//        Calendar cal2 = Calendar.getInstance();
//        cal2.set(Calendar.DAY_OF_MONTH,10);
//        System.out.println(dayBetweenTwoDates(cal1.getTime(),cal2.getTime()));
//        System.out.println(getWorkingDay(cal1.getTime(), cal2.getTime()));
        // System.out.println(ge(-1));
        // System.out.println(getTheMonth(-1));
        // System.out.println(getTheMonthDays(2010, 2));
        // System.out.println(getTheDayInWeek(new Date()));
        // Calendar cal=Calendar.getInstance();
        // int year = cal.get(Calendar.YEAR);
        // int month = cal.get(Calendar.MONTH);
        //
        // cal.set(Calendar.DAY_OF_WEEK, 1);
        // Date lastMonday = cal.getTime();
        // System.out.println("上星期天: "+dateToInputStrWOTime(lastMonday));
        //
        // cal = Calendar.getInstance();
        // cal.add(Calendar.WEEK_OF_MONTH, -1);
        // cal.set(Calendar.DAY_OF_WEEK, 2);
        // Date lastSunday = cal.getTime();
        // System.out.println("上星期一: "+dateToInputStrWOTime(lastSunday));
        // System.out.println(dateToInputStrMonth(cal.getTime())+"第几周: "+cal.get(Calendar.WEEK_OF_MONTH));
        //
        // Date lastDayOfLastMonth = DateUtil.getLastDayOfMonth(year, month);
        // System.out.println("上个月最后一天: " +
        // dateToInputStrWOTime(lastDayOfLastMonth));
        //
        // cal = Calendar.getInstance();
        // cal.setTime(lastDayOfLastMonth);
        // cal.set(Calendar.DAY_OF_MONTH, 1);
        // System.out.println(dateToInputStrWOTime(cal.getTime()));
        // cal.set(Calendar.DAY_OF_MONTH, 10);
        // System.out.println(dateToInputStrWOTime(cal.getTime()));
        // cal.set(Calendar.DAY_OF_MONTH, 11);
        // System.out.println(dateToInputStrWOTime(cal.getTime()));
        // cal.set(Calendar.DAY_OF_MONTH, 20);
        // System.out.println(dateToInputStrWOTime(cal.getTime()));
        // cal.set(Calendar.DAY_OF_MONTH, 21);
        // System.out.println(dateToInputStrWOTime(cal.getTime()));
        // System.out.println(dateToInputStrWOTime(lastDayOfLastMonth));

//		System.out.println(DateUtil.formatDateToInputStr(new Date(),
//				"MM月dd日 EEEE"));
//
//		Calendar cal1 = Calendar.getInstance();
//		cal1.set(Calendar.HOUR, 16);
//		cal1.set(Calendar.MINUTE, 0);
//
//		Calendar cal2 = Calendar.getInstance();
//		cal2.set(Calendar.HOUR, 8);
//		cal2.set(Calendar.MINUTE, 0);
//
//		Calendar cal3 = Calendar.getInstance();
//		cal3.set(Calendar.DAY_OF_WEEK, 6);
//		cal3.set(Calendar.HOUR, 16);
//		cal3.set(Calendar.MINUTE, 0);
//
//		Calendar cal4 = Calendar.getInstance();
//		cal4.set(Calendar.DAY_OF_WEEK, 2);
//		cal4.set(Calendar.HOUR, 8);
//		cal4.set(Calendar.MINUTE, 0);
//
//		System.out.println(DateUtil.dateToInputStr(cal1.getTime()));
//		System.out.println(DateUtil.dateToInputStr(cal2.getTime()));
//		System.out.println(DateUtil.dateToInputStr(cal3.getTime()));
//		System.out.println(DateUtil.dateToInputStr(cal4.getTime()));
//
//		Calendar cal = Calendar.getInstance();
//		System.out.println(cal.compareTo(cal1));
//		System.out.println(cal.compareTo(cal2));
//		// cal.compareTo(calendar);
//
//		cal = Calendar.getInstance();
//		cal.add(Calendar.WEEK_OF_MONTH, 1);
//		System.out.println(DateUtil.dateToInputStr(cal.getTime()));
    }

    public static java.util.Date inputDateFormatForWhat(String inputStr) {
        if (inputStr == null || inputStr.trim().equals("")) {
            return null;
        }
        Date result = null;
        try {
            result = inputDateFormatForWhat.parse(inputStr);
        } catch (Exception e) {
            // if (logger.isWarnEnabled()) {
            // logger.warn("Failed to parse datetime (date=" + inputStr
            // + ", pattern=" + "dd/MM/yyyy", e);
            // }
        }

        return result;
    }

    public static java.util.Date inputStrToDate(String inputStr) {
        if (inputStr == null || inputStr.trim().equals("")) {
            return null;
        }
        Date result = null;
        try {
            result = inputDateFormat.parse(inputStr);
        } catch (Exception e) {
            // if (logger.isWarnEnabled()) {
            // logger.warn("Failed to parse datetime (date=" + inputStr
            // + ", pattern=" + "dd/MM/yyyy", e);
            // }
        }

        return result;
    }

    public static Date inputStrToDateWOTime(String inputStr) {
        if (inputStr == null || inputStr.trim().equals("")) {
            return null;
        }
        Date result = null;
        try {
            result = inputDateFormatWOTime.parse(inputStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Date inputStrAppendTimeToDate(String inputStrAppendTime) {
        if (inputStrAppendTime == null || inputStrAppendTime.trim().equals("")) {
            return null;
        }
        Date result = null;
        try {
            result = timeFormatIncludeSeconds.parse(inputStrAppendTime);
        } catch (Exception e) {
            // if (logger.isWarnEnabled()) {
            // logger.warn("Failed to parse datetime (date=" +
            // inputStrAppendTime
            // + ", pattern=" + "dd/MM/yyyy HH:mm:ss", e);
            // }
        }

        return result;
    }

    public static java.util.Date inputStrToDateAMPM(String inputStr) {
        if (inputStr == null || inputStr.trim().equals("")) {
            return null;
        }
        Date result = null;
        try {
            result = timeFormatAMPM.parse(inputStr);
        } catch (Exception e) {
            // if (logger.isWarnEnabled()) {
            // logger.warn("Failed to parse datetime (date=" + inputStr
            // + ", pattern=" + "dd/MM/yyyy h:mm a", e);
            // }
        }

        return result;
    }

    public static String formateTimeTohhmm(String hourStr, String minuteStr) {
        try {
            int hour = Integer.parseInt(StringUtils.isEmpty(hourStr) ? "0"
                    : hourStr);
            int minute = Integer.parseInt(StringUtils.isEmpty(minuteStr) ? "0"
                    : minuteStr);
            if (hour >= 0 && hour < 24 && minute >= 0 && minute < 60) {
                return (hour < 10 ? "0" + hour : hour) + ":"
                        + (minute < 10 ? "0" + minute : minute);
            }
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static java.util.Date inputStrToDateAMPMIncludeSeconds(
            String inputStr) {
        if (inputStr == null || inputStr.trim().equals("")) {
            return null;
        }

        Date result = null;
        try {
            result = timeFormatAMPMIncludeSeconds.parse(inputStr);
        } catch (Exception e) {
            // if (logger.isWarnEnabled()) {
            // logger.warn("Failed to parse datetime (date=" + inputStr
            // + ", pattern=" + "dd/MM/yyyy h:mm:ss a", e);
            // }
        }

        return result;
    }

    public static String dateToInputStr(java.util.Date date) {
        if (date == null)
            return "";
        return inputDateFormat.format(date);
    }

    public static String formatDateToInputStr(java.util.Date date,
                                              String pattern) {
        return new SimpleDateFormat(pattern, Locale.CHINESE).format(date);
    }

    public static Date parseInputStrToDate(String inputStr, String pattern)
            throws ParseException {
        return new SimpleDateFormat(pattern, Locale.CHINESE).parse(inputStr);
    }

    public static String dateToInputStrWOTime(Date date) {
        if (date == null)
            return "";
        return inputDateFormatWOTime.format(date);
    }

    public static String dateToInputStrMonth(Date date) {
        if (date == null)
            return "";
        return timeFormatMonth.format(date);
    }

    public static String dateToInputStrForReport(java.util.Date date) {
        if (date == null)
            return "";
        return inputDateFormatForReport.format(date);
    }

    public static String dateToInputStrAppendTime(java.util.Date date) {
        if (date == null)
            return "";
        return timeFormatIncludeSeconds.format(date);
    }

    public static String dateToDateTimeSimpleFull(java.util.Date date) {
        if (date == null)
            return "";
        return dtFormatSimpleFull.format(date);
    }

    public static String currentToDateTimeSimpleFull() {
        return dateToDateTimeSimpleFull(getDate());
    }

    public static String dateToInputStrAMPM(java.util.Date date) {
        if (date == null)
            return "";
        return timeFormatAMPM.format(date);
    }

    public static String dateToInputStrAMPMIncludeSeconds(java.util.Date date) {
        if (date == null)
            return "";
        return timeFormatAMPMIncludeSeconds.format(date);
    }

    public static String dateToInputStrNoSeconds(java.util.Date date) {
        if (date == null)
            return "";
        return timeFormatNoSeconds.format(date);
    }

    public static Date inputStrNoSecondsToDate(String inputStr) {
        if (inputStr == null || inputStr.trim().equals("")) {
            return null;
        }
        Date result = null;
        try {
            result = timeFormatNoSeconds.parse(inputStr);
        } catch (Exception e) {
        }

        return result;
    }

    public static String getCurrentYear() {
        // if (!PropertyUtil.isProductionEnv()) {
        // String year =
        // PropertyUtil.getPropertyAsString(PROP_NAME_CURRENT_YEAR);
        // if (StringUtil.isNotEmpty(year)) {
        // return year;
        // }
        // }
        String dateTime = timeFormatSimple.format(getDate());
        return dateTime.substring(0, 4);
    }

    public static String getCurrentDay() {
        String dateTime = timeFormatSimple.format(getDate());
        return dateTime.substring(0, 8);
    }

    public static boolean isCurrentDateBetween(Date startDate, Date endDate) {
        if (startDate == null)
            return false;
        if (endDate == null)
            return isCurrentDateBetween(startDate, endDate, true);
        else
            return isCurrentDateBetween(startDate, endDate, false);

    }

    /**
     * Compute the age returning an array of integers, for year, month, and day
     * respectively.
     * <p>
     *
     * @param birthdate
     *            The start date to start the age computation.
     * @param asOf
     *            The end date for the age computation
     * @return The age in years, months, days in the 0, 1, 2 indices
     *         respectively.
     */
    public static int[] age(Date birthdate, Date asOf) {
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.setTime(birthdate);
        to.setTime(asOf);

        int birthYYYY = from.get(Calendar.YEAR);
        int birthMM = from.get(Calendar.MONTH);
        int birthDD = from.get(Calendar.DAY_OF_MONTH);

        int asofYYYY = to.get(Calendar.YEAR);
        int asofMM = to.get(Calendar.MONTH);
        int asofDD = to.get(Calendar.DAY_OF_MONTH);

        int ageInYears = asofYYYY - birthYYYY;
        int ageInMonths = asofMM - birthMM;
        int ageInDays = asofDD - birthDD;

        if (ageInDays < 0) {
            // Guaranteed after this single treatment, ageInDays will be >= 0.
            // i.e. ageInDays = asofDD - birthDD + daysInBirthMM.
            ageInDays += from.getActualMaximum(Calendar.DAY_OF_MONTH);
            ageInMonths--;
        }

        if (ageInDays == to.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            ageInDays = 0;
            ageInMonths++;
        }

        if (ageInMonths < 0) {
            ageInMonths += 12;
            ageInYears--;
        }
        if (birthYYYY < 0 && asofYYYY > 0)
            ageInYears--;

        if (ageInYears < 0) {
            ageInYears = 0;
            ageInMonths = 0;
            ageInDays = 0;
        }

        int[] result = new int[3];
        result[0] = ageInYears;
        result[1] = ageInMonths;
        result[2] = ageInDays;
        return result;
    }

    /**
     * Tests the input value to ensure that a valid Date instance can be created
     * from it. Roll over dates are not allowed here and will return a false
     * value. Eg. isValidDate(2002, 10, 32) will return false.
     * <p>
     *
     * @param year
     *            The year value.
     * @param month
     *            The month value. ( 1 - 12 )
     * @param day
     *            The day value. ( 1 - 31 )
     * @return True if all values can be used to create a single valid Date
     *         instance.
     */
    public static boolean isValidDate(int year, int month, int day) {

        if (day <= 0 || month <= 0 || year <= 0)
            return false;
        if (month > 12 || day > 31)
            return false;

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);

        // Find the maximum field value possible for the day with the year and
        // month.
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (day > maxDay)
            return false;

        return true;
    }

    /**
     * Tests the input string to ensure that a valid Date instance can be
     * created from it according to the format provided.
     * <p>
     *
     * @param date
     *            A date string.
     * @param format
     *            Date format to conform to.
     * @return True if it conforms to the specified date format; False
     *         otherwise.
     */
    public static boolean isValidDate(String date, String format) {

        if (date == null)
            return false;

        try {
            parse(date, format);
        } catch (java.text.ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Tests the input string to ensure that a valid Date instance can be
     * created according to the date format specified in the System property.
     * <p>
     * If the properties file is not available or the dateformat property has
     * not been specified, the default format "dd/MM/yyyy" will be used.
     * <p>
     *
     * @param date
     *            A date string.
     * @return True if it conforms to the system date format; False otherwise.
     */
    public static boolean isValidDate(String date) {
        if (date == null)
            return false;

        try {
            parse(date);
        } catch (java.text.ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * Tests if the inputs are valid time. When the ampm parameter is true, the
     * input hour will be tested for 12-hour format ( 1 ? 12 ). When it is
     * false, the input hour will be tested for 24-hour format ( 0 ? 23 ).
     * <p>
     *
     * @param hour
     *            The Hour value. ( 0 - 23 or 1 - 12 )
     * @param minute
     *            The Minute value. ( 0 - 59 )
     * @param ampm
     *            If true, the time is in 12 hour format. Otherwise, it is in 24
     *            hour format.
     *
     * @return True if the time inputs can be used to create a valid time
     *         instance.
     */
    public static boolean isValidTime(int hour, int minute, boolean ampm) {

        if (minute < 0 || minute > 59)
            return false;

        if (ampm && (hour < 1 || hour > 12))
            return false;

        else if (hour < 0 || hour > 23)
            return false;

        else
            return true;
    }

    /**
     * Tests the input string to ensure that a valid time can be created
     * according to the time format provided.
     * <p>
     *
     * @param time
     *            The time string.
     * @param format
     *            Time format to conform to.
     * @return True if it conforms to the specified time format; False
     *         otherwise.
     */
    public static boolean isValidTime(String time, String format) {

        if (time == null || format == null)
            return false;

        try {
            parse(time, format);
        } catch (java.text.ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * Returns a Date object instance from the input string. The input string is
     * parsed to return a date object based on the date format specified in the
     * System property.
     * <p>
     *
     * @param date
     *            The date string.
     * @return The date instance created.
     */
    public static Date parse(String date) throws java.text.ParseException {
        if (date == null)
            return null;

        String dateformat = "dd/MM/yyyy";
        return parse(date, dateformat);
    }

    /**
     * Returns a Date object instance from the input string. The input date
     * string is parsed to return a date object based on the format provided.
     * <p>
     * Eg., to parse the date string "01/01/2003 9:2 PM", use the format
     * "dd/MM/yyyy h:m a". The resultant data object will have the value
     * "Mar 11 2003 09:02", as displayed in 24 hr format.
     * <p>
     *
     * @param date
     *            The date string.
     * @param format
     *            The date format that the date string conforms to.
     * @return The date instance created.
     * @throws java.text.ParseException
     *             - if the beginning of the specified string cannot be parsed.
     */
    public static Date parse(String date, String format)
            throws java.text.ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);

        return sdf.parse(date);
    }

    /**
     * Returns a Date object instance from the input string. This method is
     * specifically for date string of the form
     * "EEE MMM dd HH:mm:ss 'GMT+08:00' yyyy"
     * <p>
     * For eg, "Thu Jun 30 15:00:54 GMT+08:00 2003"
     *
     * @param date
     *            The date string of the format
     *            "EEE MMM dd HH:mm:ss 'GMT+08:00' yyyy"
     * @return The date instance created.
     * @throws java.text.ParseException
     *             - if the date string is not of the given format.
     */
    public static Date parseLocale(String date) throws java.text.ParseException {

        String localeFormat = "EEE MMM dd HH:mm:ss 'GMT+08:00' yyyy";

        return parse(date, localeFormat);
    }

    public static boolean isCurrentDateBetween(Date effDt, Date expDt,
                                               boolean canExpDtNull) {
        if (effDt == null)
            return false;
        Date currentDate = new Date(System.currentTimeMillis());
        if (canExpDtNull && (expDt == null))
            return currentDate.compareTo(effDt) >= 0;
        else
            return ((currentDate.compareTo(effDt) >= 0) && (currentDate
                    .compareTo(getNextDate(expDt)) <= 0));
    }

    public static Date getNextDate(Date date) {
        if (date == null)
            return null;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, 1);
        return cal.getTime();
    }

    /**
     * Return the Date instance with the provided year, month ( 1 ? 12 ), and
     * day ( 1 - 31 ) values.
     * <p>
     * The date value will roll over when given a value that is greater than the
     * max possible. Eg. when getDate( 2002, 10, 32 ) is provided, the Date
     * instance will be 1st Nov 2002.
     * <p>
     *
     * @param year
     *            Year
     * @param month
     *            Month ( 1 - 12 )
     * @param day
     *            Day( 1 - 31 )
     * @return The Date instance created.
     */
    public static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();

        // Clear all fields first
        cal.clear();

        cal.set(year, month - 1, day);

        return cal.getTime();
    }

    /**
     * Returns the date instance based on the current system date
     * <p>
     *
     * @return Current System date.
     */
    public static Date getDate() {
        TimeZone tz = TimeZone.getTimeZone(timezone);
        return Calendar.getInstance(tz).getTime();
    }

    /**
     * private the constructor,and invoid the new operation
     */
    private DateUtils() {}

    /**
     * Retrieves the value of the field in the Date. Some common fields that is
     * likely to be used include :
     * <p>
     * <li>Calendar.YEAR - retrieves the year value
     * <li>Calendar.MONTH - retrieves the month value ( 1 - 12 )
     * <li>Calendar.DAY_OF_MONTH - retrieve the day value ( 1 - 31 )
     * <li>Calendar.HOUR - retrieves the hours value in 12 hour format ( 1 - 12
     * )
     * <li>Calendar.HOUR_OF_DAY - retrieves the hours value in 24 hour format (
     * 0 - 23 )
     * <li>Calendar.MINUTE - retrieves the minutes value ( 0 - 59 )
     * <li>Calendar.AM_PM - retrieves the am/pm value ( 0 = am; 1 = pm )
     * <p>
     *
     * @param date
     *            The Date object to extract value from.
     * @param field
     *            A Calendar constant to retrieve the field value from the Date
     *            object.
     * @return The value of the field that is requested.
     * @throws ArrayIndexOutOfBoundsException
     *             - if specified field is out of range (<code>field</code> &lt;
     *             0 || <code>field</code> &gt;= <code>Calendar.FIELD_COUNT
     *             </code>).
     * @see java.util.Calendar
     */
    public static int get(Date date, int field) {

        Calendar cal = Calendar.getInstance();

        cal.setTime(date);

        int value = cal.get(field);

        // Add 1 if the field is Calendar.MONTH since Calendar returns
        // the month value starting from 0.
        if (Calendar.MONTH == field)
            value += 1;

        // If it is 12 am/pm, the value will be 0. Need to change it to 12 for
        // ease of display.
        if (Calendar.HOUR == field && value == 0)
            value = 12;

        return value;
    }

    public static int age(Date dob) {
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.setTime(dob);
        to.setTime(new Date());
        int birthYYYY = from.get(Calendar.YEAR);
        int curYYYY = to.get(Calendar.YEAR);

        int ageInYears = curYYYY - birthYYYY;

        if (ageInYears < 0)
            ageInYears = 0;

        return ageInYears;
    }

    public static int dayBetweenTwoDates(Date beginDate, Date endDate) {
        int days;
        int pnMark = 1;
        if (endDate != null && beginDate != null) {

            Calendar bCalendar = Calendar.getInstance();
            Calendar eCalendar = Calendar.getInstance();
            if (beginDate.after(endDate)) {
                pnMark = -1;
                bCalendar.setTime(endDate);
                eCalendar.setTime(beginDate);
            } else {
                bCalendar.setTime(beginDate);
                eCalendar.setTime(endDate);
            }
            int dayBegin = bCalendar.get(Calendar.DAY_OF_YEAR);
            int dayEnd = eCalendar.get(Calendar.DAY_OF_YEAR);
            days = dayEnd - dayBegin;
            int endYear = eCalendar.get(Calendar.YEAR);
            if (bCalendar.get(Calendar.YEAR) != endYear) {
                bCalendar = (Calendar) bCalendar.clone();
            }
            while (bCalendar.get(Calendar.YEAR) != endYear) {
                days += bCalendar.getActualMaximum(Calendar.DAY_OF_YEAR);
                bCalendar.add(Calendar.YEAR, 1);
            }
            ;
        } else
            days = 0;

        return days * pnMark;
    }
    public static int getWorkingDay(Date beginDate, Date endDate) {
        Calendar d1 = Calendar.getInstance();
        d1.setTime(beginDate);
        Calendar d2 = Calendar.getInstance();
        d2.setTime(endDate);
        int result = -1;
        if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
            java.util.Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        int betweendays = dayBetweenTwoDates(d1.getTime(), d2.getTime());

        int charge_date = 0;
        int charge_start_date = 0;//开始日期的日期偏移量
        int charge_end_date = 0;//结束日期的日期偏移量
        // 日期不在同一个日期内
        int stmp;
        int etmp;
        stmp = 7 - d1.get(Calendar.DAY_OF_WEEK);
        etmp = 7 - d2.get(Calendar.DAY_OF_WEEK);
        if (stmp != 0 && stmp != 6) {// 开始日期为星期六和星期日时偏移量为0
            charge_start_date = stmp - 1;
        }
        if (etmp != 0 && etmp != 6) {// 结束日期为星期六和星期日时偏移量为0
            charge_end_date = etmp - 1;
        }
//  }
        result = (dayBetweenTwoDates(getNextMonday(d1).getTime(), getNextMonday(d2).getTime()) / 7)
                * 5 + charge_start_date - charge_end_date;
        //System.out.println("charge_start_date>" + charge_start_date);
        //System.out.println("charge_end_date>" + charge_end_date);
        //System.out.println("between day is-->" + betweendays);
        return result;
    }
    /**
     * 获得日期的下一个星期一的日期
     *
     * @param date
     * @return
     */
    public static Calendar getNextMonday(Calendar date) {
        Calendar result = null;
        result = date;
        do {
            result = (Calendar) result.clone();
            result.add(Calendar.DATE, 1);
        } while (result.get(Calendar.DAY_OF_WEEK) != 2);
        return result;
    }
    /**
     * Convert String To Date Using "dd/MM/yyyy HH:mm" Format.
     *
     * @param inputStr
     *            is a String to be converted to Date
     *
     * @return
     */
    public static java.util.Date parseTimeNoSeconds(String inputStr) {
        if (inputStr == null || inputStr.trim().equals("")) {
            return null;
        }

        Date result = null;
        try {
            result = timeFormatNoSeconds.parse(inputStr);
        } catch (Exception e) {
            // if (logger.isWarnEnabled()) {
            // logger.warn("Failed to parse datetime (date=" + inputStr
            // + ", pattern=" + "dd/MM/yyyy HH:mm", e);
            // }
        }

        return result;
    }

    /**
     * get data format form formatter pool.
     *
     * @param formatType
     * @return SimpleDateFormat.
     */
    public static SimpleDateFormat getDateFormat(String formatType) {
        SimpleDateFormat sdf = formatPool.get(formatType);
        if (sdf == null) {
            sdf = new SimpleDateFormat(formatType);
            formatPool.put(formatType, sdf);
        }
        return sdf;
    }

    /**
     * Returns the corresponding 3 letter string value of the month specified by
     * numeric month value
     *
     * @param month
     *            The numeric value of the specified month
     * @return the corresponding 3 letter string value of the month specified by
     *         numeric month value
     */
    public static String getStrMth(int month) {
        if (month == 1) {
            return MTH_JAN;
        } else if (month == 2) {
            return MTH_FEB;
        } else if (month == 3) {
            return MTH_MAR;
        } else if (month == 4) {
            return MTH_APR;
        } else if (month == 5) {
            return MTH_MAY;
        } else if (month == 6) {
            return MTH_JUN;
        } else if (month == 7) {
            return MTH_JUL;
        } else if (month == 8) {
            return MTH_AUG;
        } else if (month == 9) {
            return MTH_SEP;
        } else if (month == 10) {
            return MTH_OCT;
        } else if (month == 11) {
            return MTH_NOV;
        } else if (month == 12) {
            return MTH_DEC;
        } else {
            return "";
        }
    }

    public static Integer getIntMth(String month) {
        if (month == null || month.trim().equals("")) {
            return -1;
        } else if (month.trim().equalsIgnoreCase(MTH_JAN)) {
            return 1;
        } else if (month.trim().equalsIgnoreCase(MTH_FEB)) {
            return 2;
        } else if (month.trim().equalsIgnoreCase(MTH_MAR)) {
            return 3;
        } else if (month.trim().equalsIgnoreCase(MTH_APR)) {
            return 4;
        } else if (month.trim().equalsIgnoreCase(MTH_MAY)) {
            return 5;
        } else if (month.trim().equalsIgnoreCase(MTH_JUN)) {
            return 6;
        } else if (month.trim().equalsIgnoreCase(MTH_JUL)) {
            return 7;
        } else if (month.trim().equalsIgnoreCase(MTH_AUG)) {
            return 8;
        } else if (month.trim().equalsIgnoreCase(MTH_SEP)) {
            return 9;
        } else if (month.trim().equalsIgnoreCase(MTH_OCT)) {
            return 10;
        } else if (month.trim().equalsIgnoreCase(MTH_NOV)) {
            return 11;
        } else if (month.trim().equalsIgnoreCase(MTH_DEC)) {
            return 12;
        } else {
            return -1;
        }
    }

    /**
     * Returns date format dd mmStr yyyy
     *
     * @param
     * @return date format dd mmStr yyyy
     */

    @SuppressWarnings("deprecation")
    public static String getDateToinputWithMonthStr(Date date) {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy");
        String dd = String.valueOf(date.getDate());
        int month = date.getMonth() + 1;
        String yyyy = String.valueOf(bartDateFormat.format(date));
        String monthStr = getStrMth(month);
        return dd + " " + monthStr + " " + yyyy;
    }

    public static boolean isWeekend(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int weekNo = cal.get(Calendar.DAY_OF_WEEK);
        if (weekNo == 1 || weekNo == 7) {
            return true;
        }
        return false;
    }

    public static Date getFirstDayOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getLastDayOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return getPreviousDate(calendar.getTime());
    }

    public static Date getPreviousDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day - 1);
        return calendar.getTime();
    }

    public static int getSeason(Date date){
        int season = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month=cal.get(Calendar.MONTH);
        season=(month+1+4)/4;
        return season;
    }

    public static Date getFirstDayOfSeason(int year, int quarter) {
        int month = 0;
        if (quarter > 4) {
            return null;
        } else {
            month = (quarter - 1) * 3 + 1;
        }
        return getFirstDayOfMonth(year, month);
    }

    public static Date getLastDayOfSeason(int year, int quarter) {
        int month = 0;
        if (quarter > 4) {
            return null;
        } else {
            month = quarter * 3;
        }
        return getLastDayOfMonth(year, month);
    }

    public static boolean isDayInSameWeek(Date date1, Date date2) {
//		Calendar cal1 = Calendar.getInstance();
//		cal1.setFirstDayOfWeek(Calendar.MONDAY);
//		// cal1.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//		cal1.setTime(date1);
//
//		Calendar cal2 = Calendar.getInstance();
//		cal2.setFirstDayOfWeek(Calendar.MONDAY);
//		// cal2.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//		cal2.setTime(date2);
//
//		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
//		if (subYear == 0) {
//			int subMonth = cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH);
//			if (subMonth == 0) {
//				int subWeek = cal1.get(Calendar.WEEK_OF_MONTH)
//						- cal2.get(Calendar.WEEK_OF_MONTH);
//				if (subWeek == 0) {
//					return true;
//				}
//			}
//		}
//		return false;

        Calendar cal1 = Calendar.getInstance();
        cal1.setFirstDayOfWeek(Calendar.MONDAY);
        cal1.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Calendar cal2 = Calendar.getInstance();
        cal2.setFirstDayOfWeek(Calendar.MONDAY);
        cal2.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (subYear == 0) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (subYear == 1 && cal2.get(Calendar.MONTH) == 11) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
                    .get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (subYear == -1 && cal1.get(Calendar.MONTH) == 11) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
                    .get(Calendar.WEEK_OF_YEAR))
                return true;

        }
        return false;
    }

    public static boolean isDayInSameMonth(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (subYear == 0) {
            int subMonth = cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH);
            if (subMonth == 0) {
                return true;
            }
        }
        return false;
    }


    public static Timestamp convertDateToTimestamp(Date date) {
        if (date != null)
            return new Timestamp(date.getTime());
        return null;
    }

    public static Timestamp getDateTimestamp(){
        return new Timestamp(System.currentTimeMillis());
    }

    public static Date getDateWithOutTime(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        return cal.getTime();
    }

}
