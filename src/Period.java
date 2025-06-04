public class Period
{
    private int period;
    private String start;
    private String end;

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

    public boolean validPeriod(String p)
    {
        int num;
        try {
            num = Integer.parseInt(p);
        } catch (Exception e) {
            return false;
        }
        period = num;
        return num >= 1 && num <= 10;
    }

    public String toString()
    {
        if(period > 9) return period + "      " + start + "      " + end;
        return period + "       " + start + "      " + end;
    }

    public void setPeriod(int p)
    {
        period = p;
    }

    public int getPeriod()
    {
        return period;
    }

    public String getStart()
    {
        return start;
    }

    public String getEnd()
    {
        return end;
    }
}