package ru.vtb.learning.lesson5;

public class Logger {
    public static void Log(String message){
        System.out.println(System.currentTimeMillis() + " - " +  message);
    }
}
