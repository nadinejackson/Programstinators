import java.util.ArrayList;
import cs1.Keyboard;
public class Woo{
    private static ArrayList<Character> livingChars = new ArrayList<Character>();//contains a list of all the current living characters 
    private static ArrayList<Mafia> mafia = new ArrayList<Mafia>();//contains a list of all the current alive mafia characters
    private static ArrayList<Character> citizens = new ArrayList<Character>();//contains a list of all the current alive non-mafia characters
    private static Doctor Doc;//the doctor
    private static Investigator Detective;// the investigator
    private static Character player;// the player
    private static int type = 0;//type = 1 : mafia; type = 2 : Doctor; type = 3 : Investigator
    private static Character maybeDed;//the person who may or may not have died during the night come morning 
    private static final String[] NAMES = {"Taylor", "Isa", "Miguel", "Maya", "Grace", "Michael", "Jerin", "Mahtab", "Tanvirul", "Matthew", "Sofian", "Briana", "Nysa", "Sydni", "Seth", "Sasha", "Justin", "Ishmael", "Hasif", "Henry", "Soojin", "Brandon", "Raunak", "Shayan", "Peter", "Kevin", "Oliver", "Jude", "Mohtasim", "Ryan", "Alexia", "Bing", "Jackie", "Ricky", "Susan", "Victor", "Tim", "Bill", "Sean", "Angela", "Maxwell", "Kaitlin", "Alan", "Eric", "Lily", "Maggie", "Nora", "Topher Brown Mykolyk", "Connie", "Jake", "Anne", "Maxwell", "John"};//all possible character names
    

    public static void begin(){
	System.out.println("Welcome to Town of Salem, Java Edition. Here is a brief summary of the rules.\n"
			   + " ======================================================================= " 
			   + "\n\tBegin the game by selecting a role, and the number of townspeople\n you'd like in your game. When night falls, the Mafia kills off one player.\n The Doctor can select a player to heal, and if healed, the killed player\n survives the night. \n\n\tRoles of all players are revealed upon death. When the night ends\n and day comes, the killed player's role is revealed. You can talk to townspeople\n to see what they have to say, and they may have questions for you as well.  \n\n\tAt the end of the day, you and the townspeople cast their votes for\n who seems the most suspicious, and kill that person. Your\n goal is to survive, and kill off everyone who isn't on your side. There\n will be prompts to guide you along your way - good luck!"
			   + "\n======================================================================= "
			   + "\nTYPE 1 AND PRESS ENTER TO BEGIN "
			   );

	while (Keyboard.readInt() != 1){
	    System.out.println("Look, if you can't follow simple instructions, we're gonna have problems.");
	}
	
	while(type < 1 || type > 3){
	    System.out.println("Select your role:" + "\n\t1. Mafia -- Attempt to kill off the townspeople without revealing your true identity."
			       + "\n\t2. Doctor -- Prevent evil from prevailing, and save the citizens before they're killed."
			       + "\n\t3. Detective -- Use your superior investigative skills to figure out who the Mafia are, and convince the citizens.");
	    type = Keyboard.readInt();
	}
	System.out.println("\nWhat's your name? (type and press enter)");
	if (type == 1){
	    player = new Mafia(Keyboard.readString());
	    mafia.add((Mafia)player);
	}
	else if (type == 2){
	    player = new Doctor(Keyboard.readString());
	    citizens.add(player);
	    Doc = (Doctor)player; //alias
	}
	else{
	    player = new Investigator(Keyboard.readString());
	    citizens.add(player);
	    Detective = (Investigator)player;
	}
	
	int countPlayers = 0;
	livingChars.add(player);
	System.out.println("\nHow many people will be in your town? (Range: 12 - 20)");
	countPlayers = Keyboard.readInt();
	while (countPlayers < 12 || countPlayers > 20)
	    {
		System.out.println("\nThat doesn't sound like a good game. Let's choose a number BETWEEN 12 and 20 (inclusive)");
		countPlayers = Keyboard.readInt();
	    }
	popGame(countPlayers);//populates the game with countPlayer other players
    }//end begin()

    //prints out a numbered list of all the elements in ArrayList thing
    public static String display(ArrayList thing){
	String retStr = "";
	for(int i = 0; i < thing.size(); i++)
	    {
		retStr += i + "- " + thing.get(i) + "\n";
	    }
	return retStr;
    }

    //initializes numPlayers number of characters
    public static void popGame(int numPlayers){
	//creates an ArrayList called allNames so that names can be removed to avoid
	//multiple characters having the same name
	ArrayList<String> allNames = new ArrayList<String>();
	for(int i = 0; i < NAMES.length; i++)
	    {
		allNames.add(NAMES[i]);
	    }

	//sets the name of the character to a random element in allNames
	String name;
	int r = (int) (Math.random() * 10);//random integer [0,9]

	//creates the charactes who don't have any special traits
	for (int i = 0; i < (numPlayers - (numPlayers / 6) - 2); i++){
	    name = allNames.remove((r + i*i) % allNames.size());//prevents IndexOutOfBoundsException
	    Character bob = new Character(name);
	    livingChars.add(bob);
	    //citizens.add(bob);
	}
	
	if (type != 1)//if the player is not a mafia
	    {
		//add numPlayers/6 mafia to the game
		for (int i = 0; i < (numPlayers / 6); i++){
		    Mafia bob = new Mafia(allNames.remove(r + 1));
		    mafia.add(bob);
		    livingChars.add(bob);
		}
	    }
	else //if player is a mafia
	    {
		//add numPlayers/6 - 1 mafia to the game
		for (int i = 0; i < (numPlayers / 6) - 1; i++){
		    Mafia bob = new Mafia(allNames.remove(r + 1));
		    mafia.add(bob);
		    livingChars.add(bob);
		}
	    }

	if (type != 2)//if the player is not a doctor
	    {
		//create a doctor
		Doctor bob = new Doctor(allNames.remove(r + 5));
		Doc = bob;
		livingChars.add(bob);
		//citizens.add(bob);
	    }
	if (type != 3)//if the player is not an investigator
	    {
		//create an investigator
		Investigator bob = new Investigator(allNames.remove(r + 7));
		Detective = bob;
		livingChars.add(bob);
		//citizens.add(bob);
	    }
	swap();//swaps all the characters by a random amount

	for (int i = 0; i < livingChars.size(); i++)
	    {
		//sets all characters with random starting susVals
		livingChars.get(i).changeSusPts((int) (Math.random() * 20));

		//if the character isn't a Mafia then they are added to the citizens category
		if(!(livingChars.get(i).getType().equals("Mafia")))
		    citizens.add(livingChars.get(i));
	    }
	System.out.println("\nHere are the players you'll be playing against: (Your number is 0) \n" + "\n" + display(livingChars));
    }//end popGame()

    //swaps every single item randomly
    public static void swap()
    {
	Character holder = livingChars.get(0);
	for(int i = 1; i < livingChars.size(); i++)
	    {
		int place = 0;
		while (place == 0)
		    {
			place = (int) (Math.random() * (livingChars.size() - 1));
		    }
		//holder = livingChars.get(place);
		//livingChars.remove(place);
		//livingChars.add(place, livingChars.remove(i));
		livingChars.set(i, livingChars.set(place, livingChars.get(i)));
	    }
    }

    public static void day(){
	System.out.println("The following day...\n"); 
	int accuse = 2;
	for (int i = 0; i < livingChars.size(); i++)
	    livingChars.get(i).resetAcc();

	
	if (maybeDed.isAlive())//if the person attacked didn't die
	    System.out.println(Story.survivalStory(maybeDed));
	else //if the person attacked did die
	    System.out.println(Story.deathStory(maybeDed)+ "\n" + maybeDed + " was a " + maybeDed.getType() + ".\n");


	
	if (player.isAlive()){
	    converse();//allows the player to talk to one person of their choosing
	    System.out.println("Do you have an accusation? (1 for yes, any other number for no)");

	    if (Keyboard.readInt() == 1)//if the player wants to, accuses someone
		{
		    System.out.println("\nWhom? (Type their number)\n" + display(livingChars));
		    int tempAccus = Keyboard.readInt();
		    while (tempAccus < 0 || tempAccus >= livingChars.size())
			{
			    System.out.println("Please enter a valid person.");
			    tempAccus = Keyboard.readInt();
			}
		    livingChars.get(tempAccus).incAccusations();

		}

	    //if the mafia hasn't overrun the town
	    if (citizens.size() > mafia.size()){

		//will contain the 3 characters who have the hightest susPoint 
		Character[] topTwee = new Character[3];

		//starts off with the first 3 living characters
		for(int i = 0; i < 3; i++)
		    {
			topTwee[i] = livingChars.get(i);
		    }

		//looks through for every character and if they have a higher susPoint
		//then they replace them on the topTwee list
		for(int i = 3; i < livingChars.size(); i++)
		    {
			for (int x = 0; i < 3; i++)
			    {
				if (livingChars.get(i).getSusPoints() > topTwee[x].getSusPoints()){
				    topTwee[x] = livingChars.get(i);
				    break;
				}
			    }
		    }
		//every npc accuses one from the top 3
		for(int i = 1; i < livingChars.size(); i++)
		    {
			int accusation = i;
			while (accusation == i)
			    {
				accusation = (int) (Math.random() * 3);
			    }
			livingChars.get(i).accuse(topTwee[accusation]);
		    }
	    }
	    
	    int rep = 0; //this is the person who will die (if they get voted out)
	    //find max accusations
	    for(int i = 0; i < livingChars.size(); i++)
		{
		    if(livingChars.get(rep).getAccusations() < livingChars.get(i).getAccusations())
			{
			    rep = i;
			}
		}
	    livingChars.get(rep).changeSusPts(5); //rep becomes more suspicious
	    

	    //mafia seems more suspicious
	    for (int i = 0; i < mafia.size(); i++)
		{
		    mafia.get(i).changeSusPts((int) (Math.random() * 3));
		}
	    
	    //allow the user a say in whether the person dies
	    System.out.println("After a heated discussion, the townspeople decided that " + livingChars.get(rep) + " is too suspicious to live.\n" + 
			       "Do you think that " + livingChars.get(rep) + " should die for their actions? (1 for yes, any other number for no)");
	    int votes = 0;
	    if(Keyboard.readInt() == 1)
		{
		    votes += 1;
		}
	    for(int i = 1; i < livingChars.size(); i++)
		{
		    if(Math.random() >= 0.5 && i != rep)
			{
			    votes += 1;
			}
		}
	    if (votes > livingChars.size() / 2) //if they get voted out, they die
		{
		    System.out.println(livingChars.get(rep) + " was deemed too Suspicious by the townspeople, and was burned at the stake.\n");
		    livingChars.get(rep).die();
		    if (livingChars.get(rep).getType().equals("Mafia"))
			{
			    for (int x = 0; x < mafia.size(); x++)
				{
				    if (mafia.get(x).equals(livingChars.get(rep)))
					{
					    mafia.remove(x);
					}
				}
			}
		    else 
			{
			    for (int x = 0; x < citizens.size(); x++)
				{
				    if (citizens.get(x).equals(livingChars.get(rep)))
					{
					    citizens.remove(x);
					}
				}
			}
		    livingChars.remove(rep);
		}
	    else
		{
		    System.out.println("\nThe townspeople were too busy arguing about their votes, and forgot to burn someone at the stake.\n");
		}
	}
    }//end day()

    public static void night(){ //when the mafia kills, doctor saves, investigator investigates
	int killIndex; //person who will be killed
	int saveIndex; //person who will be saved
	int susNum; //index of person who will be investigated
	Character susOne; //the character of above
	System.out.println("Night falls, and an eerie darkness shrouds the town...\n");
	if (player.getType().equals("Doctor")) //doctor chooses who to save
	    {
		saveIndex = -2;
		while (saveIndex < -1 || saveIndex >= livingChars.size()){
		    System.out.println("\nNow that your day is over, you set out to save one of your many patients.\n Today you choose to save: (type their number and press enter)\n");
		    System.out.println(display(livingChars));
		    saveIndex = Keyboard.readInt();
		}
	    }
	else{ //npcs choose person to save randomly
	    saveIndex =(int) (Math.random() * livingChars.size());
	}
	if (player.getType().equals("Investigator")){
	    {
		susNum = -2;
		while (susNum < 0 || susNum >= livingChars.size() - 1){
		    System.out.println("\nUnder the cover of darkness, you prepare to uncover the truth about: (type their number and press enter)\n");
		    System.out.println(display(livingChars));
		    susNum = Keyboard.readInt() - 1;
		}
		
		susOne = livingChars.get(susNum + 1);
		
		if (susOne.getType().equals("Mafia")){
		    
		    System.out.println("Don't trust " + susOne + " because they're the Mafia.\n");
		}
		else if (susOne.equals(player)) {
		    System.out.println("Picking yourself? Are you sure you're fit to be a detective?\n");
		}
		else{
		    System.out.println("Nah, " + susOne + " is pretty innocent.\n");
		}
	    }
	}
	//mafia chooses who to kill (by voting with other mafia)
	if (player.getType().equals("Mafia"))
	    {
		System.out.println("\nThe time has come at last. You meet up with your fellow mafia:\n" + display(mafia) + "\nYou are preparing to kill, but your fellow mafia might have a different idea.\n");
		killIndex = -1;
		while (killIndex < 0 || killIndex >= citizens.size()){
		    System.out.println("Who do you want to kill in the following list? (type their number and press enter): \n");
		    System.out.println(display(citizens));
		    if (Math.random() < 0.5){ //we like to give people a choose, but not too much
			killIndex = Keyboard.readInt();
			System.out.println("Fine, you get your way this time. Next time we'll choose.\n");
		    }
		    else{
			killIndex = (int) (Math.random() * citizens.size());
			int x = Keyboard.readInt();
			System.out.println("\nNo one cares what you think, we killed " + citizens.get(killIndex) + " instead.\n");
		    }
		
		}
	    }
	else{ //if the player is not a Mafia, it is completely random who dies
	    killIndex = (int) (Math.random() * citizens.size());
	}
	//if no one is saved, kill the person the mafia picked. Otherwise, no one dies
	if (!(livingChars.get(saveIndex).equals(citizens.get(killIndex))) || ! Doc.isAlive()){
	    citizens.get(killIndex).die();
	    maybeDed = citizens.remove(killIndex);

	    for (int x = 0; x < livingChars.size(); x++) {
		if (livingChars.get(x).equals(maybeDed)) {
		    livingChars.remove(x);
		}
	    }
	}
	else {
	    maybeDed = citizens.get(killIndex);
	}
	
    }//end night()

    public static void converse() //allows the player to get info
    {
	System.out.println("Would you like to speak to a townsperson? (0 for no, any other number for yes)\n");
	if (Keyboard.readInt() == 0)
	    return;
	int person2 = 0; //pick a person
	System.out.println("Which one?: \n\n" + display(livingChars));
	person2 = Keyboard.readInt();
	while (person2 >= livingChars.size() || person2 < 1)
	    {
		System.out.println("Pick someone in the game who's not you and is still a valid character.\n" + display(livingChars));
		person2 = Keyboard.readInt();
	    }
	Character p2 = livingChars.get(person2);
	for (int i = 0; i < 3 && i < livingChars.size() - 1; i++){ //talk a little
	    System.out.println(Conversation.getInitQ());
	    int x = Keyboard.readInt();
	    while (x < 1  || x > 3)
		{  
		    System.out.println("The options only go from 1 to 3.");
		    x = Keyboard.readInt();
		}


	    System.out.println("\nYou ask: " + Conversation.getAnyInit(x  - 1, 0));
	    System.out.println(p2 + " says: " + Conversation.getInitA(x - 1) + "\n");
	    System.out.println(p2 + " has a question for you as well.\n");
	    System.out.println(p2 + " asks: " + Conversation.getFollowQ());
	    System.out.println("What would you like to say?\n" + Conversation.getFollowA());
	    int y = Keyboard.readInt();
	    while (y < 1  || y > 3)
		{  
		    System.out.println("The options only go from 1 to 3.");
		    y = Keyboard.readInt();
		}

	    System.out.println("\nYou say: " + Conversation.getAnyFollow(y) + "\n");
	    break;
	}
    }

public static void end()
{ //someone's dead
    if (!player.isAlive())
	{
	    System.out.println("You've died too early. Now you'll never know what will happen.");
	}
    else if (mafia.size() >= citizens.size())
	{
	    System.out.print("The mafia have won. There is chaos in the street and blood at every doorstep.");
	    if (player.isAlive() && player.getType().equals("Mafia"))
		System.out.println("How beautiful. Congratulations.");
	    else{
		System.out.println("How dare you let them get away with this.");
	    }
	}
    else {
	System.out.println("There is peace in the land as the mafia has finally fallen.");
	if (player.getType().equals("Mafia"))
	    System.out.println("Horrid.\n");
	else{
	    System.out.println("Love fills the air.");
	}
    }
}//end end()

public static void main(String[] args){
    begin();
	
    while (mafia.size() < citizens.size() &&
	   player.isAlive() && mafia.size() > 0){	    
	night();
	day();
    }
    end();
	

	
}//end main()
}//end Woo

