public class Story
{
    private static String[] action = {"walking down the street", "sharpening their knives", "relaxing", "sipping some tea in Starbucks(TM)", "reading the APCS Barron's review book", "watching Make Happy by Bo Burnham", "pair programming alone", "16 years old and coloring in an adult coloring book", "folding their laundry", "eating a bagel"};
    private static String[] interruption = {"accidentally stuck their tongue in a plug", "got hit with a pipe of exhaust", "were forced by the Mafia to make toast in the tub", "got nailed to a cross", "were deprived of breath until it was gone", "set fire to their hair", "poked a stick at a grizzly bear", "ate medicine that was out of date", "fell off of a cliff", "were hit by intense existential pain"};
    private static String[] luck = {" went through years of therapy"," utilized their keys to success"," just used a little elbow grease"," watched a motivational speaker, got inspired"," looked at the problem differently"," were brought a freshly sharpened #2 pencil by the love of their life"," got 8 hours of sleep, drank 8 glasses of water"," drank from the fountain of youth"," took a stroll through Central Park"," took a deep breath through their inhaler"};
    public static String deathStory(Character they)
    {
	return they + " were " + action[(int) (Math.random() * 10)] + " when, suddenly, " + they + interruption[(int) (Math.random() * 10)] + " and died.";
    }
    public static String survivalStory(Character they)
    {
	return they + " were " + action[(int) (Math.random() * 10)] + " when, suddenly, " + they + interruption[(int) (Math.random() * 10)] + " but then, " + they + luck[(int) (Math.random() * 10)] + " and survived.";
    }
    public static void main (String[] args)
    {
	Character me = new Character("Nadine");
	System.out.println(deathStory(me));
	System.out.println(survivalStory(me));
    }
}
