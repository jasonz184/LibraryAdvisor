import java.util.ArrayList;

public class Time {
    private int hour;
    private int minute;

    public Time(int h, int m) {
        hour = h;
        minute = m;
    }

    public boolean validTime(String t) {
        if (!(t.length() == 5)) return false;
        if (!(t.charAt(2) == ':')) return false;
        String first = t.substring(0, 2);
        String second = t.substring(3);
        int hours;
        int minutes;
        try {
            hours = Integer.parseInt(first);
            minutes = Integer.parseInt(second);
        } catch (Exception e) {
            return false;
        }
        hour = hours;
        minute = minutes;
        return (hours >= 0 && hours <= 24) && (minutes >= 0 && minutes <= 59);
    }

    public boolean reasonableLeaveTime(ArrayList<Period> periods, int h, int m, int period) {
        int leaveMins = h * 60 + m;
        int[] bounds = leaveTimeBounds(periods, period);
        return leaveMins >= bounds[0] && leaveMins <= bounds[1];
    }

    public String leaveTimeFeedback(ArrayList<Period> periods, int h, int m, int period) {
        int leaveMins = h * 60 + m;
        int[] bounds = leaveTimeBounds(periods, period);
        int earliest = bounds[0];
        int latest = bounds[1];
        if (leaveMins < earliest) {
            if (leaveMins < 450) return "School isn't even open yet...";
            else return "Leaving that early is a bit suspicious...";
        }
        if (leaveMins > latest && leaveMins > 1050) return "School's already closed...";
        else if (leaveMins > latest) return "Leaving too late. There's no way your getting in...";
        return "";
    }

    public int[] leaveTimeBounds(ArrayList<Period> periods, int period) {
        int[] range = new int[2];
        if (period == 1) {
            range[0] = 450;         //7:30 in minutes
            range[1] = 490;           //8:10 in minutes
        } else {
            Period p = periods.get(period - 2);
            int h = Integer.parseInt(p.getEnd().substring(0, 2));
            int m = Integer.parseInt(p.getEnd().substring(3));
            range[0] = h * 60 + m - 5;        //5 minutes before previous period ends
            if (p.getPeriod() > 6 && h < 12) range[0] += 12 * 60;
            Period p2 = periods.get(period - 1);
            int h2 = Integer.parseInt(p2.getStart().substring(0, 2));
            int m2 = Integer.parseInt(p2.getStart().substring(3));
            range[1] = h2 * 60 + m2 + 5;          //5 minutes after current period starts
            if (p2.getPeriod() > 6 && h2 < 12) range[1] += 12 * 60;
        }
        return range;
    }

    public void setHour(int h) {
        hour = h;
    }

    public int getHour() {
        return hour;
    }

    public void setMinute(int m) {
        minute = m;
    }

    public int getMinute() {
        return minute;
    }
}