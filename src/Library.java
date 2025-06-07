import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Library implements ActionListener
{
    private int inputStage = 0;
    private ArrayList<Period> periodList = new ArrayList<>();
    private final ArrayList<JLabel> periodLabels = new ArrayList<>();
    Period p = new Period(0);
    Time t = new Time(0, 0);
    Room r = new Room(0, null, 0);
    int amountClose = (int)(Math.random() * 3) + 1;
    int[] numbers = new int[amountClose + 1];
    ArrayList<Integer> close = new ArrayList<>();
    private final Timer updateTimer;
    int currentPeople = 0;
    JFrame frame = new JFrame();
    JLabel heading = new JLabel("Welcome to Library Advisor");
    JLabel prompt = new JLabel("Click the button below to begin");
    JLabel closed = new JLabel();
    JLabel periodHeading = new JLabel("Period  Start      End");
    JLabel question = new JLabel("What period are you planning to go to the library?");
    JLabel extraLine = new JLabel();
    JLabel imageHolder = new JLabel();
    JLabel peopleInRoom = new JLabel();
    JLabel feedback = new JLabel();
    JTextField input = new JTextField();
    JButton start = new JButton("Start");
    JButton home = new JButton("Home");
    ImageIcon map = new ImageIcon("src/BTHS Map.jpg");

    public Library()
    {
        updateTimer = new Timer(1000, this);
        updateTimer.start();

        heading.setFont(new Font("Consolas", Font.BOLD, 50));
        heading.setBounds(400, 200, 1080, 100);

        prompt.setFont(new Font("Consolas", Font.PLAIN, 30));
        prompt.setBounds(490, 260, 1080, 100);

        for(int i = 0; i < numbers.length - 1; i++)
        {
            numbers[i] = (int)(Math.random() * 10) + 1;
            if(numbers[i] == 3)
            {
                numbers[i] = 0;
                i--;
            }
        }
        numbers[numbers.length - 1] = 3;
        insertionSort(numbers);
        for(int i : numbers) close.add(i);
        remDupes(close);
        String s = close.toString();
        closed.setFont(new Font("Consolas", Font.PLAIN, 30));
        closed.setBounds(490, 500, 1080, 100);
        closed.setText("Periods closed: " + s.substring(1, s.length() - 1));

        start.setFocusable(false);
        start.setFont(new Font("Consolas", Font.PLAIN, 30));
        start.setBounds(650, 380, 200, 100);
        start.addActionListener(this);

        home.setFocusable(false);
        home.setVisible(false);
        home.setFont(new Font("Consolas", Font.PLAIN, 35));
        home.setBounds(650, 450, 200, 100);
        home.addActionListener(this);

        input.setVisible(false);
        input.setFont(new Font("Consolas", Font.PLAIN, 50));
        input.setBounds(300, 670, 1000, 100);
        input.addActionListener(this);

        periodHeading.setVisible(false);
        periodHeading.setFont(new Font("Consolas", Font.PLAIN, 30));
        periodHeading.setBounds(500,0,1080,100);


        question.setVisible(false);
        question.setFont(new Font("Consolas", Font.PLAIN, 35));
        question.setBounds(300, 570, 1000, 100);

        imageHolder.setVisible(false);
        imageHolder.setIcon(map);
        imageHolder.setBounds(400, 100, 650, 400);

        peopleInRoom.setVisible(false);
        peopleInRoom.setFont(new Font("Consolas", Font.PLAIN, 35));
        peopleInRoom.setBounds(450, 400, 1600, 100);

        feedback.setVisible(false);
        feedback.setFont(new Font("Consolas", Font.PLAIN, 35));
        feedback.setBounds(450, 300, 1600, 100);

        frame.add(feedback);
        frame.add(peopleInRoom);
        frame.add(closed);
        frame.add(imageHolder);
        frame.add(extraLine);
        frame.add(question);
        frame.add(periodHeading);
        frame.add(heading);
        frame.add(prompt);
        frame.add(input);
        frame.add(start);
        frame.add(home);
        frame.setTitle("LibraryAdvisor");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1650, 1080);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == start)
        {
            question.setVisible(true);
            heading.setVisible(false);
            prompt.setVisible(false);
            start.setVisible(false);
            input.setVisible(true);
            closed.setVisible(false);
            periodHeading.setVisible(true);
            showPeriods(periodList);
            if(inputStage == -1)
            {
                question.setText("What period are you planning to go to the library?");
                question.setBounds(300, 570, 1000, 100);
                input.setBounds(300, 670, 1000, 100);
            }
            inputStage = 0;
        }
        if(e.getSource() == home)
        {
            question.setVisible(false);
            extraLine.setVisible(false);
            heading.setVisible(true);
            prompt.setVisible(true);
            start.setVisible(true);
            input.setVisible(false);
            home.setVisible(false);
            imageHolder.setVisible(false);
            closed.setVisible(true);
        }
        if (e.getSource() == updateTimer) {
            currentPeople += (int)(Math.random() * 3) + 1;
            if(currentPeople >= 100) currentPeople = 100;
            peopleInRoom.setText("People in the library: " + currentPeople + "/100");
            if(currentPeople <= 25)
            {
                feedback.setText("Chances are pretty high");
            }
            else if(currentPeople <= 50)
            {
                feedback.setText("Chances are moderately high");
            }
            else if(currentPeople <= 75)
            {
                feedback.setText("Chances are relatively low");
            }
            else
            {
                feedback.setText("Chances are low to none");
            }
        }
        if(e.getSource() == input)
        {
            String text = input.getText();
            if(inputStage == 0)
            {
                if(!p.validPeriod(text))
                {
                    question.setText("Enter a valid period.");
                    question.setFont(new Font("Consolas", Font.PLAIN, 40));
                    question.setBounds(500, 570, 500, 100);
                    input.setBounds(500, 670, 500, 100);
                }
                else
                {
                    p.setPeriod(Integer.parseInt(text));
                    question.setText("What time are you leaving? Enter in the following format: HH:MM");
                    question.setFont(new Font("Consolas", Font.PLAIN, 30));
                    question.setBounds(250, 570, 1100, 100);
                    input.setBounds(250, 670, 1100, 100);
                    inputStage = 1;
                }
                if(close.contains(Integer.parseInt(text)))
                {
                    backToHome("The library is closed.", 525, 350);
                }
            }
            else if(inputStage == 1)
            {
                if(!t.validTime(text))
                {
                    question.setText("Enter a valid time. (HH:MM)");
                    question.setFont(new Font("Consolas", Font.PLAIN, 30));
                    question.setBounds(500, 570, 500, 100);
                    input.setBounds(500, 670, 500, 100);
                }
                else
                {
                    t.setHour(Integer.parseInt(text.substring(0, 2)));
                    t.setMinute(Integer.parseInt(text.substring(3)));
                    if(t.getHour() > 0 && t.getHour() < 12)
                    {
                        inputStage = 2;
                        question.setText("Is that AM or PM?");
                        input.setText("");
                        question.setBounds(525, 570, 400, 100);
                        question.setFont(new Font("Consolas", Font.PLAIN, 40));
                        input.setBounds(525, 670, 390, 100);
                        return;
                    }
                    else
                    {
                        if(!t.reasonableLeaveTime(periodList, t.getHour(), t.getMinute(), p.getPeriod()))
                        {
                            reasonableTimeCheck();
                        }
                        else
                        {
                            moveToRoomCheck();
                        }
                    }
                    if(t.getHour() == 0)
                    {
                        backToHome("School isn't even open yet...", 475, 350);
                    }
                }
            }
            else if(inputStage == 2)
            {
                if(text.equalsIgnoreCase("am") || text.equalsIgnoreCase("pm"))
                {
                    if(text.equalsIgnoreCase("pm"))
                    {
                        t.setHour(t.getHour() + 12);
                    }
                }
                else
                {
                    question.setText("Enter AM or PM");
                    input.setText("");
                    return;
                }
                if(!t.reasonableLeaveTime(periodList, t.getHour(), t.getMinute(), p.getPeriod()))
                {
                    reasonableTimeCheck();
                }
                else
                {
                    moveToRoomCheck();
                }

            }
            else if(inputStage == 3)
            {
                extraLine.setVisible(false);
                if(!r.validRoom(text) && !text.equalsIgnoreCase("lunchroom"))
                {
                    question.setFont(new Font("Consolas", Font.PLAIN, 35));
                    question.setText("Must be a valid room. (FloorSideNumber)");
                    question.setBounds(400, 570, 800, 100);
                    input.setBounds(400, 670, 780, 100);
                }
                else
                {
                    if(text.equalsIgnoreCase("lunchroom"))
                    {
                        extraLine.setVisible(true);
                        question.setText("Which side are you on? Enter one of the following:");
                        extraLine.setText("NW, CW, SW, C, NE, CE, SE");
                        question.setFont(new Font("Consolas", Font.PLAIN, 30));
                        extraLine.setFont(new Font("Consolas", Font.PLAIN, 30));
                        question.setBounds(300, 560, 1100, 100);
                        extraLine.setBounds(475, 600, 1100, 100);
                        input.setBounds(300, 670, 850, 100);
                        input.setText("");
                        inputStage = 4;
                        return;
                    }
                    else
                    {
                        showEstimatedTime();
                    }
                }
                if(r.getFloor() == 5 && r.getSide().equalsIgnoreCase("c"))
                {
                    backToHome("You are already in the library...", 450, 350);
                }
            }
            else if(inputStage == 4)
            {
                extraLine.setVisible(false);
                if(!text.equalsIgnoreCase("nw") && !text.equalsIgnoreCase("cw") && !text.equalsIgnoreCase("sw") && !text.equalsIgnoreCase("c") && !text.equalsIgnoreCase("ne") && !text.equalsIgnoreCase("ce") &&!text.equalsIgnoreCase("se"))
                {
                    question.setFont(new Font("Consolas", Font.PLAIN, 35));
                    input.setBounds(300, 670, 950, 100);
                    question.setText("Please enter either NW, CW, SW, C, NE, CE, or SE");
                }
                else
                {
                    r.setFloor(7);
                    r.setSide(text);
                    showEstimatedTime();
                }
            }
            input.setText("");
        }
    }

    public static void insertionSort(int[] elements)
    {
        for (int j = 1; j < elements.length; j++)
        {
            int temp = elements[j];
            int possibleIndex = j;
            while (possibleIndex > 0 && temp < elements[possibleIndex - 1])
            {
                elements[possibleIndex] = elements[possibleIndex - 1];
                possibleIndex--;
            }
            elements[possibleIndex] = temp;
        }
    }

    public void remDupes(ArrayList<Integer> numbs)
    {
        for(int i = 0; i < numbs.size() - 1; i++)
        {
            if(numbs.get(i).equals(numbs.get(i + 1)))
            {
                numbs.remove(i);
                i--;
            }
        }
    }

    public void setPeriods(ArrayList<Period> p)
    {
        periodList = p;
    }

    public void showPeriods(ArrayList<Period> p)
    {
        for(int i = 0; i < p.size(); i++)
        {
            JLabel l = new JLabel();
            Period p2 = p.get(i);
            l.setText(p2.toString());
            l.setFont(new Font("Consolas", Font.PLAIN, 30));
            l.setBounds(500, 50 + (i * 50), 1000, 100);
            frame.add(l);
            periodLabels.add(l);
        }
    }

    public void hidePeriods()
    {
        for(JLabel j : periodLabels)
        {
            frame.remove(j);
        }
        periodHeading.setVisible(false);
    }

    public void backToHome(String response, int qx, int qy)
    {
        hidePeriods();
        extraLine.setVisible(false);
        home.setVisible(true);
        question.setText(response);
        question.setFont(new Font("Consolas", Font.PLAIN, 35));
        question.setBounds(qx, qy, 1100, 100);
        input.setVisible(false);
        inputStage = -1;
    }

    public void moveToRoomCheck()
    {
        imageHolder.setVisible(true);
        hidePeriods();
        extraLine.setVisible(true);
        question.setText("What room are you coming from?");
        extraLine.setText("Enter in the following format with no spaces: FloorSideNumber");
        question.setFont(new Font("Consolas", Font.PLAIN, 30));
        extraLine.setFont(new Font("Consolas", Font.PLAIN, 27));
        question.setBounds(460, 560, 900, 100);
        extraLine.setBounds(275, 600, 950, 100);
        input.setBounds(275, 670, 950, 100);
        inputStage = 3;
    }

    public void reasonableTimeCheck()
    {
        String feedback = t.leaveTimeFeedback(periodList, t.getHour(), t.getMinute(), p.getPeriod());
        hidePeriods();
        if(feedback.length() < 30)
        {
            backToHome(feedback, 900 - feedback.length() * 15, 350);
        }
        else
        {
            backToHome(feedback, 1000 - feedback.length() * 14, 350);
        }
    }

    public void showEstimatedTime()
    {
        input.setVisible(false);
        imageHolder.setVisible(false);
        peopleInRoom.setVisible(true);
        feedback.setVisible(true);
        question.setBounds(20, 150, 1600, 100);
        question.setFont(new Font("Consolas", Font.PLAIN, 34));
        question.setText("It will take approximately " + r.timeToLibraryLine() / 60 + " minute(s) and " + r.timeToLibraryLine() % 60  + " second(s) to get to the library.");
    }
}