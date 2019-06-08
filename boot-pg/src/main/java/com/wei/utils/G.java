package com.wei.utils;

import java.io.File;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author whr 常用的简单功能处理
 */
public class G {

static final float ZERO = 0.0000000000001f;
static final float ZERO_point_2 = 0.01f;

/**
 * 判断字符串和List,Array是否是空 包括对象为空/size为0
 * String s = "";
 * Object o = null;
 * UserInfo x = null
 * List<String> lst = null
 * Map<Sring,Objhect> - null;
 * if(G.isEmpty(o)){
 * <p>
 * <p>
 * }
 *
 * @param obj
 * @return
 */
public static boolean isEmpty(Object obj) {
 if (obj == null) {
  return true;
 }
 // 串为空值时，返回真
 if (obj.getClass().equals(String.class)) {
  String s = (String) obj;
  return s.length() < 1;
 }
 // Array
 if (obj.getClass().isArray()) {
  Array lst = (Array) obj;
  return Array.getLength(lst) < 1;
 }
 //
 if (obj instanceof java.util.List) {
  @SuppressWarnings("unchecked")
  List<Object> lst = (List<Object>) obj;
  return lst.size() < 1;
 }
 if (obj instanceof java.util.Map) {
  @SuppressWarnings("unchecked")
  Map<Object, Object> lst = (Map<Object, Object>) obj;
  return lst.size() < 1;
 }
 if (obj instanceof java.util.Dictionary) {
  @SuppressWarnings("unchecked")
  Dictionary<Object, Object> lst = (Dictionary<Object, Object>) obj;
  return lst.size() < 1;
 }
 return false;
}

/**
 * 传入的对象列表如果是空值,则直接抛出异常
 *
 * @param dispName,用于显示在错误信彷中的名称
 * @param lst                    G.empty_throw("部门/岗位/员工/门店/"，dept,info,
 */
public static void empty_throw(String dispName,
                               Object... lst) {
 assert_empty_throw(dispName, lst);
}

public static void emptyThrowAll(String dispName,
                                 Object... lst) {
 empty_throw_all(dispName, lst);
}

/**************************************************
 * usage:
 *
 * G.empty_throw_all("部门/岗位/员工"，a,b,c);
 **************************************************/
public static void empty_throw_all(String dispName,
                                   Object... lst) {
 String[] l = dispName.split(",");
 List<Integer> indexs = getEmpty(lst);

 if (indexs != null) {
  if (!G.isEmpty(indexs)) {
   String s = "";
   for (int i : indexs) {
    if (!(i >= 0 && i < l.length)) {
     continue;
    }

    //----------------------------------------------------------
    //
    if (s == "") {
     s = l[i];
     continue;
    }
    s += "," + l[i];
   }

   if (s == "") {
    s = dispName;
   }
   throw new BizException(s);
  }
 }
}

private static List<Integer> getEmpty(Object[] l) {
 List<Integer> lst = new ArrayList<>();
 for (int i = 0; i < l.length; i++) {
  if (G.isEmpty(l[i])) {
   lst.add(i);
  }
 }

 return lst;
}

public static void assert_empty_throw(String dispName, Object... lst) {
 if (G.isEmpty(dispName)) {
  dispName = "传入参数";
 }
 if (lst == null) {
  throw new BizException(dispName + "对象为空！");
 }
 if (lst.length == 0) {
  throw new BizException(dispName + "对象为空！");
 }
 for (Object obj : lst) {
  if (G.isEmpty(obj)) {
   throw new BizException(dispName + "对象为空！");
  }
 }
}

/**
 * 如果对象或数组为空值,空串,长度为0,则抛出异常
 *
 * @param dispName
 * @param lst
 */
public static void assert_empty_throw(String dispName, List<Object> lst) {
 if (lst == null || lst.size() == 0) {
  throw new BizException("对象为空！");
 }
}

/**
 * 判断多个对象中是否任一为null
 *
 * @param lst
 * @return
 */
public static boolean isNull_or(Object... lst) {
 if (lst == null) {
  return true;
 }
 for (Object obj : lst) {
  if (obj == null) {
   return true;
  }
 }
 return false;
}

public static boolean isNull_all(Object... lst) {
 if (lst == null) {
  return true;
 }
 for (Object obj : lst) {
  if (obj != null) {
   return false;
  }
 }
 return true;
}

public static boolean in(Object obj, Object... lst) {
 if (obj == null) {
  return false;
 }
 if (lst == null) {
  return false;
 }
 if (lst.length == 0) {
  return false;
 }
 for (Object o : lst) {
  if (obj.equals(o)) {
   return true;
  }
 }
 return false;
}

public static boolean in(String obj, String... lst) {
 if (obj == null) {
  return false;
 }
 if (lst == null) {
  return false;
 }
 if (lst.length == 0) {
  return false;
 }
 for (Object o : lst) {
  if (obj.equals(o)) {
   return true;
  }
 }
 return false;
}

/**
 * 判断多个对象中是否任一为空(空值,空串,数组)
 *
 * @param lst
 * @return
 */
public static boolean isEmpty_or(Object... lst) {
 if (lst == null) {
  return true;
 }
 for (Object obj : lst) {
  if (G.isEmpty(obj)) {
   return true;
  }
 }
 return false;
}

public static boolean isEmpty_all(Object... lst) {
 if (lst == null) {
  return true;
 }
 for (Object obj : lst) {
  if (!G.isEmpty(obj)) {
   return false;
  }
 }
 return true;
}

public static boolean isZero_all(BigDecimal... lst) {
 if (lst == null) {
  return true;
 }
 for (BigDecimal d : lst) {
  if (d == null) {
   //
   continue;
  }
  float f = d.floatValue();
  if (Math.abs(f) > G.ZERO) {
   return false;
  }
 }
 return true;
}

public static void assert_isZero_all_throw(String dispname,
                                           BigDecimal... lst) {
 if (isZero_all(lst)) {
  G.throw_bizException(dispname + "传入数据全部为0!");
 }
}

public static boolean isZero_or(BigDecimal... lst) {
 if (lst == null) {
  return true;
 }
 for (BigDecimal d : lst) {
  if (d == null) {
   return true;
  }
  //
  float f = d.floatValue();
  if (Math.abs(f) < G.ZERO) {
   return true;
  }
 }
 return false;
}

/**
 * 如果一个对象为空，则抛出异常
 * G.assert_null_throw(userInfo,deptInfo,a,b)
 *
 * @param o
 */
public static void assert_null_throw(Object o) {
 assert_null_throw(o, "");
}

public static void assert_null_throw(Object... lst) {
 for (Object o : lst) {
  assert_null_throw(o);
 }
}

/**
 * 如果一个对象为空，则抛出异常
 *
 * @param o
 * @param obj_name
 */
public static void assert_null_throw(Object o, String obj_name) {
 if (G.isEmpty(obj_name)) {
  obj_name = "";
 }
 if (o == null) {
  throw new BizException(90080001, obj_name + "传入的对象为空值！");
 }
}

/**
 * 如果l == 0，则抛出异常
 *
 * @param
 * @param obj_name
 */
public static void assert_zero_throw(long l, String obj_name) {
 if (G.isEmpty(obj_name)) {
  obj_name = "";
 }
 if (Math.abs(l) < 1L) {
  throw new BizException(90080001, obj_name + "更改了0条数据");
 }
}

public static void assert_zero_throw(long l) {
 if (Math.abs(l) < 1L) {
  throw new BizException(90080001, "更改了0条数据");
 }
}

/**
 * bool值为假时，抛出异常
 *
 * @param b
 * @param obj_name
 */
public static void assert_false_throw(Boolean b, String obj_name) {
 if (G.isEmpty(obj_name)) {
  obj_name = "";
 }
 if (!b) {
  throw new BizException(90080001, obj_name + "更改了0条数据");
 }
}

public static void throw_update_not_count(String obj_name) {
 if (G.isEmpty(obj_name)) {
  obj_name = "";
 }
 throw new BizException(90080001, obj_name + "更改数据的记录数目异常！");
}

public static void throw_no_impl(String classPos) {
 throw new BizException(90080001, "没有实现的服务器代码," + classPos);
}

public static void throw_bizException(String msg) {
 throw new BizException(90080001, msg);
}

public static boolean exist(String s, String... lst) {
 return str_exist(s, lst);
}

public static boolean str_exist(String s, String... lst) {
 // G.assert_null_throw(s);
 if (G.isEmpty(s)) {
  return false;
 }
 // G.assert_null_throw(lst);
 for (String each : lst) {
  if (G.isEmpty(each)) {
   continue;
  }
  if (each.equals(s)) {
   return true;
  }
 }
 return false;
}

public static void assert_null_set_0(BigDecimal... lst) {
 for (BigDecimal d : lst) {
  d = G.get_value_null_0(d);
 }
}

public static float get_float_value(BigDecimal d) {
 if (G.isEmpty(d)) {
  return 0;
 }
 return d.floatValue();
}

public static BigDecimal decimal_scale(BigDecimal d, int scale) {
 if (scale < 0) {
  return d;
 }
 if (d == null) {
  return new BigDecimal(0);
 }
 try {
  BigDecimal a = d.setScale(4, BigDecimal.ROUND_HALF_DOWN);
  return a;
 } catch (Exception ee) {
  return d;
 }
}

public static float decimal_float_value(BigDecimal d) {
 if (G.isEmpty(d)) {
  return 0;
 }
 return d.floatValue();
}

public static double decimal_double_value(BigDecimal d) {
 if (G.isEmpty(d)) {
  return 0;
 }
 return d.doubleValue();
}

public static BigDecimal get_value_null_0(BigDecimal d) {
 if (G.isEmpty(d)) {
  return new BigDecimal(0);
 }
 return d;
}

public static void et(String info, Object o) {
 empty_throw(info, o);
}

/**************************************************
 * usage:部分相似的情况，str contains lst->eclment or lst->clement contains->str
 **************************************************/
public static boolean in_part(String str, String... lst) {
 for (String each : lst) {
  if (G.isEmpty(each)) {
   continue;
  }

  //
  if (each.contains(str)) {
   return true;
  }
  if (str.contains(each)) {
   return true;
  }
 }
 return false;
}

public static String keyPrefix(String... lst) {
 String s = "";
 for (String str : lst) {
  if (s == "") {
   s = str;
   continue;
  }
  s += "_" + (G.isEmpty(s) ? "_" : str);
 }
 return s;
}

public static Long get_Long_of_map(Map<String, Object> map,
                                   String key, Long default_value) {
 if (map == null) {
  return default_value;
 }
 // ----------------------------------------------------
 Object obj = map.get(key);
 if (obj instanceof Long) {
  return (Long) obj;
 }
 try {
  return Long.parseLong(obj.toString());
 } catch (Exception ee) {
  return default_value;
 }

}

public static Integer get_Integer_of_map(Map<String, Object> map,
                                         String key, Integer default_value) {
 if (map == null) {
  return default_value;
 }
 // ----------------------------------------------------
 Object obj = map.get(key);
 if (obj instanceof Integer) {
  return (Integer) obj;
 }
// if (obj instanceof Long) {
//  return ((Long) obj).intValue();
// }
 try {
  return Integer.parseInt(obj.toString());
 } catch (Exception ee) {
  return default_value;
 }

}

public static class N {

 public static float to_float(BigDecimal d) {
  if (d == null)
   return 0f;
  return d.floatValue();
 }

 public static float to_int(BigDecimal d) {
  if (d == null)
   return 0;
  return d.intValue();
 }
}

public static class D extends DateUtils {
}

public static class DateUtils {

 public static Integer week(Date dt) {
  TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
  Calendar c = Calendar.getInstance(Locale.CHINA);
  c.setFirstDayOfWeek(Calendar.MONDAY);
  c.setMinimalDaysInFirstWeek(7);
  c.setTime(dt);
  // ----------------------------------------------------
  int i = c.get(Calendar.WEEK_OF_YEAR);
  return i;
 }

 public static Date weekFirstDay(Date dt) {
  TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
  Calendar c = Calendar.getInstance(Locale.CHINA);
  c.setFirstDayOfWeek(Calendar.MONDAY);
  c.setMinimalDaysInFirstWeek(7);
  c.setTime(dt);
  c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
  // ----------------------------------------------------
  // int i = c.get(Calendar.DAY_OF_WEEK);
  // if (i == 0) {
  // return c.getTime();
  // }
  // ;
  // //
  // c.add(Calendar.DAY_OF_YEAR, -(i - 1));
  return c.getTime();
 }

 public static Date weekLastDay(Date dt) {
  return add_day(weekFirstDay(dt), 6);
 }

 public static int season(Date dt) {
  if (dt == null) {
   return 0;
  }
  //
  TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
  Calendar c = Calendar.getInstance(Locale.CHINA);
  c.setTime(dt);
  //
  int i = month(dt);
  //
  if (G.in(i, 1, 2, 3))
   return 1;
  if (G.in(i, 4, 5, 6))
   return 2;
  if (G.in(i, 7, 8, 9))
   return 3;
  if (G.in(i, 10, 11, 12))
   return 4;
  return 0;
 }

 public static Date seasonFirstDay(Date dt) {
  if (dt == null) {
   return dt;
  }
  //
  int i = season(dt);
  //
  TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
  Calendar c = Calendar.getInstance(Locale.CHINA);
  c.setTime(dt);
  int[] l = new int[]{1, 4, 7, 10
  };
  c.set(Calendar.MONTH, l[i - 1] - 1);
  //
  c.set(Calendar.DAY_OF_MONTH, 1);
  return c.getTime();
 }

 public static Date seasonLastDay(Date dt) {
  if (dt == null) {
   return dt;
  }
  //
  int i = season(dt);
  //
  TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
  Calendar c = Calendar.getInstance(Locale.CHINA);
  c.setTime(dt);
  int[] l = new int[]{3, 6, 9, 12
  };
  c.set(Calendar.MONTH, l[i - 1] - 1);
  //
  return monthLastDay(c.getTime());
 }

 public static Date monthFirstDay(Date dt) {
  if (dt == null) {
   return dt;
  }
  //
  TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
  Calendar c = Calendar.getInstance(Locale.CHINA);
  c.setTime(dt);
  c.set(Calendar.DAY_OF_MONTH, 1);
  return c.getTime();
 }

 public static Date monthLastDay(Date dt) {
  if (dt == null) {
   return dt;
  }
  //
  // 本月份中1,得到一个时间值
  TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
  Calendar c = Calendar.getInstance(Locale.CHINA);
  c.setTime(dt);
  c.add(Calendar.MONDAY, 1);
  // 获取下月的1号
  Date n = monthFirstDay(c.getTime());
  // 下月1号减1天
  return add_day(n, -1);
 }

 public static Date xunFirstDay(Date dt) {
  TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
  Calendar c = Calendar.getInstance(Locale.CHINA);
  c.setTime(dt);
  //
  int i = xun(dt);
  //
  if (i == 1) {
   c.set(Calendar.DAY_OF_MONTH, 1);
   return c.getTime();
  }
  if (i == 2) {
   c.set(Calendar.DAY_OF_MONTH, 11);
   return c.getTime();
  }
  c.set(Calendar.DAY_OF_MONTH, 21);
  return c.getTime();
 }

 public static Date xunLastDay(Date dt) {
  TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
  Calendar c = Calendar.getInstance(Locale.CHINA);
  c.setTime(dt);
  //
  int i = xun(dt);
  //
  if (i == 1) {
   c.set(Calendar.DAY_OF_MONTH, 10);
   return c.getTime();
  }
  if (i == 2) {
   c.set(Calendar.DAY_OF_MONTH, 20);
   return c.getTime();
  }
  return monthLastDay(dt);
 }

 public static Integer xun(Date dt) {
  if (dt == null) {
   return 0;
  }
  //
  TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
  Calendar c = Calendar.getInstance(Locale.CHINA);
  c.setTime(dt);
  //
  int i = c.get(Calendar.DAY_OF_MONTH);
  //
  if (i <= 10)
   return 1;
  if (i > 10 && i <= 20)
   return 2;
  if (i >= 20)
   return 3;
  return 0;
 }

 public static Integer year(Date dt) {
  if (dt == null) {
   return 0;
  }
  //
  TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
  Calendar c = Calendar.getInstance(Locale.CHINA);
  c.setTime(dt);
  //
  int i = c.get(Calendar.YEAR);
  return i;
 }

 public static Date yearFirstDay(Date dt) {
  TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
  Calendar c = Calendar.getInstance(Locale.CHINA);
  c.setTime(dt);
  c.set(Calendar.MONTH, 1 - 1);
  // calendar.set(Calendar.DAY_OF_MONTH, 1);
  return monthFirstDay(c.getTime());
 }

 public static Date yearLastDay(Date dt) {
  TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
  Calendar c = Calendar.getInstance(Locale.CHINA);
  c.setTime(dt);
  c.set(Calendar.MONTH, 12 - 1);
  return monthLastDay(c.getTime());
 }

 public static int month(Date dt) {
  if (dt == null) {
   return 0;
  }
  //
  TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
  Calendar c = Calendar.getInstance(Locale.CHINA);
  c.setTime(dt);
  //
  int i = c.get(Calendar.MONTH);
  return i + 1;
 }

 public static String current_datetime_str() {
  Date dt = new Date();
  SimpleDateFormat obj = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
  String s = obj.format(dt);
  return s;
 }

 public static Date add_day(Date date, int day) {
  TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
  Calendar calendar = Calendar.getInstance(Locale.CHINA);
  calendar.setTime(date);
  calendar.add(Calendar.DATE, day);// 把日期往后增加一天.整数往后推,负数往前移动
  Date dt = calendar.getTime(); // 这个时间就是日期往后推一天的结果
  return dt;
 }

 public static String current_date_str() {
  Date dt = new Date();
  SimpleDateFormat obj = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
  String s = obj.format(dt);
  return s;
 }

 /**
  * 从obj中返回date obj必须是date型或String型
  *
  * @param obj
  * @return
  */
 public static Date getDate_obj(Object obj) {
  Date dt = null;
  if (obj.getClass().equals(Date.class)) {
   dt = (Date) obj;
  }
  if (obj.getClass().equals(String.class)) {
   dt = G.DateUtils.getDate((String) obj);
  }
  if (obj.getClass().equals(GregorianCalendar.class)) {
   dt = ((GregorianCalendar) obj).getTime();
  }
  // if (obj.getClass().equals(Timestamp.class)) {
  // long l =((Timestamp) obj).getTime();
  // }
  if (obj instanceof Date) {
   dt = (Date) obj;
  }
  return dt;
 }

 public static Date getDate(Date dt) {
  if (dt == null) {
   return null;
  }
  TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
  Calendar cal = Calendar.getInstance(Locale.CHINA);
  cal.setTime(dt);
  cal.set(Calendar.HOUR_OF_DAY, 0);
  cal.set(Calendar.MINUTE, 0);
  cal.set(Calendar.SECOND, 0);
  cal.set(Calendar.MILLISECOND, 0);
  return cal.getTime();
 }

 public static Date current_date() {
  TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
  Calendar cal = Calendar.getInstance(Locale.CHINA);
  cal.set(Calendar.HOUR_OF_DAY, 0);
  cal.set(Calendar.MINUTE, 0);
  cal.set(Calendar.SECOND, 0);
  cal.set(Calendar.MILLISECOND, 0);
  Date dt = cal.getTime();
  return dt;
 }

 public static Date current_datetime() {
  TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
  Calendar cal = Calendar.getInstance(Locale.CHINA);
  Date dt = cal.getTime();
  return dt;
 }

 /**
  * 获取无效时间点
  *
  * @return
  */
 public static Date getData_zero_After() {
  return getDate("1980-01-01 01:00:00");
 }

 /**
  * 是否时时间0
  *
  * @return
  */
 public static boolean is_zero_data(Date dt) {
  if (dt == null) {
   return true;
  }
  return dt.before(getDate("2000-01-01 01:00:00"));
 }

 /**
  * 2007-07-12 12:00:00型串转为日期型
  *
  * @param s
  * @return
  */
 public static Date getDate(String s) {
  if (G.isEmpty(s)) {
   return null;
  }
  SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  if (s.length() <= 10) {
   fmt = new SimpleDateFormat("yyyy-MM-dd");
  }
  Date date = null;
  try {
   date = fmt.parse(s);
   return date;
  } catch (java.text.ParseException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  return null;
 }

 public static String getDateStr(Date dt) {
  if (dt == null) {
   return "";
  }
  SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  String s = fmt.format(dt);
  return s;
 }

 public static String getDateStr_noTime(Date dt) {
  if (dt == null) {
   return "";
  }
  SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
  String s = fmt.format(dt);
  return s;
 }

 /**
  * @author liyang cooperay@qq.com @Title: getDateStart @Description:
  * 将日期时分秒重置为0 @param @param date @param @return 设定文件 @return Date
  * 返回类型 @throws
  */
 public static Date getDateStart(Date date) {
  if (date == null) {
   return date;
  }
  TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
  Calendar starttime = Calendar.getInstance(Locale.CHINA);
  starttime.setTime(date);
  starttime.set(Calendar.HOUR_OF_DAY, 0);
  starttime.set(Calendar.MINUTE, 0);
  starttime.set(Calendar.SECOND, 0);
  starttime.set(Calendar.MILLISECOND, 0);
  return starttime.getTime();
 }

 /**
  * @author liyang cooperay@qq.com @Title: getDateStart @Description:
  * 将日期时分秒重置为23:59:59 @param @param date @param @return 设定文件 @return Date
  * 返回类型 @throws
  */
 public static Date getDateEnd(Date date) {
  if (date == null)
   return date;
  TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
  Calendar endtime = Calendar.getInstance(Locale.CHINA);
  endtime.setTime(date);
  endtime.set(Calendar.HOUR_OF_DAY, 23);
  endtime.set(Calendar.MINUTE, 59);
  endtime.set(Calendar.SECOND, 59);
  endtime.set(Calendar.MILLISECOND, 0);
  return endtime.getTime();
 }

 public static Date date_add_day(Date d, int days) {
  TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
  Calendar c = Calendar.getInstance(Locale.CHINA);
  c.setTime(d);
  c.add(Calendar.DAY_OF_YEAR, days);
  // String s = G.DateUtils.getDateStr(c.getTime());
  // System.out.println(s);
  return c.getTime();
 }

 public static Date lastWeekMonday(Date d) {
  TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
  Calendar c = Calendar.getInstance(Locale.CHINA);
  c.setTime(d);
  int i = c.get(Calendar.DAY_OF_WEEK);
  //
  Date d1 = add_day(d, (-1) * (i + 6));
  return d1;
 }
}

public static void debug(Object o) {
 try {
  System.out.println(o);
 } catch (Exception ee) {
 }
}

/**
 * 只适用于本工程 切去表名称中可能有的delete,update/insert语句，去掉所有符号
 *
 * @param s
 * @return
 */
public static String getPure_sql_table_name(String s) {
 if (G.isEmpty(s)) {
  return s;
 }
 String lst[] = new String[]{"delete", "update", "select", " ", "@", ">",
  "<", "=", "\'", "\"", "\\", "/", "(", ")"
 };
 for (String each : lst) {
  s = s.replace(each, "");
 }
 return s;
}

public static boolean zero(BigDecimal d) {
 return is_zero(d);
}

public static boolean zero(float d) {
 return is_zero(d);
}

public static boolean zero(Long d) {
 return is_zero(d);
}

public static boolean zero(Integer d) {
 return is_zero(d);
}

public static boolean is_zero(Integer d) {
 if (d == null) {
  return true;
 }
 if (Math.abs(d.intValue()) < G.ZERO) {
  return true;
 }
 return false;
}

public static boolean is_zero(float d) {

 if (Math.abs(d) < G.ZERO) {
  return true;
 }
 return false;
}

public static boolean is_zero(BigDecimal d) {
 if (d == null) {
  return true;
 }
 if (Math.abs(d.floatValue()) < G.ZERO) {
  return true;
 }
 return false;
}

public static boolean is_zero(Long d) {
 if (d == null) {
  return true;
 }
 if (Math.abs(d.longValue()) < G.ZERO) {
  return true;
 }
 return false;
}

public static BigDecimal decimal_x(BigDecimal a, BigDecimal... lst) {
 float all = (a == null ? 0 : a.floatValue());
 if (lst != null) {
  for (BigDecimal b : lst) {
   float f2 = (b == null ? 0 : b.floatValue());
   all *= f2;
  }
 }
 // BigDecimal d = new BigDecimal(String.format("%.4f", all));
 return new BigDecimal(all);
}

public static boolean decimal_not_zero(BigDecimal d) {
 float f1 = d == null ? 0 : d.floatValue();
 return Math.abs(f1) > 0;
}

/**
 * d1 / d2
 *
 * @param d1
 * @param d2
 * @return
 */
public static BigDecimal decimal_div(BigDecimal d1, BigDecimal d2) {
 float f1 = d1 == null ? 0 : d1.floatValue();
 float f2 = d2 == null ? 0 : d2.floatValue();
 if (Math.abs(f2) > G.ZERO) {
  float f = f1 / f2;
  BigDecimal d = new BigDecimal(String.format("%.4f", f));
  return d;
 }
 return new BigDecimal(0);
}

public static BigDecimal get_no_tax_price(BigDecimal price, BigDecimal vat) {
 float f1 = G.is_zero(price) ? 0f : price.floatValue();
 float f2 = G.is_zero(vat) ? 0f : vat.floatValue();
 return new BigDecimal(f1 / (1 + f2));
}

public static BigDecimal decimal_no_tax_value(BigDecimal v1, BigDecimal vat) {
 float f1 = G.is_zero(v1) ? 0f : v1.floatValue();
 float f2 = G.is_zero(vat) ? 0f : vat.floatValue();
 return new BigDecimal(f1 / (1 + f2));
}

/**
 * 返回定长的串,长度不够时,用c在前边填充
 *
 * @param code
 * @param c
 * @param len
 * @return
 */
public static String fillChar(String code, char c, int len) {
 if (code == null)
  code = "";
 String s = "";
 for (int i = 0; i < len - code.length(); i++) {
  s += c;
 }
 return s + code;
}

/**
 * 根据输入字符串长度转换生鲜码
 * TODO
 *
 * @param string
 * @return String
 * @throws
 */
public static String doSearchString(String string) {
 if (string.length() >= 8) {
  return string;
 } else {
  while (string.length() < 6) {
   string = "0" + string;
  }
  string = "2" + string + "000000";
  return string;
 }
}

public static BigDecimal decimalMap(Map<String, Object> map,
                                    String key) {
 return getDecimal_of_map(map, key);
}

/**
 * 从map中获取decimal,未完善
 *
 * @param map
 * @param key
 * @return
 */
public static BigDecimal getDecimal_of_map(Map<String, Object> map,
                                           String key) {
 if (map == null) {
  return new BigDecimal(0);
 }
 // ----------------------------------------------------
 Object obj = map.get(key);
 if (obj instanceof BigDecimal) {
  return (BigDecimal) obj;
 }
 // -------待扩充的功能,-------no-complete
 return new BigDecimal(0);
}

public static BigDecimal decimal(BigDecimal d) {
 if (d == null) {
  return new BigDecimal(0);
 }
 ;
 return d;
}

public static BigDecimal decimal_add(BigDecimal... lst) {
 if (lst == null) {
  return new BigDecimal(0);
 }
 float f = 0;
 for (BigDecimal d : lst) {
  if (d == null) {
   continue;
  }
  f += d.floatValue();
 }
 return new BigDecimal(f);
}

public static BigDecimal decimal_dec(BigDecimal a_left, BigDecimal b_right) {
 BigDecimal a = a_left;
 BigDecimal b = b_right;
 if (G.isEmpty(a)) {
  a = new BigDecimal(0);
 }
 if (G.isEmpty(b)) {
  b = new BigDecimal(0);
 }
 // ----------------------------------------------------
 return new BigDecimal(a.floatValue() - b.floatValue());
}

public static BigDecimal d_dec(BigDecimal a_left, BigDecimal b_right) {
 return decimal_dec(a_left, b_right);
}

public static Integer get_Integer_of_map(Map<String, Object> map,
                                         String key) {
 if (map == null) {
  return new Integer(0);
 }
 // ----------------------------------------------------
 Object obj = map.get(key);
 if (obj instanceof Integer) {
  return (Integer) obj;
 }
 if (obj instanceof Long) {
  return ((Long) obj).intValue();
 }
 try {
  return Integer.parseInt(obj.toString());
 } catch (Exception ee) {
  return new Integer(0);
 }
}

/**
 * 是负值
 *
 * @param d
 * @return
 */
public static boolean decimal_is_negative(BigDecimal d) {
 if (d == null) {
  return false;
 }
 float f = d.floatValue();
 return f < 0;
}

/**
 * 是正值
 *
 * @param d
 * @return
 */
public static boolean decimal_is_positive(BigDecimal d) {
 if (d == null) {
  return false;
 }
 float f = d.floatValue();
 return f > 0;
}

public static boolean decimal_equal(BigDecimal a, BigDecimal b) {
 float a1 = G.decimal_float_value(a);
 float a2 = G.decimal_float_value(b);
 return Math.abs(a1 - a2) < G.ZERO;
}

public static float f(BigDecimal d) {
 return G.decimal_float_value(d);
}

public static float f(Integer i) {
 if (i == null) {
  return 0f;
 }
 return i.intValue() + 0f;
}

public static double d(BigDecimal d) {
 return G.decimal_double_value(d);
}

public static float dAdd(BigDecimal... lst) {
 return G.decimal_add(lst).floatValue();
}

public static float dX(BigDecimal a, BigDecimal... lst) {
 return G.decimal_x(a, lst).floatValue();
}

public static float dDiv(BigDecimal d1, BigDecimal d2) {
 return G.decimal_div(d1, d2).floatValue();
}

public static float dDec(BigDecimal d1, BigDecimal d2) {
 return G.decimal_dec(d1, d2).floatValue();
}

public static String timeStamp_str() {
 Date dt = G.DateUtils.current_date();
 SimpleDateFormat obj = new SimpleDateFormat("yyyyMMdd_hh_mm_ss");
 String s = obj.format(dt);
 return s;
}

public static String defStr(String s, String default_str) {
 if (G.isEmpty(s)) {
  return default_str;
 }
 return s;
}

public static boolean zeroAll(BigDecimal... lst) {
 return G.isZero_all(lst);
}

static {
 TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
 // Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
}

public static boolean zero(BigDecimal d, int point_count) {
 if (point_count < 1) {
  point_count = 2;
 }
 //
 float z = 0.1f * point_count;
 //
 if (d == null) {
  return true;
 }
 if (Math.abs(d.floatValue()) < z) {
  return true;
 }
 return false;
}

public static BigDecimal no_tax(BigDecimal v1, BigDecimal vat) {
 return decimal_no_tax_value(v1, vat);
}

public static BigDecimal div(BigDecimal a, BigDecimal b) {
 return decimal_div(a, b);
}

public static void zero_throw(long l, String s) {
 assert_zero_throw(l, s);
}

public static void emptyThrow(String info, Object... lst) {
 assert_empty_throw(info, lst);
}

/**
 * 对象列表有空值时，抛异常
 **/
public static void eta(String info, Object... lst) {
 empty_throw_all(info, lst);
}

// 对象列表有空值时，抛异常
public static void zeroThrow(long k, String info) {
 zero_throw(k, info);
}

// 0，抛异常
public static void zt(long k) {
 zeroThrow(k);
}

public static void zt(long k, String info) {
 zeroThrow(k, info);
}

public static void zeroThrow(long k) {
 zero_throw(k, "");
}

public static long str2long(String s) {
 if (G.isEmpty(s))
  return 1L;
 try {
  long l = Long.parseLong(s);
  return l;
 } catch (Exception ee) {
 }
 return 0L;
}

//public static BigDecimal x(BigDecimal a, BigDecimal b) {
// // TODO Auto-generated method stub
// return decimal_x(a, b);
//}

public static BigDecimal x(BigDecimal... lst) {
 if (lst == null || lst.length == 9) {
  return new BigDecimal(0);
 }

 BigDecimal d = lst[0];
 //
 for (int i = 1; i < lst.length; i++) {
  d = decimal_x(d, lst[i]);
 }
 return d;
}

public static void zero_throw(long l) {
 // TODO Auto-generated method stub
 zeroThrow(l);
}

public static boolean zeroOr(BigDecimal... lst) {
 return G.isZero_or(lst);
}

public static boolean assert_mkdir(String path) {
 File file = new File(path);
 //
 if (!file.exists()) {
  try {
   file.mkdirs();
  } catch (Exception ee) {
   ee.printStackTrace();
   return false;
  }
  return true;
 }

 return false;
}

}