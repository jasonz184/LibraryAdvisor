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
        ValidChecks check = new ValidChecks();
        ArrayList<Period> periods = new ArrayList<Period>();
        periods.add(new Period(1, "8:05", "8:46"));
        periods.add(new Period(2, "8:50", "9:31"));
        periods.add(new Period(3, "9:35", "10:19"));
        periods.add(new Period(4, "10:23", "11:04"));
        periods.add(new Period(5, "11:08", "11:49"));
        periods.add(new Period(6, "11:53", "12:34"));
        periods.add(new Period(7, "12:38", "1:19"));
        periods.add(new Period(8, "1:23", "2:04"));
        periods.add(new Period(9, "2:08", "2:49"));
        periods.add(new Period(10, "2:53", "3:34"));

        System.out.println("What period are you planning to go to the library? Periods 1 - 10");
        period = s.nextLine();
        while(!check.vaildPeriod(period))
        {
            System.out.println("Enter a vaild period. (1 - 10)");
            period = s.nextLine();
        }
        if(period.compareTo("3") == 0)
        {
            System.out.println("The library is always closed.");
            System.exit(0);
        }
        Period p = new Period(check.getPeriod());       //Makes a period class with a vaild period number
        
        System.out.println("What time are you leaving? Enter in the following format with no spaces: HH:MM");
        timeLeft = s.nextLine();
        while(!check.vaildTime(timeLeft))
        {
            System.out.println("Enter a valid time. (HH:MM)");
            timeLeft = s.nextLine();
        }
        p.timeLeft(timeLeft);       //Set the time to a vaild time
        if(check.getHour() <= 12)   //Non military time input prompts an am or pm check
        {
            System.out.println("AM or PM");
            String input = s.nextLine();
            while(!(input.equalsIgnoreCase("am")) && !(input.equalsIgnoreCase("pm")))
            {
                System.out.println("Enter AM or PM");
                input = s.nextLine();
            }
        }

        System.out.println("What room are you coming from? Enter in the following format with no spaces: FloorSideNumber");
        room = s.nextLine();
        while(!check.validRoom(room))
        {
            System.out.println("Must be a vaild room. (FloorSideNumber)");
            room = s.nextLine();
        }
        s.close();
    }

}