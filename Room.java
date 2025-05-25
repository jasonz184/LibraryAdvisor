public class Room
{
    private int floor;
    private String side;
    private int number;

    public Room(int f, String s, int n)
    {
        floor = f;
        side = s;
        number = n;
    }

    public boolean validRoom(String r) //need to think about center stuff, maybe
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

    public void setFloor(int f)
    {
        floor = f;
    }

    public int getFloor()
    {
        return floor;
    }

    public void setSide(String s)
    {
        side = s;
    }

    public String getSide()
    {
        return side;
    }
    
    public void setNumber(int n)
    {
        number = n;
    }

    public int getNumber()
    {
        return number;
    }
}