package com.cn.oa.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {

    private DateFormatUtils(){

    }

    public static String dateFormat(){
        Date now = new Date(); // 创建一个Date对象，获取当前时间
        // 指定格式化格式
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        return f.format(now);
    }


}
