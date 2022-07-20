package common.utilities;

import com.github.javafaker.Faker;
import net.minidev.json.JSONUtil;

import javax.xml.bind.Element;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class DateTimeUtil {
    /**
     * Get time based on parameter format
     *
     * @param stringFormat desired string format
     */
    public static String getTodayByFormat(String stringFormat) {
        DateFormat sdf = new SimpleDateFormat(stringFormat);
        Calendar cal = Calendar.getInstance();
        return sdf.format(cal.getTime());
    }

    public static String getTimestamp() {
        return getTodayByFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static String getTimestampFormat() {
        return getTodayByFormat("dd-MM-yyyy ");
    }

    public static String getTimestampFormats() {
        return getTodayByFormat("dd/MM/yyyy H:mm:a");
    }

    public static String getTodayFormats() {
        return getTodayByFormat("MMM dd, yyyy");
    }
    public static String getDateFormat() {
        return getTodayByFormat("MMM d, yyyy");
    }
    public static String getTodayFormatRequest() {
        return getTodayByFormat("MMMMM dd, yyyy");
    }
    public static String getTodayFormatDate() {
        return getTodayByFormat("yyyyMMddhhssmm");
    }
    public static String getTodayFormatDateConvert() {
        return getTodayByFormat("mm/dd/yyyy hh:mm:ss a");
    }

    public static String getCurrentDateTime() {
        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());

        Date currentTime = localCalendar.getTime();
        int currentDay = localCalendar.get(Calendar.DATE);
        int currentMonth = localCalendar.get(Calendar.MONTH) + 1;
        int currentYear = localCalendar.get(Calendar.YEAR);

        String datevalue = currentDay + "/" + currentMonth + "/" + currentYear;
        return datevalue;
    }

    public static int getCurrentDay() {
        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
        int currentDay = localCalendar.get(Calendar.DATE);
        return currentDay;
    }

    public static int getCurrentMonth() {
        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
        int currentMonth = localCalendar.get(Calendar.MONTH) + 1;
        return currentMonth;
    }

    public static int getNextMonth() {
        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
        localCalendar.add(Calendar.MONTH, 1);
        int nextMonth = localCalendar.get(Calendar.MONTH);
        return nextMonth;
    }

    public static int getCurrentYear() {
        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
        int currentYear = localCalendar.get(Calendar.YEAR);
        return currentYear;
    }

    public static String getNextByFormat(String fomat) {
        final SimpleDateFormat format = new SimpleDateFormat(fomat);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);

        return format.format(calendar.getTime());
    }

    /**
     * Get year/month/day of a source date
     *
     * @param type Calendar.YEAR / Calendar.MONTH / Calendar.DATE
     */
    public static int getTimeType(String sourceDate, int type) {
        sourceDate = sourceDate.replace("/", "-");
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        Date myDate = null;
        try {
            myDate = format.parse(sourceDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        return cal.get(type);

    }


    public static String addDays(String sourceDate, int days, String dateFormat) {
        sourceDate = sourceDate.replace("/", "-");
        SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.US);
        Date myDate = null;
        try {
            myDate = format.parse(sourceDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.DATE, days); // minus number would decrement the days
        sourceDate = format.format(cal.getTime());
        return sourceDate;
    }

    public static String addMonthDay(String sourDate, int month, int date, String dateFormat) {
        DateFormat format = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        Date myDate = null;
        try {
            myDate = format.parse(sourDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(myDate);
            calendar.add(calendar.DATE, date);
            calendar.add(Calendar.MONTH, month);
            Date newDate = calendar.getTime();
            DateFormat dateFormats = new SimpleDateFormat("MMM dd, yyyy");
            String strDate = dateFormats.format(newDate);
            return strDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return "";

        }


    }

    public static String getDiffHourInTime(Date startDate, Date endDate) {
        long secs = (endDate.getTime() - startDate.getTime()) / 1000;
        int hours = (int) (secs / 3600);
        secs = secs % 3600;
        int mins = (int) (secs / 60);
        secs = secs % 60;

        String time = String.format("%02d", hours) + ":" + String.format("%02d", mins) + ":00";
        return time;
    }

    public static double getDiffHour(Date startDate, Date endDate) {
        long secs = (endDate.getTime() - startDate.getTime()) / 1000;
        int hours = (int) (secs / 3600);
        secs = secs % 3600;
        int mins = (int) (secs / 60);
        secs = secs % 60;
        double time = hours + (double) mins / 60;
        return time < 0 ? 0 : time;
    }

    public static int currentTime() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static long additionMinutes(long currentTimeInSec, int additionMinutes) {
        long varAdditionTime = additionMinutes * 60;
        long expectedTime = currentTimeInSec + varAdditionTime;
        return expectedTime;
    }

    public String convertTime(long strCurrentTimeInSec) {
        Date strCurrentTime = new Date(strCurrentTimeInSec);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String strCurrentTimeUTC = dateFormat.format(strCurrentTime);
        return strCurrentTimeUTC;
    }

    public static String convertTimes(String formatBefore, String formatConvert) {
        Date strCurrentTime = new Date(Long.parseLong(formatBefore));
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatConvert);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String strCurrentTimeUTC = dateFormat.format(strCurrentTime);
        return strCurrentTimeUTC;
    }

    public static Date getNowUTC() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        System.out.println(cal.toString());
        Date now = cal.getTime();
        return now;
    }

    public static long dayOfTime(String sourDayFist, String sourDayEnd, String fistDayFormat, String endDayFormat) {
        SimpleDateFormat format_1 = new SimpleDateFormat(fistDayFormat);
        SimpleDateFormat format_2 = new SimpleDateFormat(endDayFormat);
        Date myDate_1 = null;
        Date myDate_2 = null;
        try {
            myDate_1 = format_1.parse(sourDayFist);
            myDate_2 = format_2.parse(sourDayEnd);
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTime(myDate_1);
            c2.setTime(myDate_2);
            Date newDate_1 = c1.getTime();
            Date newDate_2 = c2.getTime();
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            String a = date.format(newDate_1);
            String b = date.format(newDate_2);
            Date fistDay = date.parse(a);
            Date endDay = date.parse(b);
            long aboutDay = ((endDay.getTime() - fistDay.getTime())) / (24 * 60 * 60 * 1000);
            return aboutDay;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static long calculateDayBetweenTwoDate(String before, String after) {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
        long diff = 0;
        try {
            Date date1 = myFormat.parse(before);
            Date date2 = myFormat.parse(after);
            diff = date2.getTime() - date1.getTime();

            System.out.println("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }


}







