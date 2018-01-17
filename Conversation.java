public class Conversation
{
    private static String[][] init = {{"Crazy weather today, huh?","I don't really care; leave me alone. :(", "Yeah, it feels very ominous."},
				      {"How are you?", "I feel refreshed today, because I used Colgate(TM)", "Terrible, I can't believe what happened."}};

    
    private static String[][] followUp = {{"Are you feeling okay after what happened?","no, I want to kill...myself", "Never felt better"},
					  {"Why do you think someone would do something like this?","It's our current political climate","It must be the hay fever"}};

    public static String getInitQ()
    {
	return "Do you want to ask\n1: " + init[0][0] + "\nor\n2: " + init[1][0];
    }
    public static String getInitA(int q)
    {
	if (Math.random() < 0.5)
	    {
		return init[q][1];
	    }
	else
	    {
		return init[q][2];
	    }
    }
    public static void main(String[] args)
    {
    }
}//end class Conversation
