package pl.malek.freelancebackend.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String formatDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern(FORMAT));
    }

}
