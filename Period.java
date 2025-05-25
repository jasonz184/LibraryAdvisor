import java.util.ArrayList;

public class Period
{
    private int period;
    private String start;
    private String end;
    private String left;

    public Period(int p, String s, String e)
    {
        period = p;
        start = s;
        end = e;
    }
    
    public Period(int p)
    {
        period = p;
    }

    public boolean reasonableLeaveTime(ArrayList<Period> periods, int h, int m, boolean isPm)
    {
        int leaveMins = h * 60 + m;
        int earliest;
        int latest;
        if(period == 1)
        {
            earliest = 450;         //7:30 in minutes
            latest = 490;           //8:10 in minutes
            if(leaveMins < earliest) System.out.println("School isn't even open yet...");
            if(leaveMins > latest && leaveMins > 1050) System.out.println("Schools already closed...");
            else if(leaveMins > latest) System.out.println("Leaving way too late. There's no way your getting in...");
            return leaveMins >= earliest && leaveMins <= latest;
        }
        else
        {
            Period p = periods.get(period - 2);
            ValidChecks.vaildTime(p.getEnd());
            earliest = ValidChecks.getHour() * 60 + ValidChecks.getMinute() - 5;        //5 minutes before previous period ends
            if(ValidChecks.getPeriod() > 6 && ValidChecks.getHour() < 12) earliest += 12 * 60;
            Period p2 = periods.get(period - 1);
            ValidChecks.vaildTime(p2.getStart());
            latest = ValidChecks.getHour() * 60 + ValidChecks.getMinute() + 5;          //5 minutes after current period starts (chance of getting in is low to none)
            if(ValidChecks.getPeriod() > 6 && ValidChecks.getHour() < 12) latest += 12 * 60;
            if(leaveMins < earliest && leaveMins < 450) System.out.println("School isn't even open yet...");
            else if(leaveMins < earliest) System.out.println("Leaving that early is a bit suspicious...");
            if(leaveMins > latest && leaveMins > 1050) System.out.println("Schools already closed...");
            else if(leaveMins > latest) System.out.println("Leaving way too late. There's no way your getting in...");
            return leaveMins >= earliest && leaveMins <= latest;
        } 
    }

    public String toString()
    {
        return period + " " + start + " " + end;
    }

    public void timeLeft(String time)
    {
        left = time;
    }

    public String getStart()
    {
        return start;
    }

    public String getEnd()
    {
        return end;
    }

    public String getTimeLeft()
    {
        return left;
    }

}