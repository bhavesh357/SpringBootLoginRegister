package com.bl.loginregister.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserTime {

    public static String getTimeStamp(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return dtf.format(LocalDateTime.now());
    }
}
