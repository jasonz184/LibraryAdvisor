import java.util.Scanner;

public class Main
{
    private static String period;
    private static String room;
    private static String timeleft;
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        System.out.println("What period are you planning to go to the library?");
        period = s.nextLine();
        System.out.println(vaildPeriod(period));
        while(!vaildPeriod(period))
        {
            System.out.println("Enter a vaild period.");
            period = s.nextLine();
            System.out.println(vaildPeriod(period));
        }
        if(period.compareTo("3") == 0)
        {
            System.out.println("The library is always closed.");
            System.exit(0);
        }

        System.out.println("What time are you leaving?");

        System.out.println("What room are you coming from? Enter in the following format with no spaces: FloorSideNumber");
        room = s.nextLine();
        System.out.println(validRoom(room));
        while(!validRoom(room))
        {
            System.out.println("Must be a vaild room. (FloorSideNumber)");
            room = s.nextLine();
            System.out.println(validRoom(room));
        }
        s.close();
    }

    public static boolean vaildPeriod(String period)
    {
        int temp;
        try {
            temp = Integer.parseInt(period);
        } catch (Exception e) {
            return false;
        }
        return temp >= 1 && temp <= 10;
    }

    /*public static boolean vaildTime(String time)
    {

    }*/

    public static boolean validRoom(String room) //need to think about center stuff, maybe
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
            System.out.println("bad");
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
        return floor >= 0 && floor <= 9 && vaildSide && vaildNum;
    }
}