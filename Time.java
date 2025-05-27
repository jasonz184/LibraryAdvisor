import java.util.ArrayList;

public class Time
{
    private int hour;
    private int minute; 

    public Time(int h, int m)
    {
        hour = h;
        minute = m;
    }

    public boolean validTime(String t)
    {
        if(!(t.length() == 5)) return false;
        if(!(t.charAt(2) == ':')) return false;
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

    public boolean reasonableLeaveTime(ArrayList<Period> periods, int h, int m, int period)
    {
        int leaveMins = h * 60 + m;
        int earliest;
        int latest;
        if(period == 1)
        {
            earliest = 450;         //7:30 in minutes
            latest = 490;           //8:10 in minutes
        }
        else
        {
            Period p = periods.get(period - 2);
            validTime(p.getEnd());
            earliest = hour * 60 + minute - 5;        //5 minutes before previous period ends
            if(p.getPeriod() > 6 && hour < 12) earliest += 12 * 60;
            Period p2 = periods.get(period - 1);
            validTime(p2.getStart());
            latest = hour * 60 + minute + 5;          //5 minutes after current period starts (chance of getting in is low to none)
            if(p2.getPeriod() > 6 && hour < 12) latest += 12 * 60;
        }
        if(leaveMins < earliest)
        {
            if(leaveMins < 450) System.out.println("School isn't even open yet...");
            else System.out.println("Leaving that early is a bit suspicious...");
        }
        if(leaveMins > latest && leaveMins > 1050) System.out.println("School's already closed...");
        else if(leaveMins > latest) System.out.println("Leaving way too late. There's no way your getting in...");
        return leaveMins >= earliest && leaveMins <= latest;
    }

    public void setHour(int h)
    {
        hour = h;
    }

    public int getHour()
    {
        return hour;
    }

    public int getMinute()
    {
        return minute;
    }
}