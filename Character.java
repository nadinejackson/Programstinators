public class Character{
    private String name;
    private int num;
    public Character(String givenName, int number){
	name = givenName;
	num = number;
    }
    public String setName(String newName){
	String oldName = name;
	name = newName;
	return oldName;
    }
    public String getName(){
	return name;
    }
}
