public class Character{
    protected String name;
    protected boolean alive;
    protected String type;
    protected int accusations;
    public Character(String givenName){
	type = "Citizen";
	alive = true;
	name = givenName;
    }
    public String setName(String newName){
	String oldName = name;
	name = newName;
	return oldName;
    }
    public String getType(){
	return type;
    }
    public String getName(){
	return name;
    }
    public String toString()
    {
	return name + " -- " + type;
    }
    public boolean isAlive(){
	return alive;
    }
    public void die(){
	alive = false;
    }
    public Character accuse(Character person)
    {
	person.incAccusations();
	return person;
    }
    public boolean vote(boolean yes)
    {
	return yes;
    }
    public int getAccusations()
    {
	return accusations;
    }
    public void incAccusations(){
	accusations += 1;
    }
    public void resetAcc(){
	accusations = 0;
    }
}
