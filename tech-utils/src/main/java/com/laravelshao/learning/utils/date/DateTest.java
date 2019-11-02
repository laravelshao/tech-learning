package com.laravelshao.learning.utils.date;

import com.laravelshao.learning.utils.utils.DateUtil;
import com.laravelshao.learning.utils.utils.tuple.Tuple2;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.laravelshao.learning.utils.utils.DateUtil.HHMMSS;
import static com.laravelshao.learning.utils.utils.DateUtil.YYYY_MM_DD;
import static com.laravelshao.learning.utils.utils.DateUtil.YYYY_MM_DD_HH_MM_SS;
import static com.laravelshao.learning.utils.utils.DateUtil.string2Date;

/**
 * @author qinghua.shao
 * @date 2019/10/18
 * @since 1.0.0
 */
public class DateTest {

    public static void main(String[] args) {
        //System.out.println(jointSceneInterval(new Date(),string2Date("2019-10-18 16:30:00",YYYY_MM_DD_HH_MM_SS)));

        //Tuple2<Date, Date> tuple2 = calcDayInterval(new Date());
        //System.out.println(tuple2.a);
        //System.out.println(tuple2.b);


        //Date s1 = string2Date("2019-10-19 12:00:00");
        //Date e1 = string2Date("2019-10-19 13:00:00");
        //Date s2 = string2Date("2019-10-19 14:00:00");
        //Date e2 = string2Date("2019-10-19 15:00:00");
        //
        //boolean conflict = isIntervalConflict(s1.getTime(), e1.getTime(), s2.getTime(), e2.getTime());
        //System.out.println(conflict);


        //Integer i1 = new Integer(1000);
        //Integer i2 = new Integer(1000);


        //1572750000000
        //1572753600000
        //
        //1572746400000
        //1572750000000

        boolean intervalConflict = isIntervalConflict(1572750000000L, 1572753600000L, 1572746400000L, 1572750000000L);
        System.out.println(intervalConflict);
    }

    /**
     * 拼接场次时间段(093000-113000)
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    private static String jointSceneInterval(Date startTime, Date endTime) {
        String[] array = new String[2];
        array[0] = DateUtil.date2String(startTime, HHMMSS);
        array[1] = DateUtil.date2String(endTime, HHMMSS);
        return StringUtils.join(array, "-");
    }

    /**
     * 计算指定时间所在天开始至结束时间
     *
     * @param date 指定时间
     * @return
     */
    public static Tuple2<Date, Date> calcDayInterval(Date date) {
        String dateString = DateUtil.date2String(date, YYYY_MM_DD);
        Date startTime = DateUtil.string2Date(dateString,YYYY_MM_DD);
        Date endTime = DateUtil.string2Date(dateString + " 23:59:59");
        return new Tuple2<>(startTime, endTime);
    }

    /**
     * 判断区间是否冲突
     *
     * @param startTime1 时间段1开始时间
     * @param endTime1   时间段1结束时间
     * @param startTime2 时间段2开始时间
     * @param endTime2   时间段2结束时间
     * @return
     */
    public static boolean isIntervalConflict(long startTime1, long endTime1, long startTime2, long endTime2) {
        return (endTime1 <= startTime2 || endTime2 <= startTime1) ? false : true;
    }

}
