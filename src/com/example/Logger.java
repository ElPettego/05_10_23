package com.example;

import java.time.LocalDateTime;

public class Logger {
    public final static String redColor      = "\u001B[31m";
    public final static String greenColor    = "\u001B[32m";
    public final static String reset         = "\u001B[0m";
    public final static String yellowColor   = "\u001B[33m";
    public final static String magentaColor  = "\u001B[35m";
    public final static String boldText      = "\u001B[1m";
    public final static String lampText      = "\u001B[6m";
    public final static String underlineText = "\u001B[4m";

    public static String baseLog() {
        return "[" + magentaColor + LocalDateTime.now() + reset + "]";
    }
    public static void logInfo(String message) {
        System.out.println(baseLog() + " [" + greenColor + "INFO" + reset + "] " + message);
    }

    public static void logError(String message) {
         System.out.println(baseLog() + " [" + redColor + "ERRR" + reset + "] " + message);
    }
}
