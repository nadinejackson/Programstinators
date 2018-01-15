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
	    
	

	
	livingChars.add(player);
	//System.out.println("How many other people do you want in this game?");
	//popGame(Keyboard.readInt());
	popGame(7);
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
	String name;
	int r = (int) (Math.random() * 10);
	for (int i = 0; i < numPlayers - 2; i++){
	    name = ALLNAMES[r + i*i];
	    Character bob = new Character(name);
	    livingChars.add(bob);
	    citizens.add(bob);
	}
	if (type != 1)
	    {
		Mafia bob = new Mafia(ALLNAMES[r - 1]);
		mafia.add(bob);
		livingChars.add(bob);
	    }
	if (type != 2)
	    {
		Doctor bob = new Doctor(ALLNAMES[r + 5]);
		Doc = bob;
		livingChars.add(bob);
		citizens.add(bob);
	    }
	if (type != 3)
	    {
		Investigator bob = new Investigator(ALLNAMES[r + 7]);
		Detective = bob;
		livingChars.add(bob);
		citizens.add(bob);
	    }
	System.out.println("Here are the players you'll be playing against:\n" + display(livingChars));
    }//end popGame()

    public static void day(){
	if (maybeDed.isAlive())
	    System.out.println("It's daytime and no one died, not even " + maybeDed + "\n");
	else
	    System.out.println("It's daytime, and " + maybeDed + " died. \nThey were " + maybeDed.getType() + "\n");

	
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
		killIndex = -1;
		while (killIndex < 0 || killIndex >= citizens.size()){
		System.out.println("Type the number of who you want to kill, in the following list:");
		System.out.println(display(citizens));
		killIndex = Keyboard.readInt();
		}
	    }
	else{
	    
	    killIndex = (int) (Math.random() * citizens.size());
	}
	if (!(livingChars.get(saveIndex).equals(citizens.get(killIndex)))){
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
