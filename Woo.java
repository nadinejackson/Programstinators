import java.util.ArrayList;
import cs1.Keyboard;
public class Woo{
    private static ArrayList<Character> livingChars = new ArrayList<Character>();
    private static ArrayList<Mafia> mafia = new ArrayList<Mafia>();
    private static ArrayList<Character> citizens = new ArrayList<Character>();
    private static Doctor Doc;
    private static Investigator Detective;
    private static ArrayList<Object> notebook;
    private static Character player;
    private static int type = 0;
    private static Character maybeDed;
    private static final String[] NAMES = {"Taylor", "Isa", "Miguel", "Maya", "Grace", "Michael", "Jerin", "Mahtab", "Tanvirul", "Matthew", "Sofian", "Briana", "Nysa", "Sydni", "Seth", "Sasha", "Justin", "Ishmael", "hydrogen", "helium", "lithium", "beryllium", "boron", "carbon", "nitrogen", "oxygen", "flourine", "neon", "Hasif", "Henry", "Soojin", "Brandon", "Raunak", "Shayan", "Peter", "Kevin", "Oliver", "Jude", "Mohtasim", "Ryan", "Alexia", "Bing", "Jackie", "Ricky", "Susan", "Victor", "Tim", "Bill", "Sean", "Angela", "Maxwell", "Kaitlin", "Alan", "Eric", "Lily", "Maggie", "Nora", "Topher Brown Mykolyk", "Connie", "Jake", "Cheryl", "Nadine", "Fabiha"};
    

    public static void begin(){
	System.out.println("Welcome to Town of Salem, Java Edition. Type 1 and press Enter to begin.");
	while (Keyboard.readInt() != 1){
	    System.out.println("Look, if you can’t follow simple instructions, this isn’t gonna work out. Try again.");
	}
	System.out.println("The rules will go here once we add things to the game. Press 2 if you understand.");
	while (Keyboard.readInt() != 2){
	    System.out.println("the rules from above");
	}
	
	while(type < 1 || type > 3){
	    System.out.println("Type the number of one of the three following types and press enter\n" + "1. Mafia\n2. Doctor\n3. Detective");
	    type = Keyboard.readInt();
	}
	System.out.println("Pick a name (type and press enter)");
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
	System.out.println("How many other people do you want in this game? (min 7, max 20)");
	countPlayers = Keyboard.readInt();
	while (countPlayers < 7 || countPlayers > 20)
	    {
		System.out.println("That doesn't sound like a good game. Let's choose a number BETWEEN 7 and 20 (inclusive)");
		countPlayers = Keyboard.readInt();
	    }
	popGame(countPlayers);
    }//end begin()

    public static String display(ArrayList thing){
	String retStr = "";
	for(int i = 0; i < thing.size(); i++)
	    {
		retStr += i + "- " + thing.get(i) + "\n";
	    }
	return retStr;
    }
    public static void popGame(int numPlayers){
	ArrayList<String> allNames = new ArrayList<String>();
	for(int i = 0; i < NAMES.length; i++)
	    {
		allNames.add(NAMES[i]);
	    }
	String name;
	int r = (int) (Math.random() * 10);
	for (int i = 0; i < (numPlayers - (numPlayers / 6) - 2); i++){
	    name = allNames.remove((r + i*i) % allNames.size()); 
	    Character bob = new Character(name);
	    livingChars.add(bob);
	    //citizens.add(bob);
	}
	if (type != 1)
	    {
		for (int i = 0; i < (numPlayers / 6); i++){
		    Mafia bob = new Mafia(allNames.remove(r + 1));
		    mafia.add(bob);
		    livingChars.add(bob);
		}
	    }
	else
	    {
		for (int i = 0; i < (numPlayers / 6) - 1; i++){
		    Mafia bob = new Mafia(allNames.remove(r + 1));
		    mafia.add(bob);
		    livingChars.add(bob);
		}
	    }
	if (type != 2)
	    {
		Doctor bob = new Doctor(allNames.remove(r + 5));
		Doc = bob;
		livingChars.add(bob);
		//citizens.add(bob);
	    }
	if (type != 3)
	    {
		Investigator bob = new Investigator(allNames.remove(r + 7));
		Detective = bob;
		livingChars.add(bob);
		//citizens.add(bob);
	    }
	swap();
	for (int i = 0; i < livingChars.size(); i++)
	    {
		livingChars.get(i).changeSusPts((int) (Math.random() * 20));
		if(!(livingChars.get(i).getType().equals("Mafia")))
		    citizens.add(livingChars.get(i));
	    }
	System.out.println("Here are the players you'll be playing against:\n" + display(livingChars));
    }//end popGame()

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
	int accuse = 2;
	for (int i = 0; i < livingChars.size(); i++)
	    livingChars.get(i).resetAcc();
	if (maybeDed.isAlive())
	    System.out.println("It's daytime and no one died, not even " + maybeDed + "\n");
	else
	    System.out.println("It's daytime, and " + maybeDed + " died. \nThey were " + maybeDed.getType() + "\n");
	converse();
	if (player.isAlive()){
	    System.out.println("Do you have an accusation? (0 for no, 1 for yes)");
	    if (Keyboard.readInt() == 1)
		{
		    System.out.println("Whom? (Type therey're number)\n" + display(livingChars));
		    livingChars.get(Keyboard.readInt()).incAccusations();
		}
	    if (citizens.size() > mafia.size()){
		Character[] topTwee = new Character[3];
		for(int i = 0; i < 3; i++)
		    {
			topTwee[i] = livingChars.get(i);
		    }
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
		int rep = 0;
		for(int i = 0; i < livingChars.size(); i++)
		    {
			if(livingChars.get(rep).getAccusations() < livingChars.get(i).getAccusations())
			    {
				rep = i;
			    }
		    }
		livingChars.get(rep).changeSusPts(5);
	    
	    
		for (int i = 0; i < mafia.size(); i++)
		    {
			mafia.get(i).changeSusPts((int) (Math.random() * 5));
		    }
	    System.out.println("Do you think that " + livingChars.get(rep) + " should die for their actions? (0 or 1)");
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
	    if (votes > livingChars.size() / 2)
		{
		    System.out.println("Oh hey look we killed a person and their name is " + livingChars.get(rep));
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
		    System.out.println("We didn't kill anybody this time :(");
		}
	}
    }//end day()

    public static void night(){
	int killIndex;
	int saveIndex;
	int susNum;
	Character susOne;
	if (player.getType().equals("Doctor"))
	    {
		saveIndex = -2;
		while (saveIndex < -1 || saveIndex >= livingChars.size()){
		System.out.println("Type the number of who you want to save, in the following list:");
		System.out.println(display(livingChars));
		saveIndex = Keyboard.readInt();
		}
	    }
	else{
	    saveIndex =(int) (Math.random() * livingChars.size());
	}
	if (player.getType().equals("Investigator")){
	    {
		susNum = -2;
		while (susNum < -1 || susNum >= livingChars.size() - 1){
		    System.out.println("Type the number of who you want to investigate, in the following list:");
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
	if (player.getType().equals("Mafia"))
	    {
		System.out.println("You and the other mafia:\n" + display(mafia) + "\nare preparing to kill.");
		killIndex = -1;
		while (killIndex < 0 || killIndex >= citizens.size()){
		    System.out.println("Type the number of who you want to kill in the following list:");
		    System.out.println(display(citizens));
		    if (Math.random() < 0.5){
			killIndex = Keyboard.readInt();
			System.out.println("Fine, you get your way.");
		    }
		    else{
			killIndex = (int) (Math.random() * citizens.size());
			int x = Keyboard.readInt();
			System.out.println("No one cares what you think, we killed " + citizens.get(killIndex) + ".");
		    }
		
		}
	    }
	else{
	    killIndex = (int) (Math.random() * citizens.size());
	}
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

    public static void converse()
    {
	System.out.println("Would you like to speak to a townsperson? (0 for no, 1 for yes)");
	if (Keyboard.readInt() == 0)
	    return;
	int person2 = 0;
	System.out.println("Which one?: \n" + display(livingChars));
	while (p2 >= livingChars.size() || p2 < 1)
	Character p2 = livingChars.get(person2);
	for (int i = 0; i < 3 && i < livingChars.size() - 1; i++){
	    System.out.println(Conversation.getInitQ());
	    System.out.println(Conversation.getInitA(Keyboard.readInt() - 1));
	    System.out.println("Would you like to ask another question? (0 for no, 1 for yes)");
	    if (Keyboard.readInt() == 0)
		break;
	}
    }
    public static void end(){

    }//end end()

    public static void main(String[] args){
	begin();
	
	while (mafia.size() < citizens.size() &&
	       player.isAlive() && mafia.size() > 0){	    
	    night();
	    day();
	    }
	
	if (!(player.isAlive() ||
	      ( !(player.getType().equals("Mafia")) && mafia.size() >= citizens.size()))) {
	    System.out.println("You lose. :(\n");	    
	}
	else {
	    System.out.println("You win!!!\n");
	}
	System.out.println("Here is who all the characters were:");
	System.out.println(display(livingChars));

	
    }//end main()
}//end Woo
