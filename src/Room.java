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
        String first = r.substring(0, 1);
        String second = r.substring(1, 2);
        String end = r.substring(2);
        int fl;
        int num;
        boolean validSide = false;
        boolean validNum = false;
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
            validSide = true;
            validNum = num >= 1 && num <= 8;
        }
        else if(second.equalsIgnoreCase("e") || second.equalsIgnoreCase("w"))
        {
            validSide = true;
            validNum = num >= 1 && num <= 26;
        }
        else if(second.equalsIgnoreCase("s"))
        {
            validSide = true;
            validNum = num >= 1 && num <= 13;
        }
        floor = fl;
        side = second;
        number = num;
        return fl >= 0 && fl <= 7 && validSide && validNum;     //only up to floor 7 because client doesn't have classes on 8th or 9th floor and doesn't want
    }

    public int timeToLibraryLine()
    {
        int time = 0;
        int floorDistance = floor - 5;
        if(floorDistance < 0) time += Math.abs(floorDistance) * 20;     //time to go upstairs
        else time += floorDistance * 15;                                //time to go downstairs
        if(floor == 7 && number == 0) time += timeToStairsLunch();
        if(floor == 5 && side.equalsIgnoreCase("e")) time += specialFifthFloorCase();
        else time += timeToStairs(side);
        time += 3;                  //time it takes to walk in line after getting to the stairs E and F
        return time;
    }

    public int timeToStairsLunch()      //average time because there are no room numbers
    {
        int timeToStairs = 0;
        if(side.equalsIgnoreCase("nw") || side.equalsIgnoreCase("sw") || side.equalsIgnoreCase("c"))
        {
            timeToStairs += 45;
        }
        else if(side.equalsIgnoreCase("ne") || side.equalsIgnoreCase("ce") || side.equalsIgnoreCase("se"))
        {
            timeToStairs += 70;
        }
        else if(side.equalsIgnoreCase("cw"))        //stairs E and F closest to the cw area
        {
            timeToStairs += 35;
        }
        return timeToStairs;
    }

    public int timeToStairs(String s)
    {
        int timeToStairs = 0;
        if(s.equalsIgnoreCase("c"))    //xc1 is closest to west
        {
            timeToStairs += (number - 1) * 5;
            timeToStairs += 5;                        //to get to the closest staircase to library (G and H)
        }
        else if(s.equalsIgnoreCase("n"))
        {
            timeToStairs += Math.abs(number - 8) * 5;       //time to get to the west side
            for(int i = 1; i < 18; i++)                     //time to get to staircase
            {
                timeToStairs += 3;
            }
        }
        else if(s.equalsIgnoreCase("s"))
        {
            timeToStairs += (number - 1) * 3;   //almost same time as north but there is higher room number for some reason
            for(int i = 26; i > 18; i--)
            {
                timeToStairs += 3;
            }
        }
        else if(s.equalsIgnoreCase("w"))
        {
            timeToStairs += Math.abs(number - 17) * 3;
        }
        else if(s.equalsIgnoreCase("e") && floor != 5)
        {
            timeToStairs += Math.abs(number - 12) * 3;          //go to xe12, opposite of library entrance
            timeToStairs += (number - 1) * 5;                   //go through center
            timeToStairs += 5;
        }
        return timeToStairs;
    }

    public int specialFifthFloorCase()
    {
        int timeToStairs = 0;
        timeToStairs += (number - 1) * 3;           //traveling along the east side, going towards south
        timeToStairs += timeToStairs("s");      //go all the way to the south side
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
}