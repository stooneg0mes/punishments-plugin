package net.stonegomes.trial.bukkit.util;

import java.util.concurrent.TimeUnit;

public class TimeFormatter {

    public static String formatTime(long time, boolean compareDifference) {
        return formatTimeToString(false, time, compareDifference);
    }

    public static String formatTime(long time) {
        return formatTime(time, false);
    }

    public static String formatTimeMinimalist(long time, boolean compareDifference) {
        return formatTimeToString(true, time, compareDifference);
    }

    public static String formatTimeMinimalist(long time) {
        return formatTimeMinimalist(time, false);
    }

    private static String formatTimeToString(boolean minimalist, long time, boolean compareDifference) {
        final long finalTime = (compareDifference ? time - System.currentTimeMillis() : time);

        final long day = TimeUnit.MILLISECONDS.toDays(finalTime);
        final long hours = TimeUnit.MILLISECONDS.toHours(finalTime) - day * 24L;
        final long minutes = TimeUnit.MILLISECONDS.toMinutes(finalTime) - TimeUnit.MILLISECONDS.toHours(finalTime) * 60L;
        final long seconds = TimeUnit.MILLISECONDS.toSeconds(finalTime) - TimeUnit.MILLISECONDS.toMinutes(finalTime) * 60L;

        StringBuilder stringBuilder = new StringBuilder();
        if (minimalist) {
            if (finalTime <= 0) return "0s";

            if (day > 0L) stringBuilder.append(day).append("d");
            if (hours > 0L) stringBuilder.append(hours).append("h");
            if (minutes > 0L) stringBuilder.append(minutes).append("m");
            if (seconds > 0L) stringBuilder.append(seconds).append("s");
        } else {
            if (finalTime <= 0) return "0 seconds";

            if (day > 0L) stringBuilder.append(day).append(" ").append("days");
            if (hours > 0L) stringBuilder.append(" ").append(hours).append(" ").append("hours");
            if (minutes > 0L) stringBuilder.append(" ").append(minutes).append(" ").append("minutes");
            if (seconds > 0L) stringBuilder.append(" ").append(seconds).append(" ").append("seconds");
        }

        return stringBuilder.toString();
    }

}