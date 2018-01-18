public class Conversation {
    private static String[][] init = {{"Crazy weather today, huh?","I don't really care; leave me alone.", "Yes, it feels very ominous. God's justice will prevail.", "Not good weather for being out and about at this time."}, {"How are you?", "I feel refreshed today, because I used Colgate(TM). Thanks for asking.", "Terrible, I can't believe what happened last night.", "I'm getting by...can't trust anyone anymore nowadays."}, {"What's the deal with these mafia people?", "Huh? What do you mean?", "Perhaps they're simply bringing to us the fate we must all face.", "Dunno, I'm just glad it wasn't me they chose."} };

    private static String[][] followUp = {{"Are you feeling okay after what happened?", "No, I want to kill...myself.", "A little under the weather, but I'll be okay. We have to stick together.", "No, it was awful - I don't want to stay in this town anymore."}, {"Why do you think someone would do something like this?","It's our current political climate, polarization tends to make people do things like this.","Why not?", "I wish I knew, but only God knows the truth."}, {"I thought I saw you across the street last night, but I'm not sure. Do you know who was out so late?", "I mean, why were YOU out so late?", "It was me, I was just dropping off some extra casserole at the mailman's.", "No idea, keep an eye out for them next time."} };
    
    private static int qNum = 0;

    public static String getAnyInit( int a, int b)
    {
	return init[a][b];
    }

    public static String getAnyFollow( int b)
    {
	return followUp[qNum][b];
    }
    
    
    public static String getInitQ()
    {
	return "What would you like to ask them?\n\n\t1: " + init[0][0] + "\n\n\t2: " + init[1][0] + "\n\n\t3: " + init[2][0];
    }

    public static String getInitA(int q)
    {
	if (Math.random() < 0.33)
	    {
		return init[q][1];
	    }
	else if (Math.random() < 0.66)
	    {
		return init[q][2];
	    }
	else
	    {
		return init[q][3];
	    } }
    public static String getFollowQ()
    {
	String q = "";
	if (Math.random() < 0.33)
	    {
		q = followUp[0][0];
		qNum = 0;
	    }
	else if (Math.random() < 0.66)
	    {
		q = followUp[1][0];
		qNum = 1;
	    }
	else {
	    q = followUp[2][0];
	    qNum = 2;
	}
	return q;
    }
    public static String getFollowA()
    {
	return "\n\t1: " + followUp[qNum][1] + "\n\n\t2: " +
        followUp[qNum][2] + "\n\n\t3: " +
	followUp[qNum][3] + "\n";
    }
    public static void main(String[] args)
    {
    }
}//end class Conversation
