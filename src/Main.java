import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        Library l = new Library();
        ArrayList<Period> periods = new ArrayList<>();
        periods.add(new Period(1, "08:05", "08:46"));
        periods.add(new Period(2, "08:50", "09:31"));
        periods.add(new Period(3, "09:35", "10:19"));
        periods.add(new Period(4, "10:23", "11:04"));
        periods.add(new Period(5, "11:08", "11:49"));
        periods.add(new Period(6, "11:53", "12:34"));
        periods.add(new Period(7, "12:38", "01:19"));
        periods.add(new Period(8, "01:23", "02:04"));
        periods.add(new Period(9, "02:08", "02:49"));
        periods.add(new Period(10, "02:53", "03:34"));
        l.setPeriods(periods);
    }
}