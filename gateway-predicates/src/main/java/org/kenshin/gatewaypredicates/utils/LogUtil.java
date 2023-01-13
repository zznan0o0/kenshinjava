package org.kenshin.gatewaypredicates.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@Slf4j
public class LogUtil {
    public static void info(String msg){
        log.info(msg);
    }

    public static void debug(String msg){
        log.debug(msg);
    }

    public static void debug (Throwable throwable){
        log.debug(getTraceStr(throwable));
    }

    public static void error (String msg){
        log.error(msg);
    }

    public static void error (Throwable throwable){
        log.error(getTraceStr(throwable));
    }

    public static void trace(Throwable throwable){
        log.trace(getTraceStr(throwable));
    }

    public static String getTraceStr(Throwable throwable){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        throwable.printStackTrace(new PrintStream(baos));
        return baos.toString();
    }
}
