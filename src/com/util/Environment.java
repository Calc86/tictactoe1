package com.util;

/**
 * Created by calc on 24.03.14.
 */
public class Environment {
    private Environment() {
    }

    public static String getOsName(){
        return System.getProperty("os.name").toLowerCase();
    }

    public static boolean isMac(){
        return getOsName().contains("mac");
    }

    public static boolean isWin(){
        return getOsName().contains("win");
    }

    public static boolean isUnix(){
        return (getOsName().contains("nix") || getOsName().contains("nux"));
    }

    public static String getOsVersion(){
        return System.getProperty("os.version");
    }
}
