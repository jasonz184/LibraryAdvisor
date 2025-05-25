public class ValidChecks 
{
    private static int period;
    private static int hour;
    private static int minute;
    private static int floor;
    private static String side;
    private static int number;

    public static boolean vaildPeriod(String p)
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
    
    public static boolean vaildTime(String t)
    {
        if(!(t.length() == 5)) return false;
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

    public static boolean validRoom(String r) //need to think about center stuff, maybe
    {
        if(!(r.length() == 3) && !(r.length() == 4)) return false;
        String first = r.substring(0, 1); //7th floor (lunchroom)  defined using standard notation for easy calculation
        String second = r.substring(1, 2);
        String end = r.substring(2);
        int fl;
        int num;
        boolean vaildSide = false;
        boolean vaildNum = false;
        if((first.equalsIgnoreCase("b")))
        {
            
            first = "0";
        }
        try {
            fl = Integer.parseInt(first);
            num = Integer.parseInt(end);
        } catch (Exception e) {
            return false;
        }
        if(second.equalsIgnoreCase("n"))
        {
            vaildSide = true;
            vaildNum = num >= 1 && num <= 8;
        }
        else if(second.equalsIgnoreCase("e") || second.equalsIgnoreCase("w"))
        {
            vaildSide = true;
            vaildNum = num >= 1 && num <= 26;
        }
        else if(second.equalsIgnoreCase("s"))
        {
            vaildSide = true;
            vaildNum = num >= 1 && num <= 13;
        }
        floor = fl;
        side = second;
        number = num; 
        return fl >= 0 && fl <= 9 && vaildSide && vaildNum;
    }

    public static int getPeriod()
    {
        return period;
    }

    public static void setHour(int h)
    {
        hour = h;
    }

    public static int getHour()
    {
        return hour;
    }

    public static int getMinute()
    {
        return minute;
    }

    public static int getFloor()
    {
        return floor;
    }

    public static String getSide()
    {
        return side;
    }
    
    public static int getNumber()
    {
        return number;
    }
}