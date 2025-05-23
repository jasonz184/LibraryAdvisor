import java.util.Scanner;

public class Main
{
    private static String room;
    private static String timeleft;
    private static int period;
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        System.out.println("What room are you coming from?");
        room = s.nextLine();
        System.out.println(check(room));
        while(!(room.length() == 6) && !(room.length() == 5))
        {
            System.out.println("Enter the room in the following format with no spaces: FloorSideNumber");
            room = s.nextLine();
            System.out.println(check(room));
        }
        s.close();
    }

    public static boolean check(String room)
    {
        String first = room.substring(0, 1);
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
        return floor >= 0 && floor <= 10 && vaildSide && vaildNum;
    }
}