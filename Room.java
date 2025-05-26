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

    public boolean validRoom(String r) 
    {
        if(!(r.length() == 3) && !(r.length() == 4)) return false;
        String first = r.substring(0, 1); //7th floor (lunchroom)  defined using standard notation for easy calculation
        String second = r.substring(1, 2);
        String end = r.substring(2);
        int fl;
        int num;
        boolean vaildSide = false;
        boolean vaildNum = false;
        if(first.equalsIgnoreCase("b") && second.equalsIgnoreCase("c")) return false;
        if(first.equalsIgnoreCase("b"))
        {
            
            first = "0";
        }
        try {
            fl = Integer.parseInt(first);
            num = Integer.parseInt(end);
        } catch (Exception e) {
            return false;
        }
        if(second.equalsIgnoreCase("n") || second.equalsIgnoreCase("c"))
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

    public int timeToLibraryLine()
    {
        int time = 0;
        time += timeToStairs();
        int floorDistance = floor - 5;
        if(floorDistance < 0) time += Math.abs(floorDistance) * 20;     //time to go up stairs
        else time += floorDistance * 10;                                //time to go down stairs
        if(!validRoom(7 + side + 1))            //if is lunchroom
        {
            time += timeToStairsLunch();
            //time += 4;                          //to get to line after walking stairs
            return time;
        }
        //time += 4;                  //time it takes to walk in line after getting to the stairs E and F
        return time;
    }

    public int timeToStairsLunch()
    {
        int timeToStairs = 0;
        if(side.equalsIgnoreCase("nw") || side.equalsIgnoreCase("sw") || side.equalsIgnoreCase("c"))
        {
            timeToStairs += 15;   
        }
        else if(side.equalsIgnoreCase("ne") || side.equalsIgnoreCase("ce") || side.equalsIgnoreCase("se"))
        {
            timeToStairs += 30;
        }
        else if(side.equalsIgnoreCase("cw"))
        {
            timeToStairs += 7;
        }
        return timeToStairs;
    }

    public int timeToStairs()
    {
        int timeToStairs = 0;
        if(side.equalsIgnoreCase("c"))    //xc1 is closest to west     
        {
            timeToStairs += (number - 1) * 5;         //3 seconds to travel the length of a room??
            timeToStairs += 5;                        //to get to closest staircase to library (E and F)
        }
        else if(side.equalsIgnoreCase("n"))
        {
            timeToStairs += Math.abs(number - 8) * 5;       //time to get to the west side
            for(int i = 1; i < 18; i++)                     //time to get to staircase
            {
                timeToStairs += 3;
            }
        }
        else if(side.equalsIgnoreCase("s"))
        {
            timeToStairs += (number - 1) * 2;   //almost same time as north but there is higher room number for some reason
            for(int i = 26; i > 18; i--)                     
            {
                timeToStairs += 3;
            }
        }
        else if(side.equalsIgnoreCase("w"))
        {
            timeToStairs += Math.abs(number - 17) * 3;
        }
        else if(side.equalsIgnoreCase("e") && floor != 5)
        {
            timeToStairs += Math.abs(number - 12) * 3;          //go to xe12, opposite of library entrance
            timeToStairs += (number - 1) * 5;                   //go through center
            timeToStairs += 5;
        }
        return timeToStairs;
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