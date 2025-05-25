import java.util.Scanner;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        String period;
        String room;
        String timeLeft;
        Scanner s = new Scanner(System.in);
        ArrayList<Period> periods = new ArrayList<Period>();
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

        System.out.println("What period are you planning to go to the library? Periods 1 - 10");
        period = s.nextLine();
        while(!ValidChecks.vaildPeriod(period))
        {
            System.out.println("Enter a vaild period. (1 - 10)");
            period = s.nextLine();
        }
        if(period.compareTo("3") == 0)
        {
            System.out.println("The library is always closed.");
            System.exit(0);
        }
        Period p = new Period(ValidChecks.getPeriod());       //Makes a period class with a vaild period number
        

        System.out.println("What time are you leaving? Enter in the following format with no spaces: HH:MM");
        timeLeft = s.nextLine();
        while(!ValidChecks.vaildTime(timeLeft))
        {
            System.out.println("Enter a valid time. (HH:MM)");
            timeLeft = s.nextLine();
        }
        p.timeLeft(timeLeft);       //Set the time to a vaild time
        boolean isPm = false;
        if(ValidChecks.getHour() > 0 && ValidChecks.getHour() < 12)   //Am or pm check for input that doesn't look like military time
        {
            System.out.println("AM or PM");
            String input = s.nextLine();
            while(!(input.equalsIgnoreCase("am")) && !(input.equalsIgnoreCase("pm")))
            {
                System.out.println("Enter AM or PM");
                input = s.nextLine();
            }
            if(input.equalsIgnoreCase("pm"))
            {
                isPm = true;
                ValidChecks.setHour(ValidChecks.getHour() + 12);
            }
        }
        if(!p.reasonableLeaveTime(periods, ValidChecks.getHour(), ValidChecks.getMinute(), isPm))
        {
            System.exit(0);
        }


        System.out.println("What room are you coming from? Enter in the following format with no spaces: FloorSideNumber");
        room = s.nextLine();
        while(!ValidChecks.validRoom(room) && !room.equalsIgnoreCase("lunchroom") && !room.equalsIgnoreCase("center"))
        {
            System.out.println("Must be a vaild room. (FloorSideNumber)");
            room = s.nextLine();
        }
        if(room.equalsIgnoreCase("lunchroom"))
        {
            System.out.println("Which side are you on? Enter one of the following: NW, CW, SW, C, NE, CE, SE");
            room = s.nextLine();
            while(!room.equalsIgnoreCase("nw") && !room.equalsIgnoreCase("cw") && !room.equalsIgnoreCase("sw") && !room.equalsIgnoreCase("c") && !room.equalsIgnoreCase("ne") && !room.equalsIgnoreCase("ce") &&!room.equalsIgnoreCase("se"))
            {
                System.out.println("Please enter either NW, CW, SW, C, NE, CE, or SE");
                room = s.nextLine();
            }
        }
        else if(room.equalsIgnoreCase("center"))
        {
            System.out.println("Which floor?");
            
        }



        s.close();
    }

}