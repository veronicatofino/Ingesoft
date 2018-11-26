package com.javeriana.sdp.utils;

/**
 * Created by Sebastian on 26/11/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
public class StringUtils {

    public static final String removeImages(String content) {
        int startIndex = content.indexOf("<img");
        while (startIndex != -1) {
            int endIndex = content.indexOf("/img>");
            content = content.substring(0, startIndex) + content.substring(endIndex + 5);
            startIndex = content.indexOf("<img");
        }
        return content;
    }
}
