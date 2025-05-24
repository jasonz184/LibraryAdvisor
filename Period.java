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

    public String toString()
    {
        return period + start + end;
    }

    public int getPeriod()
    {
        return period;
    }

    public void timeLeft(String time)
    {
        left = time;
    }

    public String getTimeLeft()
    {
        return left;
    }

}