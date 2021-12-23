package com.mycomp.util;

import org.apache.commons.lang3.StringUtils;

public class StringExtractor {
    private static final String OPEN_BRK="(";
    private static final String CLOSED_BRK=")";

    public static String getMethodParameter(String expr, String methodName){
        String methodParam = StringUtils.substringAfter(expr,methodName);
        methodParam = StringUtils.substringBetween(methodParam,OPEN_BRK,CLOSED_BRK);
        return methodParam;
    }
}
