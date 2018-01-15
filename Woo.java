import java.util.ArrayList;
import cs1.Keyboard;
public class Woo{
    private static ArrayList<Character> livingChars = new ArrayList<Character>();
    private static ArrayList<Mafia> mafia = new ArrayList<Mafia>();
    private static Doctor Doc;
    private static Investigator Detective;
    private static ArrayList<Object> notebook;
    private static Character player;
    private static int type = 0;
    private static final String[] ALLNAMES = {"Cheryl", "Nadine", "Fabiha", "Taylor", "Isa", "Miguel", "Maya", "Grace", "Michael", "Jerin", "Mahtab", "Tanvirul", "Matthew", "Sofian", "Briana", "Nysa", "Sydni", "Seth", "Sasha", "Justin", "Ishmael", "hydrogen", "helium", "lithium", "beryllium", "boron", "carbon", "nitrogen", "oxygen", "flourine", "neon"};
    

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
	    System.out.println("Type the number of one of the three following types and press enter\n" + "1. Mafia\n2.Doctor\n3.Detective");
	    type = Keyboard.readInt();
	}
	System.out.println("Pick a name (type and press enter)");
	if (type == 1){
	    Character player = new Mafia(Keyboard.readString());
	    mafia.add((Mafia)player);
	}
	else if (type == 2){
	    Character player = new Doctor(Keyboard.readString());
	    Doc = (Doctor)player; //alias
	}
	else{
	    Character player = new Investigator(Keyboard.readString());
	    Detective = (Investigator)player;
	}
	    
	

	
	livingChars.add(player);
	//System.out.println("How many other people do you want in this game?");
	//popGame(Keyboard.readInt());
	popGame(7);
    }//end begin()

    public static String display(){
	String retStr = "";
	for(int i = 0; i < livingChars.size(); i++)
	    {
		retStr += (i + 1) + "- " + livingChars.get(i) + "\n";
	    }
	return retStr;
    }
    public static void popGame(int numPlayers){
	String name;
	for (int i = 0; i < numPlayers - 2; i++){
	    name = ALLNAMES[i];
	    Character bob = new Character(name);
	    livingChars.add(bob);
	}
	if (type != 1)
	    {
		Mafia bob = new Mafia(ALLNAMES[livingChars.size() + 1]);
		mafia.add(bob);
	    }
	if (type != 2)
	    {
		Doctor bob = new Doctor(ALLNAMES[livingChars.size() + 5]);
		Doc = bob;
	    }
	if (type != 3)
	    {
		Investigator bob = new Investigator(ALLNAMES[livingChars.size() + 7]);
		Detective = bob;
	    }
	System.out.println("Here are the players you'll be playing against:\n" + display());
    }//end popGame()

    public static void day(){
	
    }//end day()

    public static void night(){
	int killIndex;
	int saveIndex;
	int susNum;
	Character susOne;
	if (player.getType().equals("Doctor"))
	    {
		saveIndex = -1;
		while (saveIndex <= 0 || saveIndex >= livingChars.size()){
		System.out.println("Type the number of who you want to save, in the following list:");
		System.out.println(display());
		saveIndex = Keyboard.readInt() - 1;
		}
	    }
	else{
	    saveIndex =(int) Math.random() * livingChars.size();
	}
	if (player.getType().equals("Investigator")){
	    {
		susNum = -1;
		while (susNum <= 0 || susNum >= livingChars.size()){
		System.out.println("Type the number of who you want to investigate, in the following list:");
		System.out.println(display());
		susNum = Keyboard.readInt() - 1;
		}
		susOne = livingChars.get(susNum);
		if (susOne.getType().equals("Mafia")){
		    System.out.println("Don't trust " + susOne + " because they're the Mafia");
		}
		else{
		    System.out.println("Nah, " + susOne + " is pretty innocent.");
		}
	    }
	}
	if (player.getType().equals("Mafia"))
	    {
		killIndex = -1;
		while (killIndex <= 0 || killIndex >= livingChars.size()){
		System.out.println("Type the number of who you want to kill, in the following list:");
		System.out.println(display());
		killIndex = Keyboard.readInt() - 1;
		}
	    }
	else{

	    killIndex = (int) Math.random() * livingChars.size();
	}
	if (saveIndex != killIndex){
	    ((Mafia)player).kill(livingChars.get(killIndex));
	}
    }//end night()

    public static void end(){

    }//end end()

    public static void main(String[] args){
	begin();
	System.out.println("We’re done for now.");
	while (mafia.size() > 0 &&
	       player.isAlive())
	    {
	    night();
	    day();
	    }
    }//end main()
}//end Woo
