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
        while(!checkVaildInput(room))
        {
            System.out.println("Enter the room in the following format with no spaces: Floor-Side-Number");
            room = s.nextLine();
        }
        s.close();
    }

    public static boolean checkVaildInput(String input)
    {
        if(!(input.length() == 3) && !(input.length() == 4)) return false;
        return true;
    }
}