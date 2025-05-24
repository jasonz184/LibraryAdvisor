public class ValidChecks 
{
    private int period;
    private int hour;
    private String room;

    public boolean vaildPeriod(String period)
    {
        int num;
        try {
            num = Integer.parseInt(period);
        } catch (Exception e) {
            return false;
        }
        this.period = num;
        return num >= 1 && num <= 10;
    }
    
    public boolean vaildTime(String time)
    {
        if(!(time.length() == 5)) return false;
        String first = time.substring(0, 2);
        String second = time.substring(3);
        int hour;
        int minute;
        try {
            hour = Integer.parseInt(first);
            minute = Integer.parseInt(second);
        } catch (Exception e) {
            return false;
        }
        this.hour = hour;
        return (hour >= 0 && hour <= 24) && (minute >= 0 && minute <= 59);
    }

    public boolean validRoom(String room) //need to think about center stuff, maybe
    {
        if(!(room.length() == 3) && !(room.length() == 4)) return false;
        String first = room.substring(0, 1); //7th floor (lunchroom)  defined using standard notation for easy calculation
        String second = room.substring(1, 2);
        String end = room.substring(2);
        int floor;
        int num;
        boolean vaildSide = false;
        boolean vaildNum = false;
        if((first.equalsIgnoreCase("b")))
        {
            
            first = "0";
        }
        try {
            floor = Integer.parseInt(first);
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
        this.room = floor + second + num;
        return floor >= 0 && floor <= 9 && vaildSide && vaildNum;
    }

    public int getPeriod()
    {
        return period;
    }

    public int getHour()
    {
        return hour;
    }

    public String getRoom()
    {
        return room;
    }
}
