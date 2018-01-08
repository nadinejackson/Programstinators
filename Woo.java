import java.util.ArrayList;
import cs1.Keyboard;
public class Woo{
private ArrayList livingChars;
private ArrayList notebook;
private Character player;
private final String[] ALLNAMES = {“Cheryl”, “Nadine”, “Fabiha”, “Taylor”, “Isa”, “Miguel”, “Maya”, “Grace”, “Michael”, “Jerin”, “Mahtab”, “Tanvirul”, “Matthew”, “Sofian”, “Briana”, “Nysa”, “Sydni”, “Seth”, “Sasha”, “Justin”, “Ishmael”, “hydrogen”, “helium”, “lithium”, “beryllium”, “boron”, “carbon”, “nitrogen”, “oxygen”, “flourine”, “neon”};
private int mafiaLoc = 0;

public static void begin(){
System.out.println(“Welcome to Town of Salem, Java Edition. Type 1 and press Enter to begin.”);
while (Keyboard.readInt() != 1){
System.out.println(“Look, if you can’t follow simple instructions, this isn’t gonna work out. Try again.”);
}
System.out.println(“The rules will go here once we add things to the game. Press 2 if you understand.”);
while (Keyboard.readInt() != 2){
System.out.println(“the rules from above”);
}
player = new Character(“jim”, 15);
System.out.println(“Pick a name (type and press enter)”);
player.setName(Keyboard.readString());
System.out.println(“How many other people do you want in this game?”);
popGame(Keyboard.readInt());
}//end begin()
public void popGame(int numPlayers){
String name;
for (int i = 0; i < numPlayers; i++){
name = ALLNAMES[i];
Character bob = new Character(name, i);
livingChars.add(bob);
}
livingChars.add(player);
}//end popGame()

public void day(){

}//end day()

public void night(){

}//end night()

public void end(){

}//end end()

public static void main(String[] args){
begin();
System.out.println(“We’re done for now.”);
}//end main()
}//end Woo
